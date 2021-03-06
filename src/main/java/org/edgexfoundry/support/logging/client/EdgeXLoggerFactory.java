/*******************************************************************************
 * Copyright 2016-2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @microservice:  support-logging-client library
 * @author: Jude Hung, Dell
 * @version: 1.0.0
 *******************************************************************************/
package org.edgexfoundry.support.logging.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EdgeXLoggerFactory {
	
	private static ConcurrentMap<String, EdgeXLogger> LoggerMap = new ConcurrentHashMap<String, EdgeXLogger>();
		
	public static EdgeXLogger getEdgeXLogger(String name){
		EdgeXLogger edgeXLogger = LoggerMap.get(name);
        if (null!=edgeXLogger) {
            return edgeXLogger;
        } else {
        	edgeXLogger = new EdgeXLogger(name);
        	EdgeXLogger oldInstance = LoggerMap.putIfAbsent(name, edgeXLogger);
        	return oldInstance == null ? edgeXLogger : oldInstance;
        }
	}
	
	public static EdgeXLogger getEdgeXLogger(Class<?> clazz){
		return getEdgeXLogger(clazz.getName());
	}

}
