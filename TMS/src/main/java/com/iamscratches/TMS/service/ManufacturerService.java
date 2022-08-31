package com.iamscratches.TMS.service;

import com.iamscratches.TMS.model.Manufacturer;
import com.iamscratches.TMS.model.Owner;
import com.iamscratches.TMS.model.Vehicle;
import com.iamscratches.TMS.repo.ManufacturerRepository;
import com.iamscratches.TMS.utils.IdGenerator;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ManufacturerService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerService.class);
    private final String ALL_OK = "ALL_OK";

    private final ManufacturerRepository repository;

    @Autowired
    public ManufacturerService(ManufacturerRepository repository) {
        LOGGER.debug("ManufacturerService Autowired with ManufacturerRepository");
        this.repository = repository;
    }

    public ResponseMapper getAllManufacturers(){
        try{
            LOGGER.debug("Recieved Manufacturer List request");
            Iterable<Manufacturer> manufacturers = this.repository.findAll();
            List<Manufacturer> manufacturerList = new ArrayList<>();
            manufacturers.forEach(manufacturerList::add);

            LOGGER.debug("Send Manufacturer List");
            return new ResponseMapper(HttpStatus.OK, "Fetched All Owners", manufacturerList);
        }catch (Exception e){
            LOGGER.error("Owner fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Owner details");
        }
    }

    public ResponseMapper insertManufacturer(Manufacturer manufacturer){
        String msg = validateManufacturer(manufacturer);
        if(msg!=ALL_OK)
            return new ResponseMapper(HttpStatus.NOT_ACCEPTABLE, msg);
        try{
            LOGGER.debug("Received insert request for new and verified manufacturer");
            repository.save(manufacturer);
            LOGGER.debug("manufacturer entry saved successfully");
            return new ResponseMapper(HttpStatus.CREATED,
                    "manufacturer details saved successfully", manufacturer);
        }catch (Exception e){
            LOGGER.error("manufacturer insertion failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to insert manufacturer details");
        }
    }

    public ResponseMapper deleteManufacturerById(Integer manufacturerID){
        LOGGER.debug("Recieved manufacturer delete request by OwnerID");
        try{
            if(!repository.existsById(manufacturerID)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No manufacturer with given ID is present to be deleted");
            }
            repository.deleteById(manufacturerID);
            return new ResponseMapper(HttpStatus.OK, "manufacturer entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("manufacturer entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete manufacturer details");
        }
    }

    public ResponseMapper getManufacturerById(int ID){
        if(this.repository.existsById(ID))
            return new ResponseMapper(HttpStatus.FOUND, "Manufacturer details found with given ID",
                    this.repository.findById(ID));
        return new ResponseMapper(HttpStatus.NOT_FOUND, "No manufacturer Info found with given ID");
    }

    private String validateManufacturer(Manufacturer manufacturer){
        if(manufacturer.getManufacturerId()==null){
            manufacturer.setManufacturerId(IdGenerator.generateUniqueId());
        }
        if(manufacturer.getEstd()==null){
            manufacturer.setEstd(new Date());
        }
        if(manufacturer.getLicenseDate()==null){
            manufacturer.setLicenseDate(new Date());
        }
        Date date = manufacturer.getLicenseDate();
        if(manufacturer.getExpiryDate()==null)
            manufacturer.setExpiryDate(new Date(date.getYear()+15, date.getMonth(), date.getDay()));

        if(repository.existsById(manufacturer.getManufacturerId()))
            return "manufacturer id already exists";


        manufacturer.setExpired(manufacturer.getExpiryDate().before(manufacturer.getLicenseDate()));

        return ALL_OK;
    }
}
