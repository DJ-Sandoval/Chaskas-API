package com.api.BackChaskas.service.user;

import com.api.BackChaskas.model.entity.User;
import com.api.BackChaskas.view.dto.auth.AuthResponseDto;

public interface UserService {
    User getCurrentUser();
    AuthResponseDto getUserProfile();
    boolean isAdmin();
    boolean isEmployee();
    boolean isCustomer();
}
