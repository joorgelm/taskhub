package br.com.wk.taskhub.application.service.projeto;

import br.com.wk.taskhub.application.service.AbstractIOData;
import br.com.wk.taskhub.application.service.atividade.AtividadeOutput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProjetoOutput extends AbstractIOData {

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String descricao;

    @NotNull
    @NotBlank
    private String status;

    private List<AtividadeOutput> atividades;
}
