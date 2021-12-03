package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.LocationService;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.webapp.caching.LocationCaching;
import ar.edu.itba.paw.webapp.dto.LocationDto;
import ar.edu.itba.paw.webapp.dto.PrepaidDto;
import ar.edu.itba.paw.webapp.form.LocationForm;
import ar.edu.itba.paw.webapp.helpers.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@Path("locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @Autowired
    LocationCaching locationCaching;

    @Autowired
    MessageSource messageSource;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getLocations(@QueryParam("page") @DefaultValue("0") Integer page,
                                 @Context Request request) {

        int i = 0 / 0;
        page = (page < 0) ? 0 : page;

        String pageStr = messageSource.getMessage("page",null, Locale.getDefault());
        String prev = messageSource.getMessage("previous",null, Locale.getDefault());
        String next = messageSource.getMessage("next",null, Locale.getDefault());
        String last = messageSource.getMessage("last",null, Locale.getDefault());
        String first = messageSource.getMessage("first",null, Locale.getDefault());

        List<LocationDto> locations = locationService.getPaginatedObjects(page).stream()
                .map(LocationDto::fromLocation).collect(Collectors.toList());
        int maxPage = locationService.maxAvailablePage();

        Response.ResponseBuilder ret = CacheHelper.handleResponse(locations, locationCaching,
                new GenericEntity<List<LocationDto>>(locations) {}, "locations", request)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, 0).build(), first)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, page - 1).build(), prev)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, page + 1).build(), next)
                .link(uriInfo.getAbsolutePathBuilder().queryParam(pageStr, maxPage).build(), last);
        return ret.build();
    }

    @DELETE
    @Path("/{name}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteLocation(@PathParam("name") final String name) {
        locationService.deleteLocation(name);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLocation(@Valid final LocationForm form) {
        Location location = locationService.createLocation(form.getName());
        return Response.created(uriInfo.getAbsolutePathBuilder().
                path(location.getLocationName()).build()).build();
    }
}
