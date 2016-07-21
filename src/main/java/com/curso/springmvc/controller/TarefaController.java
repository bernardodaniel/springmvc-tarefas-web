package com.curso.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.curso.springmvc.modelo.Tarefa;
import com.curso.springmvc.repositorio.TarefaFiltro;
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
		
		mv.setViewName("redirect:/tarefas/lista");
		return mv;
	}

	
	@RequestMapping("/lista")
	public ModelAndView lista(@ModelAttribute("filtro") TarefaFiltro filtro) {
		List<Tarefa> tarefas = repositorio.findAll();
		
		ModelAndView mv = new ModelAndView("lista-tarefas");
		mv.addObject("tarefas", tarefas);
		
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Tarefa tarefa) {
		ModelAndView mv = new ModelAndView("edita-tarefa");
		mv.addObject(tarefa);
		
		return mv;
	}
	
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Tarefa tarefa) {
		
		repositorio.delete(tarefa);
		
		return "forward:/tarefas/lista";
	}

	@RequestMapping("/pesquisa")
	public ModelAndView pesquisa(@ModelAttribute("filtro") TarefaFiltro filtro) {
		List<Tarefa> tarefas = repositorio.findByDescricao(filtro.getDescricao());
		
		ModelAndView mv = new ModelAndView("lista-tarefas");
		mv.addObject("tarefas", tarefas);
		
		return mv;
	}
	
	
}

