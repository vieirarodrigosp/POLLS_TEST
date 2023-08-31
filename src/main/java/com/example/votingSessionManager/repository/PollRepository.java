package com.example.votingSessionManager.repository;

import com.example.votingSessionManager.entity.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {
  Poll findByPollId(Integer pollId);
  Poll save(Poll poll);
  Poll findByAssemblyId(Integer assemblyId);
  Page<Poll> findAll(Pageable pageable);
  @Query("SELECT u FROM Poll u WHERE u.endSession IS NULL")
  Collection<Poll> findByEndSessionIsNull();
  @Query("SELECT u FROM Poll u WHERE u.endSession IS NOT NULL")
  Collection<Poll> findByEndSessionNotIsNull();
  Poll findByAssemblyIdAndAndEndSession(Integer assemblyId, LocalDateTime localDateTime);
}