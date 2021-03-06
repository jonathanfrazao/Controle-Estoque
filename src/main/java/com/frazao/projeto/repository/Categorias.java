package com.frazao.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frazao.projeto.model.Categoria;

@Repository
public interface Categorias extends JpaRepository<Categoria, Long> {

}
