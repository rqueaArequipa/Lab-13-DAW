package com.miempresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.interfaceServicio.ITareaServicio;
import com.miempresa.interfaces.ITarea;
import com.miempresa.modelo.Empleado;
import com.miempresa.modelo.Tarea;

@Service
public class TareaServicio implements ITareaServicio {

	@Autowired
	private ITarea repo;
	
	@Override
	public List<Tarea> listar() {
		return (List<Tarea>)repo.findAll();
	}

	@Override
	public Optional<Tarea> listarId(int id) {
		return repo.findById(id);
	}

	@Override
	public int guardar(Tarea t) {
		Tarea ta = repo.save(t);
		if (!ta.equals(null)) {
			return 1;
		}
		return 0;
	}

	@Override
	public void borrar(int id) {
		repo.deleteById(id);
		
	}

}
