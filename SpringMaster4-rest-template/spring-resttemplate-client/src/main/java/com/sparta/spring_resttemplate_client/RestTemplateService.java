package com.sparta.spring_resttemplate_client;

import com.sparta.spring_resttemplate_client.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;
    public RestTemplateService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * 요청 받은 검색어를 Server로 RestTemplate 을 사용하여 요청
     * @param query
     * @return
     */
    public ItemDto getCallObject(String query) {
        log.warn(query);
        // UriComponentBuilder 는 Spring 에서 제공하는 클래스
        // Builder 패턴 통해 URI를 동적으로 생성하고 조작하기 위해 사용된다
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/get-call-obj")
                .queryParam("query", query)
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);

        /**
         * restTemplate 의 getForEntity 가 GET 방식으로 해당 URI의 서버에 요청하는 것
         * 첫번째 파라미터에는 URI, 두번째 파라미터에는 받은 데이터와 매핑할 클래스타입을 주면됨
         * getForEntity 함수 자체가 reponseEntity를 반환한다.
         */
        ResponseEntity<ItemDto> responseEntity = restTemplate.getForEntity(uri, ItemDto.class);

        log.info("statusCode = " + responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

    public List<ItemDto> getCallList() throws JSONException {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/get-call-list")
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        log.info("statusCode = " + responseEntity.getStatusCode());
        log.info("Body = " + responseEntity.getBody());

        return fromJSONtoItems(responseEntity.getBody());
    }

    public ItemDto postCall(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/post-call/{query}")
                .encode()
                .build()
                .expand(query)
                .toUri();
        log.info("uri = " + uri);

        User user = new User("Robbie", "1234");

        ResponseEntity<ItemDto> responseEntity = restTemplate.postForEntity(uri, user, ItemDto.class);

        log.info("statusCode = " + responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

    public List<ItemDto> exchangeCall(String token) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/exchange-call")
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);

        User user = new User("Robbie", "1234");

        RequestEntity<User> requestEntity = RequestEntity
                .post(uri)
                .header("X-Authorization", token)
                .body(user);

        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        return fromJSONtoItems(responseEntity.getBody());
    }

    public List<ItemDto> fromJSONtoItems(String responseEntity) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseEntity);
        JSONArray items  = jsonObject.getJSONArray("items");
        List<ItemDto> itemDtoList = new ArrayList<>();

        // JSONArray 로 for문 돌면서 상품 하나씩 ItemDto로 변환
        for (Object item : items) {
            ItemDto itemDto = new ItemDto((JSONObject) item);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }
}