package br.com.wk.taskhub.application.service.atividade;

import br.com.wk.taskhub.application.repository.AtividadeRepository;
import br.com.wk.taskhub.application.service.projeto.ProjetoService;
import br.com.wk.taskhub.domain.entity.Atividade;
import br.com.wk.taskhub.domain.entity.Projeto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeServiceImpl implements AtividadeService {
    private final AtividadeRepository atividadeRepository;

    private final ProjetoService projetoService;

    public AtividadeServiceImpl(AtividadeRepository atividadeRepository, ProjetoService projetoService) {
        this.atividadeRepository = atividadeRepository;
        this.projetoService = projetoService;
    }

    @Override
    @Transactional
    public void cadastrarAtividade(AtividadeInput atividadeInput) {
        atividadeInput.validarDados();
        Projeto projeto = projetoService.buscaProjetoPorId(atividadeInput.getProjetoId());

        atividadeRepository.save(Atividade.of(atividadeInput, projeto));
    }

    @Override
    @Transactional
    public List<AtividadeOutput> listarAtividadesPorProjeto(Long projetoId) {
        return atividadeRepository.findAllByProjetoId(projetoId)
                .stream()
                .map(Atividade::toOutput)
                .toList();
    }
}
