package ru.otus.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.dao.impl.AuthorDaoImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(AuthorDaoImpl.class)
@DisplayName("Класс для тестирования репозитория по работе с авторами")
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Получение автора по айди.")
    void getAuthorByIdTest() {
        var sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        var byIdOpt = authorDao.getById(3L);
        assertTrue(byIdOpt.isPresent());
        assertTrue(CollectionUtils.isNotEmpty(byIdOpt.get().getBooks()));
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(2);
    }
}