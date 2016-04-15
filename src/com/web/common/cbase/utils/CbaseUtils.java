package com.web.common.cbase.utils;

import java.util.Map;
import java.util.Map.Entry;

import com.couchbase.client.java.document.json.JsonObject;

public class CbaseUtils {

	public static JsonObject getJsonObject(Map<String, Object> params) {
		// TODO Auto-generated method stub
		JsonObject content = JsonObject.empty();
		for (Entry<String, Object> entry : params.entrySet()) {
			content.put(entry.getKey(), entry.getValue());
		}
		return content;
	}

}
