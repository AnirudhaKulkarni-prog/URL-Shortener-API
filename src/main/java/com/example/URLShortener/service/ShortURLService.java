package com.example.URLShortener.service;

import com.example.URLShortener.entity.ShortURL;
import com.example.URLShortener.exceptionhandling.ResourceNotFoundException;
import com.example.URLShortener.repository.ShortURLRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class ShortURLService {

    @Autowired
    ShortURLRepository shortURLRepository;

    public ShortURL createShortURL(String originalURL)
    {
        ShortURL shortURL=new ShortURL();

        shortURL.setOriginalUrl(originalURL);
        shortURL.setShortCode(UUID.randomUUID().toString().substring(0,6));
        shortURL.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shortURL.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return shortURLRepository.save(shortURL);
    }

    public ShortURL getOriginalURL(String shortCode)
    {
        ShortURL shortURL=shortURLRepository.findByShortCode(shortCode).orElseThrow(()->new EntityNotFoundException("ShortURL not found"));
        shortURL.setAccessCount(shortURL.getAccessCount()+1);
        return shortURLRepository.save(shortURL);

    }

    public ShortURL updateOriginalURL(String shortCode,String newURL)
    {
        ShortURL shortURL=shortURLRepository.findByShortCode(shortCode).orElseThrow(()->new EntityNotFoundException("ShortURL not found"));
        shortURL.setOriginalUrl(newURL);
        shortURL.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return shortURLRepository.save(shortURL);
    }

    public void deleteShortURL(String shortCode)
    {
        ShortURL shortURL=shortURLRepository.findByShortCode(shortCode).orElseThrow(()->new EntityNotFoundException("ShortURL not found"));
        shortURLRepository.delete(shortURL);
    }

    public ShortURL getStatistics(String shortCode)
    {
        return shortURLRepository.findByShortCode(shortCode).orElseThrow(()->new ResourceNotFoundException("ShortURL not found"));
    }


}
