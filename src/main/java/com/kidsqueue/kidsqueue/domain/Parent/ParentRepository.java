package com.kidsqueue.kidsqueue.domain.Parent;
import java.com.kidsqueue.kidsqueue.domain.Parent.Parent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

     Parent findByUsername(String login_id);
}
