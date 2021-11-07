package app.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import app.dto.UserDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserStepDefinitions {

    @Autowired
    private ClientAndServer mockServer;

    private ResponseEntity<UserDto> response;

    @Given("an external datasource")
    public void an_external_datasource() {
        mockServer
            .when(request().withMethod("GET").withPath("/user/1"), Times.exactly(1))
            .respond(response().withHeader("Content-Type", "application/json").withBody("{}"));
    }

    @When("I request {string} endpoint")
    public void i_request_endpoint(String string) {
        response = new RestTemplate().getForEntity("http://localhost:8080/user/1", UserDto.class);
    }

    @Then("I should see a list of users")
    public void i_should_see_a_list_of_users() {
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(new UserDto());
    }
}
