package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        FlightModel flight = new FlightModel();
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flight.setPilot(pilot);

        model.addAttribute("flight", flight);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add", method = RequestMethod.POST)
    private String addFlightSubmit(@ModelAttribute FlightModel flight){
        flightService.addFlight(flight);
        return "add";
    }

    @RequestMapping("/flight/delete")
    public String deleteFlight(@RequestParam(value = "Id") long id, Model model){
        FlightModel flightModel = flightService.getFlightDetailById(id);

        if(flightModel!=null) {
            flightService.deleteFlight(flightModel);
            return "delete";
        }
        return "error";
    }

    @RequestMapping(value="/flight/update", method = RequestMethod.POST)
    public String update(@ModelAttribute FlightModel flight){
        flightService.addFlight(flight);
        return "update";
    }

    @RequestMapping(value = "/flight/update/{Id}", method = RequestMethod.GET)
    private String updateFlight(@PathVariable(value = "Id") int id, Model model) {
        FlightModel f = flightService.getFlightDetailById(id);
        model.addAttribute("flight", f);
        return "updateFlight";
    }

    @RequestMapping("/flight/view")
    public String view(@RequestParam(value = "Id") int id, Model model){
        FlightModel flight = flightService.getFlightDetailById(id);

        if(flight!=null) {
            model.addAttribute("flight", flight);
            model.addAttribute("pilot", flight.getPilot());
            return "view-flight";
        }
        return "error";
    }
}
