package app.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.verify.VerificationTimes.once;

import app.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserStepDefinitions {

    @Autowired
    private ClientAndServer mockServer;

    private ResponseEntity<?> response;

    private String address = "http://localhost:8080";

    @Before
    public void before() {
        mockServer.reset();
    }

    @Given("a resource for {string} endpoint via {string}")
    public void a_resource_for_endpoint_via_http(String endpoint, String http, String body) {
        mockServer
            .when(request().withMethod(http).withPath(endpoint))
            .respond(response().withHeader("Content-Type", "application/json").withBody(body));
    }

    @When("the {string} endpoint is requested")
    public void the_endpoint_is_requested(String endpoint) {
        response = new RestTemplate().getForEntity(address + endpoint, UserDto[].class);
    }

    @When("an object is requested via {string} endpoint")
    public void an_object_is_requested_via_endpoint(String endpoint) {
        response = new RestTemplate().getForEntity(address + endpoint, UserDto.class);
    }

    @When("an object is sent to {string} endpoint")
    public void an_object_is_sent_to_endpoint(String endpoint, String body) throws Exception {
        UserDto user = new ObjectMapper().readValue(body, UserDto.class);

        response = new RestTemplate().postForEntity(address + endpoint, user, UserDto.class);
    }

    @When("an object is updated via {string} endpoint")
    public void an_object_is_updated_via_endpoint(String endpoint, String body) throws Exception {
        HttpEntity<UserDto> entity = new HttpEntity<UserDto>(new ObjectMapper().readValue(body, UserDto.class));

        response = new RestTemplate().exchange(address + endpoint, HttpMethod.PUT, entity, Object.class);
    }

    @When("an object is deleted via {string} endpoint")
    public void an_object_is_deleted_via_endpoint(String endpoint) {
        response = new RestTemplate().exchange(address + endpoint, HttpMethod.DELETE, null, Object.class);
    }

    @Then("a list of users is received")
    public void a_list_of_users_is_received(String json) throws Exception {
        UserDto[] users = new ObjectMapper().readValue(json, UserDto[].class);

        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(users);
    }

    @Then("a user object is received")
    public void a_user_object_is_received(String body) throws Exception {
        UserDto user = new ObjectMapper().readValue(body, UserDto.class);

        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(user);
    }

    @Then("a successful message is received")
    public void a_successful_message_is_received() {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Then("the resource {string} was called")
    public void the_resource_was_called(String endpoint) {
        mockServer.verify(request().withPath(endpoint), once());
    }
}
