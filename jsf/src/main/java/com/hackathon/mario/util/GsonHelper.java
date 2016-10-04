package com.hackathon.mario.util;

import java.lang.reflect.Type;
import java.util.Date;

import org.joda.time.DateTime;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonHelper {

	public static GsonBuilder getBaseGsonBuilder() {
		GsonBuilder gson = new GsonBuilder();
		gson.setDateFormat("dd/MM/yyyy");
		gson.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
		gson.setPrettyPrinting();
		return gson;
	}

	private static class DateTimeTypeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
		@Override
		public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
			return new JsonPrimitive(src.toString());
		}

		@Override
		public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			try {
				return new DateTime(json.getAsString());
			} catch (IllegalArgumentException e) {
				// could also be a java.util.Date
				Date date = context.deserialize(json, Date.class);
				return new DateTime(date);
			}
		}
	}
}
