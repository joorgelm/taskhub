package br.com.wk.taskhub.application.service.atividade;

import br.com.wk.taskhub.application.service.AbstractIOData;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AtividadeInput extends AbstractIOData {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    @Positive
    private Long projetoId;
}
