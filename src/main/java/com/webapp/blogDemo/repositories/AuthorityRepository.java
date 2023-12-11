package com.webapp.blogDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.blogDemo.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String>{

}