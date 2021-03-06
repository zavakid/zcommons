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

/**
 * @author zavakid 2013-6-4 下午10:33:49
 * @since 1.0
 */
public class CasperJsException extends RuntimeException {

    private static final long serialVersionUID = -354732672261252769L;

    public CasperJsException(){
        super();
    }

    public CasperJsException(String message, Throwable cause){
        super(message, cause);
    }

    public CasperJsException(String message){
        super(message);
    }

    public CasperJsException(Throwable cause){
        super(cause);
    }

}
