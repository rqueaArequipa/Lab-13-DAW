package com.miempresa.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.interfaceServicio.IEmpleadoServicio;
import com.miempresa.interfaceServicio.ITareaServicio;
import com.miempresa.interfaces.IEmpleado;
import com.miempresa.interfaces.ITarea;
import com.miempresa.modelo.Empleado;
import com.miempresa.modelo.Tarea;

@Controller
@RequestMapping
public class controlador {
	
	@Autowired
	private IEmpleadoServicio servicio;
	
	@Autowired
	private ITareaServicio TServicio;
	
	@Autowired
	private IEmpleado empleado;
	
	@Autowired
	private ITarea IPtarea;
	
	@GetMapping("/listarEmpleados")
	public String listarEmpleados(Model model) {
		List<Empleado> empleados = servicio.listar();
		model.addAttribute("empleados", empleados);
		return "empleados";
	}
	
	@GetMapping("/buscarEmpleado")
    public String buscarEmpleado(@RequestParam("search") String nombre, Model model) {
        List<Empleado> empleados = empleado.findByNombre(nombre);

        if (empleados.isEmpty()) {
            model.addAttribute("noCoincidencia", true);
        } else {
            model.addAttribute("empleados", empleados);
        }

        return "buscarEmpleado";
    }
	
	@GetMapping("/agregarEmpleado")
	public String agregarEmpleado(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "agregarEmpleado";
	}
	
	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(Empleado p) {
		servicio.guardar(p);
		return "redirect:/listarEmpleados";
	}
	
	@GetMapping("/editarEmpleado/{id}")
	public String editarEmpleado(@PathVariable int id, RedirectAttributes atributos) {
		Optional<Empleado> empleado = servicio.listarId(id);
		atributos.addFlashAttribute("empleado", empleado);
		return "redirect:/mostrarEmpleado";
	}
	
	@GetMapping("/mostrarEmpleado")
	public String mostrarEmpleado(@ModelAttribute("empleado") Empleado p, Model model) {
		model.addAttribute("empleado", p);
		return "agregarEmpleado";
	}
	
	@GetMapping("/eliminarEmpleado/{id}")
	public String eliminarEmpleado(@PathVariable int id) {
		servicio.borrar(id);
		return "redirect:/listarEmpleados";
	}
	
	///-----------TAREAS---------------------
	
	@GetMapping("/listarTareas")
	public String listarTareas(Model model) {
		List<Tarea> tareas = TServicio.listar();
		model.addAttribute("tareas", tareas);
		return "tareas";
	}
	
	@GetMapping("/agregarTarea")
	public String agregarTareas(Model model) {
		model.addAttribute("tarea", new Tarea());
		return "agregarTarea";
	}
	
	@PostMapping("/guardarTarea")
	public String guardarTarea(Tarea t) {
		TServicio.guardar(t);
		return "redirect:/listarTareas";
	}
	
	@GetMapping("/editarTarea/{id}")
	public String editarTarea(@PathVariable int id, RedirectAttributes atributos) {
		Optional<Tarea> tarea = TServicio.listarId(id);
		atributos.addFlashAttribute("tarea", tarea);
		return "redirect:/mostrarTarea";
	}
	
	@GetMapping("/mostrarTarea")
	public String mostrarTarea(@ModelAttribute("tarea") Tarea t, Model model) {
		model.addAttribute("tarea", t);
		return "agregarTarea";
	}
	
	@GetMapping("/eliminarTarea/{id}")
	public String eliminarTarea(@PathVariable int id) {
		TServicio.borrar(id);
		return "redirect:/listarTareas";
	}
	
	@GetMapping("/buscarTarea")
    public String buscarTarea(@RequestParam("search") String descripcion, Model model) {
        List<Tarea> tareas = IPtarea.findByDescripcion(descripcion);

        if (tareas.isEmpty()) {
            model.addAttribute("noCoincidencia", true);
        } else {
            model.addAttribute("tareas", tareas);
        }

        return "buscarTarea";
    }
	
}











