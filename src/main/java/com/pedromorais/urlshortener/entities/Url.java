package com.pedromorais.urlshortener.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "urls")
public class Url {
	@Id
	private String shortCode;
	
	@Column(nullable = false, length = 2048)
	private String originalUrl;
	
	private LocalDateTime expirationDate;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	public Url() {

	}

	public Url(String shortCode, String originalUrl, LocalDateTime expirationDate) {
		super();
		this.shortCode = shortCode;
		this.originalUrl = originalUrl;
		this.expirationDate = expirationDate;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
