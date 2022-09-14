package com.vobidu.myfinances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vobidu.myfinances.entities.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

}
