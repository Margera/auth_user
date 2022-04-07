package com.ead.authuser.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.authuser.models.UserModel;

public interface UserService {

    List<UserModel> FindAll();

    Optional<UserModel> findById(UUID userId);

    void delete(UserModel userModel);

    void Save(UserModel userModel);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    
}
