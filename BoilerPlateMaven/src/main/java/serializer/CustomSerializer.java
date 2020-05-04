package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pojo.CustomObject;

import java.util.Map;

@Slf4j
public class CustomSerializer implements org.apache.kafka.common.serialization.Serializer<CustomObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, CustomObject data) {
        byte[] returns = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            returns = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            log.error("Serialization error " + e);
        }
        return returns;
    }

    @Override
    public void close() {

    }
}
