package br.com.wk.taskhub.application.service.cliente;

import br.com.wk.taskhub.application.service.AbstractIOData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.br.CPF;

@Jacksonized
@Builder
@Getter
public class ClienteInput extends AbstractIOData {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String telefone;

    @NotNull
    @NotBlank
    @CPF
    private String cpf;
}
