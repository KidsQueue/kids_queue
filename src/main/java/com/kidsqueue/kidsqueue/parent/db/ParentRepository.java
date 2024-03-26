package com.kidsqueue.kidsqueue.parent.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    Boolean existsByLoginId(String loginId);

    Parent findByLoginId(String loginId);

    Parent findByNameAndEmail(String name, String email);
}
