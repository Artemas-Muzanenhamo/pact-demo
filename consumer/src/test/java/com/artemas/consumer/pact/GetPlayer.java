package com.artemas.consumer.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import com.google.common.collect.ImmutableMap;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static java.lang.String.format;
import static org.apache.http.client.fluent.Request.Get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.OK;

@PactFolder("../pacts")
@PactTestFor(providerName = "provider")
@ExtendWith(PactConsumerTestExt.class)
class GetPlayer {

    private static final long PLAYER_ID = 1234L;

    @Pact(provider = "provider", consumer = "consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .uponReceiving("A Request to get an NBA player's information given the player id")
                    .path("/player")
                    .query(format("id=%s", PLAYER_ID))
                    .method("GET")
                .willRespondWith()
                    .headers(ImmutableMap.of(CONTENT_TYPE, "application/json;charset=UTF-8"))
                    .status(OK.value())
                    .body(playerDetails())
                .toPact();
    }

    @Test
    void test(MockServer mockServer) throws IOException {
        HttpResponse response = Get(mockServer.getUrl() + "/player?id=" + PLAYER_ID).execute().returnResponse();

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(OK.value());
    }

    private DslPart playerDetails() {
        return new PactDslJsonBody()
                .numberType("id", PLAYER_ID)
                .stringType("playerName", "Kobe")
                .stringType("playerSurname", "Bryant");
    }
}
