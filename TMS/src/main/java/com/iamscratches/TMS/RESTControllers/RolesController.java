package com.iamscratches.TMS.RESTControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iamscratches.TMS.model.Roles;
import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.service.RolesService;
import com.iamscratches.TMS.service.UserService;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    public static final Logger LOGGER = LoggerFactory.getLogger(RolesController.class);
    private final RolesService service;

    @Autowired
    public RolesController(RolesService service) {
        LOGGER.debug("RolesController Autowired with RolesService");
        this.service = service;
    }

    /*
     * Get all users list
     */
    @GetMapping(value = "/roleslist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllRoles() {
        LOGGER.debug("Recieved Roles List request");
        ResponseMapper mapper = service.getAllRoles();
        LOGGER.debug("getAllRoles: " + mapper.getValue());

        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send Roles List");
        return entity;
    }

    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> insertManufacturer(@RequestBody Roles roles){
        LOGGER.debug("Recieved roles insert request : " + roles);
        ResponseMapper mapper = service.insertRoles(roles);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMapper> deleteUser(
            @RequestParam(value="username") String username,
            @RequestParam(value = "rolename", required = false) String rolename){
        LOGGER.debug("Recieved roles delete request");
        ResponseMapper mapper;
        if(rolename==null)
             mapper = service.deleteRoleById(username);
        else
             mapper = service.deleteRolesByUsernameAndRolename(username, rolename);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseMapper> findRole(@RequestParam(value = "ID") String username) throws NoSuchElementException {
        LOGGER.debug("Received search request for Role username " + username);
        ResponseMapper mapper = service.getRoleByUsername(username);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }
}