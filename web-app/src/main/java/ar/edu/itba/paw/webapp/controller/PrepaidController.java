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

        String pageStr = messageSource.getMessage("page",null, Locale.getDefault());
        String prev = messageSource.getMessage("previous",null, Locale.getDefault());
        String next = messageSource.getMessage("next",null, Locale.getDefault());
        String last = messageSource.getMessage("last",null, Locale.getDefault());
        String first = messageSource.getMessage("first",null, Locale.getDefault());

        return CacheHelper.handleResponse(prepaids, prepaidCaching, new GenericEntity<List<PrepaidDto>>(prepaids) {}, "prepaids", request)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, 0).build(), first)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, page - 1).build(), prev)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, page + 1).build(), next)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, maxPage).build(), last)
                .build();
        /*return Response.ok(new GenericEntity<List<PrepaidDto>>(prepaids) {})
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(), "first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page - 1).build(), "prev")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(), "next")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxPage).build(), "last")
                .build(); */
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
