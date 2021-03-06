package io.github.dannyflowerz.calendaraggregator.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.dannyflowerz.calendaraggregator.model.Appointment;
import io.github.dannyflowerz.calendaraggregator.service.AppointmentCrudService;

@RestController
public class CalendarController {
	
    @Autowired
    AppointmentCrudService googleAppointmentCrudService;
    @Autowired
    AppointmentCrudService outlookAppointmentCrudService;

    @GetMapping("/appointments")
    public List<Appointment> getAppointments(@RequestParam String startDate, @RequestParam String endDate) {
    	LocalDate start = LocalDate.parse(startDate);
    	LocalDate end = LocalDate.parse(endDate);
        return Stream.concat(googleAppointmentCrudService.getAppointments(start, end).stream(), outlookAppointmentCrudService.getAppointments(start, end).stream())
                .collect(Collectors.toList());
    }

}
