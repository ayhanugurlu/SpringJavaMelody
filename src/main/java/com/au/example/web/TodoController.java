package com.au.example.web;

import com.au.example.data.model.Todo;
import com.au.example.service.TodoService;
import com.au.example.service.dto.TodoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @PostMapping("/")
  public ResponseEntity<TodoDTO> save(TodoDTO todo) {
    return ResponseEntity.ok(todoService.save(todo));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(todoService.get(id));
  }

  @GetMapping("/jdbcTemplate/{id}")
  public ResponseEntity<TodoDTO> getWithJdbcTemplate(@PathVariable Long id) {
    return ResponseEntity.ok(todoService.getWithJdbcTemplate(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    todoService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/update")
  public ResponseEntity<Void> update(TodoDTO todo) {
    todoService.update(todo);
    return ResponseEntity.ok().build();
  }
}
