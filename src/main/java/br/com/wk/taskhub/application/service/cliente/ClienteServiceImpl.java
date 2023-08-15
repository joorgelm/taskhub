package br.com.wk.taskhub.application.service.cliente;

import br.com.wk.taskhub.application.repository.ClienteRepository;
import br.com.wk.taskhub.domain.entity.Cliente;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ClienteOutput cadastrarCliente(ClienteInput clienteInput) {
        Cliente cliente = clienteRepository.save(Cliente.of(clienteInput));
        return cliente.toOutput();
    }

    @Override
    @Transactional
    public ClienteOutput buscaPorIdOutput(Long clienteId) {
        return this.buscaPorId(clienteId).toOutput();
    }

    @Override
    @Transactional
    public Cliente buscaPorId(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public List<ClienteOutput> listaTodosClientes() {
        return clienteRepository.findAll()
                .stream().map(Cliente::toOutput)
                .toList();
    }
}
