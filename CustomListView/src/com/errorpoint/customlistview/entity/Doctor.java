package com.errorpoint.customlistview.entity;

import java.util.Comparator;

public class Doctor {
   
    private long id; 
	private String code;
	private String name;
	private String status;
	private String frequency;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name==null?"":name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status==null?"":status;
	}

	
	private int quantity;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/// not related to DB
	
	private String shift;
	private boolean selected;
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getShift() {
		return shift;
	}
	 
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
}
