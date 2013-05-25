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
 * @author zavakid 2013-5-25 上午9:06:57
 * @since 1.0
 */
public class AbstractLifeCycle implements Lifecycle {

    private final Object lock   = new Object();
    LifecycleStatus      status = LifecycleStatus.STOPPED;

    @Override
    public void start() {
        synchronized (lock) {
            if (isRunning()) {
                return;
            }
            status = LifecycleStatus.RUNNING;
            doStart();
        }
    }

    @Override
    public void stop(String why) {
        synchronized (lock) {
            if (!isRunning()) {
                return;
            }
            doStop(why);
            status = LifecycleStatus.STOPPED;
        }
    }

    @Override
    public void stop() {
        stop("caller doesn't tell any reason to stop");
    }

    @Override
    public void abort(String why, Throwable e) {
        synchronized (lock) {
            if (!isRunning()) {
                return;
            }
            doAbort(why, e);
            status = LifecycleStatus.ABORTED;
        }
    }

    @Override
    public boolean isAborted() {
        return status.isAborted();
    }

    @Override
    public boolean isRunning() {
        return status.isRunning();
    }

    @Override
    public boolean isStopped() {
        return status.isStopped() || status.isAborted();
    }

    protected void doStart() {
    }

    protected void doStop(String why) {
    }

    protected void doAbort(String why, Throwable e) {
    }

}
