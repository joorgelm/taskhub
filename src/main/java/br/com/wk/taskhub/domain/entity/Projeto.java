package br.com.wk.taskhub.domain.entity;

import br.com.wk.taskhub.application.service.atividade.AtividadeOutput;
import br.com.wk.taskhub.application.service.projeto.ProjetoInput;
import br.com.wk.taskhub.application.service.projeto.ProjetoOutput;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@SequenceGenerator(name = "seq_projeto", sequenceName = "seq_projeto_id", allocationSize = 1)
public class Projeto extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_projeto")
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjetoStatus status;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
    private List<Atividade> atividades;

    protected Projeto() {}

    public Projeto(Long id, String nome, String descricao, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.status = ProjetoStatus.EM_ANDAMENTO;
        this.cliente = cliente;
        this.atividades = List.of();

        validarDadosEntidade(this);
    }

    public static Projeto of(ProjetoInput input, Cliente cliente) {
        return new Projeto(null, input.getNome(), input.getDescricao(), cliente);
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

    public String getStatus() {
        return this.status.name();
    }

    public Cliente getCliente() {
        return new Cliente(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone()
        );
    }

    public ProjetoOutput toOutput() {
        List<AtividadeOutput> atividadeOutputList = this.atividades.stream()
                .map(Atividade::toOutput)
                .toList();

        ProjetoOutput output = ProjetoOutput
                .builder()
                .id(this.getId())
                .nome(this.getNome())
                .status(this.getStatus())
                .descricao(this.getDescricao())
                .atividades(atividadeOutputList)
                .build();

        output.validarDados();
        return output;
    }
}
