package br.com.wk.taskhub.application.service.projeto;

import br.com.wk.taskhub.application.repository.ProjetoRepository;
import br.com.wk.taskhub.application.service.cliente.ClienteService;
import br.com.wk.taskhub.domain.entity.Cliente;
import br.com.wk.taskhub.domain.entity.Projeto;
import br.com.wk.taskhub.domain.entity.ProjetoStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;

    private final ClienteService clienteService;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository, ClienteService clienteService) {
        this.projetoRepository = projetoRepository;
        this.clienteService = clienteService;
    }

    @Override
    @Transactional
    public ProjetoOutput cadastrarProjeto(ProjetoInput input) {
        input.validarDados();
        Cliente cliente = clienteService.buscaPorId(input.getClienteId());
        Projeto projeto = projetoRepository.save(Projeto.of(input, cliente));

        return projeto.toOutput();
    }

    @Override
    @Transactional
    public List<ProjetoOutput> listarTodosPorCliente(Long clienteId) {
        List<Projeto> projetos = projetoRepository.findAllByClienteId(clienteId);

        return projetos.stream()
                .map(Projeto::toOutput)
                .toList();
    }

    @Override
    @Transactional
    public List<ProjetoOutput> listarProjetosPorClienteEStatus(Long clienteId, String status) {
        List<Projeto> projetoList = projetoRepository
                .findAllByClienteIdAndAndStatus(clienteId, ProjetoStatus.valueOf(status));

        return projetoList.stream()
                .map(Projeto::toOutput)
                .toList();
    }

    @Override
    @Transactional
    public Projeto buscaProjetoPorId(Long projetoId) {
        return projetoRepository.findById(projetoId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
