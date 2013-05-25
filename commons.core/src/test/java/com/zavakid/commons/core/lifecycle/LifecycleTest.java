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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author zavakid 2013-5-25 上午9:09:03
 * @since 1.0
 */
public class LifecycleTest {

    @Test
    public void testStatus() {
        Alice alice = new Alice();
        assertTrue(alice.isStopped());

        alice.start();
        assertTrue(alice.isRunning());

        alice.stop("test stop");
        assertTrue(alice.isStopped());
        assertFalse(alice.isAborted());
    }

    public void testAborted() {
        Alice alice = new Alice();
        alice.start();
        alice.abort("test abort", null);
        assertTrue(alice.isStopped());
        assertTrue(alice.isAborted());
    }

    static class Alice extends AbstractLifeCycle {
    }
}
