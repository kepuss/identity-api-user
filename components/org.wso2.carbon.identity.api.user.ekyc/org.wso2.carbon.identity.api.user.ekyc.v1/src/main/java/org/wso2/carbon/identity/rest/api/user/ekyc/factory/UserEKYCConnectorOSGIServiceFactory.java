/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.identity.rest.api.user.ekyc.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.user.ekyc.UserEKYCConnector;

/**
 * Factory Beans serves as a factory for creating other beans within the IOC container. This factory bean is used to
 * instantiate the UserEKYCConnector type of object inside the container.
 */
public class UserEKYCConnectorOSGIServiceFactory extends AbstractFactoryBean<UserEKYCConnector> {

    private UserEKYCConnector userEKYCConnector;

    @Override
    public Class<?> getObjectType() {
        return Object.class;
    }

    @Override
    protected UserEKYCConnector createInstance() throws Exception {

        if (this.userEKYCConnector == null) {
            UserEKYCConnector userEKYCConnector = (UserEKYCConnector) PrivilegedCarbonContext.
                    getThreadLocalCarbonContext().getOSGiService(UserEKYCConnector.class, null);
            if (userEKYCConnector != null) {
                this.userEKYCConnector = userEKYCConnector;
            } else {
                throw new Exception("Unable to retrieve UserEKYCConnector service.");
            }
        }
        return this.userEKYCConnector;
    }

}
