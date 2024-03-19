package org.springframework.cloud.contract.verifier.tests;

import nl.nl0e0.appointmentmicroservice.contract.BaseTest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SuppressWarnings("rawtypes")
public class ContractVerifierTest extends BaseTest {

	@Test
	public void validate_contract() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/appointment/createAppointments");

		// then:
			assertThat(response.statusCode()).isEqualTo(500);
			assertThat(response.header("Content-Type")).matches("application/json.*");
	}

	@Test
	public void validate_testContract() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"client.id\":\"2786876706\",\"loanAmount\":99999}");

		// when:
			ResponseOptions response = given().spec(request)
					.put("/fraudcheck");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['fraudCheckStatus']").isEqualTo("FRAUD");
			assertThatJson(parsedJson).field("['rejection.reason']").isEqualTo("Amount too high");
	}

}
