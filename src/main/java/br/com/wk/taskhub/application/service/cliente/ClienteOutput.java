package br.com.wk.taskhub.application.service.cliente;

import br.com.wk.taskhub.application.service.AbstractIOData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClienteOutput extends AbstractIOData {

    @NotNull(message = "Campo obrigatório")
    @Positive(message = "Id inválido")
    private Long id;

    @NotNull(message = "Campo obrigatório")
    @NotBlank(message = "Nome inválido")
    private String nome;

    @NotNull(message = "Campo obrigatório")
    @NotBlank(message = "Nome inválido")
    private String cpf;

    @NotNull(message = "Campo obrigatório")
    @NotBlank(message = "Nome inválido")
    private String telefone;
}
