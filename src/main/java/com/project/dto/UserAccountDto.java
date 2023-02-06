package com.project.dto;

import com.project.domain.Address;
import com.project.domain.UserAccount;
import com.project.domain.constant.UserRole;

import java.time.LocalDateTime;

public record UserAccountDto(
        String userId,
        String userPassword,
        String nickname,
        Address address,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {


    public static UserAccountDto of(String userId, String userPassword, String nickname, Address address
            , UserRole role) {
        return new UserAccountDto(userId, userPassword, nickname, address, role, null,null);
    }


    public static UserAccountDto of(String userId, String userPassword, String nickname, Address address
                                 , UserRole role, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new UserAccountDto(userId, userPassword, nickname, address, role, createdAt,modifiedAt);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getNickname(),
                entity.getAddress(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                nickname,
                address,
                role
        );
    }
}
