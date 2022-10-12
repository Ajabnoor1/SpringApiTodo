package com.rest_api.controller;

import com.rest_api.service.Todo;
import com.rest_api.repository.TodoRepository;
import com.rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ToDoController {
//bezieht sich auf anfragen

    @Autowired //Automatisch verkabelt
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/Todo")
    public ResponseEntity<Todo> get(@RequestParam(value = "id") int id) {
   // get todo from db by id
        Optional<Todo> todoInDb = todoRepository.findById(id);
        if(todoInDb.isPresent()){
            return new ResponseEntity<Todo>(todoInDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity("No Todo found with by id " + id, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/todo/getAll")
    public ResponseEntity <Iterable<Todo>> getAll(@RequestHeader("api-secret") String secret){

        var userBySecret = userRepository.findBySecret(secret);

        if (userBySecret.isPresent()) {
            Iterable<Todo> allTodosInDb = todoRepository.findAllByUserId(userBySecret.get().getId());
        return new ResponseEntity<Iterable<Todo>>(allTodosInDb, HttpStatus.OK);
        }
        return new ResponseEntity("Invalid by api secret", HttpStatus.NOT_FOUND);


    }
    @PostMapping("/Todo")
    public ResponseEntity<Todo>create(@RequestBody Todo newTodo){
    todoRepository.save(newTodo);
        return new ResponseEntity<Todo>(newTodo, HttpStatus.OK);
    }
    @DeleteMapping("/todo/delete")
    public ResponseEntity<Todo>delete(@RequestParam(value = "id") int id){
       Optional<Todo> toDoInDb = todoRepository.findById(id);
        if(toDoInDb.isPresent()){
            todoRepository.deleteById(id);
        return new ResponseEntity("Todo deleted" , HttpStatus.OK);
        }else {
            return new ResponseEntity("ID not Excise   " + id , HttpStatus.NOT_FOUND);
        }


    }
    @PutMapping("/todo/put")
    public ResponseEntity<Todo>edit(@RequestBody Todo updateTodo){

        Optional<Todo> toDoInDb = todoRepository.findById(updateTodo.getId());

        if(toDoInDb.isPresent()){
           Todo savedTodo = todoRepository.save(updateTodo);
            return new ResponseEntity<Todo>(savedTodo , HttpStatus.OK);
        }else {
            return new ResponseEntity("Not Todo Updated   " + updateTodo.getId() , HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/todo/erledigt")

    public ResponseEntity<Todo>erledigt(@RequestParam(value = "erledigt") boolean erledigt,
                                        @RequestParam(value = "id") int id){
        Optional<Todo> toDoInDb = todoRepository.findById(id);

        if(toDoInDb.isPresent()){
            toDoInDb.get().setStimmt(erledigt);
            Todo saveTodo = todoRepository.save(toDoInDb.get());
            return new ResponseEntity<Todo>( saveTodo, HttpStatus.OK);
        }else {
            return new ResponseEntity("Not Todo Updated   " + id , HttpStatus.NOT_FOUND);
        }

    }

}

