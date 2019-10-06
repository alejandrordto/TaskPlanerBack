/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.TaskPlaner.services;

import escuelaing.edu.co.TaskPlaner.model.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aleandro Rodriguez
 */
public interface UserService {
    public List<User> getUsersList();

    public User getUserById(String userId);

    public User createUser(String userId, String name, String email, String password);

    public User updateUser(String userId, String name, String email, String password);

    public void removeUser(String userId);
}
