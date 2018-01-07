package io.github.dannyflowerz.calendaraggregator.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.dannyflowerz.calendaraggregator.configuration.GoogleProperties;
import io.github.dannyflowerz.calendaraggregator.model.Appointment;
import io.github.dannyflowerz.calendaraggregator.model.AppointmentTranslatorUtil;
import io.github.dannyflowerz.calendaraggregator.model.GoogleAppointment;

@Service
class GoogleAppointmentCrudService implements AppointmentCrudService {

    @Autowired
    private GoogleProperties googleProperties;

    @Override
    public List<Appointment> getAppointments(LocalDate startDate, LocalDate endDate) {
        String url = googleProperties.getBaseUrl() + googleProperties.getGetAppointmentsEndPoint() + "?startDate="  + startDate + "&endDate="  + endDate;
    	List<GoogleAppointment> googleAppointments = new RestTemplate().getForObject(url, List.class);
        return googleAppointments.stream()
                .map(AppointmentTranslatorUtil::translate)
                .collect(Collectors.toList());
    }

}
