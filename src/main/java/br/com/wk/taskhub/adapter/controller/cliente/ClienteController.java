package br.com.wk.taskhub.adapter.controller.cliente;

import br.com.wk.taskhub.application.service.cliente.ClienteInput;
import br.com.wk.taskhub.application.service.cliente.ClienteOutput;
import br.com.wk.taskhub.application.service.cliente.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteOutput> cadastrar(@RequestBody ClienteInput input) {
        ClienteOutput output = clienteService.cadastrarCliente(input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteOutput> buscaClientePorId(@PathVariable Long clienteId) {
        ClienteOutput output = clienteService.buscaPorIdOutput(clienteId);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteOutput>> listarTodosClientes() {
        List<ClienteOutput> clienteOutputs = clienteService.listaTodosClientes();
        return new ResponseEntity<>(clienteOutputs, HttpStatus.OK);
    }
}
