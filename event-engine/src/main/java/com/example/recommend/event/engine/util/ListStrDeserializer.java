package com.example.recommend.event.engine.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:jycx
 * @date: 2024/12/4 16:46
 */
public class ListStrDeserializer extends StdDeserializer<List<String>> {
    public ListStrDeserializer() {
        this(null);
    }

    public ListStrDeserializer(Class<String> clazz) {
        super(clazz);
    }

    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);
        List<String> list = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode ele : node) {
                list.add(mapper.readValue(ele.traverse(mapper), String.class));
            }
        } else {
            list.add(mapper.readValue(node.traverse(mapper), String.class));
        }
        return list;
    }
}