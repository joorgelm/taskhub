package br.com.wk.taskhub.application.service.projeto;

import br.com.wk.taskhub.domain.entity.Projeto;

import java.util.List;

public interface ProjetoService {

    ProjetoOutput cadastrarProjeto(ProjetoInput input);

    List<ProjetoOutput> listarTodosPorCliente(Long clienteId);

    List<ProjetoOutput> listarProjetosPorClienteEStatus(Long clienteId, String status);

    Projeto buscaProjetoPorId(Long projetoId);
}
