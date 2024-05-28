package com.api.bank.config;

import com.api.bank.entity.EnumUserType;
import com.api.bank.repository.BankUserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BankUserTypeRepository userTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(EnumUserType.values())
                .forEach(userType -> userTypeRepository.save(userType.get()));
    }
}