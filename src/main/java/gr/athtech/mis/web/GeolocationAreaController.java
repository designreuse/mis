/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.GeolocationArea;
import gr.athtech.mis.service.GeolocationAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author xrist
 */
@Controller
@RequestMapping(value = "/geolocationAreas")
public class GeolocationAreaController {

    private final Logger logger = LoggerFactory.getLogger(GeolocationAreaController.class);

    @Autowired
    private GeolocationAreaService geolocationAreaService;

    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GeolocationArea show(@PathVariable("id") Long id) {
        GeolocationArea geolocationArea  =geolocationAreaService.findOneWithCitiesWithInstitutions(id);

        return geolocationArea;
    }

}
