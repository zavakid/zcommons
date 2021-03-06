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
package com.zavakid.commons.test;

import scala.beans.BeanProperty

/**
 * @author zavakid 2013-5-29 上午11:08:35
 * @since 1.0
 */
class TestScala {

  @BeanProperty
  var hello: Int = 0

  def sayHello() = {
    this.hello = 5
    println("hello from scala");
  }
}