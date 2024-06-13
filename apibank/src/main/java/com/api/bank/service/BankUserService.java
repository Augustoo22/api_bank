package com.api.bank.service;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.dto.BankUserFormRegister;
import com.api.bank.entity.dto.BankUserFormResponse;
import com.api.bank.entity.dto.BankUserFormUpdate;
import com.api.bank.repository.BankUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankUserService {

    @Autowired
    private BankUserRepository userRepository;

    private BankUserModel authenticateUser(String userEmail, String userPassword) {
        return userRepository.findByEmailAndPassword(userEmail, userPassword)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + userEmail));
    }

    @Transactional
    public List<BankUserFormResponse> getAllUsers() {
        List<BankUserModel> users = userRepository.findAll();
        return BankUserFormResponse.toUserResponse(users);
    }

    @Transactional
    public BankUserFormResponse userLogin(String userEmail, String userPassword) {
        BankUserModel existingUser = authenticateUser(userEmail, userPassword);
        return BankUserFormResponse.toUserResponse(existingUser);
    }

    @Transactional
    public BankUserModel createUser(BankUserFormRegister dto) {
        BankUserModel userModel = BankUserFormRegister.toUserRequest(dto);
        return userRepository.save(userModel);
    }

    @Transactional
    public void deleteUser(String userEmail, String userPassword) {
        BankUserModel existingUser = authenticateUser(userEmail, userPassword);
        userRepository.delete(existingUser);
    }

    @Transactional
    public BankUserFormResponse updateUser(BankUserFormUpdate userFormUpdate) {
        BankUserModel existingUser = authenticateUser(userFormUpdate.userFormLogin().userEmailPixKey(), userFormUpdate.userFormLogin().userPassword());
        existingUser.setUsername(userFormUpdate.userFormRegister().username());
        existingUser.setUserCpf(userFormUpdate.userFormRegister().userCPF());
        existingUser.setUserEmailPixKey(userFormUpdate.userFormRegister().userEmailPixKey());
        existingUser.setUserPassword(userFormUpdate.userFormRegister().userPassword());
        existingUser.setUserType(userFormUpdate.userFormRegister().userType());
        BankUserModel updatedUser = userRepository.save(existingUser);
        return BankUserFormResponse.toUserResponse(updatedUser);
    }
}
