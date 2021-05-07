package org.wso2.carbon.identity.rest.api.user.ekyc.v1.core;

import org.wso2.carbon.identity.user.ekyc.dto.EKYCSessionDTO;
import org.wso2.carbon.identity.user.ekyc.dto.EKYCVerifiedCredentialDTO;

import java.util.List;

/**
 * API service implementation of a specific user's ekyc operations
 */
public interface UserEKYCService {
    EKYCSessionDTO getEKYCSession(String userId, int tenantId);

    List<EKYCVerifiedCredentialDTO> getEKYCVerifiedCredentials(String userId, int tenantId);

    EKYCVerifiedCredentialDTO getEKYCPendingVerifiedCredentials(String sessionId, String userId, int tenantId);

    void deleteEKYCVerifiedCredential(String sessionId, String userId, int tenantId);

    void updateClaimsFromEKYCVerifiedCredential(String sessionId, String userId, String username, int tenantId);
}
