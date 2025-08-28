package com.pedromorais.urlshortener.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedromorais.urlshortener.controller.dto.ShortenUrlRequest;
import com.pedromorais.urlshortener.controller.dto.ShortenUrlResponse;
import com.pedromorais.urlshortener.service.UrlService;

@RestController
public class UrlController {
	private final UrlService urlService;
	
	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}
	
	@PostMapping(value = "/shorten-url")
	public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest shortenUrlRequest) {
		String shortCode = urlService.createShortUrl(shortenUrlRequest.url(), shortenUrlRequest.ttlSeconds());
		
		String redirectUrl = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/{id}")
				.buildAndExpand(shortCode)
				.toUriString();
		
		return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Void> redirect(@PathVariable String id) {
		String originalUrl = urlService.getOriginalUrl(id);
		
		if (originalUrl == null) {
	        return ResponseEntity.notFound().build();
	    }
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(originalUrl));
		
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}
}
