package br.com.wk.taskhub.application.service.projeto;

import br.com.wk.taskhub.application.repository.ProjetoRepository;
import br.com.wk.taskhub.application.service.cliente.ClienteService;
import br.com.wk.taskhub.domain.entity.Cliente;
import br.com.wk.taskhub.domain.entity.Projeto;
import br.com.wk.taskhub.domain.entity.ProjetoStatus;
import net.datafaker.Faker;
import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private ClienteService clienteService;

    @Captor
    private ArgumentCaptor<Projeto> projetoArgumentCaptor;

    private ProjetoService projetoService;


    private static final Faker faker = new Faker();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        projetoService = new ProjetoServiceImpl(projetoRepository, clienteService);
    }

    @Test
    public void deveCadastrarProjeto() {
        long clienteId = faker.number().numberBetween(1L, 100L);
        ProjetoInput input = ProjetoInput.builder()
                .nome(faker.name().username())
                .descricao(faker.lorem().paragraph())
                .clienteId(clienteId)
                .build();

        when(clienteService.buscaPorId(clienteId)).thenReturn(mock(Cliente.class));
        when(projetoRepository.save(projetoArgumentCaptor.capture())).thenReturn(mock(Projeto.class));

        projetoService.cadastrarProjeto(input);

        Projeto projetoSalvo = projetoArgumentCaptor.getValue();

        assertNotNull(projetoSalvo);
        assertEquals(input.getNome(), projetoSalvo.getNome());
        assertEquals(input.getDescricao(), projetoSalvo.getDescricao());
    }

    @Test(expected = IllegalStateException.class)
    public void deveFalharAoCadastrarProjetoComNomeVazio() {
        long clienteId = faker.number().numberBetween(1L, 100L);
        ProjetoInput input = ProjetoInput.builder()
                .nome(Strings.EMPTY)
                .descricao(faker.lorem().paragraph())
                .clienteId(clienteId)
                .build();

        when(clienteService.buscaPorId(clienteId)).thenReturn(mock(Cliente.class));
        projetoService.cadastrarProjeto(input);
    }


    @Test(expected = IllegalStateException.class)
    public void deveFalharAoCadastrarProjetoComDescricaoVazia() {
        long clienteId = faker.number().numberBetween(1L, 100L);
        ProjetoInput input = ProjetoInput.builder()
                .nome(faker.name().username())
                .descricao(Strings.EMPTY)
                .clienteId(clienteId)
                .build();

        when(clienteService.buscaPorId(clienteId)).thenReturn(mock(Cliente.class));
        projetoService.cadastrarProjeto(input);
    }

    @Test
    public void deveListarProjetosPorClienteEStatus() {
        long clienteId = faker.number().numberBetween(1L, 100L);

        when(projetoRepository.findAllByClienteIdAndAndStatus(clienteId, ProjetoStatus.EM_ANDAMENTO))
                .thenReturn(List.of(mock(Projeto.class)));

        List<ProjetoOutput> projetoOutputList = projetoService
                .listarProjetosPorClienteEStatus(clienteId, ProjetoStatus.EM_ANDAMENTO.name());

        assertFalse(projetoOutputList.isEmpty());
    }

    @Test
    public void deveBuscarProjetoPorId() {

        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();
        Cliente cliente = Mockito.mock(Cliente.class);
        Projeto projeto = new Projeto(id, nome, descricao, cliente);

        when(projetoRepository.findById(id))
                .thenReturn(Optional.of(projeto));


        Projeto projetoPorId = projetoService.buscaProjetoPorId(id);
        verify(projetoRepository, times(1)).findById(id);

        assertNotNull(projetoPorId);
    }

    @Test
    public void deveListarTodosProjetosCadastradosDeUmCliente() {
        long clienteId = faker.number().numberBetween(1L, 100L);

        when(projetoRepository.findAllByClienteId(clienteId))
                .thenReturn(List.of(mock(Projeto.class)));

        List<ProjetoOutput> projetoOutputList = projetoService.listarTodosPorCliente(clienteId);

        assertFalse(projetoOutputList.isEmpty());
    }
}
