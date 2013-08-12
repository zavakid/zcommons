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
package com.zavakid.commons.test.casperjs;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

/**
 * 执行 casperjs。如果 casperjs 脚本退出时，返回码不为0，就抛出 {@link CasperJsException}
 * @author zavakid 2013-6-4 下午10:06:05
 * @since 1.0
 */
public abstract class CasperJsExecutor {

    public static void execCasperJsFile(String casperJsPath, String testFilePath, String... args) {
        StringBuilder sb = new StringBuilder(casperJsPath).append(" ").append(testFilePath);
        for (String arg : args) {
            sb.append(" ").append(arg);
        }
        CommandLine cmd = CommandLine.parse(sb.toString());
        DefaultExecutor executor = new DefaultExecutor();
        try {
            int returnValue = executor.execute(cmd);
            if (returnValue != 0) {
                throw new CasperJsException("return value is " + returnValue);
            }
        } catch (Exception e) {
            throw new CasperJsException(e);
        }
    }

}
