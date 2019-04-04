package com.kone.authservice.dao;

import com.kone.authservice.entity.UserT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserT, Long> {
    UserT findByUsername(String username);
}
