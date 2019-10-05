package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.DoctorService;
import ar.edu.itba.paw.interfaces.service.ImageService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Image;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/insertDoctorProfileImage", method = { RequestMethod.POST })
    public ModelAndView insertDoctorProfileImage(@RequestParam("photo") MultipartFile photo){
        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Doctor doctor = doctorService.getDoctorByEmail(userEmail);

        // TODO: check for result == 0, and errors above!!!!
        long result = imageService.createProfileImage(photo, doctor);

        final ModelAndView mav = new ModelAndView("doctor/doctorProfile");
        return mav;
    }

    @RequestMapping(value = "/images/{license}", method = { RequestMethod.GET })
    public @ResponseBody byte[] getImage(@PathVariable String license) throws IOException {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        Image image = imageService.getProfileImage(doctor);
        if(image.getImage() != null)
            return IOUtils.toByteArray(image.getImage());
        else
            return new byte[] { };
    }
}
