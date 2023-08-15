package br.com.wk.taskhub.application.service.atividade;

import br.com.wk.taskhub.application.service.AbstractIOData;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AtividadeOutput extends AbstractIOData {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String descricao;
}
