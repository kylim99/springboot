import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void  main(String args[]) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setAge(10);
        user.setName("홍길동");

        Car car1 = new Car();
        car1.setName("k5");
        car1.setCarNumber("11가1111");
        car1.setType("sedan");


        Car car2 = new Car();
        car2.setName("q5");
        car2.setCarNumber("22가2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1,car2);

        user.setCars(carList);

        System.out.println(user.toString());

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println(_name);
        System.out.println(_age);

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars;

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name","steve");
        objectNode.put("age",20);

        System.out.println(objectNode.toPrettyString());










    }
}
