package com.curso.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.curso.springmvc.modelo.Tarefa;
import com.curso.springmvc.repositorio.TarefaRepositorio;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
	private TarefaRepositorio repositorio;
	
	@RequestMapping("/novo")
	public String novo() {
		return "nova-tarefa";
	}

	@RequestMapping("/adiciona")
	public String adiciona(Tarefa tarefa) {
		repositorio.save(tarefa);
		
		return "lista-tarefas";
	}

	@RequestMapping("/lista")
	public ModelAndView lista() {
		List<Tarefa> tarefas = repositorio.findAll();
		
		ModelAndView mv = new ModelAndView("lista-tarefas");
		mv.addObject("tarefas", tarefas);
		
		return mv;
	}
	
	
}

