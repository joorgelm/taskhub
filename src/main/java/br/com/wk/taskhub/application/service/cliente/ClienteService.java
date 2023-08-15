package br.com.wk.taskhub.application.service.cliente;

import br.com.wk.taskhub.domain.entity.Cliente;

import java.util.List;

public interface ClienteService {
    ClienteOutput cadastrarCliente(ClienteInput clienteInput);

    Cliente buscaPorId(Long clienteId);

    List<ClienteOutput> listaTodosClientes();

    ClienteOutput buscaPorIdOutput(Long clienteId);
}
