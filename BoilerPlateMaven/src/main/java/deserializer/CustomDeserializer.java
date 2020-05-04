package deserializer;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.CustomObject;

@Slf4j
public class CustomDeserializer implements Deserializer<CustomObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // TODO
    }

    @Override
    public CustomObject deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        CustomObject object = null;
        try {
            object = mapper.readValue(data, CustomObject.class);
        } catch (Exception e) {
            log.error("Error in deserialization process " + e);
        }
        return object;
    }

    @Override
    public void close() {
        // TODO
    }
}
