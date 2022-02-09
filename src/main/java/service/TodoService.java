package service;

import dto.CreateTodoDto;
import dto.UpdateTodoDto;
import java.util.ArrayList;
import java.util.List;
import model.TodoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;

public class TodoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);

    private List<TodoModel> todos = new ArrayList<TodoModel>();

    public TodoModel createTodo(CreateTodoDto dto) {
        LOGGER.info("Creating todo with payload: {}", JsonUtils.toJson(dto));
        TodoModel newTodo = new TodoModel(dto.title, dto.description);
        this.todos.add(newTodo);
        return newTodo;
    }

    public Boolean deleteTodo(String todoId) {
        LOGGER.info("Deleting todo with id: {}", todoId);
        return this.todos.removeIf(t -> t.id.equals(todoId));
    }

    public TodoModel getById(String todoId) {
        LOGGER.info("Getting single todo with id: {}", todoId);
        TodoModel todo = null;
        for (TodoModel t: this.todos) {
            if (t.id.equals(todoId)) {
                todo = t;
                break;
            }
        }
        return todo;
    }

    public List<TodoModel> getAll() {
        return this.todos;
    };


    public Boolean updateTodo(String id, UpdateTodoDto dto) {
        LOGGER.info("Updating todo with id: {}, payload: {}", id, JsonUtils.toJson(dto));
        for(int i = 0; i < this.todos.size(); i++) {
            TodoModel todo = this.todos.get(i);
            if (todo.id.equals(id)) {
                todo.done = dto.done;
                this.todos.set(i, todo);
                return true;
            }
        }

        return false;
    }
}
