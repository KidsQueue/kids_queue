package com.kidsqueue.kidsqueue.favor.repository;

import com.kidsqueue.kidsqueue.favor.entity.FavorEntity;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FavorRepositoryTest {

	@Autowired
	private FavorRepository favorRepository;

	@Test
	public void findAllByParentIdAndIsActiveTest() {

		List<FavorEntity> list = favorRepository.findAllByParentIdAndIsActive(2L, 1);
		System.out.println("list.size() = " + list.size());
	}

}
