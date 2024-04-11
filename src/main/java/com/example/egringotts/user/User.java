package com.example.egringotts.user;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Class that describes users of our system
 * @param <T>
 */
@Entity
@Table(name = "user")
public class User<T> {
    @Id
    @GeneratedValue(generator = "sequence-generator-user")
    @GenericGenerator(
            name = "sequence-generator-user",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "2"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;
    private String name;
    private String email;
    private String passwordHash;
    private String status;
    @Transient
    private T currentUser;

    // Default constructor
    public User(){
        // Don't construct user without appropriate parameters
        // Here just because Spring Boot needs it :(
    }

    // Constructor
    public User(long id, String name, String email, String passwordHash, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
    }

    // Getter and setter methods
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getCurrentUser() {
        return currentUser;
    }

    /**
     * Changes type of current user
     * @param currentUser User's new type
     */
    public void setCurrentUser(T currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Tostring methods that returns information about this object in JSON format
     * @return information about this object in JSON format
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", status=\n" + currentUser +
                '}';
    }
}
