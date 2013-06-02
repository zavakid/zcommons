/*
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.zavakid.commons.web;

import org.junit.Test;

import com.zavakid.commons.jetty.JettyServer;

/**
 * @author zavakid 2013-6-2 下午3:28:58
 * @since 1.0
 */
public class SpringMVCTest {

    @Test
    public void testSpringMVC() throws InterruptedException {
        JettyServer server = new JettyServer();
        server.setContextPath("/");
        server.setMaxThreads(200);
        server.setParentLoaderPriority(true);
        server.setPort(8888);
        server.setResourceBase(SpringMVCTest.class.getResource("/mvc-test-app").toString());
        server.start();
        server.join();
    }
}
