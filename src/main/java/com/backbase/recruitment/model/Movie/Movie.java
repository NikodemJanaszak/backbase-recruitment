package com.backbase.recruitment.model.Movie;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String director;

    private boolean wonAward;

    private String wonYear;

    private Long BoxOffice;

    private Long votes;

    private Long summaryVoting;

    public Movie(String title, String director, boolean wonAward, String wonYear, Long boxOffice, Long votes, Long summaryVoting) {
        this.title = title;
        this.director = director;
        this.wonAward = wonAward;
        this.wonYear = wonYear;
        this.BoxOffice = boxOffice;
        this.votes = votes;
        this.summaryVoting = summaryVoting;
    }

    public Movie() {

    }
}
