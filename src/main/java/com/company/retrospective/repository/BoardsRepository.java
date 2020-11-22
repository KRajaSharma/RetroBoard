package com.company.retrospective.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.company.retrospective.model.Board;

@Repository
public interface BoardsRepository extends MongoRepository<Board, String> {

	
	
}
