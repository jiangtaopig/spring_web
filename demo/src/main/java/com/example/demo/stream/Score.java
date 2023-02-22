package com.example.demo.stream;

import lombok.Data;

@Data
public class Score {

    public Score(int score) {
        this.score = score;
    }

    private int score;
}
