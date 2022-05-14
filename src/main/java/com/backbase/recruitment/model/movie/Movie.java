package com.backbase.recruitment.model.movie;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String director;

    private boolean wonAward;

    private String wonYear;

    private Long BoxOffice;

    private Long votesNumber;

    private Long votesSum;

    public Movie(String title, String director, boolean wonAward, String wonYear, Long boxOffice, Long votesNumber, Long votesSum) {
        this.title = title;
        this.director = director;
        this.wonAward = wonAward;
        this.wonYear = wonYear;
        this.BoxOffice = boxOffice;
        this.votesNumber = votesNumber;
        this.votesSum = votesSum;
    }
}