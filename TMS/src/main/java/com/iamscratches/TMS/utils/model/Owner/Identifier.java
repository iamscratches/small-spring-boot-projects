package com.iamscratches.TMS.utils.model.Owner;

public enum Identifier {
    OWNER_ID("OWNER_ID"), MOBILE_NO("MOBILE_NO"), ADHAAR("ADHAAR"), PANCARD("PANCARD");

    private String value;

    Identifier(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
