package com.kidsqueue.kidsqueue;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.db.ParentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Test
    public void testFindByLoginId() {
        String testLoginId = "gyu"; // 테스트에 사용할 loginId

        // 해당 loginId가 존재하는지 확인
        boolean existsByLoginId = parentRepository.existsByLoginId(testLoginId);
        assertNotNull(existsByLoginId, "existsByLoginId should not be null");

        if (existsByLoginId) {
            // 해당 loginId에 해당하는 Parent 엔터티 조회
            Parent parentEntity = parentRepository.findByLoginId(testLoginId);
            assertNotNull(parentEntity, "Parent entity should not be null");

            // Parent 엔터티 출력 또는 로깅
            System.out.println("Parent entity: " + parentEntity);
        } else {
            System.out.println("Parent entity not found for loginId: " + testLoginId);
        }
    }
}
