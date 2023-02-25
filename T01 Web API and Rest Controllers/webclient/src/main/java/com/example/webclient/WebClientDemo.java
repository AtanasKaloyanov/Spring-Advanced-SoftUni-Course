package com.example.webclient;

import com.example.webclient.model.UserDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientDemo implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        WebClient client = WebClient.create("https://reqres.in/");

       UserDTO user =  client.get().uri("api/users/2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        System.out.println(user);
    }
}
