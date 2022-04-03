package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.PrepaidService;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.exceptions.DuplicateEntityException;
import ar.edu.itba.paw.model.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.webapp.caching.PrepaidCaching;
import ar.edu.itba.paw.webapp.dto.PrepaidDto;
import ar.edu.itba.paw.webapp.form.PrepaidForm;
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
@Path("prepaids")
public class PrepaidController {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private PrepaidCaching prepaidCaching;

    /**
     * Returns paginated list of prepaids for ADMIN to manage
     * @param page
     * @return list of Prepaids
     */
    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getPrepaids(@QueryParam("page") @DefaultValue("0") Integer page,
                                @QueryParam("mode") @DefaultValue("paged") final String mode,
                                @Context Request request) {
        page = (page < 0) ? 0 : page;

        if(mode.equals("all")) {
            List<PrepaidDto> prepaids = prepaidService.getPrepaids().stream()
                    .map(PrepaidDto::fromPrepaid).collect(Collectors.toList());

            return CacheHelper.handleResponse(prepaids, prepaidCaching, new GenericEntity<List<PrepaidDto>>(prepaids) {}, "prepaids", request)
                    .build();
        } else {
            List<PrepaidDto> prepaids = prepaidService.getPaginatedObjects(page).stream()
                    .map(PrepaidDto::fromPrepaid).collect(Collectors.toList());
            int maxPage = prepaidService.maxAvailablePage() - 1;

            return PaginationHelper.handlePagination(page, maxPage, "prepaids", uriInfo,
                    prepaids, prepaidCaching, new GenericEntity<List<PrepaidDto>>(prepaids) {},
                    request);
        }
    }

    /**
     * Lets ADMIN delete a prepaid
     * @param name
     * @return
     * @throws EntityNotFoundException
     */
    @DELETE
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deletePrepaid(@QueryParam("prepaid") final String name) throws EntityNotFoundException {
        prepaidService.deletePrepaid(name);
        return Response.noContent().build();
    }

    /**
     * Lets ADMIN add prepaid to application
     * @param form
     * @return
     * @throws DuplicateEntityException
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPrepaid(@Valid PrepaidForm form) throws DuplicateEntityException {
        Prepaid prepaid = prepaidService.createPrepaid(form.getName());
        return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(prepaid.getName()).build()).build();
    }
}
