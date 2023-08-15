package br.com.wk.taskhub.domain.entity;

import br.com.wk.taskhub.application.service.atividade.AtividadeInput;
import br.com.wk.taskhub.application.service.atividade.AtividadeOutput;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "seq_atividade", sequenceName = "seq_atividade_id", allocationSize = 1)
public class Atividade extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_atividade")
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projeto_id", referencedColumnName = "id")
    private Projeto projeto;

    protected Atividade() {}

    public Atividade(String nome, String descricao, Projeto projeto) {
        this.nome = nome;
        this.descricao = descricao;
        this.projeto = projeto;

        validarDadosEntidade(this);
    }

    public Atividade(Long id, String nome, String descricao, Projeto projeto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.projeto = projeto;

        validarDadosEntidade(this);
    }

    public static Atividade of(AtividadeInput atividadeInput, Projeto projeto) {
        return new Atividade(
                atividadeInput.getNome(),
                atividadeInput.getDescricao(),
                projeto
        );
    }

    public Long getId() {
        return Long.valueOf(id);
    }

    public String getNome() {
        return String.valueOf(nome);
    }

    public String getDescricao() {
        return String.valueOf(descricao);
    }

    public AtividadeOutput toOutput() {
        AtividadeOutput output = AtividadeOutput
                .builder()
                .nome(this.getNome())
                .descricao(this.getDescricao())
                .build();
        output.validarDados();
        return output;
    }
}
