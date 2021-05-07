package org.wso2.carbon.identity.rest.api.user.ekyc.v1.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.api.user.common.function.UserToUniqueId;
import org.wso2.carbon.identity.application.common.model.User;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.MeApiService;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.core.UserEKYCServiceImpl;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.dto.EKYCPendingVerifiedCredentialDTO;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.dto.EKYCSessionResponseDTO;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.dto.EKYCUpdateClaimsDTO;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.util.UserEKYCServiceHolder;
import org.wso2.carbon.identity.user.ekyc.dto.EKYCSessionDTO;
import org.wso2.carbon.identity.user.ekyc.dto.EKYCVerifiedCredentialDTO;

import java.util.List;
import javax.ws.rs.core.Response;


import static org.wso2.carbon.identity.api.user.common.ContextLoader.getUserFromContext;

/**
 * EKYC API service implementation for users/me endpoint.
 */
public class MeApiServiceImpl extends MeApiService {

    @Autowired
    private UserEKYCServiceImpl userEKYCService;

    @Override
    public Response meUserEKYCGenerateSession() {
        User user = getUserFromContext();
        String userId = getUserIdFromUser(user);
        int tenantId = IdentityTenantUtil.getTenantId(user.getTenantDomain());
        EKYCSessionDTO ekycSessionDTO = userEKYCService.getEKYCSession(userId, tenantId);
        return Response.ok().entity(new EKYCSessionResponseDTO(ekycSessionDTO.getRedirectUrl())).build();
    }

    @Override
    public Response meUserEKYCVerifiedCredentials() {
        User user = getUserFromContext();
        String userId = getUserIdFromUser(user);
        int tenantId = IdentityTenantUtil.getTenantId(user.getTenantDomain());
        List<EKYCVerifiedCredentialDTO> ekycVerifiedCredentialDTOS = userEKYCService
                .getEKYCVerifiedCredentials(userId, tenantId);
        return Response.ok().entity(ekycVerifiedCredentialDTOS).build();
    }

    @Override
    public Response meUserEKYCPendingVerifiedCredential
            (EKYCPendingVerifiedCredentialDTO ekycPendingVerifiedCredentialDTO) {
        User user = getUserFromContext();
        String userId = getUserIdFromUser(user);
        int tenantId = IdentityTenantUtil.getTenantId(user.getTenantDomain());
        EKYCVerifiedCredentialDTO ekycVerifiedCredentialDTO = userEKYCService
                .getEKYCPendingVerifiedCredentials(ekycPendingVerifiedCredentialDTO.getSessionId(), userId, tenantId);
        return Response.ok().entity(ekycVerifiedCredentialDTO).build();
    }

    @Override
    public Response meUserEKYCDeleteVerifiedCredential(String sessionId) {
        User user = getUserFromContext();
        String userId = getUserIdFromUser(user);
        int tenantId = IdentityTenantUtil.getTenantId(user.getTenantDomain());
        userEKYCService.deleteEKYCVerifiedCredential(sessionId, userId, tenantId);
        return Response.ok().build();
    }

    @Override
    public Response meUserEKYCUpdateClaims(EKYCUpdateClaimsDTO updateClaimsDTO) {
        User user = getUserFromContext();
        String userId = getUserIdFromUser(user);
        int tenantId = IdentityTenantUtil.getTenantId(user.getTenantDomain());
        userEKYCService.updateClaimsFromEKYCVerifiedCredential(
                updateClaimsDTO.getSessionId(), userId, user.getUserName(), tenantId);
        return Response.ok().build();
    }

    private String getUserIdFromUser(User user) {
        return new UserToUniqueId().apply(UserEKYCServiceHolder.getRealmService(), user);
    }
}
