package com.api.bank;

import com.api.bank.repository.BankUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest
class apibankapplicationtests {

	@Autowired
	private BankUserRepository bankUserRepository;


	@Test
	void contextLoads() { }

}
