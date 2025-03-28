package java16.clothingstore.repo;

import java16.clothingstore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;


import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByIdException(Long id){
        return findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    default User findCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth;
    }
}
