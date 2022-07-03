package cl.diego.java.tesoreria.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proveedor {
	
	@Id // Le dice que es la PK
	@GeneratedValue(strategy = GenerationType.AUTO) // Autoincremental
	private Long id;
	@Column(nullable = false, length = 10, unique = true )
	private String rut;
	@Column(nullable = false)
	private String razonSocial;
	
	public Proveedor() {
	}

	public Proveedor(Long id, String rut, String razonSocial) {
		this.id = id;
		this.rut = rut;
		this.razonSocial = razonSocial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	
}
