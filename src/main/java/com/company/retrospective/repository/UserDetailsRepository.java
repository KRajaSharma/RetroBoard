package com.company.retrospective.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.company.retrospective.model.UserDetails;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {

	public UserDetails findByUserId(String id);
}
