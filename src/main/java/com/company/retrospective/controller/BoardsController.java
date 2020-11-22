package com.company.retrospective.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.retrospective.model.Board;
import com.company.retrospective.model.UserDetails;
import com.company.retrospective.repository.BoardsRepository;

@RestController
@RequestMapping(path = "/v1/boards")
public class BoardsController {

	@Autowired
	private BoardsRepository boardsRepository;
	
	@PostMapping()
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Board>> getBoardsByIds(@RequestBody List<String> boardIds){
		
		System.out.println("Request recived for Get Boards by Ids "+boardIds.toString());
		List<Board> boardsList = new ArrayList<>();
		boardIds.forEach((boardId)->{boardsList.add(boardsRepository.findById(boardId).get());});
		System.out.println("Response for  Get Boards by Ids "+boardsList);
		return new ResponseEntity<>(boardsList, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{boardID}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Board> getBoardById(@PathVariable String boardID){
		System.out.println("Request recived for Get Board by Id : "+boardID);
		Board board = boardsRepository.findById(boardID).orElse(null);
		System.out.println("Response for  Get Boards by Ids :"+board);
		return new ResponseEntity<>(board, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/save")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Board> saveBoard(@RequestBody Board board){
		
		System.out.println("Request for save Boards : "+ board);
		Board savedBoard =  boardsRepository.save(board);
		System.out.println("Saved board is : "+savedBoard);
		return new ResponseEntity<>(savedBoard, HttpStatus.OK);
	}
}
