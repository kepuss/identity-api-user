package org.wso2.carbon.identity.rest.api.user.ekyc.v1.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.api.user.common.error.APIError;
import org.wso2.carbon.identity.api.user.common.error.ErrorResponse;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.util.UserEKYCServiceHolder;
import org.wso2.carbon.identity.user.ekyc.dto.EKYCSessionDTO;
import org.wso2.carbon.identity.user.ekyc.dto.EKYCVerifiedCredentialDTO;
import org.wso2.carbon.user.api.UserStoreException;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;


/**
 * API service implementation of a specific user's ekyc operations
 */
public class UserEKYCServiceImpl implements UserEKYCService {

    private static final Log log = LogFactory.getLog(UserEKYCServiceImpl.class);


    @Override
    public EKYCSessionDTO getEKYCSession(String userId, int tenantId) {
        try {
            EKYCSessionDTO ekycSessionDTO = UserEKYCServiceHolder.getUserEKYCConnector()
                    .generateNewSession(userId, tenantId, "passbase", Arrays.asList("firstname", "givenname"));
            return ekycSessionDTO;
        } catch (IdentityException e) {
            throw handleUserEKYCException(e, "EKYC session generation failed");
        } catch (ConfigurationManagementException e) {
            throw handleUserEKYCException(e, "EKYC configuration failed");
        }

    }

    @Override
    public List<EKYCVerifiedCredentialDTO> getEKYCVerifiedCredentials(String userId, int tenantId) {
        try {
            List<EKYCVerifiedCredentialDTO> ekycVerifiedCredentialDTOS = UserEKYCServiceHolder.getUserEKYCConnector()
                    .getVerifiedCredentials(userId, tenantId);
            return ekycVerifiedCredentialDTOS;
        } catch (IdentityException e) {
            throw handleUserEKYCException(e, "EKYC get verified credentials generation failed");
        }
    }

    @Override
    public EKYCVerifiedCredentialDTO getEKYCPendingVerifiedCredentials(String sessionId, String userId, int tenantId) {
        try {
            EKYCVerifiedCredentialDTO ekycVerifiedCredentialDTO = UserEKYCServiceHolder.getUserEKYCConnector()
                    .getPendingVerifiedCredential(sessionId, userId, tenantId);
            return ekycVerifiedCredentialDTO;
        } catch (IdentityException e) {
            throw handleUserEKYCException(e, "EKYC  get pending verified credentials failed");
        } catch (ConfigurationManagementException e) {
            throw handleUserEKYCException(e, "EKYC configuration failed");
        }
    }

    @Override
    public void deleteEKYCVerifiedCredential(String sessionId, String userId, int tenantId) {
        try {
            UserEKYCServiceHolder.getUserEKYCConnector()
                    .deleteVerifiedCredential(sessionId, userId, tenantId);
        } catch (IdentityException e) {
            throw handleUserEKYCException(e, "EKYC  get pending verified credentials failed");
        }
    }

    @Override
    public void updateClaimsFromEKYCVerifiedCredential(String sessionId, String userId, String username, int tenantId) {
        try {
            UserEKYCServiceHolder.getUserEKYCConnector()
                    .updateUserClaimsFromVerifiedCredential(sessionId, userId, username, tenantId);
        } catch (IdentityException e) {
            throw handleUserEKYCException(e, "EKYC  get pending verified credentials failed");
        } catch (ConfigurationManagementException e) {
            throw handleUserEKYCException(e, "EKYC configuration failed");
        } catch (UserStoreException e) {
            throw handleUserEKYCException(e, "EKYC user store update failed");
        }
    }

    private APIError handleUserEKYCException(IdentityException e, String message) {

        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withCode(e.getErrorCode())
                .withMessage(message)
                .build(log, e, e.getMessage());

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        return new APIError(status, errorResponse);
    }

    private APIError handleUserEKYCException(ConfigurationManagementException e, String message) {

        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withCode(e.getErrorCode())
                .withMessage(message)
                .build(log, e, e.getMessage());

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        return new APIError(status, errorResponse);
    }

    private APIError handleUserEKYCException(UserStoreException e, String message) {

        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withCode("500")
                .withMessage(message)
                .build(log, e, e.getMessage());

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        return new APIError(status, errorResponse);
    }
}
