package com.edu.uj.uploader.uploader.persistence.datastore;

import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import com.edu.uj.uploader.uploader.persistence.model.DbSubscribedUser;

public interface Datastore {

    void setSubscribedUser(DbSubscribedUser dbSubscribedUser);

    SubscribedUser findByUsername(String username);
}
