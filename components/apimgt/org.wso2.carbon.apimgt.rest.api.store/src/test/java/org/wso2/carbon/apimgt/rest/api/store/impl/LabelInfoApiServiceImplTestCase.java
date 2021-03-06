/*
 *   Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.apimgt.rest.api.store.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.wso2.carbon.apimgt.core.api.APIStore;
import org.wso2.carbon.apimgt.core.exception.APIManagementException;
import org.wso2.carbon.apimgt.core.impl.APIStoreImpl;
import org.wso2.carbon.apimgt.core.models.Label;
import org.wso2.carbon.apimgt.rest.api.common.util.RestApiUtil;
import org.wso2.carbon.apimgt.rest.api.store.NotFoundException;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RestApiUtil.class)
public class LabelInfoApiServiceImplTestCase {

    private static final String USER = "admin";
    private static final String contentType = "application/json";

    @Test
    public void testLabelInfoGet() throws APIManagementException, NotFoundException {
        TestUtil.printTestMethodName();

        LabelInfoApiServiceImpl labelInfoApiService = new LabelInfoApiServiceImpl();
        APIStore apiStore = Mockito.mock(APIStoreImpl.class);

        PowerMockito.mockStatic(RestApiUtil.class);
        PowerMockito.when(RestApiUtil.getConsumer(USER)).thenReturn(apiStore);
        PowerMockito.when(RestApiUtil.getLoggedInUsername()).thenReturn(USER);

        List<String> labelNames = new ArrayList<>();
        List<Label> labelList = new ArrayList<>();

        Mockito.when(apiStore.getLabelInfo(labelNames, USER)).thenReturn(labelList);

        Response response = labelInfoApiService.labelInfoGet("label1", null, null,
                TestUtil.getRequest());

        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testLabelInfoGetWithNull() throws APIManagementException, NotFoundException {
        TestUtil.printTestMethodName();

        LabelInfoApiServiceImpl labelInfoApiService = new LabelInfoApiServiceImpl();
        APIStore apiStore = Mockito.mock(APIStoreImpl.class);

        PowerMockito.mockStatic(RestApiUtil.class);
        PowerMockito.when(RestApiUtil.getConsumer(USER)).thenReturn(apiStore);
        PowerMockito.when(RestApiUtil.getLoggedInUsername()).thenReturn(USER);

        List<String> labelNames = new ArrayList<>();
        List<Label> labelList = new ArrayList<>();

        Mockito.when(apiStore.getLabelInfo(labelNames, USER)).thenReturn(labelList);

        Response response = labelInfoApiService.labelInfoGet(null, null, null,
                TestUtil.getRequest());

        Assert.assertEquals(400, response.getStatus());
    }
}
