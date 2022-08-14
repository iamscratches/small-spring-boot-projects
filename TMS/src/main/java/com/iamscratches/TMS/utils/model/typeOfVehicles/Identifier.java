package com.iamscratches.TMS.utils.model.typeOfVehicles;

public enum Identifier {
    VEHICLE_TYPE_ID("VEHICLE_TYPE_ID"), VEHICLE_CATEGORY("VEHICLE_CATEGORY");

    private String value;

    Identifier(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
