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
	public void validate_createAppointments() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"ownerId\":\"1\",\"petId\":\"2\",\"vetId\":\"1\",\"appointmentDate\":\"2024-03-05T21:00:00\"}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/appointment/createAppointments");

		// then:
			assertThat(response.statusCode()).isEqualTo(201);
	}

	@Test
	public void validate_getAppointmentByOwnerName() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"firstName\":\"George\",\"lastName\":\"Franklin\"}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/appointment/getAppointments");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
	}

	@Test
	public void validate_getRecordById() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.get("/appointment/medicalRecord/317498db-9841-408a-b01d-6f7d4d8da4fc");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['id']").isEqualTo("317498db-9841-408a-b01d-6f7d4d8da4fc");
			assertThatJson(parsedJson).field("['ownerId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['vetId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['state']").isEqualTo("init");
			assertThatJson(parsedJson).field("['petId']").isEqualTo(2);
			assertThatJson(parsedJson).field("['appointmentId']").isEqualTo("2056e53e-72b7-4110-9afb-b219d2907dec");
			assertThatJson(parsedJson).field("['consultationId']").isEqualTo("70c3ad0c-547e-458f-901b-76c1a9dce9be");
			assertThatJson(parsedJson).field("['paymentId']").isEqualTo("2536026b-4b7b-4d1b-86c3-72214e952a23");
			assertThatJson(parsedJson).field("['medicineId']").isEqualTo("c3f865b1-4b3c-4c0a-9bc2-7c703f2ba99e");
	}

	@Test
	public void validate_setState() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"recordId\":\"317498db-9841-408a-b01d-6f7d4d8da4fc\",\"state\":\"payment\"}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/appointment/setState");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
	}

}
