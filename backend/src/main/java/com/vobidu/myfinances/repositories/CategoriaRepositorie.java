package com.vobidu.myfinances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vobidu.myfinances.entities.Categoria;

@Repository
public interface CategoriaRepositorie extends JpaRepository<Categoria, Long> {

}
