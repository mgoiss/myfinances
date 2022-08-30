package com.vobidu.myfinances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vobidu.myfinances.entities.Usuario;

@Repository
public interface UsuarioRepositorie extends JpaRepository<Usuario, Long> {

}
