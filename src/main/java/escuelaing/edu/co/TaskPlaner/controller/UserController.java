/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.TaskPlaner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import escuelaing.edu.co.TaskPlaner.model.User;
import escuelaing.edu.co.TaskPlaner.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.ServletException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Alejandro
 */
@Service
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/Users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public boolean signup(@RequestBody User signup) {
        return userService.createUser(signup);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody User login)
            throws ServletException {

        String jwtToken = "";

        if (login.getId() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        User user = userService.findUserByUsernameAndPassword(login.getId(), login.getPassword());

        if (user == null) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }
        jwtToken = Jwts.builder().setSubject(login.getId()).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, "secretkey420").compact();

        return new Token(jwtToken);
    }

    public class Token {

        String accessToken;

        public Token(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String access_token) {
            this.accessToken = access_token;
        }
    }
}
