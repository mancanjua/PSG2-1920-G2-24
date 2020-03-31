package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CauseFormatter implements Formatter<Cause> {

    private ClinicService clinicService;

    @Autowired
    public CauseFormatter(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Override
    public Cause parse(String idText, Locale locale) throws ParseException {
        Integer id = Integer.valueOf(idText);
        Cause cause = this.clinicService.findCauseById(id);
        if(cause != null) {
        	return cause;
        }else {
            throw new ParseException("Flat not found with id " + id, 0);

        }
    }

    @Override
    public String print(Cause object, Locale locale) {
        return object.getId().toString();
    }
}
