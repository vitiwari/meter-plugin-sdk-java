package com.boundary.plugin.sdk.jmx;

//Copyright 2014 Boundary, Inc.
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Hashtable;

import javax.management.MBeanAttributeInfo;
import javax.management.ObjectName;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.boundary.plugin.sdk.PluginUtil;

public abstract class MBeanTransformBase implements MBeanTransform {
	
	private final char METRIC_NAME_SEPARATOR='.';
	protected String prefix;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}

	protected String getMetricName(ObjectName name,MBeanAttributeInfo info) {
		StringBuilder builder = new StringBuilder();
		StringBuilder nameBuilder = new StringBuilder();
		Hashtable<String, String> keys = name.getKeyPropertyList();
		for (String s : keys.values()) {
			nameBuilder.append(METRIC_NAME_SEPARATOR);
			nameBuilder.append(PluginUtil.toUpperUnderscore(s,METRIC_NAME_SEPARATOR));
		}

		builder.append(String.format("%s%s.%s",
				name.getDomain().toUpperCase(),
				nameBuilder.toString().toUpperCase(),
				PluginUtil.toUpperUnderscore(info.getName(),METRIC_NAME_SEPARATOR)));
		return builder.toString();
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}