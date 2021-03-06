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
package be.ordina.msdashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static be.ordina.msdashboard.constants.Constants.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author Andreas Evers
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_EMPTY)
public class Node {

	@JsonProperty(ID)
	private String id;

	@JsonProperty(DETAILS)
	private Map<String, Object> details = new HashMap<>();

	@JsonProperty(LANE)
	private Integer lane;

	@Deprecated
	@JsonProperty("linkedToNodes")
	private Set<Node> linkedToNodes = new HashSet<>();

	private Set<String> linkedToNodeIds = new HashSet<>();

	private Set<String> linkedFromNodeIds = new HashSet<>();

	Node() {
		// For Jackson
	}

	public Node(String id) {
		this.id = id;
		details = new HashMap<>();
		linkedToNodeIds = new HashSet<>();
		linkedFromNodeIds = new HashSet<>();
		linkedToNodes = new HashSet<>();
	}

	public void setLane(Integer lane) {
		this.lane = lane;
	}

	public Integer getLane() {
		return lane;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Set<Node> getLinkedToNodes() {
		if (linkedToNodes == null) {
			return new HashSet<>();
		}
		return linkedToNodes;
	}

	public void setLinkedToNodes(Set<Node> linkedToNodes) {
		this.linkedToNodes = linkedToNodes;
	}

	public Set<String> getLinkedToNodeIds() {
		if (linkedToNodeIds == null) {
			return new HashSet<>();
		}
		return linkedToNodeIds;
	}

	public void setLinkedToNodeIds(Set<String> linkedToNodeIds) {
		this.linkedToNodeIds = linkedToNodeIds;
	}

	public Set<String> getLinkedFromNodeIds() {
		if (linkedFromNodeIds == null) {
			return new HashSet<>();
		}
		return linkedFromNodeIds;
	}

	public void setLinkedFromNodeIds(Set<String> linkedFromNodeIds) {
		this.linkedFromNodeIds = linkedFromNodeIds;
	}

	public Map<String, Object> getDetails() {
		if (details == null) {
			return new HashMap<>();
		}
		return details;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}

	public void addDetail(String key, Object value) {
		details.put(key, value);
	}

	public void mergeWith(Node node) {
		if (linkedToNodeIds == null) {
			linkedToNodeIds = node.getLinkedToNodeIds();
		} else {
			linkedToNodeIds.addAll(node.getLinkedToNodeIds());
		}
		if (linkedFromNodeIds == null) {
			linkedFromNodeIds = node.getLinkedFromNodeIds();
		} else {
			linkedFromNodeIds.addAll(node.getLinkedFromNodeIds());
		}
		if (details == null) {
			details = node.getDetails();
		} else {
			details.forEach((k, v) -> {
				if (v != null) {
					node.getDetails().merge(k, v, (v1, v2) -> {
						return v1;
					});
				}
			});
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Node node = (Node) o;

		if (!id.equals(node.id)) return false;
		if (details != null ? !details.equals(node.details) : node.details != null) return false;
		if (lane != null ? !lane.equals(node.lane) : node.lane != null) return false;
		if (linkedToNodes != null ? !linkedToNodes.equals(node.linkedToNodes) : node.linkedToNodes != null)
			return false;
		if (linkedToNodeIds != null ? !linkedToNodeIds.equals(node.linkedToNodeIds) : node.linkedToNodeIds != null)
			return false;
		return !(linkedFromNodeIds != null ? !linkedFromNodeIds.equals(node.linkedFromNodeIds) : node.linkedFromNodeIds != null);

	}

	@Override
	public int hashCode() {
		if (id == null) System.out.println("ID IS NULL: " + toString());
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "Node{" +
				"id='" + id + '\'' +
				", details=" + details +
				", lane=" + lane +
				", linkedToNodes=" + linkedToNodes +
				", linkedToNodeIds=" + linkedToNodeIds +
				", linkedFromNodeIds=" + linkedFromNodeIds +
				'}';
	}
}