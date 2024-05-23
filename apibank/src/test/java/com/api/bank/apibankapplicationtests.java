package com.api.bank;

import com.api.bank.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
class apibankapplicationtests {

	@MockBean
	BankRepository bankRepository;

	@Test
	void contextLoads() { }

}
