/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.batch;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.registry.FunctionCatalog;

/**
 * @author Michael Minella
 */
public class FunctionItemWriter<T> implements ItemWriter<T> {

	@Autowired
	private FunctionCatalog registry;

	@Override
	public void write(List<? extends T> items) throws Exception {
		Consumer<T> writer = this.registry.lookupConsumer("writer");

		for (T item : items) {
			writer.accept(item);
		}
	}
}
