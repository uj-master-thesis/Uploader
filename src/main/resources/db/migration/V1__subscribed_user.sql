CREATE TABLE IF NOT EXISTS "subscribed_users"
(
    "username"   VARCHAR(255) NOT NULL,
    "subscribed" BOOLEAN      NOT NULL,

    CONSTRAINT "pk_subscribed_users" PRIMARY KEY ("username")
);