package br.com.wk.taskhub.domain.entity;

import br.com.wk.taskhub.application.service.projeto.ProjetoOutput;
import net.datafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProjetoTest {

    private static final Faker faker = new Faker();

    @Test
    public void deveCriarProjeto() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();
        Cliente cliente = Mockito.mock(Cliente.class);

        Projeto projeto = new Projeto(id, nome, descricao, cliente);

        assertNotNull(projeto);
        assertEquals(id, projeto.getId());
        assertEquals(nome, projeto.getNome());
        assertEquals(descricao, projeto.getDescricao());
    }

    @Test
    public void deveCriarOutput() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();
        Cliente cliente = Mockito.mock(Cliente.class);

        Projeto projeto = new Projeto(id, nome, descricao, cliente);

        ProjetoOutput projetoOutput = projeto.toOutput();

        assertNotNull(projetoOutput);
        assertEquals(projeto.getId(), projetoOutput.getId());
        assertEquals(projeto.getNome(), projetoOutput.getNome());
        assertEquals(projeto.getDescricao(), projetoOutput.getDescricao());
        assertEquals(projeto.getStatus(), projetoOutput.getStatus());
    }

    @Test
    public void deveRecuperarClienteDoProjeto() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();

        long clienteId = faker.number().numberBetween(1L, 100L);
        String clienteNome = faker.name().fullName();
        String cpf = faker.cpf().valid();
        String telefone = faker.phoneNumber().phoneNumber();

        Cliente cliente = new Cliente(clienteId, clienteNome, cpf, telefone);
        Projeto projeto = new Projeto(id, nome, descricao, cliente);

        Cliente projetoCliente = projeto.getCliente();

        assertNotNull(projetoCliente);
        assertNotEquals(cliente, projetoCliente);
        assertEquals(cliente.getId(), projetoCliente.getId());
        assertEquals(cliente.getNome(), projetoCliente.getNome());
        assertEquals(cliente.getCpf(), projetoCliente.getCpf());
        assertEquals(cliente.getTelefone(), projetoCliente.getTelefone());
    }

    @Test
    public void deveRetornarStatusDoProjeto() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();
        Cliente cliente = Mockito.mock(Cliente.class);

        Projeto projeto = new Projeto(id, nome, descricao, cliente);

        assertEquals(ProjetoStatus.EM_ANDAMENTO, ProjetoStatus.valueOf(projeto.getStatus()));
    }

    @Test(expected = IllegalStateException.class)
    public void deveLancarExcecaoProjetoComNomeNulo() {
        Long id = faker.number().numberBetween(1L, 100L);
        String descricao = faker.lorem().paragraph();
        Cliente cliente = Mockito.mock(Cliente.class);

        new Projeto(id, null, descricao, cliente);
    }

    @Test(expected = IllegalStateException.class)
    public void deveLancarExcecaoProjetoComDescricaoNula() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        Cliente cliente = Mockito.mock(Cliente.class);

        new Projeto(id, nome, null, cliente);
    }

    @Test(expected = IllegalStateException.class)
    public void deveLancarExcecaoProjetoComClienteNulo() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();

        new Projeto(id, nome, descricao, null);
    }
}