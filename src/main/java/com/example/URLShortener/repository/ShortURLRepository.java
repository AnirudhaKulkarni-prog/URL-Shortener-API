package com.example.URLShortener.repository;

import com.example.URLShortener.entity.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortURLRepository extends JpaRepository<ShortURL,Long> {
    Optional<ShortURL> findByShortCode(String shortcode);


}
