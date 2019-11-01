/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.TaskPlaner.services;

import escuelaing.edu.co.TaskPlaner.model.Task;
import escuelaing.edu.co.TaskPlaner.model.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejandro Rodriguez
 */
public interface TaskService {
    public List<Task> geTasksList();

    public Task getTaskById(String id);

    public List<Task> getTasksByUserId(String userId);

    public void assignedTaskToUser(Task task, String userId);

    public void removeTask(String taskId);

    public void updateTask(Task task);
}
