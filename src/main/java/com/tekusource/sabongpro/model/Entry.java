package com.tekusource.sabongpro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="entries")
public class Entry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4340065369022318035L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="entryName")
	private String entryName;
	
	@Column(name="ownerName") 
	private String ownerName;
	
	@Column(name="side")
	private String side;
	
	@Column(name="bloodLine")
	private String bloodLine;
	
	@Column(name="fightWeight")
	private String fightWeight;
	
	@Column(name="fightNumber")
	private String fightNumber;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEntryName() {
		return entryName;
	}
	
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String getSide() {
		return side;
	}
	
	public void setSide(String side) {
		this.side = side;
	}
	
	public String getFightWeight() {
		return fightWeight;
	}
	
	public void setFightWeight(String fightWeight) {
		this.fightWeight = fightWeight;
	}
	
	public String getBloodLine() {
		return bloodLine;
	}
	
	public void setBloodLine(String bloodLine) {
		this.bloodLine = bloodLine;
	}
	
	public String getFightNumber() {
		return fightNumber;
	}
	
	public void setFightNumber(String fightNumber) {
		this.fightNumber = fightNumber;
	}
}
