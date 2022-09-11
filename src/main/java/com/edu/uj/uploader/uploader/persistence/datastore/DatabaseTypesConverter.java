package com.edu.uj.uploader.uploader.persistence.datastore;

import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import com.edu.uj.uploader.uploader.persistence.model.DbSubscribedUser;

public class DatabaseTypesConverter {

    public DbSubscribedUser toDbSubscribedUser(SubscribedUser subscribedUser) {
        DbSubscribedUser dbSubscribedUser = new DbSubscribedUser();
        dbSubscribedUser.setSubscribed(subscribedUser.isSubscribed());
        dbSubscribedUser.setUsername(subscribedUser.getUsername());
        return dbSubscribedUser;
    }

    public SubscribedUser toSubscribedUser(DbSubscribedUser dbSubscribedUser, String username) {
        boolean isSubscribed = dbSubscribedUser != null && dbSubscribedUser.isSubscribed();
        String username1 = dbSubscribedUser != null ? dbSubscribedUser.getUsername() : username;
        return new SubscribedUser(username1, isSubscribed);
    }
}
