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

package org.wso2.carbon.identity.rest.api.user.ekyc.v1.util;

import org.wso2.carbon.identity.user.ekyc.UserEKYCConnector;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * Service holder class for user ekyc.
 */
public class UserEKYCServiceHolder {

    private static UserEKYCConnector userEKYCConnector;
    private static RealmService realmService;

    public static RealmService getRealmService() {
        return realmService;
    }

    public static void setRealmService(RealmService realmService) {
        UserEKYCServiceHolder.realmService = realmService;
    }

    public static UserEKYCConnector getUserEKYCConnector() {
        return userEKYCConnector;
    }

    public static void setUserEKYCConnector(UserEKYCConnector userEKYCConnector) {
        UserEKYCServiceHolder.userEKYCConnector = userEKYCConnector;
    }
}

