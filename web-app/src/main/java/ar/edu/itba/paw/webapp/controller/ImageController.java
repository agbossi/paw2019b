package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.DoctorService;
import ar.edu.itba.paw.interfaces.service.ImageService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/insertDoctorProfileImage", method = { RequestMethod.POST })
    public ModelAndView insertDoctorProfileImage(@RequestParam("photo") MultipartFile photo){
        String email = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        imageService.createProfileImage(photo, doctorService.getDoctorByEmail(email));

        final ModelAndView mav = new ModelAndView("doctor/doctorProfile");
        return mav;
    }

    @RequestMapping(value = "/images/{license}", method = { RequestMethod.GET })
    public @ResponseBody byte[] getImage(@PathVariable String license) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        return imageService.getProfileImage(doctor).getImage();
    }
}
