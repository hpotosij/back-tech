package com.example.backend.controller;

import java.util.List;
import java.util.stream.Collector;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.AppiService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;



@RestController
@RequestMapping("/appi")
public class AppiController {

    @Autowired
    private AppiService appiService;

    @GetMapping("ExternalApi")
    public Mono<String> fetchDataFromExternalApi() {
        return appiService.fetchDataFromExternalApi();
    }

    @GetMapping("title")
    public Mono<String> fetchTitlesFromExternalApi() {
        return appiService.fetchTitlesFromExternalApi().collect(Collectors.joining("\n"));
    }
    

    @GetMapping("/titlesContaining")
    public Mono<String> getTitlesContainingWord(@RequestParam String word) {
        return appiService.searchTitleWithWord(word);
    }
    
    

}
