package com.sponsrnet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firebaseUid;

    private String name;

    @Column(unique = true)
    private String email;

    private String photoUrl;

    private String role;

@Pattern(
    regexp = "(^$|\\d{10})",
    message = "Phone number must contain exactly 10 digits"
)
private String phoneNumber;

    public User() {
    }

    public User(Long id, String firebaseUid, String name,
                String email, String photoUrl, String role) {
        this.id = id;
        this.firebaseUid = firebaseUid;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
    return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}
}