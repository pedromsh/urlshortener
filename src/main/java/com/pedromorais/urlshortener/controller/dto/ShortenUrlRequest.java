package com.pedromorais.urlshortener.controller.dto;

public record ShortenUrlRequest(String url, Long ttlSeconds) {

}
