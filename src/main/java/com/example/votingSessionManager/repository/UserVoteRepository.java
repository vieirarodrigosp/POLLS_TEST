package com.example.votingSessionManager.repository;

import com.example.votingSessionManager.dto.CountUserVoteDTO;
import com.example.votingSessionManager.entity.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVoteRepository extends JpaRepository<UserVote, Long> {
  UserVote findByPollIdAndUserId(Integer pollId, Integer userId);
  UserVote save(UserVote userVote);
  @Query(value = "SELECT DISTINCT tb_01.pollId as pollId, ( SELECT COUNT(tb_02.voteValue) FROM UserVote tb_02 WHERE tb_02.pollId = :pollId AND tb_02.voteValue = true GROUP BY tb_02.pollId, tb_02.voteValue ) AS countTrue, ( SELECT COUNT(tb_03.voteValue) FROM UserVote tb_03 WHERE tb_03.pollId = :pollId AND tb_03.voteValue = false GROUP BY tb_03.pollId, tb_03.voteValue ) AS countFalse FROM UserVote tb_01 WHERE tb_01.pollId = :pollId")
  CountUserVoteDTO voteCountById(Integer pollId);
}