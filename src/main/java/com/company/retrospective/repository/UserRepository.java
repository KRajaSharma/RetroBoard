package com.company.retrospective.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.company.retrospective.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByUserNameAndPassword(String userName, String password);
	public User findByuserName(String userName);
}
