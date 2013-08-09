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
package com.zavakid.commons.log;

import java.util.Collections;
import java.util.Map;

import org.slf4j.MDC;

/**
 * @author zavakid 2013-6-3 下午11:17:17
 * @since 1.0
 */
public abstract class MDCs {

    /**
     * 将当前线程的MDC打一个快照
     * 
     * @return
     */
    public static MDCSnapshot snapshotMDC() {
        return new MDCSnapshot();
    }

    /**
     * 将当前线程的MDC用快照恢复
     * 
     * @param mdcSnapshot
     */
    public static void recoverMDC(MDCSnapshot mdcSnapshot) {
        mdcSnapshot.recover();
    }

    public static class MDCSnapshot {

        @SuppressWarnings("rawtypes")
		private Map map;

        private MDCSnapshot(){
            this.snapshot();
        }

        public void snapshot() {
            map = MDC.getCopyOfContextMap();
            if (map == null) {
                map = Collections.EMPTY_MAP;
            }
        }

        public void recover() {
            MDC.setContextMap(map);
        }
    }
}
