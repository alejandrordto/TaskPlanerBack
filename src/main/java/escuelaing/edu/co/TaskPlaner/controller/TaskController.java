    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.TaskPlaner.controller;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import escuelaing.edu.co.TaskPlaner.model.Task;
import escuelaing.edu.co.TaskPlaner.model.User;
import escuelaing.edu.co.TaskPlaner.services.TaskService;
import escuelaing.edu.co.TaskPlaner.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author Alejandro Rodriguez
 */
@Service
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/Task")
public class TaskController {
    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @RequestMapping(path="/addtask", method = RequestMethod.PUT)
    public ResponseEntity<?> addTask(@RequestBody Task task, String userId){
        try {
            taskService.assignedTaskToUser(task,userId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NumberFormatException ex){
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("An error has ocurred",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/gettasks", method = RequestMethod.GET)
    public ResponseEntity<?> getTasks(String userid){
        try {
            return new ResponseEntity<>(taskService.getTasksByUserId(userid), HttpStatus.ACCEPTED);
        } catch (NumberFormatException ex){
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("An error has ocurred",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/updatetask", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTasks(@RequestBody Task task){
        taskService.updateTask(task);
        return getResponseEntity();
    }

    private ResponseEntity<?> getResponseEntity() {
        try {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NumberFormatException ex){
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("An error has ocurred",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/updateuser", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTasks(@RequestBody User user){
        userService.updateUser(user);
        return getResponseEntity();
    }
}