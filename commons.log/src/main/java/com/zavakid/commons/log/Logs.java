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

import org.slf4j.Logger;

/**
 * @author zavakid 2013-6-3 下午11:04:53
 * @since 1.0
 */
public abstract class Logs {

    /**
     * log.error throwable and string format with args
     * 
     * @param log
     * @param t
     * @param format
     * @param args
     */
    public static void error(Logger log, Throwable t, String format, Object... args) {
        if (!log.isErrorEnabled()) {
            return;
        }
        String msg = String.format(format, args);
        log.error(msg, t);
    }

    /**
     * log.warn throwable and string format with args
     * 
     * @param log
     * @param t
     * @param format
     * @param args
     */
    public static void warn(Logger log, Throwable t, String format, Object... args) {
        if (!log.isWarnEnabled()) {
            return;
        }
        String msg = String.format(format, args);
        log.warn(msg, t);
    }

    /**
     * log.info throwable and string format with args
     * 
     * @param log
     * @param t
     * @param format
     * @param args
     */
    public static void info(Logger log, Throwable t, String format, Object... args) {
        if (!log.isInfoEnabled()) {
            return;
        }
        String msg = String.format(format, args);
        log.info(msg, t);
    }

    /**
     * log.debug throwable and string format with args
     * 
     * @param log
     * @param t
     * @param format
     * @param args
     */
    public static void debug(Logger log, Throwable t, String format, Object... args) {
        if (!log.isDebugEnabled()) {
            return;
        }
        String msg = String.format(format, args);
        log.debug(msg, t);
    }

    /**
     * log.trace throwable and string format with args
     * 
     * @param log
     * @param t
     * @param format
     * @param args
     */
    public static void trace(Logger log, Throwable t, String format, Object... args) {
        if (!log.isTraceEnabled()) {
            return;
        }
        String msg = String.format(format, args);
        log.trace(msg, t);
    }
}
