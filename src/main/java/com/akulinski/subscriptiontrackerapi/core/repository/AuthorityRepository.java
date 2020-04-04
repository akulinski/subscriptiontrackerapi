package com.akulinski.subscriptiontrackerapi.core.repository;

import com.akulinski.subscriptiontrackerapi.core.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {}
