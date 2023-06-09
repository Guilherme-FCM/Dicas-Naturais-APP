package com.example.dicasnaturais.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tip {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private String category;

    public Tip() {}

    public Tip(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
