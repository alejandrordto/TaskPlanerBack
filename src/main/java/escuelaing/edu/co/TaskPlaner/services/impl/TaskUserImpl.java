/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.TaskPlaner.services.impl;
import escuelaing.edu.co.TaskPlaner.model.Task;
import escuelaing.edu.co.TaskPlaner.model.User;
import escuelaing.edu.co.TaskPlaner.services.TaskService;
import escuelaing.edu.co.TaskPlaner.services.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Alejandro Rodriguez
 */
@Service
public class TaskUserImpl implements TaskService{
     @Autowired
    UserService userService;
    HashMap <String, Task> taskMap = new HashMap<>();

    @Override
    public List<Task> geTasksList() {
        List<Task> tasks = new ArrayList<Task>(taskMap.values());
        return tasks;
    }

    @Override
    public Task getTaskById(String id) {
        Task task = taskMap.get(id);
        return task;
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        List<Task> tasks = geTasksList();
        List<Task> userTasks = new ArrayList<Task>();
        for (int i=0; i<tasks.size();i++){
            if(tasks.get(i).getResponsible().getId().equals(userId)){
                userTasks.add(tasks.get(i));
            }
        }
        return userTasks;
    }

    @Override
    public void assignedTaskToUser(Task task, String userId) {
        userService.getUserById(userId).addTask(task);
    }

    @Override
    public void removeTask(String taskId) {
        taskMap.remove(taskId);
    }

    @Override
    public void updateTask(Task task) {
       for(User u:userService.getUsersList()){
            int i = 0;
            int pos = 0;
            boolean found = false;
            for(Task t:u.getTaskList()){
                if(t.getId().equals(task.getId())){
                    pos = i;
                    found = true;
                }
                i++;
            }
            if(found) u.getTaskList().set(pos,task);
        }
    }
}
