package com.iamscratches.TMS.service;

import com.iamscratches.TMS.model.Owner;
import com.iamscratches.TMS.repo.OwnerRepository;
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
public class OwnerService {
    public static final Logger LOGGER = LoggerFactory.getLogger(OwnerService.class);
    private final String ALL_OK = "ALL_OK";

    private final OwnerRepository repository;

    @Autowired
    public OwnerService(OwnerRepository repository) {
        LOGGER.debug("OwnerService Autowired with OwnerRepository");
        this.repository = repository;
    }

    public ResponseMapper getAllOwners(){
        try{
            LOGGER.debug("Recieved Owner List request");
            Iterable<Owner> owners = this.repository.findAll();
            List<Owner> ownerList = new ArrayList<>();
            owners.forEach(ownerList::add);
            LOGGER.debug("Send Owner List");
            return new ResponseMapper(HttpStatus.OK, "Fetched All Owners", ownerList);
        }catch (Exception e){
            LOGGER.error("Owner fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Owner details");
        }
    }

    public ResponseMapper getOwnerById(int id){
        try{
            Owner owner;
            LOGGER.debug("Recieved Owner ID request");
            if(this.repository.existsById(id))
                owner = this.repository.findByOwnerId(id);
            else
                return new ResponseMapper(HttpStatus.NOT_FOUND, "No Such owner with given ID is found");
            return new ResponseMapper(HttpStatus.FOUND, "Fetched Owner by ID", owner);
        }catch (Exception e){
            LOGGER.error("Owner fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Owner details");
        }
    }

    public ResponseMapper insertOwner(Owner owner,boolean update){
        if(update && owner.getOwnerId()!=null && this.repository.existsById(owner.getOwnerId())){
            deleteOwnerById(owner.getOwnerId());
        }
        String msg = validateOwner(owner);
        if(msg!=ALL_OK)
            return new ResponseMapper(HttpStatus.NOT_ACCEPTABLE, msg);
        try{
            LOGGER.debug("Received insert request for new and verified owner");
            repository.save(owner);
            LOGGER.debug("Owner entry saved successfully");
            return new ResponseMapper(HttpStatus.CREATED, "Owner details saved successfully", owner);
        }catch (Exception e){
            LOGGER.error("Owner insertion failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to insert Owner details");
        }
    }

    public ResponseMapper deleteOwnerById(Integer ownerID){
        LOGGER.debug("Recieved Owner delete request by OwnerID");
        try{
            if(!repository.existsById(ownerID)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No Owner with given ID is present to be deleted");
            }
            repository.deleteById(ownerID);
            return new ResponseMapper(HttpStatus.OK, "Owner entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("Owner entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete Owner details");
        }
    }

    public ResponseMapper deleteByContactNo(Long mobileNo){
        LOGGER.debug("Recieved Owner delete request by OwnerID");
        try{
            if(!repository.existsByMobileNo(mobileNo)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No Owner with given ID is present to be deleted");
            }
            repository.deleteByMobileNo(mobileNo);
            return new ResponseMapper(HttpStatus.OK, "Owner entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("Owner entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete Owner details");
        }
    }

    public ResponseMapper deleteByAdhaarNo(String adhaar){
        LOGGER.debug("Recieved Owner delete request by OwnerID");
        try{
            if(!repository.existsByAdhaar(adhaar)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No Owner with given ID is present to be deleted");
            }
            repository.deleteByAdhaar(adhaar);
            return new ResponseMapper(HttpStatus.OK, "Owner entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("Owner entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete Owner details");
        }
    }

    public ResponseMapper deleteByPancardNo(String pancard){
        LOGGER.debug("Recieved Owner delete request by OwnerID");
        try{
            if(!repository.existsByPancard(pancard)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No Owner with given ID is present to be deleted");
            }
            repository.deleteByPancard(pancard);
            return new ResponseMapper(HttpStatus.OK, "Owner entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("Owner entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete Owner details");
        }
    }

    private String validateOwner(Owner owner){
        if(owner.getOwnerId()==null){
            owner.setOwnerId(IdGenerator.generateUniqueId());
        }
        if(repository.existsById(owner.getOwnerId()))
            return "owner id already exists";
        else if(owner.getFname()==null)
            return "First name cannot be empty";
        else if(owner.getDob()==null)
            return "please provide Date of Birth of the Owner";
        else if(owner.getMobileNo()==null)
            return "please provide atleast one contact no.";
        else if(owner.getGender()==null)
            return "gender needs to be specified";
        else if(owner.getPermAddr()==null)
            return "please provide a permanent address";
        else if(owner.getPincode()==null)
            return "please provide proper PIN code";
        else if (owner.getStateCode()==null)
            return "please provide a state code";
        else if(owner.getAdhaar()==null)
            return "please provide Aadhaar details";
        else if(owner.getAddProofName()==null)
            return "please provide valid address proof name";
        else if(repository.existsByAdhaarOrMobileNoOrPancard(owner.getAdhaar(),
                owner.getMobileNo(), owner.getPancard()))
            return "Adhaar no., contact no. or pancard already registered with some other owner";
        return ALL_OK;
    }
}
