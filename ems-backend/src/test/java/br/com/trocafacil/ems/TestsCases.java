package br.com.trocafacil.ems;

import br.com.trocafacil.ems.apps.main.service.AccountService;
import br.com.trocafacil.ems.apps.main.service.UserService;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import br.com.trocafacil.ems.domain.model.account.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestsCases {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;


	@Test
	public void documentRule1Invalid() throws Exception{
		User user1 = new User("ronald@gmail.com", "passwordEcrypt123");
		Address address = new Address(null, "04843210", 70, "São Paulo", "São Paulo", null);
		Account account = new Account(null, "Ronald Garcia", "ronald_amorim", "11983702899", user1, address, "", null);
		account.setDocument("5231357987");

		boolean validDocument = accountService.saveEntity(account);
		System.out.println("CPF inserido: " + account.getDocument());
		System.out.println("Tamanho CPF: " + account.getDocument().length());
		System.out.println("CPF é valido? " + validDocument);
		assertThat(validDocument).isFalse();
	}

	@Test
	public void documentRule2Invalid() throws Exception{
		User user1 = new User("ronald@gmail.com", "passwordEcrypt123");
		Address address = new Address(null, "04843210", 70, "São Paulo", "São Paulo", null);
		Account account = new Account(null, "Ronald Garcia", "ronald_amorim", "11983702899", user1, address, "", "");
		account.setDocument("523135798755");

		boolean validDocument = accountService.saveEntity(account);
		System.out.println("CPF inserido: " + account.getDocument());
		System.out.println("Tamanho CPF: " + account.getDocument().length());
		System.out.println("CPF é valido? " + validDocument);
		assertThat(validDocument).isFalse();
	}

	@Test
	public void documentRule3Invalid() throws Exception{
		User user1 = new User("ronald@gmail.com", "passwordEcrypt123");
		Address address = new Address(null, "04843210", 70, "São Paulo", "São Paulo", null);
		Account account = new Account(null, "Ronald Garcia", "ronald_amorim", "11983702899", user1, address, "", "");
		account.setDocument("52313579811");

		boolean validDocument = accountService.saveEntity(account);
		System.out.println("CPF inserido: " + account.getDocument());
		System.out.println("Tamanho CPF: " + account.getDocument().length());
		System.out.println("CPF é valido? " + validDocument);
		assertThat(validDocument).isFalse();
	}

	@Test
	public void documentRule4Valid() throws Exception{
		User user1 = new User("ronald@gmail.com", "passwordEcrypt123");
		Address address = new Address(null, "04843210", 70, "São Paulo", "São Paulo", null);
		Account account = new Account(null, "Ronald Garcia", "ronald_amorim", "11983702899", user1, address, "", "");
		account.setDocument("52313579875");

		boolean validDocument = accountService.saveEntity(account);
		System.out.println("CPF inserido: " + account.getDocument());
		System.out.println("Tamanho CPF: " + account.getDocument().length());
		System.out.println("CPF é valido? " + validDocument);
		assertThat(validDocument).isTrue();
	}

	@Test
	public void emailRule1Valid() throws Exception{
		User user1 = new User("ronald@gmail.com", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Email inserido: " + user1.getLogin());
		System.out.println("Email é válido? " + valid);
		assertThat(valid).isTrue();
	}


	@Test
	public void emailRule2Invalid() throws Exception{
		User user1 = new User("ron@gmail", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Email inserido: " + user1.getLogin());
		System.out.println("Email é válido? " + valid);
		assertThat(valid).isFalse();
	}

	@Test
	public void emailRule3Invalid() throws Exception{
		User user1 = new User("ron@gmail.com", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Email inserido: " + user1.getLogin());
		System.out.println("Email é válido? " + valid);
		assertThat(valid).isFalse();
	}

	@Test
	public void emailRule4Invalid() throws Exception{
		User user1 = new User("ronald@gmail", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Email inserido: " + user1.getLogin());
		System.out.println("Email é válido? " + valid);
		assertThat(valid).isFalse();
	}

	@Test
	public void emailRule5Invalid() throws Exception{
		User user1 = new User("ronaldgmail.com", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Email inserido: " + user1.getLogin());
		System.out.println("Email é válido? " + valid);
		assertThat(valid).isFalse();
	}
	@Test
	public void emailRule6Invalid() throws Exception{
		User user1 = new User("rongmail", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Email inserido: " + user1.getLogin());
		System.out.println("Email é válido? " + valid);
		assertThat(valid).isFalse();
	}

	@Test
	public void emailRule7Invalid() throws Exception{
		User user1 = new User("ro", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Email inserido: " + user1.getLogin());
		System.out.println("Email é válido? " + valid);
		assertThat(valid).isFalse();
	}

	@Test
	public void emailRule8Invalid() throws Exception{
		User user1 = new User("ro@gmail.com", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Email inserido: " + user1.getLogin());
		System.out.println("Email é válido? " + valid);
		assertThat(valid).isFalse();
	}

	@Test
	public void passwordlRule1Valid() throws Exception{
		User user1 = new User("ro@gmail.com", "passwordEcrypt123!");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Senha inserida: " + user1.getPassword());
		System.out.println("Senha é válido? " + valid);
		assertThat(valid).isTrue();
	}

	@Test
	public void passwordlRule2Invalid() throws Exception{
		User user1 = new User("ro@gmail.com", "passwordEcrypt123");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Senha inserida: " + user1.getPassword());
		System.out.println("Senha é válido? " + valid);
		assertThat(valid).isFalse();
	}

	@Test
	public void passwordlRule3Invalid() throws Exception{
		User user1 = new User("ro@gmail.com", "passw123!@");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Senha inserida: " + user1.getPassword());
		System.out.println("Senha é válido? " + valid);
		assertThat(valid).isFalse();
	}

	@Test
	public void passwordlRule4Invalid() throws Exception{
		User user1 = new User("ro@gmail.com", "");

		boolean valid = userService.saveEntity(user1);
		System.out.println("Senha inserida: " + user1.getPassword());
		System.out.println("Senha é válido? " + valid);
		assertThat(valid).isFalse();
	}


}
