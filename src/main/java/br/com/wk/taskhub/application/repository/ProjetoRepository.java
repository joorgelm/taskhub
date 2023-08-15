package br.com.wk.taskhub.application.repository;

import br.com.wk.taskhub.domain.entity.Projeto;
import br.com.wk.taskhub.domain.entity.ProjetoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findAllByClienteId(Long clienteId);

    List<Projeto> findAllByClienteIdAndAndStatus(Long clienteId, ProjetoStatus status);
}
