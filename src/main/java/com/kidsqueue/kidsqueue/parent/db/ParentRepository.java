package com.kidsqueue.kidsqueue.parent.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

    Boolean existsByLoginId(String loginId);

    Parent findByLoginId(String loginId);
}
