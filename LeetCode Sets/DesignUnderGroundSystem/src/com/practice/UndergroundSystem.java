package com.practice;

import java.util.*;

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */

public class UndergroundSystem {

    public Map<Integer,Map<Integer,String>> customerIDs = new HashMap<>();

    public UndergroundSystem() {

    }

    public void addStationInfo(int id, String stationName, int t, boolean checkVals, Map<Integer, String> stationsAndTimes) {
        if(checkVals) {
            customerIDs.get(id).put(t,stationName);
        }
        else {
            stationsAndTimes.put(t,stationName);
            customerIDs.put(id, stationsAndTimes);
        }
    }

    public boolean checkVals(int id) {
        for (Map.Entry<Integer, Map<Integer, String>> entry : customerIDs.entrySet()) {
            if(entry.getKey() == id) {
                return true;
            }
        }

        return false;
    }


    public void checkIn(int id, String stationName, int t) {
        // A customer with a card ID equal to id, checks in at the station stationName at time t
        // A customer can only be checked into one place at a time
        Map<Integer, String> stationsAndTimes = new HashMap<>();
        boolean check = checkVals(id);
        addStationInfo(id,stationName,t,check,stationsAndTimes);
    }

    public void checkOut(int id, String stationName, int t) {
        // A customer with a card ID equal to id, checks out from the station stationName at time t
        Map<Integer, String> stationsAndTimes = new HashMap<>();
        boolean check = checkVals(id);
        addStationInfo(id,stationName,t,check,stationsAndTimes);
    }

    public double getAverageTime(String startStation, String endStation) {
        // Returns the average time it takes to travel from startStation to endStation0
        // The average time is computed from all the previous traveling times from startStation to endStation that happened directly
            // meaning a check in at the startStation followed by a checkout from the endStation
        // The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation
        // There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called

//        TODO - loop customerID's map and check if any values contain start and end stations
//        TODO - if values have both stations add counter
//        TODO - if values have both stations subtract endStations key from startStations
//        TODO - Divide subtracted value by counter and return double

        int counter = 0;
        double calculate = 0.00;
        double startVal = 0.00;
        double endVal = 0.00;
        double subtract = 0.00;
        double sum = 0.00;
        for(Map.Entry<Integer, Map<Integer,String>> entry : customerIDs.entrySet()) {
            if(entry.getValue().containsValue(startStation) && entry.getValue().containsValue(endStation)) {
                counter++;
                for(Map.Entry<Integer,String> diff : entry.getValue().entrySet()) {
                    if(diff.getValue().contains(startStation)) {
                        startVal = diff.getKey();
                    }
                    else {
                        endVal = diff.getKey();
                    }
                    if(startVal != 0 && endVal !=0) {
                        subtract = endVal - startVal;
                        sum = sum + subtract;
                        subtract = 0;
                        startVal = 0;
                        endVal = 0;
                    }
                }
            }
        }

        calculate = sum/counter;
        System.out.print(calculate + " ");

        return calculate;
    }

}
