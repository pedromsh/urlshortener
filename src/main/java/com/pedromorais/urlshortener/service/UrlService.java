package com.pedromorais.urlshortener.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pedromorais.urlshortener.entities.Url;
import com.pedromorais.urlshortener.repository.UrlRepository;
import com.pedromorais.urlshortener.utils.UrlShortenerRandom;

@Service
public class UrlService {
	
	private final UrlRepository urlRepository;
	
	public UrlService(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}
	
	public String createShortUrl(String originalUrl, Long ttlSeconds) {		
		String shortCode;
		do {
			shortCode = UrlShortenerRandom.generateShortCode(6);
		} while (urlRepository.existsById(shortCode));
		
		LocalDateTime expirationDate = null;
		if (ttlSeconds != null) {
			expirationDate = LocalDateTime.now().plusSeconds(ttlSeconds);
		}
		
		Url url = new Url(shortCode, originalUrl, expirationDate);
		urlRepository.save(url);
		
		return shortCode;
	}
	
	public String getOriginalUrl(String shortCode) {
		Url url = urlRepository.findById(shortCode).orElseThrow();
		
		if (url.getExpirationDate() != null && url.getExpirationDate().isBefore(LocalDateTime.now())) {
			urlRepository.delete(url);
			return null;
		}
		
		return url.getOriginalUrl();
	}
}
