package com.pedromorais.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedromorais.urlshortener.entities.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, String>{

}
