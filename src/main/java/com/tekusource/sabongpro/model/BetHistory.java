package com.tekusource.sabongpro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bet_histories")
public class BetHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5913626103523230775L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="fightNumber")
	private String fightNumber;
	
	@Column(name="side")
	private String side;
	
	@Column(name="result")
	private String result;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName="id", insertable=true, nullable=false)
	private User user;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFightNumber() {
		return fightNumber;
	}
	
	public void setFightNumber(String fightNumber) {
		this.fightNumber = fightNumber;
	}
	
	public String getSide() {
		return side;
	}
	
	public void setSide(String side) {
		this.side = side;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
