package com.project.domain;

import com.project.domain.constant.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.EnumType.*;

@Entity
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccount{

    @Id
    @Column(name = "user_id")
    private String userId;

    private String userPassword;

    @Column(nullable = false)
    private String nickname;

    @Embedded
    private Address address;

    @Enumerated(STRING)
    private UserRole role; // Vendor, Consumer

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; //생성일시

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; //수정일시


    @ToString.Exclude
    @OneToMany(mappedBy = "userAccount")
    private List<Order> orders = new ArrayList<>();

    private UserAccount(String userId, String userPassword, String nickname, Address address, UserRole role) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.nickname = nickname;
        this.address = address;
        this.role = role;
    }

    public static UserAccount of(String userId, String userPassword, String nickname, Address address, UserRole role) {
        return new UserAccount(userId, userPassword, nickname, address, role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return userId != null && userId.equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
