package com.curso.springmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("nova-tarefa");
		mv.addObject(new Tarefa());
		return mv;
	}

	@RequestMapping("/adiciona")
	public ModelAndView adiciona(@Validated Tarefa tarefa, Errors errors) {
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName("nova-tarefa");
			return mv;
		}
		
		repositorio.save(tarefa);
		
		mv.setViewName("lista-tarefas");
		return mv;
	}

	
	@RequestMapping("/lista")
	public ModelAndView lista() {
		List<Tarefa> tarefas = repositorio.findAll();
		
		ModelAndView mv = new ModelAndView("lista-tarefas");
		mv.addObject("tarefas", tarefas);
		
		return mv;
	}
	
	
}

