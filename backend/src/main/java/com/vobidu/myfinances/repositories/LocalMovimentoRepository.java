package com.vobidu.myfinances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vobidu.myfinances.entities.LocalMovimento;

@Repository
public interface LocalMovimentoRepository extends JpaRepository<LocalMovimento, Long> {

}
