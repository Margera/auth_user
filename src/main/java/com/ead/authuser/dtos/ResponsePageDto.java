package com.ead.authuser.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ResponsePageDto<T> extends PageImpl<T>{

    public ResponsePageDto(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ResponsePageDto(List<T> content){
        super(content);
    }
   
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResponsePageDto(@JsonProperty("content") List<T> content,
                           @JsonProperty("number") int number,
                           @JsonProperty("size") int size,
                           @JsonProperty("totalElements") Long totalElements,
                           @JsonProperty("pageable") JsonNode pageable,
                           @JsonProperty("last") Boolean last,
                           @JsonProperty("totalPages") int totalPages,
                           @JsonProperty("sort") JsonNode sort,
                           @JsonProperty("first") Boolean first,
                           @JsonProperty("empty") Boolean empty) {
                               super(content, PageRequest.of(number, size), totalElements);
                           }
}
