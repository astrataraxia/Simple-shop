package com.project.dto.security;

import com.project.domain.Address;
import com.project.domain.constant.UserRole;
import com.project.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.project.domain.constant.UserRole.*;

public record ShopPrincipal(
        String username,
        String password,
        String nickname,
        Address address,
        UserRole role
) implements UserDetails {

    public static ShopPrincipal of(String username, String password, String nickname, Address address, UserRole role) {
        return new ShopPrincipal(username, password, nickname, address, role);
    }

    public static ShopPrincipal from(UserAccountDto dto) {
        return ShopPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.nickname(),
                dto.address(),
                dto.role()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                username,
                password,
                nickname,
                address,
                role
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(role == VENDOR){
            authorities.add(new SimpleGrantedAuthority(VENDOR.getValue()));
        }  else if (role == CONSUMER) {
            authorities.add(new SimpleGrantedAuthority(CONSUMER.getValue()));
        } else if (role == ADMIN) {
            authorities.add(new SimpleGrantedAuthority(ADMIN.getValue()));
        }
        return authorities;
    }

    @Override public String getPassword() {return password;}
    @Override public String getUsername() {return username;}
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

}
