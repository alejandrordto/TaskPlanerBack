/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.TaskPlaner.services.impl;
import escuelaing.edu.co.TaskPlaner.model.Task;
import escuelaing.edu.co.TaskPlaner.model.User;
import escuelaing.edu.co.TaskPlaner.services.TaskService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 *
 * @author Alejandro Rodriguez
 */
@Service
public class TaskUserImpl implements TaskService{
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
    public Task assignedTaskToUser(String taskId, User user) {
        Task taskNewAssigned = null;
        List<Task> task = geTasksList();
        for (int i=0; i< task.size();i++){
            if(task.get(i).getId().equals(taskId)){
                task.get(i).setResponsible(user);
                taskNewAssigned = task.get(i);
            }
        }
        return taskNewAssigned;
    }

    @Override
    public void removeTask(String taskId) {
        taskMap.remove(taskId);
    }

    @Override
    public Task updateTask(String id, User responsible, String status, Date dueDate) {
        Task task = getTaskById(id);
        task.setId(id);
        task.setResponsible(responsible);
        task.setStatus(status);
        task.setDueDate(dueDate);
        taskMap.replace(task.getId(),task);
        return task;
    }
}
