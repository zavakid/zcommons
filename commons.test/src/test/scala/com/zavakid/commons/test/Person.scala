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
package com.zavakid.commons.test

/**
 * @author zavakid 2013-5-29 下午6:09:03
 * @since 1.0
 */
class Person(val name: String) {
  var age: Int = 0
  age = 0
  override def toString = "[name=]" + name + ",age=" + age + "]"
}