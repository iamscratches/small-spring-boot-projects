package com.iamscratches.TMS.service;

import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.repo.UserRepository;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final String ALL_OK = "ALL_OK";

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        LOGGER.debug("UserService Autowired with UserRepository");
        this.repository = repository;
    }

    public ResponseMapper getAllUsers(){
        try{
            LOGGER.debug("Recieved User List request");
            Iterable<User> users = this.repository.findAll();
            List<User> userList = new ArrayList<>();
            users.forEach(userList::add);
//            for(User user: userList){
//                user.setPassword(new BCryptPasswordEncoder(user.get));
//            }

            LOGGER.debug("Send User List");
            return new ResponseMapper(HttpStatus.OK, "Fetched All Users", userList);
        }catch (Exception e){
            LOGGER.error("Users fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Users details");
        }
    }

    public ResponseMapper insertUser(User user){
        String msg = validateUser(user);
        if(msg!=ALL_OK)
            return new ResponseMapper(HttpStatus.NOT_ACCEPTABLE, msg);
        try{
            LOGGER.debug("Received insert request for new and verified user");
            repository.save(user);
            LOGGER.debug("user entry saved successfully");
            return new ResponseMapper(HttpStatus.CREATED,
                    "user details saved successfully", user);
        }catch (Exception e){
            LOGGER.error("user insertion failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to insert user details");
        }
    }

    public ResponseMapper deleteUserById(String username){
        LOGGER.debug("Recieved user delete request by username" +
                "");
        try{
            if(!repository.existsByUsername(username)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No user with given ID is present to be deleted");
            }
            repository.deleteByUsername(username);
            return new ResponseMapper(HttpStatus.OK, "user entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("user entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete user details");
        }
    }

    public ResponseMapper getUserByUsername(String username){
        if(this.repository.existsByUsername(username))
            return new ResponseMapper(HttpStatus.FOUND, "User info found with given username",
                    this.repository.findByUsername(username));
        return new ResponseMapper(HttpStatus.NOT_FOUND, "No User Info found with given username");
    }

    private String validateUser(User user){
        if(user.getUsername()==null)
            return "Please provide a username";
        else if(user.getPassword()==null)
            return "Please provide a password";
        else if(user.getFname()==null)
            return "Please provide a first name";
        else if(user.getLname()==null)
            return "Please provide a last name";

        return ALL_OK;
    }


}
