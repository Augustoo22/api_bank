package com.api.bank;

import com.api.bank.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest
class apibankapplicationtests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void contextLoads() {

	}

}
