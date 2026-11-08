package com.pi.frioVale.dto;

import com.pi.frioVale.domain.User;
import com.pi.frioVale.domain.enums.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone,
        Role role
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole());
    }
}
