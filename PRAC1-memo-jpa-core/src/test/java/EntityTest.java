import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class EntityTest {

//     Java환경에서?
//     EntityManagerFactory emf;
//     EntityManager em;
//    @BeforeEach
//    void setUp() {
//        emf = Persistence.createEntityManagerFactory("memo");
//        em = emf.createEntityManager();
//    }

    @PersistenceContext // Entity Manager 주입받고 싶을때. Autowired 써놓고 주입받는듯이
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
    @DisplayName("메모 생성 성공")
    void test1() {
        com.sparta.memo.entity.Memo memo = new com.sparta.memo.entity.Memo();
        memo.setUsername("Robbert");
        memo.setContents("@Transactional 테스트 중!");

        em.persist(memo);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }

    @Test
    @DisplayName("메모 생성 실패")
    void test2() {
        com.sparta.memo.entity.Memo memo = new com.sparta.memo.entity.Memo();
        memo.setUsername("Robbie");
        memo.setContents("@Transactional 테스트 중!");

        em.persist(memo);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }
}