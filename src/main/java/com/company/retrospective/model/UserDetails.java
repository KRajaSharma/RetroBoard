package com.company.retrospective.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserDetails {

	@Id
	private String id;
	private String userId;
	private List<String> boards;
	private String userName;
	
	public UserDetails() {
		super();
	}
	
	

	public UserDetails(String userName, List<String> boards) {
		super();
		this.userName = userName;
		this.boards = boards;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getBoards() {
		return boards;
	}

	public void setBoards(List<String> boards) {
		this.boards = boards;
	}

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", userId=" + userId + ", boards=" + boards + ", userName=" + userName + "]";
	}
	
	
}
