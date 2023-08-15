package br.com.wk.taskhub.application.service.atividade;

import br.com.wk.taskhub.application.repository.AtividadeRepository;
import br.com.wk.taskhub.application.service.projeto.ProjetoService;
import br.com.wk.taskhub.domain.entity.Atividade;
import br.com.wk.taskhub.domain.entity.Projeto;
import net.datafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AtividadeServiceTest {

    private AtividadeService atividadeService;

    @Captor
    private ArgumentCaptor<Atividade> atividadeArgumentCaptor;

    @Mock
    private AtividadeRepository atividadeRepository;

    @Mock
    private ProjetoService projetoService;

    private static final Faker faker = new Faker();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        atividadeService = new AtividadeServiceImpl(atividadeRepository, projetoService);
    }

    @Test
    public void deveCadastrarAtividade() {
        long projetoId = faker.number().numberBetween(1L, 100L);
        AtividadeInput atividadeInput = AtividadeInput.builder()
                .nome(faker.lorem().word())
                .descricao(faker.lorem().sentence())
                .projetoId(projetoId)
                .build();

        when(projetoService.buscaProjetoPorId(projetoId))
                .thenReturn(mock(Projeto.class));

        atividadeService.cadastrarAtividade(atividadeInput);

        verify(atividadeRepository, times(1))
                .save(atividadeArgumentCaptor.capture());

        Atividade atividadeCadastrada = atividadeArgumentCaptor.getValue();

        assertNotNull(atividadeCadastrada);
        assertEquals(atividadeInput.getNome(), atividadeCadastrada.getNome());
        assertEquals(atividadeInput.getDescricao(), atividadeCadastrada.getDescricao());
    }

    @Test
    public void deveListarAtividadesPorProjeto() {
        long projetoId = faker.number().numberBetween(1L, 100L);

        when(atividadeRepository.findAllByProjetoId(projetoId))
                .thenReturn(List.of(mock(Atividade.class)));

        List<AtividadeOutput> atividadeOutputList = atividadeService.listarAtividadesPorProjeto(projetoId);

        verify(atividadeRepository, times(1)).findAllByProjetoId(projetoId);
        assertFalse(atividadeOutputList.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void deveFalharAoCadastrarUmaAtividadeSemNome() {
        long projetoId = faker.number().numberBetween(1L, 100L);
        AtividadeInput atividadeInput = AtividadeInput.builder()
                .descricao(faker.lorem().sentence())
                .projetoId(projetoId)
                .build();

        when(projetoService.buscaProjetoPorId(projetoId))
                .thenReturn(mock(Projeto.class));

        atividadeService.cadastrarAtividade(atividadeInput);
    }

    @Test(expected = IllegalStateException.class)
    public void deveFalharAoCadastrarUmaAtividadeSemDescricao() {
        long projetoId = faker.number().numberBetween(1L, 100L);
        AtividadeInput atividadeInput = AtividadeInput.builder()
                .nome(faker.lorem().word())
                .projetoId(projetoId)
                .build();

        when(projetoService.buscaProjetoPorId(projetoId))
                .thenReturn(mock(Projeto.class));

        atividadeService.cadastrarAtividade(atividadeInput);
    }

    @Test(expected = IllegalStateException.class)
    public void deveFalharAoCadastrarUmaAtividadeSemProjeto() {
        AtividadeInput atividadeInput = AtividadeInput.builder()
                .nome(faker.lorem().word())
                .descricao(faker.lorem().sentence())
                .build();

        atividadeService.cadastrarAtividade(atividadeInput);
    }
}