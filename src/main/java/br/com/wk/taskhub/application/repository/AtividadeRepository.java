package br.com.wk.taskhub.application.repository;

import br.com.wk.taskhub.domain.entity.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findAllByProjetoId(Long projetoId);
}
