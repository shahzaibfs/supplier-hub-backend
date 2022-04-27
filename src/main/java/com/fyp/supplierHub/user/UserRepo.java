package com.fyp.supplierHub.user;

import com.fyp.supplierHub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String username);

    @Query("SELECT COUNT(u.userName) from User u where u.userName =?1")
    int findByUsername(String username);

    boolean existsUserByUserName (String username);
}
