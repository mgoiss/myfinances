package com.vobidu.myfinances.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vobidu.myfinances.entities.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
	
	//Pegando apenas os movimento de um determinado usuario
	@Query("FROM Movimento mov " +
	"WHERE mov.localMovimento.usuario.id = :idUsuario")
	Page<Movimento> BuscarPorUsuario(PageRequest requisicaoPaginada,@Param("idUsuario") Long idUsuario);

}
