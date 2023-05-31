package com.lyw.LeadYourWay.service.impl;

import com.lyw.LeadYourWay.model.Bicycle;
import com.lyw.LeadYourWay.repository.BicycleRepository;
import com.lyw.LeadYourWay.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BicycleServiceImpl implements BicycleService {
    @Autowired
    private BicycleRepository bicycleRepository;

    @Override
    public Bicycle createBicycle(Bicycle bicycle) {
        return bicycleRepository.save(bicycle);
    }
}
