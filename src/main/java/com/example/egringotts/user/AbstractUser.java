package com.example.egringotts.user;

public abstract class AbstractUser {
    @Override
    public String toString() {
        return "AbstractUser{}";
    }

    // Gettype of child classes should not return empty string. Let's hope it works
    public abstract String getType();
}
