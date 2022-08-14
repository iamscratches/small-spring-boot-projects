package com.iamscratches.TMS.utils.model.Owner;

public enum Gender {
    MALE("M"), FEMALE("F"), OTHERS("O");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public static Gender findByLabel(String byLabel){
        for(Gender r:Gender.values()){
            if(r.gender.equalsIgnoreCase(byLabel))
                return r;
        }
        return null;
    }

    public String getLabel() {
        return gender;
    }

    @Override
    public String toString() {
        return String.valueOf(gender);
    }
}
