package com.api.bank.service;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.dto.BankUserForm;
import com.api.bank.repository.BankUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankUserService {

    @Autowired
    private BankUserRepository userRepository;

    @Transactional
    public List<BankUserForm> getAllUsers() {
        return userRepository.findAll().stream()
                .map(BankUserForm::fromUser)
                .collect(Collectors.toList());
    }

    @Transactional
    public BankUserForm getOneUser(String userEmail, String userPassword) {
        Optional<BankUserModel> existingUser = userRepository.findByEmailAndPassword(userEmail, userPassword);
        if (existingUser.isPresent()) {
            return BankUserForm.fromUser(existingUser.get());
        } else {
            throw new NoSuchElementException("User not found with email: " + userEmail);
        }
    }

    @Transactional
    public BankUserModel createUser(BankUserForm dto) {
        return userRepository.save(dto.toUser());
    }

    @Transactional
    public String deleteUser(String userEmail, String userPassword) {
        Optional<BankUserModel> existingUser = userRepository.findByEmailAndPassword(userEmail, userPassword);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return "User deleted successfully";
        } else {
            throw new NoSuchElementException("User not found with email: " + userEmail);
        }
    }

    @Transactional
    public BankUserForm updateUser(String userEmail, String userPassword, BankUserForm userForm) {
        BankUserModel existingUser = userRepository.findByEmailAndPassword(userEmail, userPassword)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userEmail));
        existingUser.setUsername(userForm.username());
        existingUser.setUserAge(userForm.userAge());
        existingUser.setUserEmail(userForm.userEmail());
        existingUser.setUserPassword(userForm.userPassword());
        existingUser.setUserBalance(userForm.userBalance());
        existingUser.setBankUserType(userForm.userType());
        BankUserModel updatedUser = userRepository.save(existingUser);
        return BankUserForm.fromUser(updatedUser);
    }
}