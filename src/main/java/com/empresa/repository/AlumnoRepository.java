package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

	//JPQL--> Son querys con clases no con tablas
	@Query("Select a from Alumno a where nombre like :fil order by a.idAlumno desc")
	public abstract List<Alumno> listaAlumnoPorNombreLike(@Param("fil") String filtro);

	
	//Query apartir del nombre de los métodos
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	public abstract List<Alumno> findByDni(String dni);
	public abstract List<Alumno> findByDniAndIdAlumnoNot(String dni, int idAlumno);
}
