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
package com.zavakid.commons.web.webx;

import static com.alibaba.citrus.service.requestcontext.util.RequestContextUtil.findRequestContext;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.citrus.service.requestcontext.RequestContext;
import com.alibaba.citrus.service.requestcontext.RequestContextChainingService;
import com.alibaba.citrus.service.requestcontext.lazycommit.LazyCommitRequestContext;
import com.alibaba.citrus.service.requestcontext.util.RequestContextUtil;

/**
 * @author zavakid 2013-6-2 下午4:07:56
 * @since 1.0
 */
public class WebxRequestContextFilter extends OncePerRequestFilter {

    private static final Logger           log   = LoggerFactory.getLogger(WebxRequestContextFilter.class);

    private String                        contextAttribute;
    private WebApplicationContext         webApplicationContext;
    private RequestContextChainingService webxRequestContext;

    private Object                        mutux = new Object();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                                                                                                                      throws ServletException,
                                                                                                                      IOException {

        RequestContext requestContext = RequestContextUtil.getRequestContext(request);

        if (requestContext == null) {
            requestContext = getWebxRequestContext().getRequestContext(getServletContext(), request, response);
        }

        try {
            filterChain.doFilter(requestContext.getRequest(), requestContext.getResponse());
        } catch (Exception e) {
        } finally {
            if (isRequestFinished(requestContext)) {
                return;
            }
            commitRequest(requestContext);
        }
    }

    protected boolean isRequestFinished(RequestContext requestContext) {
        LazyCommitRequestContext lcrc = findRequestContext(requestContext, LazyCommitRequestContext.class);
        return lcrc != null && lcrc.isRedirected();
    }

    /** 提交request。 */
    private void commitRequest(RequestContext requestContext) {
        if (requestContext == null) {
            return;
        }

        try {
            webxRequestContext.commitRequestContext(requestContext);
        } catch (Exception e) {
            log.error("Exception occurred while commit rundata", e);
        }
    }

    protected RequestContextChainingService getWebxRequestContext() {
        synchronized (this.mutux) {
            if (this.webxRequestContext == null) {

                WebApplicationContext wac = findWebApplicationContext();
                if (wac != null) {
                    this.webxRequestContext = wac.getBean("webxRequestContext", RequestContextChainingService.class);
                }
            }
        }
        return this.webxRequestContext;
    }

    protected WebApplicationContext findWebApplicationContext() {
        if (this.webApplicationContext != null) {
            // the user has injected a context at construction time -> use it
            if (this.webApplicationContext instanceof ConfigurableApplicationContext) {
                if (!((ConfigurableApplicationContext) this.webApplicationContext).isActive()) {
                    // the context has not yet been refreshed -> do so before
                    // returning it
                    ((ConfigurableApplicationContext) this.webApplicationContext).refresh();
                }
            }
            return this.webApplicationContext;
        }
        String attrName = this.contextAttribute;
        if (attrName != null) {
            return WebApplicationContextUtils.getWebApplicationContext(getServletContext(), attrName);
        } else {
            return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        }
    }

    // ======== setter ========
    public void setContextAttribute(String contextAttribute) {
        this.contextAttribute = contextAttribute;
    }

}
