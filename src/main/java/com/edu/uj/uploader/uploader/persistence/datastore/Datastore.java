package com.edu.uj.uploader.uploader.persistence.datastore;

import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import com.edu.uj.uploader.uploader.persistence.model.DbSubscribedUser;
import com.edu.uj.uploader.uploader.persistence.repositories.DbSubscribedUserDao;
import org.springframework.transaction.annotation.Transactional;

public class Datastore {
    private final DbSubscribedUserDao dbSubscribedUserDao;
    private final DatabaseTypesConverter databaseTypesConverter;

    public Datastore(DbSubscribedUserDao dbSubscribedUserDao, DatabaseTypesConverter databaseTypesConverter) {
        this.dbSubscribedUserDao = dbSubscribedUserDao;
        this.databaseTypesConverter = databaseTypesConverter;
    }

    public DatabaseTypesConverter getDatabaseTypesConverter() {
        return databaseTypesConverter;
    }

    @Transactional(timeout = 120)
    public void setSubscribedUser(DbSubscribedUser dbSubscribedUser) {
        dbSubscribedUserDao.setSubscribedUser(dbSubscribedUser);
    }

    @Transactional(timeout = 120)
    public SubscribedUser findByUsername(String username) {
        return databaseTypesConverter.toSubscribedUser(dbSubscribedUserDao.findByUsername(username), username);
    }
}
