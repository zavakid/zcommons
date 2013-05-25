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
package com.zavakid.commons.jetty;

import org.junit.Test;

/**
 * @author zavakid 2013-5-25 上午9:29:40
 * @since 1.0
 */
public class JettyServerTest {

    @Test
    public void testJettyServer() throws InterruptedException {
        JettyServer httpServer = new JettyServer();
        httpServer.setContextPath("/");
        httpServer.setMaxThreads(10);
        httpServer.setParentLoaderPriority(true);
        httpServer.setPort(8080);
        httpServer.setResourceBase(JettyServerTest.class.getResource("/test-web").toString());
        httpServer.setThreadName("jetty-test-thread");
        httpServer.start();
        httpServer.join();
        httpServer.stop();

    }
}
