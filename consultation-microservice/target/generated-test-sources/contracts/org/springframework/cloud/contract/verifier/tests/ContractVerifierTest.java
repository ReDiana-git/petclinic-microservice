package org.springframework.cloud.contract.verifier.tests;

import nl.nl0e0.consultationmicroservice.contract.BaseTest;
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
	public void validate_deleteConsultation() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.post("/appointment/deleteConsultation");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
	}

	@Test
	public void validate_contract() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()

					.body("12345 ");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/appointment/createConsultation");

		// then:
			assertThat(response.statusCode()).isEqualTo(201);
	}

	@Test
	public void validate_updateConsultation() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"recordId\":\"317498db-9841-408a-b01d-6f7d4d8da4fc\",\"medicines\":\"Aspirin\",\"symptom\":\"cough\"}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/appointment/updateConsultation");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
	}

}
