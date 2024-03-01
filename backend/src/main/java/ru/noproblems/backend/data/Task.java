package ru.noproblems.backend.data;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String condition;

    @Column(columnDefinition = "TEXT")
    private String solution;

    @ManyToOne(optional = false)
    private Topic topic;
    @ManyToOne
    private Olympiad olympiad;

    private Long complexity;
    private Long year;
    private Long grade;

    private String author;
    
    @ManyToOne(optional = false)
    private User whoAdded;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<LikeReaction> likeReactions;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<SolveReaction> solveReactions;
}

