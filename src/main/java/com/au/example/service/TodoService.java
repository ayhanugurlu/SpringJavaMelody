package com.au.example.service;

import com.au.example.data.TodoRepository;
import com.au.example.data.model.Todo;
import com.au.example.service.dto.TodoDTO;
import com.au.example.service.exception.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  private final JdbcTemplate jdbcTemplate;

  public TodoService(TodoRepository todoRepository, JdbcTemplate jdbcTemplate) {
    this.todoRepository = todoRepository;
    this.jdbcTemplate = jdbcTemplate;
  }


  public TodoDTO save(TodoDTO todoDTO) {
    Todo todo =  todoRepository.save(Todo.builder().description(todoDTO.description()).done(todoDTO.done()).build());
    return new TodoDTO(todo.getId(), todo.getDescription(), todo.isDone());
  }

  public TodoDTO get(Long id) {
    Todo todo = todoRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found %s", id)));
    return new TodoDTO(todo.getId(), todo.getDescription(), todo.isDone());
  }

  public TodoDTO getWithJdbcTemplate(Long id) {
    return jdbcTemplate.queryForObject("SELECT * FROM todo WHERE id = ?", new Object[]{id}, (rs, rowNum) -> new TodoDTO(
      rs.getLong("id"),
      rs.getString("description"),
      rs.getBoolean("done")
    ));
  }

  public void delete(Long id) {
    todoRepository.deleteById(id);
  }

  public void update(TodoDTO todoDTO) {
    Todo todo = todoRepository.findById(todoDTO.id()).orElse(null);
    todo.setDescription(todoDTO.description());
    todo.setDone(todoDTO.done());
    todoRepository.save(todo);
  }

  public void deleteAll() {
    todoRepository.deleteAll();
  }

}
