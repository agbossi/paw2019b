package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.SpecialtyService;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.webapp.caching.SpecialtyCaching;
import ar.edu.itba.paw.webapp.dto.SpecialtyDto;
import ar.edu.itba.paw.webapp.form.SpecialtyForm;
import ar.edu.itba.paw.webapp.helpers.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@Path("specialties")
public class SpecialtyController {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private SpecialtyCaching specialtyCaching;

    @Autowired
    private MessageSource messageSource;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getSpecialties(@QueryParam("page") @DefaultValue("0") Integer page,
                                   @Context Request request) {
        page = (page < 0) ? 0 : page;

        List<SpecialtyDto> specialties = specialtyService.getPaginatedObjects(page).stream()
                .map(SpecialtyDto::fromSpecialty).collect(Collectors.toList());
        int maxPage = specialtyService.maxAvailablePage();

        String pageStr = messageSource.getMessage("page",null, Locale.getDefault());
        String prev = messageSource.getMessage("previous",null, Locale.getDefault());
        String next = messageSource.getMessage("next",null, Locale.getDefault());
        String last = messageSource.getMessage("last",null, Locale.getDefault());
        String first = messageSource.getMessage("first",null, Locale.getDefault());

        return CacheHelper.handleResponse(specialties, specialtyCaching,
                new GenericEntity<List<SpecialtyDto>>(specialties) {}, "specialties", request)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, 0).build(), first)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, page - 1).build(), prev)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, page + 1).build(), next)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, maxPage).build(), last)
                .build();

        /*return Response.ok(new GenericEntity<List<SpecialtyDto>>(specialties) {})
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(), "first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page - 1).build(), "prev")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(), "next")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxPage).build(), "last")
                .build(); */
    }

    @DELETE
    @Path("/{specialty}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteSpecialty(@PathParam("specialty") final String specialty) {
        specialtyService.deleteSpecialty(specialty);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSpecialty(@Valid final SpecialtyForm form) {
        Specialty specialty = specialtyService.createSpecialty(form.getName());
        return Response.created(uriInfo.getAbsolutePathBuilder().
                path(specialty.getSpecialtyName()).build()).build();
    }
}