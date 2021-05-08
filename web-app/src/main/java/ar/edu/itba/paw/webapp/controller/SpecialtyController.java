package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.SpecialtyService;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.webapp.dto.SpecialtyDto;
import ar.edu.itba.paw.webapp.form.SpecialtyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("specialties")
public class SpecialtyController {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private SpecialtyService specialtyService;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getSpecialties(@QueryParam("page") @DefaultValue("0") Integer page) {
        page = (page < 0) ? 0 : page;

        List<SpecialtyDto> specialties = specialtyService.getPaginatedObjects(page).stream()
                .map(SpecialtyDto::fromSpecialty).collect(Collectors.toList());
        int maxPage = specialtyService.maxAvailablePage();
        return Response.ok(new GenericEntity<List<SpecialtyDto>>(specialties) {})
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(), "first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page - 1).build(), "prev")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(), "next")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxPage).build(), "last")
                .build();
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