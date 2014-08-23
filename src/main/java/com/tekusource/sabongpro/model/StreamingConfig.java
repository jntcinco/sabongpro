package com.tekusource.sabongpro.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="live_streaming")
public class StreamingConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9102360003898274783L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="url")
	private String url;
	
	@Column(name="dateCreated")
	private Calendar dateCreated;
	
	@Column(name="dateLastUpdated")
	private Calendar dateLastUpdated;
	
	@Column(name="isStreamOnline", columnDefinition = "TINYINT(1)")
	private boolean isStreamOnline;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Calendar getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Calendar getDateLastUpdated() {
		return dateLastUpdated;
	}
	
	public void setDateLastUpdated(Calendar dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}

	public boolean isStreamOnline() {
		return isStreamOnline;
	}

	public void setStreamOnline(boolean isStreamOnline) {
		this.isStreamOnline = isStreamOnline;
	}
}
