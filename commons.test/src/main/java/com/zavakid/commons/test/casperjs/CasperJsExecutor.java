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
import org.apache.commons.exec.util.StringUtils;

/**
 * 执行 casperjs。如果 casperjs 脚本退出时，返回码不为0，就抛出 {@link CasperJsException}
 * @author zavakid 2013-6-4 下午10:06:05
 * @since 1.0
 */
public abstract class CasperJsExecutor {
	
	private static final String CASPERJS_HOME = "CASPERJS_HOME";

	/**
	 * 执行 casperjs
	 * @param casperJsPath
	 * @param subCmd
	 * @param testFilePath
	 * @param args
	 */
    public static void execCasperJsFile(String casperJsPath, String subCmd, String testFilePath, String... args) {
        StringBuilder sb = new StringBuilder(casperJsPath).append(" ").append(subCmd).append(" ").append(testFilePath);
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
    
	
	/**
	 * 执行 casperjs test
	 * @param casperJsPath
	 * @param testFilePath
	 * @param args
	 */
    public static void execCasperJsFile(String casperJsPath,String testFilePath, String... args) {
    	execCasperJsFile(casperJsPath , "test", testFilePath, args);
    }
    
    /**
     * 直接执行 casperj test，casperjs 的执行路径将从：
     * <ol>
     * <li>从 system 变量获取 CASPERJS_HOME</li>
     * <li>如果上一步没有，将从环境变量中获取 CASPERJS_HOME</li>
     * <li>如果上一步获取不到，将抛出异常 {@link CasperJsException}</li>
     * </ol>
     * @param testFilePath
     * @param args
     */
    public static void execCasperJsFile(String testFilePath, String... args){
    	String casperjsHome = System.getProperty(CASPERJS_HOME, System.getenv(CASPERJS_HOME));
    	if(casperjsHome == null || casperjsHome.isEmpty()){
    		throw new CasperJsException(CASPERJS_HOME + " is not sepcify in java System or env");
    	}
    	execCasperJsFile(casperjsHome + "/bin/casperjs" , "test", testFilePath, args);
    }

}
