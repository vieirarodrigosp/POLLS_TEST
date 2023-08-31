package com.example.votingSessionManager.repository;

import com.example.votingSessionManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findByUserId(Integer userId);
}