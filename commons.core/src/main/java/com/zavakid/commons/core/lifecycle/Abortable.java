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
 * @author zavakid 2013-5-25 上午8:57:03
 * @since 1.0
 */
public interface Abortable {

    /**
     * 异常中断停止
     * 
     * @param why
     * @param e
     */
    public void abort(String why, Throwable e);

    /**
     * 是否处于异常停止状态。<br/>
     * 如果 lifecycle 是被正常 stop ，而不是被 abort 的，则返回 false
     * 
     * @return 当被 abort 之后，才会返回 true
     */
    public boolean isAborted();
}
