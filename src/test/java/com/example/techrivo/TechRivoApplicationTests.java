package com.example.techrivo;

import com.example.techrivo.controller.TransformationController;
import com.example.techrivo.model.Transformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TechRivoApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    TransformationController transformationController;

    @Test
    void contextLoads() {
    }

    @Test
    public void transformationShouldReturnTransformation() {
        Transformation transformation = Transformation
                .builder()
                .name("post request")
                .items(List.of("orange", "apple", "banana"))
                .build();


        ResponseEntity<Transformation> responseEntity = restTemplate
                .postForEntity("http://localhost:" + port + "/transformation",
                        transformation, Transformation.class);

        assertThat(responseEntity.getBody()).isInstanceOf(Transformation.class);
    }

    @Test
    public void transformationIncludingNumberShouldReturnBadRequest() {

        String transformation = "{\"name\"=\"name1\", \"items\"=[\"item1\", \"item2\"]}";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(transformation, headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://localhost:" + port + "/transformation",
                        HttpMethod.POST, entity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void transformationIncludingDuplicateShouldReturnUnique() {
        Transformation transformation = Transformation
                .builder()
                .name("post request")
                .items(List.of("orange", "orange", "apple", "banana"))
                .build();

        ResponseEntity<Transformation> responseEntity = restTemplate
                .postForEntity("http://localhost:" + port + "/transformation",
                        transformation, Transformation.class);

        assertThat(responseEntity.getBody().getItems()
                .stream()
                .filter(s -> s.equals("orange"))
                .count()
        ).isEqualTo(1);
    }


    @Test
    public void transformationSnakeShouldReturnCamelCase() {
        Transformation transformation = Transformation
                .builder()
                .name("post request")
                .items(List.of("orange_blue", "orange_red", "apple", "banana"))
                .build();

        ResponseEntity<Transformation> responseEntity = restTemplate
                .postForEntity("http://localhost:" + port + "/transformation",
                        transformation, Transformation.class);

        assertThat(responseEntity.getBody().getItems()
                .stream()
                .filter(s -> s.equals("orangeBlue"))
                .count()
        ).isEqualTo(1);
    }



}
