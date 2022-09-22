package com.vobidu.myfinances.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vobidu.myfinances.entities.LocalMovimento;

@Repository
public interface LocalMovimentoRepository extends JpaRepository<LocalMovimento, Long> {

	//Pegando apenas os locais de movimento de um determinado usuario
	@Query("FROM LocalMovimento lm WHERE lm.usuario.id = :idUsuario")
	Page<LocalMovimento> BuscarPorUsuario(PageRequest requisicaoPaginada,@Param("idUsuario") Long idUsuario);
	

}
