package com.edu.uj.uploader.uploader.persistence.repositories;

import com.edu.uj.uploader.uploader.persistence.model.DbSubscribedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DbSubscribedRepository extends JpaRepository<DbSubscribedUser, String> {

    DbSubscribedUser findByUsername(String username);

    @Modifying
    @Query("UPDATE DbSubscribedUser As u SET u.subscribed = :subscribed WHERE u.username = :username")
    void setSubscribeForUsername(@Param("username") String username,
                                 @Param("subscribed") boolean subscribed);
}
