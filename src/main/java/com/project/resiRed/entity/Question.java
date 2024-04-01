package com.project.resiRed.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    private String description;
    @ManyToOne
    @JoinColumn(name="survey_id")
    private Survey survey;
    @OneToMany(mappedBy = "question")
    private List<Choice> choices;
}