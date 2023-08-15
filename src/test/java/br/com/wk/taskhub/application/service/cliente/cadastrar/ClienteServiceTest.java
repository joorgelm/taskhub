package br.com.wk.taskhub.application.service.cliente.cadastrar;

import br.com.wk.taskhub.application.repository.ClienteRepository;
import br.com.wk.taskhub.application.service.cliente.ClienteInput;
import br.com.wk.taskhub.application.service.cliente.ClienteOutput;
import br.com.wk.taskhub.application.service.cliente.ClienteService;
import br.com.wk.taskhub.application.service.cliente.ClienteServiceImpl;
import br.com.wk.taskhub.domain.entity.Cliente;
import net.datafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    private ClienteService clienteService;

    @Captor
    private ArgumentCaptor<Cliente> clienteArgumentCaptor;

    private static final Faker faker = new Faker();

    @Mock
    private ClienteRepository clienteRepositoryMock;

    @Before
    public void setup() {
        clienteService = new ClienteServiceImpl(clienteRepositoryMock);
    }

    @Test(expected = IllegalStateException.class)
    public void deveFalharAoCadastrarClienteComCpfInvalido() {
        ClienteInput clienteInput = ClienteInput.builder()
                .nome(faker.name().fullName())
                .cpf("00000000")
                .telefone(faker.phoneNumber().phoneNumber())
                .build();

        clienteService.cadastrarCliente(clienteInput);
    }

    @Test
    public void deveCadastrarCliente() {
        ClienteInput clienteInput = ClienteInput.builder()
                .nome(faker.name().fullName())
                .cpf(faker.cpf().valid())
                .telefone(faker.phoneNumber().phoneNumber())
                .build();

        Cliente clienteMock = mock(Cliente.class);
        when(clienteRepositoryMock.save(clienteArgumentCaptor.capture()))
                .thenReturn(clienteMock);

        clienteService.cadastrarCliente(clienteInput);

        Cliente clienteSalvo = clienteArgumentCaptor.getValue();

        verify(clienteRepositoryMock, times(1)).save(clienteSalvo);
        assertNotNull(clienteSalvo);
        assertEquals(clienteInput.getNome(), clienteSalvo.getNome());
        assertEquals(clienteInput.getCpf(), clienteSalvo.getCpf());
        assertEquals(clienteInput.getTelefone(), clienteSalvo.getTelefone());
    }

    @Test
    public void deveBuscarClientePorId() {
        long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String cpf = faker.cpf().valid();
        String telefone = faker.phoneNumber().phoneNumber();
        Cliente cliente = new Cliente(id, nome, cpf, telefone);

        when(clienteRepositoryMock.findById(id))
                .thenReturn(Optional.of(cliente));

        Cliente clienteRetornado = clienteService.buscaPorId(id);

        verify(clienteRepositoryMock, times(1)).findById(id);
        assertNotNull(clienteRetornado);
    }

    @Test
    public void deveListarTodosClientes() {
        when(clienteRepositoryMock.findAll())
                .thenReturn(List.of(mock(Cliente.class)));

        List<ClienteOutput> clienteOutputs = clienteService.listaTodosClientes();

        assertFalse(clienteOutputs.isEmpty());
    }
}