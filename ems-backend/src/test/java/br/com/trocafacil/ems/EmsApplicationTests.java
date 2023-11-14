package br.com.trocafacil.ems;

import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.token.TokenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void loginShouldReturnTokenJWT() throws Exception {
		User user = new User();
		user.setLogin("ronald");
		user.setPassword("1234");

		TokenDto token = this.restTemplate.postForObject("http://localhost:8050/auth/login", user, TokenDto.class);

		System.out.println(token.token());

		// Nesse caso, é para retornar true, pois com o login e senha correto, o token é gerado
		assert(!token.token().isBlank());
	}

}
