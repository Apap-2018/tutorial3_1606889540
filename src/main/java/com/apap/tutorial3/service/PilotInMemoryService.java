package com.apap.tutorial3.service;

import com.apap.tutorial3.model.PilotModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PilotInMemoryService implements PilotService{
    private static List<PilotModel> archivePilot;

    public PilotInMemoryService() {
        this.archivePilot = new ArrayList<>();
    }

    @Override
    public void addPilot(PilotModel pilot){
        archivePilot.add(pilot);
    }

    @Override
    public List<PilotModel> getPilotList(){
        return archivePilot;
    }

    @Override
    public PilotModel getPilotDetailByLicenseNumber(String licenseNumber){
        for (PilotModel pil : getPilotList()){
            if (pil.getLicenseNumber().equalsIgnoreCase(licenseNumber)){
                return pil;
            }
        }
        return null;
    }

    @Override
    public PilotModel getPilotDetailById(String id){
        for (PilotModel pil : getPilotList()){
            if (pil.getId().equalsIgnoreCase(id)){
                return pil;
            }
        }
        return null;
    }

}
