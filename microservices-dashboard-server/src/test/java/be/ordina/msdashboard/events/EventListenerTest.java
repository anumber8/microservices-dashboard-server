/*
 * Copyright 2012-2016 the original author or authors.
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
package be.ordina.msdashboard.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for {@link EventListener}
 *
 * @author Tim De Bruyn
 */
public class EventListenerTest {

	private EventListener eventListener = new EventListener();
	
	@SuppressWarnings("unchecked")
	@Test
	public void operationsOnEvents(){
		eventListener.handleContextRefresh(new SystemEvent());
		assertThat(eventListener.getEvents().size()).isEqualTo(1);
		eventListener.deleteEvents();
		assertThat(eventListener.getEvents()).isEmpty();
	}
}
