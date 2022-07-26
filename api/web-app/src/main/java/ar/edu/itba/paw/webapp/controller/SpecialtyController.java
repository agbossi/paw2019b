package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.SpecialtyService;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.model.exceptions.DuplicateEntityException;
import ar.edu.itba.paw.model.exceptions.EntityDependencyException;
import ar.edu.itba.paw.model.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.webapp.caching.SpecialtyCaching;
import ar.edu.itba.paw.webapp.dto.SpecialtyDto;
import ar.edu.itba.paw.webapp.form.SpecialtyForm;
import ar.edu.itba.paw.webapp.helpers.CacheHelper;
import ar.edu.itba.paw.webapp.helpers.PaginationHelper;
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

    @Autowired
    private SpecialtyCaching specialtyCaching;

    /**
     * Returns paginated list of specialties for ADMIN to manage
     * @param page
     * @return list of Specialties
     */
    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getSpecialties(@QueryParam("page") @DefaultValue("0") Integer page,
                                   @QueryParam("mode") @DefaultValue("paged") final String mode,
                                   @Context Request request) {
        page = (page < 0) ? 0 : page;

        if(mode.equals("all")) {
            List<SpecialtyDto> specialties = specialtyService.getSpecialties().stream()
                    .map(SpecialtyDto::fromSpecialty).collect(Collectors.toList());

            return CacheHelper.handleResponse(specialties, specialtyCaching,
                    new GenericEntity<List<SpecialtyDto>>(specialties) {}, "specialties", request)
                    .build();
        } else {
            List<SpecialtyDto> specialties = specialtyService.getPaginatedObjects(page).stream()
                    .map(SpecialtyDto::fromSpecialty).collect(Collectors.toList());
            int maxPage = specialtyService.maxAvailablePage() - 1;
            return PaginationHelper.handlePagination(page, maxPage, "specialties",
                    uriInfo, specialties, specialtyCaching,
                    new GenericEntity<List<SpecialtyDto>>(specialties) {}, request);
        }
    }

    /**
     * Lets ADMIN delete specialty. If a doctor belongs to the specialty, it throws
     * EntityDependencyException.
     * @param specialty
     * @return
     * @throws EntityNotFoundException
     * @throws EntityDependencyException
     */
    @DELETE
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteSpecialty(@QueryParam("specialty") final String specialty)
            throws EntityNotFoundException, EntityDependencyException {
        specialtyService.deleteSpecialty(specialty);
        return Response.noContent().build();
    }

    /**
     * Lets ADMIN add specialty to application
     * @param form
     * @return
     * @throws DuplicateEntityException
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSpecialty(@Valid final SpecialtyForm form) throws DuplicateEntityException {
        Specialty specialty = specialtyService.createSpecialty(form.getName());
        return Response.created(uriInfo.getAbsolutePathBuilder().
                path(specialty.getSpecialtyName()).build()).build();
    }
}