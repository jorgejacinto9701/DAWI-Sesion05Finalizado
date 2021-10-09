package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

@Controller
public class AlumnoController {

	@Autowired
	private AlumnoService service;
	
	@RequestMapping("/")
	public String verInicio() {
		return "crudAlumno";
	}

	@RequestMapping("/consultaCrudAlumno")
	@ResponseBody
	public List<Alumno> consulta(String filtro) {
		List<Alumno> lista = service.listaAlumnoPorNombreLike(filtro+"%");
		return lista;
	}

	@RequestMapping("/registraCrudAlumno")
	@ResponseBody
	public Map<String, Object> registro(Alumno obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Alumno objSalida =  service.insertaActualizaAlumno(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error al registrar");
			}else {
				List<Alumno> lista = service.listaAlumnoPorNombreLike("%");
				salida.put("lista", lista);
				salida.put("mensaje", "Registro exitoso");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	
	@RequestMapping("/actualizaCrudAlumno")
	@ResponseBody
	public Map<String, Object> actualiza(Alumno obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Alumno objSalida =  service.insertaActualizaAlumno(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error al actualizar");
			}else {
				List<Alumno> lista = service.listaAlumnoPorNombreLike("%");
				salida.put("lista", lista);
				salida.put("mensaje", "Actualización exitosa");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	
	@RequestMapping("/eliminaCrudAlumno")
	@ResponseBody
	public Map<String, Object> elimina(int id){
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Alumno> optAlumno =  service.obtienePorId(id);
			if (optAlumno.isEmpty()) {
				salida.put("mensaje", "El alumno no existe");
			}else {
				service.eliminaAlumno(id);
				List<Alumno> lista = service.listaAlumnoPorNombreLike("%");
				salida.put("lista", lista);
				salida.put("mensaje", "Eliminación exitosa");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	
}
