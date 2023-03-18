package com.Intelligent_Forms.Intelligent_Forms_FCR.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    public Optional<User> findByEmailAndPassword(String email, String password);

    public User findUserByEmail(String email);


}
