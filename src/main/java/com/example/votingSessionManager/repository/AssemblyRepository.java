package com.example.votingSessionManager.repository;

import com.example.votingSessionManager.entity.Assembly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssemblyRepository extends JpaRepository<Assembly, Integer> {
  Assembly save(Assembly assembly);
  Assembly findByAssemblyId(Integer assemblyId);
}