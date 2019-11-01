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

    public boolean createUser(User user);

    public void updateUser(User user);

    public void removeUser(String userId);
    
     boolean authorizeUser(String id, String password);
     
    User findUserByUsernameAndPassword(String username, String password);
}
