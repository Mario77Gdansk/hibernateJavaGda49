package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String nationality;
    private Integer age;
    @Enumerated(value = EnumType.STRING)
    private Rating rating;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

}
