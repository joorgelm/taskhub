package br.com.wk.taskhub.adapter.controller.projeto;

import br.com.wk.taskhub.application.service.projeto.ProjetoInput;
import br.com.wk.taskhub.application.service.projeto.ProjetoOutput;
import br.com.wk.taskhub.application.service.projeto.ProjetoService;
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
@RequestMapping(value = "projeto")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<ProjetoOutput> cadastrarProjeto(@RequestBody ProjetoInput input) {
        ProjetoOutput projetoOutput = projetoService.cadastrarProjeto(input);
        return new ResponseEntity<>(projetoOutput, HttpStatus.OK);
    }

    @GetMapping(value = "/{clienteId}")
    public ResponseEntity<List<ProjetoOutput>> listarProjetosPorCliente(@PathVariable Long clienteId) {
        List<ProjetoOutput> projetoOutputList = projetoService.listarTodosPorCliente(clienteId);
        return new ResponseEntity<>(projetoOutputList, HttpStatus.OK);
    }
}
