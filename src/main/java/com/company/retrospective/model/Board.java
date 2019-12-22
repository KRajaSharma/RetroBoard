package com.company.retrospective.model;

import java.sql.Date;
import java.util.List;

public class Board {
	
	private List<String> wentWell;
	private List<String> toImprove;
	private List<String> actionItem;
	private String title;
	private String context;
	private boolean isActive = true;
	private String createdOn;
	
	public Board() {
		super();
	}

	public List<String> getWentWell() {
		return wentWell;
	}

	public void setWentWell(List<String> wentWell) {
		this.wentWell = wentWell;
	}

	public List<String> getToImprove() {
		return toImprove;
	}

	public void setToImprove(List<String> toImprove) {
		this.toImprove = toImprove;
	}

	public List<String> getActionItem() {
		return actionItem;
	}

	public void setActionItem(List<String> actionItem) {
		this.actionItem = actionItem;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Board [wentWell=" + wentWell + ", toImprove=" + toImprove + ", actionItem=" + actionItem + ", title="
				+ title + ", context=" + context + ", isActive=" + isActive + ", createdOn=" + createdOn + "]";
	}


	
	
}
