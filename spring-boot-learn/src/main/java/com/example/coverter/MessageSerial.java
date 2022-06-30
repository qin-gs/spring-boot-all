package com.example.coverter;

import com.example.bean.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class MessageSerial {

    public static class Serializer extends JsonSerializer<User> {

        @Override
        public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        }
    }

    public static class Deserializer extends JsonSerializer<User> {

        @Override
        public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        }
    }
}
