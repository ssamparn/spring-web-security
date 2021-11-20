package com.spring.basicwebsecurity.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 2487045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    @NotEmpty
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @NotEmpty
    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "date_creation", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_AUTHORITY",
               joinColumns = { @JoinColumn(name = "user_id") },
               inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<AuthorisationEntity> authorisationEntities = new HashSet<>();

}
