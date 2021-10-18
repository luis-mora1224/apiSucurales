package com.example.demo.services;


import com.fasterxml.jackson.databind.JsonNode;

public interface IHttpConsumes {
	String sendGet();
    void process(String prefix, JsonNode currentNode);
}
