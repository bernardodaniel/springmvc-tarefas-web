package com.curso.springmvc.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.springmvc.modelo.Tarefa;

public interface TarefaRepositorio extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findByDescricao(String descricao);
	
}
