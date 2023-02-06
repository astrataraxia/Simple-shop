package com.project.service;

import com.project.domain.UserAccount;
import com.project.dto.UserAccountDto;
import com.project.exception.DuplicateUser;
import com.project.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public void createUser(UserAccountDto dto) { 
            ValidateDuplicateUser(dto);
            userAccountRepository.save(dto.toEntity());
    }


    @Transactional(readOnly = true)
    public UserAccountDto findUsers(String userName) {
        UserAccount userAccount = userAccountRepository.findById(userName)
                .get();
        return UserAccountDto.from(userAccount);
    }


    private void ValidateDuplicateUser(UserAccountDto dto) {
        boolean valid = userAccountRepository.existsById(dto.userId());
        if (valid) {
            throw new DuplicateUser();
        }
    }

}
