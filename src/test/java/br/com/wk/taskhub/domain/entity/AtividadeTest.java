package br.com.wk.taskhub.domain.entity;

import br.com.wk.taskhub.application.service.atividade.AtividadeOutput;
import net.datafaker.Faker;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class AtividadeTest {

    private static final Faker faker = new Faker();

    @Test
    public void deveCriarAtividade() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();
        Projeto projeto = Mockito.mock(Projeto.class);

        Atividade atividade = new Atividade(id, nome, descricao, projeto);

        assertNotNull(atividade);
        assertEquals(id, atividade.getId());
        assertEquals(nome, atividade.getNome());
        assertEquals(descricao, atividade.getDescricao());
    }

    @Test
    public void deveCriarOutput() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();
        Projeto projeto = Mockito.mock(Projeto.class);

        Atividade atividade = new Atividade(id, nome, descricao, projeto);
        AtividadeOutput output = atividade.toOutput();

        assertNotNull(output);
        assertEquals(atividade.getNome(), output.getNome());
        assertEquals(atividade.getDescricao(), output.getDescricao());
    }

    @Test(expected = IllegalStateException.class)
    public void deveLancarExcecaoAtividadeComNomeNulo() {
        Long id = faker.number().numberBetween(1L, 100L);
        String descricao = faker.lorem().paragraph();
        Projeto projeto = Mockito.mock(Projeto.class);

        new Atividade(id, null, descricao, projeto);
    }

    @Test(expected = IllegalStateException.class)
    public void deveLancarExcecaoAtividadeComDescricaoNula() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        Projeto projeto = Mockito.mock(Projeto.class);

        new Atividade(id, nome, null, projeto);
    }

    @Test(expected = IllegalStateException.class)
    public void deveLancarExcecaoAtividadeComProjetoNulo() {
        Long id = faker.number().numberBetween(1L, 100L);
        String nome = faker.name().fullName();
        String descricao = faker.lorem().paragraph();

        new Atividade(id, nome, descricao, null);
    }
}