package br.com.trocafacil.ems;

import br.com.trocafacil.ems.apps.main.service.AccountService;
import br.com.trocafacil.ems.domain.helpers.enums.ProductCondition;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.product.dto.ProductCreateDto;
import br.com.trocafacil.ems.domain.model.token.TokenDto;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import br.com.trocafacil.ems.domain.model.trade.dto.TradeCreateDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmsApplicationTests {

	/*@Autowired
	private static TestRestTemplate restTemplate;
	private static User user;
	private static TokenDto token;
	private static HttpHeaders headers;
	private static String baseUrl = "http://localhost:8050/";
	private static final int TRADE_ID = 1;

@BeforeAll
	public static void initialize(){
		restTemplate = new TestRestTemplate();
		user = new User();
		headers = new HttpHeaders();

		user.setLogin("ronald");
		user.setPassword("1234");
		token = restTemplate.postForObject(baseUrl.concat("auth/login"), user, TokenDto.class);
		headers.add("Authorization", "Bearer " + token.token());
	}*/

	/*@Test
	public void shouldCreateALoginRF01() throws Exception{
		User user1 = new User();
		user1.setLogin("ronald");
		user1.setPassword("1234");
		User response = restTemplate.postForObject(baseUrl.concat("auth/register"), user1, User.class);
		assertThat(response.getLogin().equals("ronald"));
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

		address.setZip("2324423");
		address.setNumber(70);

		account.setFullName("ronald");
		account.setAddress(address);
		account.setDocument("55544433311");

		HttpEntity<Account> httpEntity = new HttpEntity<>(account, headers);
		Account response = restTemplate.postForObject("http://localhost:8050/account/create", httpEntity, Account.class);
		System.out.println(response);
		assertThat(response.getFullName().equals("ronald"));
	}

	@Test
	void shouldRegisterAProductRF04() throws Exception {
		ProductCreateDto product = new ProductCreateDto(null, "Tv", 3000d, 1, ProductCondition.BOM, 1l);
		HttpEntity<ProductCreateDto> httpEntity = new HttpEntity<>(product, headers);

		Product response = restTemplate.postForObject(baseUrl.concat("product/create"), httpEntity, Product.class);
		assertThat(response.getName().equals("Tv"));
	}

	@Test
	void findAllProductsInYourAccountRF06() throws Exception {
		HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
		ArrayList<Product> products = restTemplate.exchange(baseUrl.concat("product/findall/personal"), HttpMethod.GET, entity, ArrayList.class).getBody();
		System.out.println(products);
		assertThat(!products.isEmpty());
	}

	@Test
	void createATradeShouldReturnTheIdTradeRF07() throws Exception{
		TradeCreateDto tradeCreateDto = new TradeCreateDto(1l, 2l);
		HttpEntity<TradeCreateDto> httpEntity = new HttpEntity<>(tradeCreateDto, headers);
		Trade response = restTemplate.postForObject(baseUrl.concat("trade/create"), httpEntity, Trade.class);
		assertThat(response.getProductPosted().getId() == 1);
		assertThat(response.getProductProposal().getId() == 2);
	}

	@Test
	void acceptATradeShouldCloseItRF08() throws Exception {
		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
		Trade response = restTemplate.exchange(baseUrl.concat("trade/accept/" + TRADE_ID), HttpMethod.GET, httpEntity, Trade.class).getBody();
		System.out.println(response);
		assertThat(response.getStatus().equals("FECHADO"));
	} */

}
