package com.apap.tutorial3.controller;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @RequestMapping("/pilot/add")
    public String add(@RequestParam(value = "id", required = true) String id,
                      @RequestParam(value = "licenseNumber", required = true) String licenseNumber,
                      @RequestParam(value = "name", required = true) String name,
                      @RequestParam(value = "flyHour", required = true) int flyHour){
        PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
        pilotService.addPilot(pilot);
        return "add";
    }

    @RequestMapping("/pilot/view")
    public String view(@RequestParam("licenseNumber") String licenseNumber, Model model){
        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

        model.addAttribute("pilot", archive);
        return "view-pilot";
    }

    @RequestMapping("/pilot/view/license-number/{licenseNumber}")
    public String viewWithPath(@PathVariable String licenseNumber, Model model){
        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

        if(archive!=null) {
            model.addAttribute("pilot", archive);
            return "view-pilot";
        }
        return "error";
    }


    @RequestMapping("/pilot/viewall")
    public String viewall(Model model){
        List<PilotModel> archive = pilotService.getPilotList();

        model.addAttribute("listPilot", archive);
        return "viewall-pilot";
    }

    @RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{hour}")
    public String updateFlyHour(@PathVariable String licenseNumber, @PathVariable String hour, Model model){
        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

        if(archive!=null) {
            int h = Integer.parseInt(hour);
            archive.setFlyHour(h);
            model.addAttribute("pilot", archive);
            return "update";
        }
        return "error";
    }

    @RequestMapping("/pilot/delete/id/{id}")
    public String deletePilot(@PathVariable String id, Model model){
        PilotModel archive = pilotService.getPilotDetailById(id);

        if(archive!=null) {
            pilotService.getPilotList().remove(archive);
            return "delete";
        }
        return "error";
    }
}
