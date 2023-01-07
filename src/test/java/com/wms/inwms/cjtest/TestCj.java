package com.wms.inwms.cjtest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wms.inwms.domain.cj.Hello;
import com.wms.inwms.domain.cj.QHello;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import static org.assertj.core.api.Assertions.*;

@Commit
@Transactional
@SpringBootTest
public class TestCj {

    @Autowired
    EntityManager em;

    @Test
    public void test() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        Hello hello = new Hello();
        em.persist(hello);

        QHello qHello = new QHello("h");

        Hello result = query
                .selectFrom(qHello)
                .fetchOne();

        assertThat(result).isEqualTo(hello);
        assertThat(result.getId()).isEqualTo(hello.getId());

    }
}
