package com.ead.authuser.repositories;

import java.util.UUID;

import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID>{

    boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);

    //Falta o que aqui?

    boolean existsByCourseId(UUID courseId);

    void deleteAllByCourseId(UUID courseId);
}
