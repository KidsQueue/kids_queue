package com.kidsqueue.kidsqueue.domain.Parent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

    Parent findByLoginId(String loginId);
}
