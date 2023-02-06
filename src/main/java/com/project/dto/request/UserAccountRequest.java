package com.project.dto.request;

import com.project.domain.Address;
import com.project.domain.constant.UserRole;
import com.project.dto.UserAccountDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserAccountRequest {

    @NotBlank(message = "Id 입력은 필수입니다")
    @Size(min = 5, max = 20, message = "아이디는 5자 이상 20자 이하로 입력해주세요.")
    String userId;

    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    @NotBlank(message = "비밀번호 입력은 필수 입니다")
    String userPassword;

    String nickname;
    String city;
    String zipcode;

    @NotBlank(message = "역할은 필수적으로 선택해야 합니다.")
    UserRole role;

    public UserAccountDto toEntity() {
        return UserAccountDto.of(
                userId,
                "{noop}"+userPassword,
                nickname,
                new Address(city,zipcode),
                role
        );
    }
}
