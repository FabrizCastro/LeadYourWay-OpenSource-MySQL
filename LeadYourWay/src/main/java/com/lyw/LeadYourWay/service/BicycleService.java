package com.lyw.LeadYourWay.service;

import com.lyw.LeadYourWay.model.Bicycle;

public interface BicycleService {
    public abstract Bicycle createBicycle(Bicycle bicycle);
    public abstract Bicycle getBicycleById(Long bicycle_id);
    public abstract Bicycle updateBicycle(Bicycle bicycle);
    public abstract void deleteBicycle(Long bicycle_id);
}
