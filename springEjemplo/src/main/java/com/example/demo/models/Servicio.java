package com.example.demo.models;

import java.util.List;

import lombok.Data;

@Data
public class Servicio {
	private String servicio;
	private List<Dato> dato;
}
