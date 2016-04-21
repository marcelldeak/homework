package hu.codingmentor.mobile.webshop.service;

import hu.codingmentor.mobile.webshop.dto.UserDTO;
import hu.codingmentor.mobile.webshop.exception.UserAlreadyExsistsException;
import hu.codingmentor.mobile.webshop.exception.UserDontExsistException;
import hu.codingmentor.mobile.webshop.interceptor.Validate;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserManagementService implements Serializable{

    private static final String USER_DOESNT_EXIST_MESSAGE = "User don't exists! Registrate first!";
    
    private final Map<String, UserDTO> users = new HashMap<>();

    public UserManagementService() {
        // Default constructor
    }

    @PostConstruct
    private void init() {
        UserDTO admin = new UserDTO(), user = new UserDTO();

        admin.setAdmin(true);
        admin.setRegistrationDate(LocalDate.now());
        admin.setUsername("admin");
        admin.setPassword("admin");

        user.setAdmin(false);
        user.setRegistrationDate(LocalDate.now());
        user.setUsername("user");
        user.setPassword("user");

        users.put(admin.getUsername(), admin);
        users.put(user.getUsername(), user);
    }

    @Lock(LockType.WRITE)
    @Validate
    public UserDTO addUser(UserDTO user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
            return user;
        }
        throw new UserAlreadyExsistsException("User alredy exists!");
    }

    @Lock(LockType.WRITE)
    public UserDTO removeUser(String username) {
        UserDTO user = users.remove(username);
        if (null != user) {
            return user;
        } else {
            throw new UserDontExsistException(USER_DOESNT_EXIST_MESSAGE);
        }
    }

    @Lock(LockType.WRITE)
    @Validate
    public UserDTO editUser(String username, UserDTO user) {
        if (users.containsKey(username)) {
            removeUser(username);
            users.put(username, user);
            return user;
        }
        throw new UserDontExsistException(USER_DOESNT_EXIST_MESSAGE);
    }

    @Lock(LockType.READ)
    public UserDTO getUserByUsername(String username) {
        UserDTO user = users.get(username);
        if (null != user) {
            return user;
        }
        throw new UserDontExsistException(USER_DOESNT_EXIST_MESSAGE);
    }

    @Lock(LockType.READ)
    public List<UserDTO> getUserList() {
        return new ArrayList(users.values());
    }
}
