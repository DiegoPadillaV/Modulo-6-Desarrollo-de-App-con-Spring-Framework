package cl.diego.java.cft.modelo;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Carrera {
	
	@Min(0)
	private int id;
	@Size(min = 3, max = 20, message = "Debe estar entre 3 y 20 caracteres")
	private String nombre;
	private String descripcion;
	
	public Carrera() {
	}

	public Carrera(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	
}
