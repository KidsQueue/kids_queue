package com.kidsqueue.kidsqueue.favor.repository;

import com.kidsqueue.kidsqueue.favor.entity.FavorEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorRepository extends JpaRepository<FavorEntity, Long> {

	List<FavorEntity> findAllByParentIdAndIsActive(Long parentId, Integer isActive);

}
