package br.com.wk.taskhub.application.service.projeto;

import br.com.wk.taskhub.application.service.AbstractIOData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProjetoInput extends AbstractIOData {

    @NotNull(message = "Campo obrigatório")
    @NotBlank(message = "Nome inválido")
    private String nome;

    @NotNull(message = "Campo obrigatório")
    @NotBlank(message = "descricao inválida")
    private String descricao;

    @NotNull(message = "Campo obrigatório")
    @Positive
    private Long clienteId;
}
