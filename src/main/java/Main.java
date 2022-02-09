import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import controller.TodoController;
import java.util.Date;
import service.TodoService;

public class Main {
    public static void main(String[] args) {
        port(4000);

        get("/hello", (req, res) -> "Hello World");
        get("/now", (req, res) -> {
            Date now = new Date();
            return now.toString();
        });

        TodoService todoService = new TodoService();
        TodoController todoController = new TodoController(todoService);
        Gson parser = new Gson();
        get("/todos", todoController.getTodos, parser::toJson);
        post("/todos", todoController.postCreateTodo, parser::toJson);
        get("/todos/:id", todoController.getTodo, parser::toJson);
        delete("/todos/:id", todoController.deleteTodo, parser::toJson);
        put("/todos/:id", todoController.updateTodo, parser::toJson);
    }
}