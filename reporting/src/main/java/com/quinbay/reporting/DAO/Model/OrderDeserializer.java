package com.quinbay.reporting.DAO.Model;

import com.fasterxml.jackson.databind.*;
import org.apache.kafka.common.errors.*;
import org.apache.kafka.common.serialization.*;

public class OrderDeserializer implements Deserializer<Order> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Order deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Order.class);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing OrderDTO", e);
        }
    }

}
