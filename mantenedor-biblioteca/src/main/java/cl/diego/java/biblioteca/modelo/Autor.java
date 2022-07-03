package cl.diego.java.biblioteca.modelo;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor {
	
	private Long id;
	@Size(min=1,max=50)
	private String nombre;
}
