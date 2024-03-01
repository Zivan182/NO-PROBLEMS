package ru.noproblems.backend.data;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String login;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private String password;
    
    @Column(name = "is_admin")
    private Boolean isAdmin;

    @ManyToMany
    private List<Role> roles;

    @OneToMany(mappedBy = "whoAdded", cascade = CascadeType.ALL)
    private List<Task> addedTasks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikeReaction> likeReactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SolveReaction> solveReactions;
    
}
