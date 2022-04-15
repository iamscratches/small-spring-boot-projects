package com.iamscratches.spring.springbootdemo.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamscratches.spring.springbootdemo.business.ReservationService;
import com.iamscratches.spring.springbootdemo.data.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RoomCleanerListener {
    private final static Logger LOG = LoggerFactory.getLogger((RoomCleanerListener.class));

    private final ObjectMapper mapper;
    private final ReservationService reservationService;

    public RoomCleanerListener(ObjectMapper mapper, ReservationService reservationService) {
        this.mapper = mapper;
        this.reservationService = reservationService;
    }

    public void receiveMsg(String msg){
        try{
            AsyncPayload payload = mapper.readValue(msg, AsyncPayload.class);
            if("ROOM".equals(payload.getModel())){
                Room room = reservationService.getRoomById(payload.getId());
                if(room!=null)
                    LOG.info("ROOM {}: {} needs to be cleaned", room.getRoomNumber(), room.getName());
                else
                    LOG.warn("ROOM {} {} not registered", payload.getId(), payload.getModel());
            }else{
                LOG.warn("unknown model type");
            }
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
