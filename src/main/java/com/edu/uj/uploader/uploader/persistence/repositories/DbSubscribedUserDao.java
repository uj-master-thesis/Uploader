package com.edu.uj.uploader.uploader.persistence.repositories;

import com.edu.uj.uploader.uploader.persistence.model.DbSubscribedUser;
import org.springframework.stereotype.Repository;

@Repository
public class DbSubscribedUserDao {

    private final DbSubscribedRepository dbSubscribedRepository;

    public DbSubscribedUserDao(DbSubscribedRepository dbSubscribedRepository) {
        this.dbSubscribedRepository = dbSubscribedRepository;
    }

    public void setSubscribedUser(DbSubscribedUser dbSubscribedUser) {
        dbSubscribedRepository.save(dbSubscribedUser);
    }

    public DbSubscribedUser findByUsername(String username) {
        return dbSubscribedRepository.findByUsername(username);
    }
}
