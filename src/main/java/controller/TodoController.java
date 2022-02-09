package controller;

import com.google.gson.Gson;

import dto.CreateTodoDto;
import dto.UpdateTodoDto;
import model.TodoModel;
import service.TodoService;

import spark.Request;
import spark.Response;
import spark.Route;

public class TodoController {
    private TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    public Route postCreateTodo = (Request req, Response res) -> {
        Gson parser = new Gson();
        CreateTodoDto payload = parser.fromJson(req.body(), CreateTodoDto.class);
        res.status(201);
        res.type("application/json");
        return this.service.createTodo(payload);
    };

    public Route deleteTodo = (Request req, Response res) -> {
        Boolean removed = this.service.deleteTodo(req.params("id"));
        res.type("application/json");
        if (removed) {
            res.status(204);
            return "";
        }
        res.status(404);
        return "Todo not found";
    };

    public Route getTodo = (Request req, Response res) -> {
        res.type("application/json");
        TodoModel found = this.service.getById(req.params("id"));
        if (found == null) {
            res.status(404);
            return "No todo found";
        }
        res.status(200);
        return found;
    };

    public Route getTodos = (Request req, Response res) -> {
        res.type("application/json");
        return this.service.getAll();
    };

    public Route updateTodo = (Request req, Response res) -> {
        Gson parser = new Gson();
        UpdateTodoDto payload = parser.fromJson(req.body(), UpdateTodoDto.class);
        Boolean updated = this.service.updateTodo(req.params("id"), payload);
        if(updated) {
            res.status(202);
            return null;
        }
        res.status(404);
        return "Todo not found";
    };
}
