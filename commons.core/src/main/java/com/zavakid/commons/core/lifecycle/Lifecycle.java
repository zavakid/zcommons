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
package com.zavakid.commons.core.lifecycle;

/**
 * @author zavakid 2013-5-25 上午8:58:54
 * @since 1.0
 */
public interface Lifecycle extends Abortable {

    /**
     * 正常启动
     */
    void start();

    /**
     * 正常停止
     */
    void stop();

    /**
     * 因为某种原因停止
     * 
     * @param why
     */
    void stop(String why);

    /**
     * 是否正在运行中
     * 
     * @return
     */
    boolean isRunning();

    /**
     * 是否处于停止状态，有可能是正常停止，也有可能是异常停止
     * 
     * @return
     */
    boolean isStopped();
}
