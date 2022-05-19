package com.example.demo.entity;

public record OutputPayload(Integer id, String name) {
    public static OutputPayload of( InputPayload inputPayload){
        return new OutputPayload(inputPayload.id() + 1, inputPayload.name() + "_001");
    }
}
