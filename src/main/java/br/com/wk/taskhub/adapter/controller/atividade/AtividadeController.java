package br.com.wk.taskhub.adapter.controller.atividade;

import br.com.wk.taskhub.application.service.atividade.AtividadeInput;
import br.com.wk.taskhub.application.service.atividade.AtividadeService;
import br.com.wk.taskhub.application.service.projeto.ProjetoOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "atividade")
public class AtividadeController {

    private final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @PostMapping
    public ResponseEntity<ProjetoOutput> cadastrarAtividade(@RequestBody AtividadeInput input) {
        atividadeService.cadastrarAtividade(input);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
