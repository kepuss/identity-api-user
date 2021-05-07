package org.wso2.carbon.identity.rest.api.user.ekyc.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

@ApiModel(description = "")
public class EKYCUpdateClaimsDTO {
    @Valid
    private String sessionId = null;


    @ApiModelProperty(value = "")
    @JsonProperty("sessionId")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "EKYCUpdateClaimsDTO{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
