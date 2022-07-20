package com.example.autocomplete.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

@Component
public class RedisUtilStrObj extends RedisUtil<String, Object> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public RedisUtilStrObj(RedisTemplate<String, Object> redisTemplate) {
        valOps = redisTemplate.opsForValue();
        setOps = redisTemplate.opsForSet();
    }

    @Override
    public void saveDataAsString(String key, Object data) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(data);
        valOps.set(key, value);
    }

    @Override
    public <T> T getDataAsString(String key, Class<T> classType) throws JsonProcessingException {
        String value = (String) valOps.get(key);
        return objectMapper.readValue(value, classType);
    }

    @Override
    public void saveDataAsSet(String key, Object data) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(data);
        setOps.add(key, value);
    }

    @Override
    public <T> ArrayList<T> getDataAsSet(String key, Class<T> classType) throws JsonProcessingException {
        Set<Object> value = setOps.members(key);
        ArrayList<T> ret = new ArrayList<>();
        if (value == null) {
            return ret;
        }
        for (Object item : value) {
            ret.add(objectMapper.readValue((String) item, classType));
        }
        return ret;
    }
}
