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

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zavakid.commons.core.lifecycle.AbstractLifeCycle;
import com.zavakid.commons.core.lifecycle.Joinable;

/**
 * @author zavakid 2013-5-25 上午8:44:50
 * @since 1.0
 */
public class JettyServer extends AbstractLifeCycle implements Joinable {

    protected static final Logger log                  = LoggerFactory.getLogger(JettyServer.class);

    protected int                 maxThreads           = 200;
    protected String              threadName           = "jetty-server-thread";
    protected int                 port                 = 8080;
    protected String              contextPath          = "/";
    protected boolean             parentLoaderPriority = true;
    protected String              resourceBase         = JettyServer.class.getResource("/").toString();

    protected Server              server;

    @Override
    protected void doStart() {
        // thread for server
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads);
        threadPool.setName(threadName);

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);

        // add webApp
        WebAppContext webApp = new WebAppContext();
        webApp.setContextPath(contextPath);
        webApp.setParentLoaderPriority(parentLoaderPriority);
        webApp.setResourceBase(resourceBase);

        this.server = new Server();
        this.server.setThreadPool(threadPool);
        this.server.setConnectors(new Connector[] { connector });
        this.server.setHandler(webApp);
        this.server.setGracefulShutdown(2000);
        this.server.setStopAtShutdown(true);
        try {
            this.server.start();
        } catch (Exception e) {
            throw new RuntimeException("jettyServer start error", e);
        }
    }

    @Override
    protected void doStop(String why) {
        log.warn("jettyServer was stopped because: {}", why);
        try {
            this.server.stop();
        } catch (Exception e) {
            throw new RuntimeException("jettyServer stop error", e);
        }
        log.info("jettyServer was stopped");
    }

    @Override
    protected void doAbort(String why, Throwable e) {
        log.warn(String.format("jettyServer was aborted because: %s", why), e);
        try {
            this.server.stop();
        } catch (Exception e1) {
            throw new RuntimeException("jettyServer aborted error", e1);
        }
        log.info("jettyServer was aborted (stopped)");
    }

    @Override
    public void join() throws InterruptedException {
        this.server.join();
    }

    // ========== setter ===========
    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setParentLoaderPriority(boolean parentLoaderPriority) {
        this.parentLoaderPriority = parentLoaderPriority;
    }

    public void setResourceBase(String resourceBase) {
        this.resourceBase = resourceBase;
    }
}
