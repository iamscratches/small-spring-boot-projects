package com.iamscratches.TMS.service;

import com.iamscratches.TMS.model.OffenceDetails;
import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.repo.OffenceDetailsRepository;
import com.iamscratches.TMS.repo.UserRepository;
import com.iamscratches.TMS.utils.IdGenerator;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OffenceDetailsService {
    public static final Logger LOGGER = LoggerFactory.getLogger(OffenceDetailsService.class);
    private final String ALL_OK = "ALL_OK";

    private final OffenceDetailsRepository repository;

    @Autowired
    public OffenceDetailsService(OffenceDetailsRepository repository) {
        LOGGER.debug("OffenceDetailsService Autowired with OffenceDetailsRepository");
        this.repository = repository;
    }

    public ResponseMapper getAllOffenceDetails(){
        try{
            LOGGER.debug("Recieved OffenceDetails List request");
            Iterable<OffenceDetails> offenceDetails = this.repository.findAll();
            List<OffenceDetails> offenceDetailsList = new ArrayList<>();
            offenceDetails.forEach(offenceDetailsList::add);

            LOGGER.debug("Send OffenceDetails List");
            return new ResponseMapper(HttpStatus.OK, "Fetched All OffenceDetails", offenceDetailsList);
        }catch (Exception e){
            LOGGER.error("OffenceDetails fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch OffenceDetails details");
        }
    }

    public ResponseMapper insertOffenceDetails(OffenceDetails details){
        String msg = validateOffenceDetails(details);
        if(msg!=ALL_OK)
            return new ResponseMapper(HttpStatus.NOT_ACCEPTABLE, msg);
        try{
            LOGGER.debug("Received insert request for new and verified OffenceDetails");
            repository.save(details);
            LOGGER.debug("OffenceDetails entry saved successfully");
            return new ResponseMapper(HttpStatus.CREATED,
                    "OffenceDetails details saved successfully", details);
        }catch (Exception e){
            LOGGER.error("OffenceDetails insertion failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to insert OffenceDetails");
        }
    }

    public ResponseMapper deleteOffenceDetailsById(Integer appNo){
        LOGGER.debug("Recieved OffenceDetails delete request");
        try{
            if(!repository.existsById(appNo)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No OffenceDetails with given ID is present to be deleted");
            }
            repository.deleteById(appNo);
            return new ResponseMapper(HttpStatus.OK, "OffenceDetails entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("OffenceDetails entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete OffenceDetails");
        }
    }

    public ResponseMapper getOffenceDetailsById(int ID){
        if(this.repository.existsById(ID))
            return new ResponseMapper(HttpStatus.FOUND, "Offence Details details found with given ID",
                    this.repository.findById(ID));
        return new ResponseMapper(HttpStatus.NOT_FOUND, "No Offence Details Info found with given ID");
    }

    private String validateOffenceDetails(OffenceDetails details){
        if(details.getAppNo()==null)
            details.setAppNo(IdGenerator.generateUniqueId());
        if(details.getOffenceTime()==null)
            details.setOffenceTime(LocalDateTime.now());

        if(repository.existsById(details.getAppNo()))
            return "Application ID already present";
        else if(details.getOffenceId()==null)
            return "Please provide a Offence ID";
        else if(details.getVehicleNo()==null)
            return "Please provide a Vehicle no";

        return ALL_OK;
    }


}
