package com.example.clientmorracinese;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class ClientMorraCineseApplicationTests {
    @Test
    void contextLoads() {
        String url = "http://localhost:8080/getUser?id=C";
        WebClient.Builder builder = WebClient.builder();
        User user = builder.build().get().uri(url).retrieve().bodyToMono(User.class).block();

        System.out.println("--------------------------------------------------------------");
        System.out.println(user);
        System.out.println("--------------------------------------------------------------");
    }

}
