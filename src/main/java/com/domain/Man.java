package com.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "mans")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Man {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "man_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ElementCollection
    @CollectionTable(name = "dogs")
    @Column(name = "dogs")
    private List<String> dogsName;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "man",
            orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Email> emails = new ArrayList<>();
}
