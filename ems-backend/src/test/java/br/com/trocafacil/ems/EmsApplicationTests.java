package br.com.trocafacil.ems;

import br.com.trocafacil.ems.domain.helpers.enums.ProductCondition;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.product.Category;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import br.com.trocafacil.ems.domain.model.product.dto.ProductCreateDto;
import br.com.trocafacil.ems.domain.model.token.TokenDto;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import br.com.trocafacil.ems.domain.model.trade.dto.TradeCreateDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmsApplicationTests {

	@Autowired
	private static TestRestTemplate restTemplate;
	private static User user;
	private static TokenDto token;
	private static HttpHeaders headers;
	private static String baseUrl = "http://localhost:8050/";
	private static final int TRADE_ID = 3;

	@BeforeAll
	public static void initialize(){
		restTemplate = new TestRestTemplate();
		user = new User();
		headers = new HttpHeaders();

		user.setLogin("jhon");
		user.setPassword("1234");
		token = restTemplate.postForObject(baseUrl.concat("auth/login"), user, TokenDto.class);
		headers.add("Authorization", "Bearer " + token.token());
	}

	@Test
	public void shouldCreateALoginRF01() throws Exception{
		User user1 = new User();
		user.setLogin("jhon");
		user.setPassword("1234");
		User response = restTemplate.postForObject(baseUrl.concat("auth/register"), user, User.class);
		assertThat(response.getLogin().equals("jhon"));
	}

	@Test
	void loginShouldReturnTokenJWTRF02() throws Exception {
		System.out.println(token.token());
		assert(!token.token().isBlank());
	}

	@Test
	void shouldCreateAnAccountRF03() throws Exception {
		Address address = new Address();
		Account account = new Account();

		address.setCep("2324423");
		address.setNumero(70);

		account.setName("jhon");
		account.setSurname("ribeiro");
		account.setAddress(address);

		HttpEntity<Account> httpEntity = new HttpEntity<>(account, headers);
		Account response = restTemplate.postForObject("http://localhost:8050/account/create", httpEntity, Account.class);

		assertThat(response.getName().equals("jhon"));
	}

	//TODO: FAZER OS TESTES UNITÁRIOS PARA CREATE CATEGORY E SUBCATEGORY

	@Test
	void shouldRegisterAProductRF04() throws Exception {
		ProductCreateDto product = new ProductCreateDto(null, "Computador", 5000d, 1, ProductCondition.EXCELENTE, 1l);
		HttpEntity<ProductCreateDto> httpEntity = new HttpEntity<>(product, headers);

		Product response = restTemplate.postForObject(baseUrl.concat("product/create"), httpEntity, Product.class);
		assertThat(response.getName().equals("Computador"));
	}

	@Test
	void findAllProductsInYourAccountRF05() throws Exception {
		HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
		List<Product> products = restTemplate.exchange(baseUrl.concat("product/findall/personal"), HttpMethod.GET, entity, ArrayList.class).getBody();

		assertThat(!products.isEmpty());
	}

	@Test
	void createATradeShouldReturnTheIdTrade() throws Exception{
		TradeCreateDto tradeCreateDto = new TradeCreateDto(6l, 7l);
		HttpEntity<TradeCreateDto> httpEntity = new HttpEntity<>(tradeCreateDto, headers);
		Trade response = restTemplate.postForObject(baseUrl.concat("trade/create"), httpEntity, Trade.class);
		assertThat(response.getProductPosted().getId() == 6);
		assertThat(response.getProductProposal().getId() == 7);
	}

	@Test
	void acceptATradeShouldCloseIt() throws Exception {
		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
		Trade response = restTemplate.exchange(baseUrl.concat("trade/accept/" + TRADE_ID), HttpMethod.GET, httpEntity, Trade.class).getBody();
		assertThat(response.getStatus().equals("FECHADO"));
	}

}
