package model;

import java.util.UUID;

public class TodoModel {
    public String id;
    public String title;
    public String description;
    public Boolean done;

    public TodoModel(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.done = false;
    }
}
