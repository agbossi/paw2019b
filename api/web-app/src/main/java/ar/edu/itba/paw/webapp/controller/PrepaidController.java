package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.PrepaidService;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.webapp.caching.PrepaidCaching;
import ar.edu.itba.paw.webapp.dto.PrepaidDto;
import ar.edu.itba.paw.webapp.form.PrepaidForm;
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
@Path("prepaids")
public class PrepaidController {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private PrepaidCaching prepaidCaching;

    @Autowired
    private MessageSource messageSource;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getPrepaids(@QueryParam("page") @DefaultValue("0") Integer page,
                                @Context Request request) {
        page = (page < 0) ? 0 : page;
        List<PrepaidDto> prepaids = prepaidService.getPaginatedObjects(page).stream()
                .map(PrepaidDto::fromPrepaid).collect(Collectors.toList());
        int maxPage = prepaidService.maxAvailablePage();

        return CacheHelper.handleResponse(prepaids, prepaidCaching, new GenericEntity<List<PrepaidDto>>(prepaids) {}, "prepaids", request)
                .header("Access-Control-Expose-Headers", "X-max-page")
                .header("X-max-page", maxPage)
                .build();
        /*return Response.ok(new GenericEntity<List<PrepaidDto>>(prepaids) {})
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(), "first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page - 1).build(), "prev")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(), "next")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxPage).build(), "last")
                .build(); */
    }

    //TODO: Use: admin for adding prepaid to clinic
    @GET
    @Path("/all")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getAllPrepaids(@Context Request request) {
        List<PrepaidDto> prepaids = prepaidService.getPrepaids().stream()
                .map(PrepaidDto::fromPrepaid).collect(Collectors.toList());

        return CacheHelper.handleResponse(prepaids, prepaidCaching, new GenericEntity<List<PrepaidDto>>(prepaids) {}, "prepaids", request)
                .build();
    }

    @DELETE
    @Path("/{name}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteLocation(@PathParam("name") final String name) {
        prepaidService.deletePrepaid(name);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPrepaid(@Valid PrepaidForm form) {
        Prepaid prepaid = prepaidService.createPrepaid(form.getName());
        return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(prepaid.getName()).build()).build();
    }
}
