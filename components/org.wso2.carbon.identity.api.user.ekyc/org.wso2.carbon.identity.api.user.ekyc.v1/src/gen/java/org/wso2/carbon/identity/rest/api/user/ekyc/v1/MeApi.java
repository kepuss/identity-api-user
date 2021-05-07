/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.rest.api.user.ekyc.v1;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.dto.EKYCPendingVerifiedCredentialDTO;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.dto.EKYCSessionResponseDTO;
import org.wso2.carbon.identity.rest.api.user.ekyc.v1.dto.EKYCUpdateClaimsDTO;
import org.wso2.carbon.identity.user.ekyc.dto.EKYCVerifiedCredentialDTO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/me")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/me", description = "the me API")
public class MeApi  {

    @Autowired
    private MeApiService delegate;

    @Valid
    @GET
    @Path("/ekyc/generate-session")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Generate new EKYC session",
            notes = "This API is used to generate new session and redeirection url for EKYC process.",
            response = EKYCSessionResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Resource Forbidden"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response meUserEKYCGenerateSession() {
        return delegate.meUserEKYCGenerateSession();
    }

    @Valid
    @GET
    @Path("/ekyc/vcs")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get user's verified credentials",
            notes = "This API is used to get user's verified credentials.",
            response = EKYCVerifiedCredentialDTO[].class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 403, message = "Resource Forbidden"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response meUserEKYCVerifiedCredentials() {
        return delegate.meUserEKYCVerifiedCredentials();
    }

    @Valid
    @POST
    @Path("/ekyc/vc/check-pending")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Check verified credential that is in pending state",
            notes = "This API is used to check verified credential that is in pending state.",
            response = EKYCVerifiedCredentialDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 403, message = "Resource Forbidden"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response meUserEKYCCheckVerifiedCredential(@ApiParam(value = "EKYC VS details to check vc" ,required=true ) @Valid EKYCPendingVerifiedCredentialDTO ekycCheckVerifiedCredentialDTO) {
        return delegate.meUserEKYCPendingVerifiedCredential(ekycCheckVerifiedCredentialDTO);
    }

    @Valid
    @DELETE
    @Path("/ekyc/vc/{session-id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete verified credential",
            notes = "This API is used to delete verified credential")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 403, message = "Resource Forbidden"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response meUserEKYCCheckVerifiedCredential(@PathParam("session-id")  String sessionId) {
        return delegate.meUserEKYCDeleteVerifiedCredential(sessionId);
    }

    @Valid
    @POST
    @Path("/ekyc/update-claims")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update user claims with claims from verified credential",
            notes = "This API is used to update user claims with claims from verified credential")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),

            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),

            @io.swagger.annotations.ApiResponse(code = 403, message = "Resource Forbidden"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response meUserEKYCUpdateClaims(@ApiParam(value = "EKYC VS details to check vc" ,required=true ) @Valid EKYCUpdateClaimsDTO ekycUpdateClaimsDTO) {
        return delegate.meUserEKYCUpdateClaims(ekycUpdateClaimsDTO);
    }

}
