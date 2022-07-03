package cl.diego.java.cft.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.diego.java.cft.modelo.Alumno;
import cl.diego.java.cft.modelo.Carrera;

@Repository
public class AlumnoRepositoryImp implements AlumnoRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	private Alumno makeObject(ResultSet rs, int filaNum) throws SQLException {
		int id = rs.getInt("id");
		String nombre = rs.getString("nombre");
		LocalDate fechaNacimiento = rs.getObject("fecha_nacimiento", LocalDate.class);
		int carreraId = rs.getInt("carrera_id");
		Carrera carrera = carreraRepository.findById(carreraId);
		return new Alumno(id, nombre, fechaNacimiento, carrera);
	}
	
	@Override
	public List<Alumno> findAll() {
		String sql = "SELECT * FROM alumnos";
		return jdbcTemplate.query(sql, this::makeObject);
	}

	@Override
	public Alumno findById(int id) {
		String sql = "SELECT * FROM alumnos WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, this::makeObject, id);
	}

	@Override
	public void create(Alumno alumno) {
		String sql = "INSERT INTO alumnos(nombre, fecha_nacimiento, carrera_id) VALUES(?, ?, ?)";
		jdbcTemplate.update(sql, alumno.getNombre(), alumno.getFechaNacimiento(), alumno.getCarrera().getId());	
	}
	
	@Override
	public void edit(Alumno alumno) {
		String sql = "UPDATE alumnos SET nombre = ?, fecha_nacimiento = ?, carrera_id = ? WHERE id = ?";
		jdbcTemplate.update(sql, alumno.getNombre(), alumno.getFechaNacimiento(), alumno.getCarrera().getId(), alumno.getId());		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM alumnos WHERE id = ?";
		jdbcTemplate.update(sql, id);	
	}
	
}
