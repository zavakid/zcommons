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
package com.zavakid.commons.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zavakid 2013-6-2 下午3:54:54
 * @since 1.0
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static final String COUNTER_KEY = "counter";
    private static final String TEXT_KEY    = "text";

    @RequestMapping("{id}")
    public void index(@PathVariable int id, HttpServletRequest request, HttpServletResponse reponse) throws IOException {
        HttpSession session = request.getSession();
        Integer count = (Integer) session.getAttribute(COUNTER_KEY);
        if (count == null) {
            count = new Integer(0);
        }
        count++;
        session.setAttribute(COUNTER_KEY, count);

        String text = (String) session.getAttribute(TEXT_KEY);
        if (text == null) {
            String sessionValue = "";
            for (int i = 0; i < 1500; i++) {
                sessionValue += "Hello world : " + i + "<br/>";
            }
            text = sessionValue;
            session.setAttribute(TEXT_KEY, text);
        }

        reponse.getWriter().append("Hello world from " + id + " viste times : " + count);
        reponse.getWriter().append("<br/> " + text);

    }
}
