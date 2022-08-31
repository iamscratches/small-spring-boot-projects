package com.iamscratches.TMS.service;

import com.iamscratches.TMS.model.Offence;
import com.iamscratches.TMS.model.Roles;
import com.iamscratches.TMS.repo.OffenceRepository;
import com.iamscratches.TMS.repo.RolesRepository;
import com.iamscratches.TMS.utils.IdGenerator;
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
public class OffenceService {
    public static final Logger LOGGER = LoggerFactory.getLogger(OffenceService.class);
    private final String ALL_OK = "ALL_OK";

    private final OffenceRepository repository;

    @Autowired
    public OffenceService(OffenceRepository repository) {
        LOGGER.debug("OffenceService Autowired with OffenceRepository");
        this.repository = repository;
    }

    public ResponseMapper getAllOffence(){
        try{
            LOGGER.debug("Recieved Offence List request");
            Iterable<Offence> offences = this.repository.findAll();
            List<Offence> offenceList = new ArrayList<>();
            offences.forEach(offenceList::add);

            LOGGER.debug("Send Offence List");
            return new ResponseMapper(HttpStatus.OK, "Fetched All Offence", offenceList);
        }catch (Exception e){
            LOGGER.error("Offence fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Offence details");
        }
    }

    public ResponseMapper insertOffence(Offence offence){
        String msg = validateOffence(offence);
        if(msg!=ALL_OK)
            return new ResponseMapper(HttpStatus.NOT_ACCEPTABLE, msg);
        try{
            LOGGER.debug("Received insert request for new and verified Offence");
            repository.save(offence);
            LOGGER.debug("offence entry saved successfully");
            return new ResponseMapper(HttpStatus.CREATED,
                    "offence details saved successfully", offence);
        }catch (Exception e){
            LOGGER.error("Offence insertion failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to insert Offence details");
        }
    }

    public ResponseMapper deleteOffenceById(Integer offenceId){
        LOGGER.debug("Recieved Offence delete request");
        try{
            if(!repository.existsById(offenceId)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No offence with given ID is present to be deleted");
            }
            repository.deleteByOffenceId(offenceId);
            return new ResponseMapper(HttpStatus.OK, " Offence entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("Offence entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete Offence details");
        }
    }

    public ResponseMapper getOffenceById(int ID){
        if(this.repository.existsById(ID))
            return new ResponseMapper(HttpStatus.FOUND, "Offence info found with given ID",
                    this.repository.findById(ID));
        return new ResponseMapper(HttpStatus.NOT_FOUND, "No Offence Info found with given ID");
    }

    private String validateOffence(Offence offence){
        if(offence.getOffenceId()==null)
            offence.setOffenceId(IdGenerator.generateUniqueId());

        if(offence.getPenalty()==null)
            return "Penalty cannot be null";

        return ALL_OK;
    }

}
