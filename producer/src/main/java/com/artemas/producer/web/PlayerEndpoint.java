package com.artemas.producer.web;

import com.artemas.producer.model.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.*;

@RestController
public class PlayerEndpoint {
    @GetMapping(value = "/player/{id}", produces = APPLICATION_JSON_VALUE )
    public Player getPlayerById(@PathVariable Long id) {
        return new Player(id, "Lebron", "James", "Small Forward");
    }
}
