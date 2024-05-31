package com.v15k.users.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false, length = 1024)
    private String email;
    
    @Column(nullable = false, length = 250)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Phone> phones;

    @Column(name = "user_ref", length = 36)
    private UUID uuid;

    @Column(length = 36)
    private String salt;

    private LocalDateTime created;

    private LocalDateTime modified;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    private String token;

    @Column(name = "active")
    private Boolean isActive;

    private boolean isEnabled() {
        return isActive != null && isActive;
    }

    public List<Phone> getPhones() {
        if (phones == null)
            phones = new ArrayList<>();
        return phones;
    }

    public void addPhones(List<Phone> phones) {
        phones.stream().forEach(p -> p.setUser(this));
        getPhones().addAll(phones);
    }

    public UserDetails toUserDetails() {
        return new org.springframework.security.core.userdetails.User(
                email, 
                "", 
                isEnabled(), 
                true, 
                true, 
                true, 
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
