package com.iamscratches.TMS.service;

import com.iamscratches.TMS.model.Roles;
import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.repo.RolesRepository;
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
public class RolesService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RolesService.class);
    private final String ALL_OK = "ALL_OK";

    private final RolesRepository repository;

    @Autowired
    public RolesService(RolesRepository repository) {
        LOGGER.debug("RolesService Autowired with RolesRepository");
        this.repository = repository;
    }

    public ResponseMapper getAllRoles(){
        try{
            LOGGER.debug("Recieved Roles List request");
            Iterable<Roles> roles = this.repository.findAll();
            List<Roles> rolesList = new ArrayList<>();
            roles.forEach(rolesList::add);

            LOGGER.debug("Send Roles List");
            return new ResponseMapper(HttpStatus.OK, "Fetched All Roles", rolesList);
        }catch (Exception e){
            LOGGER.error("Roles fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Roles details");
        }
    }

    public ResponseMapper insertRoles(Roles roles){
        String msg = validateRoles(roles);
        if(msg!=ALL_OK)
            return new ResponseMapper(HttpStatus.NOT_ACCEPTABLE, msg);
        try{
            LOGGER.debug("Received insert request for new and verified roles");
            repository.save(roles);
            LOGGER.debug("roles entry saved successfully");
            return new ResponseMapper(HttpStatus.CREATED,
                    "roles details saved successfully", roles);
        }catch (Exception e){
            LOGGER.error("roles insertion failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to insert roles details");
        }
    }

    public ResponseMapper deleteRoleById(String username){
        LOGGER.debug("Recieved user delete request by username roles");
        try{
            if(!repository.existsByUsername(username)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No roles with given username is present to be deleted");
            }
            List<Roles> rolesList = repository.findByUsername(username);
            repository.deleteByUsername(username);
            return new ResponseMapper(HttpStatus.OK, rolesList.size() + " Roles entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("roles entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete user details");
        }
    }

    public ResponseMapper deleteRolesByUsernameAndRolename(String username, String rolename){
        try{
            if(!repository.existsByUsernameAndRolename(username, rolename)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No roles with given username is present to be deleted");
            }
            repository.deleteByUsernameAndRolename(username,rolename);
            return new ResponseMapper(HttpStatus.OK, "roles entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("roles entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete user details");
        }
    }

    public ResponseMapper getRoleByUsername(String username){
        if(this.repository.existsByUsername(username))
            return new ResponseMapper(HttpStatus.FOUND, "Role info found with given username",
                    this.repository.findByUsername(username));
        return new ResponseMapper(HttpStatus.NOT_FOUND, "No Role Info found with given username");
    }

    private String validateRoles(Roles roles){
        if(roles.getUsername()==null)
            return "Please provide a username";
        else if(repository.existsByUsername(roles.getUsername())) {
            List<Roles> rolesList = repository.findByUsername(roles.getUsername());
            for (Roles role : rolesList) {
                if (role.getRolename().equals(roles.getRolename())) {
                    return "username has already access to given rolename";
                }
            }
        }

        return ALL_OK;
    }

}
