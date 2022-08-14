package com.iamscratches.TMS.model.vehicle;

import com.iamscratches.TMS.model.Vehicle;

import java.util.Map;

public class VehiclesUpdateList {
    private Map<String, Integer> topStats;
    private Map<String, Map<Integer, Vehicle>> baseStats;

    public VehiclesUpdateList() {
    }

    public VehiclesUpdateList(Map<String, Integer> topStats, Map<String, Map<Integer, Vehicle>> baseStats) {
        this.topStats = topStats;
        this.baseStats = baseStats;
    }

    public Map<String, Integer> getTopStats() {
        return topStats;
    }

    public void setTopStats(Map<String, Integer> topStats) {
        this.topStats = topStats;
    }

    public void putTopStats(String key, Integer value){
        this.topStats.put(key, value);
    }

    public Map<String, Map<Integer, Vehicle>> getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(Map<String, Map<Integer, Vehicle>> baseStats) {
        this.baseStats = baseStats;
    }
}
