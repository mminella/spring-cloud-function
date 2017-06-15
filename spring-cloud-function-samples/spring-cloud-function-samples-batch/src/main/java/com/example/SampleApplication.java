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
package com.example;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Michael Minella
 */
@EnableBatchProcessing
@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean
	public Supplier<String> words() {
		return new Supplier<String>() {

			private String [] items = {"foo", "bar", "baz"};
			private int count = -1;

			@Override
			public String get() {
				count++;

				if(count < items.length) {
					return items[count];
				}
				else {
					return null;
				}
			}
		};
	}

	@Bean
	public Function<String, String> uppercase() {
		return String::toUpperCase;
	}

	@Bean
	public Consumer<String> consumer() {
		return System.out::print;
	}
}
