package com.curso.springmvc.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.springmvc.modelo.Tarefa;

public interface TarefaRepositorio extends JpaRepository<Tarefa, Long> {

}
