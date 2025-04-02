package no.klp.intervju.teknisk.oppgave.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "60000")
class UserInfoResourceIT {

	private static final String TEST_DATA_PATH = "src/test/resources/";
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateUser() {

		var expectedOutput = getContentFromFile("/testdata/createUserInfo/expectedResponse.json");
		var request = getContentFromFile("/testdata/createUserInfo/request.json");

		EntityExchangeResult<String> response = restPOST(webTestClient, "/api/user", request);

		assertThat(response.getStatus())
				.overridingErrorMessage(response.getResponseBody())
				.isEqualTo(HttpStatus.OK);
		assertThat(response.getResponseBody()).isEqualTo(expectedOutput);

	}

	private static String getContentFromFile(String path) {
		try {
			File file = new File(TEST_DATA_PATH + path);
			JsonNode jsonNode = objectMapper.readTree(file);
			return objectMapper.writeValueAsString(jsonNode);
		} catch (IOException ioException) {
			throw new RuntimeException(String.format("Not able to read / parse JSON content from file by given path: '%s'", path), ioException);
		}
	}

	protected EntityExchangeResult<String> restPOST(WebTestClient webTestClient, String uri, String requestBody) {
		return webTestClient
				.post()
				.uri(uri)
				.header(HttpHeaders.AUTHORIZATION, "Bearer token")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(requestBody), String.class)
				.exchange()
				.expectBody(String.class)
				.returnResult();
	}
}
