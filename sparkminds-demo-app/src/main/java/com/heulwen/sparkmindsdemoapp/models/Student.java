package com.heulwen.sparkmindsdemoapp.models;

public class Student {
    private Long id;
    private String name;
    private Double score;

    public Student(){}

    public Student(Long id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String toFileString(){
        return String.format("%d,%s,%.1f", id, name, score);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
