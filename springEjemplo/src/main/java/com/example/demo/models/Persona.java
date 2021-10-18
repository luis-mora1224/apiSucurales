package com.example.demo.models;

public class Persona {
	private String nombre;
	private boolean sexo;
	
	public Persona(String nombre, boolean sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isSexo() {
		return sexo;
	}
	
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}
}
