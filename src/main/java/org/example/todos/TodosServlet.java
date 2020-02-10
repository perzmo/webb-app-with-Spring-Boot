package org.example.todos;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
class TodosServlet {
    private final Logger logger = LoggerFactory.getLogger(TodosServlet.class);
    private TodosRepository repository;

    public TodosServlet(TodosRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Todos>> findAllTodos() {
        logger.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<Todos> toggleTodo(@PathVariable Integer id) {
        var todo = repository.findById(id);
        todo.ifPresent(t -> {
            t.setDone(!t.isDone());
            repository.save(t);
        });
        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Todos> saveTodo(@RequestBody Todos todos) {
        return ResponseEntity.ok(repository.save(todos));
    }
}
