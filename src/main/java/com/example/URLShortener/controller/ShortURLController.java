package com.example.URLShortener.controller;


import com.example.URLShortener.entity.ShortURL;
import com.example.URLShortener.service.ShortURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shorten")
public class ShortURLController {

    @Autowired
    private ShortURLService shortURLService;

    @PostMapping
    public ResponseEntity<ShortURL> createShortURL(@RequestBody Map<String,String> request)
    {
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortURLService.createShortURL(request.get("url")));

    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<ShortURL> getOriginalURL(@PathVariable String shortCode)
    {
        System.out.println("Short URL");
        return ResponseEntity.ok(shortURLService.getOriginalURL(shortCode));
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<ShortURL> updateOriginalURL(@PathVariable String shortCode,@RequestBody Map<String,String> request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(shortURLService.updateOriginalURL(shortCode,request.get("url")));
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteShortURL(@PathVariable String shortCode)
    {
        shortURLService.deleteShortURL(shortCode);
        return ResponseEntity.noContent().build();


    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<ShortURL> getStatistics(@PathVariable String shortCode)
    {
        return ResponseEntity.ok(shortURLService.getStatistics(shortCode));
    }



}
