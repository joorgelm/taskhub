package br.com.wk.taskhub.domain.entity;

import br.com.wk.taskhub.application.service.cliente.ClienteInput;
import br.com.wk.taskhub.application.service.cliente.ClienteOutput;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente_id", allocationSize = 1)
public class Cliente extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cliente")
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    @CPF
    private String cpf;

    @NotNull
    @NotEmpty
    private String telefone;

    protected Cliente() {}

    public Cliente(
        Long id,
        String nome,
        String cpf,
        String telefone
    ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;

        validarDadosEntidade(this);
    }

    public Cliente atualizaDados(Cliente novo) {
        validarDadosEntidade(novo);
        this.nome = novo.nome;
        this.telefone = novo.telefone;
        this.cpf = novo.cpf;

        return this;
    }

    public Long getId() {
        return Long.valueOf(id);
    }

    public String getNome() {
        return String.valueOf(nome);
    }

    public String getCpf() {
        return String.valueOf(cpf);
    }

    public String getTelefone() {
        return String.valueOf(telefone);
    }

    public static Cliente of (ClienteInput input) {
        input.validarDados();
        return new Cliente(
                null,
                input.getNome(),
                input.getCpf(),
                input.getTelefone()
        );
    }

    public ClienteOutput toOutput() {
        ClienteOutput build = ClienteOutput.builder()
                .id(this.getId())
                .nome(this.getNome())
                .cpf(this.getCpf())
                .telefone(this.getTelefone())
                .build();

        build.validarDados();
        return build;
    }
}
