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
package com.zavakid.commons.log

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.mockito.Mockito._
import org.mockito.Mock

/**
 * @author zavakid 2013-6-3 下午11:38:29
 * @since 1.0
 */
class LogsSpec extends FlatSpec with ShouldMatchers {

  "Logs" should "log error with throwable and format with args" in {
    val log = mock(classOf[Logger])
    val t = new Throwable();

    when(log.isErrorEnabled()).thenReturn(true)
    Logs.error(log, t, "this is a %s test from %s", "error", "LogsSpec")
    verify(log).error("this is a error test from LogsSpec", t)
  }
}

object LogsSpec {

}