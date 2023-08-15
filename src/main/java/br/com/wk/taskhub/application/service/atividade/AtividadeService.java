package br.com.wk.taskhub.application.service.atividade;

import java.util.List;

public interface AtividadeService {

    void cadastrarAtividade(AtividadeInput atividadeInput);

    List<AtividadeOutput> listarAtividadesPorProjeto(Long projetoId);
}
