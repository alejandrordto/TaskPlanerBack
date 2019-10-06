/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.TaskPlaner.controller;
import escuelaing.edu.co.TaskPlaner.model.Task;
import escuelaing.edu.co.TaskPlaner.model.User;
import escuelaing.edu.co.TaskPlaner.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alejandro Rodriguez
 */
@RestController
@RequestMapping(value = "/TaskPlanner")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/Tasks")
    public ResponseEntity<?> getTaskList(){
        return new ResponseEntity<>(taskService.geTasksList(), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable(name = "taskId") String taskId){
        try {
            return new ResponseEntity<>(taskService.getTaskById(taskId),HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error task no encontrado",HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable(name = "taskId") String taskId){
        try {
            taskService.removeTask(taskId);
            return new ResponseEntity<>("Task eliminado",HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error usuario no encontrado",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping ("/{userId}")
    public ResponseEntity<?> getTaskByUserId(@PathVariable(name = "userId") String userId){
        try {
            taskService.getTasksByUserId(userId);
            return new ResponseEntity<>(taskService.getTasksByUserId(userId),HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error usuario no encontrado",HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        try {
            return new ResponseEntity<>(taskService.updateTask(task.getId(),task.getResponsible(),task.getStatus(),task.getDueDate()), HttpStatus.ACCEPTED);
        } catch (Exception ex) {

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> assignedTaskToUser(@PathVariable(name = "taskId") String taskId,@RequestBody User user){
        try {
            return new ResponseEntity<>(taskService.assignedTaskToUser(taskId,user), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}