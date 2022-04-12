package com.iamscratches.bootingweb.models;

public enum Position {
    HOUSEKEEPING, FRONT_DESK, SECURITY, CONCEIRGE;

    @Override
    public String toString() {
        switch (this){
            case CONCEIRGE:
                return "Conceirge";
            case SECURITY:
                return "Security";
            case FRONT_DESK:
                return "Front_Desk";
            case HOUSEKEEPING:
                return "Housekeeping";
        }
        return "";
    }
}
