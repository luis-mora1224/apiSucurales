package com.example.demo.services.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {
	
	private static ObjectMapper objectMapper = getDefaultObjectMapper();

	private static ObjectMapper getDefaultObjectMapper(){
		ObjectMapper defaultObjectMapper = new ObjectMapper(); 
		defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return defaultObjectMapper;
	}

	public static JsonNode parse(String json) throws IOException{
		return objectMapper.readTree(json);
	}
	
	public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException, IllegalArgumentException{
		return objectMapper.treeToValue(node, clazz);
	}
	
	public static String stingify(JsonNode node) throws JsonProcessingException{
		ObjectWriter objectWriter = objectMapper.writer();
		return objectWriter.writeValueAsString(node);
	}
	
	public static String prettyPrint(JsonNode node) throws JsonProcessingException{
		ObjectWriter objectWriter = objectMapper.writer();
		objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
		return objectWriter.writeValueAsString(node);
	}
}
