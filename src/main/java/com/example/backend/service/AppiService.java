package com.example.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.backend.model.ApiExternal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class AppiService {
    
    @Autowired
    private WebClient webClient;

    private final String externalApiUrl = "https://jsonplaceholder.typicode.com/posts";


    public Mono<String> fetchDataFromExternalApi() {
        return webClient.get()
                .uri(externalApiUrl)
                .retrieve()
                .bodyToMono(String.class);
    }

        public Flux<String> fetchTitlesFromExternalApi() {
        return webClient.get()
                .uri(externalApiUrl)
                .retrieve()
                .bodyToFlux(ApiExternal.class) 
                .map(ApiExternal::getTitle);
    }


    public Mono<String> searchTitleWithWord(String word) {
       return fetchDataFromExternalApi().map(data->{
        JSONArray jsonArray = new JSONArray(data);
            StringBuilder titles = new StringBuilder();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                if (title.contains(word)) {
                    titles.append(title).append("\n");
                }
            }
            return titles.toString();

       });
    }


}
