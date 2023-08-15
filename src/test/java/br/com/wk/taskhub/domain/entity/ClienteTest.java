package br.com.wk.taskhub.domain.entity;

import br.com.wk.taskhub.application.service.cliente.ClienteInput;
import br.com.wk.taskhub.application.service.cliente.ClienteOutput;
import net.datafaker.Faker;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClienteTest {

    private static final Faker faker = new Faker();

    private static Cliente criarCliente() {
        long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String cpf = faker.cpf().valid();
        String telefone = faker.phoneNumber().phoneNumber();

        return new Cliente(id, nome, cpf, telefone);
    }

    @Test
    public void deveCriarClienteComValoresValidos() {
        long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String cpf = faker.cpf().valid();
        String telefone = faker.phoneNumber().phoneNumber();

        Cliente cliente = new Cliente(id, nome, cpf, telefone);

        assertNotNull(cliente);
        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(telefone, cliente.getTelefone());
    }

    @Test
    public void deveAtualizarClienteComValoresValidos() {
        Cliente clienteComValoresAntigos = criarCliente();
        Cliente clienteComNovosDados = criarCliente();

        Cliente clienteAtualizado = clienteComValoresAntigos.atualizaDados(clienteComNovosDados);

        assertNotNull(clienteAtualizado);
        assertEquals(clienteComNovosDados.getNome(), clienteAtualizado.getNome());
        assertEquals(clienteComNovosDados.getCpf(), clienteAtualizado.getCpf());
        assertEquals(clienteComNovosDados.getTelefone(), clienteAtualizado.getTelefone());
    }

    @Test
    public void deveCriarOutputDeUmClienteValido() {
        Cliente cliente = criarCliente();
        ClienteOutput output = cliente.toOutput();

        assertNotNull(output);
        assertEquals(cliente.getNome(), output.getNome());
        assertEquals(cliente.getId(), output.getId());
    }

    @Test
    public void deveCriarClienteDeInputValido() {
        ClienteInput clienteInput = ClienteInput.builder()
                .nome(faker.name().fullName())
                .cpf(faker.cpf().valid())
                .telefone(faker.phoneNumber().phoneNumber())
                .build();

        Cliente cliente = Cliente.of(clienteInput);

        assertNotNull(cliente);
        assertEquals(clienteInput.getNome(), cliente.getNome());
        assertEquals(clienteInput.getCpf(), cliente.getCpf());
        assertEquals(clienteInput.getCpf(), cliente.getCpf());
    }

    @Test(expected = IllegalStateException.class)
    public void deveFalharAoPassarValoresComStringVazia() {
        new Cliente(faker.number().randomNumber(), "", "", "");
    }

    @Test(expected = IllegalStateException.class)
    public void deveFalharAoAtualizarClienteComValoresNulos() {
        Cliente clienteComValoresAntigos = criarCliente();

        clienteComValoresAntigos.atualizaDados(null);
    }

    @Test(expected = IllegalStateException.class)
    public void deveFalharAoCriarClienteComValoresNulo() {
        new Cliente(null, null, null, null);
    }
}