package com.example.autocomplete.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public abstract class RedisUtil<T1, T2> {
    ValueOperations<T1, T2> valOps = null;

    SetOperations<T1, T2> setOps = null;

    public abstract void saveDataAsString(T1 key, T2 data) throws JsonProcessingException;

    public abstract <T> T getDataAsString(T1 key, Class<T> classType) throws JsonProcessingException;

    public abstract void saveDataAsSet(T1 key, T2 data) throws JsonProcessingException;

    public abstract <T> ArrayList<T> getDataAsSet(T1 key, Class<T> classType) throws JsonProcessingException;
}
