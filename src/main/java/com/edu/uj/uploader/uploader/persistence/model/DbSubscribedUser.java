package com.edu.uj.uploader.uploader.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

import static com.edu.uj.uploader.uploader.persistence.DatabaseConstants.SCHEMA;

@Entity
@Table(name = "subscribed_users", schema = SCHEMA)
public class DbSubscribedUser {
    @Id
    @Column(name = "username", updatable = false, nullable = false)
    private String username;

    @Column(name = "subscribed", nullable = false)
    private boolean subscribed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DbSubscribedUser)) return false;

        DbSubscribedUser that = (DbSubscribedUser) o;

        if (subscribed != that.subscribed) return false;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (subscribed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DbSubscribedUser{" +
                "username='" + username + '\'' +
                ", subscribed=" + subscribed +
                '}';
    }
}
