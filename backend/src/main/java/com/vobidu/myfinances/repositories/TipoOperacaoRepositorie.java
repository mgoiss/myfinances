package com.vobidu.myfinances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vobidu.myfinances.entities.TipoOperacao;

@Repository
public interface TipoOperacaoRepositorie extends JpaRepository<TipoOperacao, Long> {

}
