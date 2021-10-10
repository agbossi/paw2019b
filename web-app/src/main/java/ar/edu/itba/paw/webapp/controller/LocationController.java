package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.LocationService;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.webapp.caching.LocationCaching;
import ar.edu.itba.paw.webapp.dto.LocationDto;
import ar.edu.itba.paw.webapp.dto.PrepaidDto;
import ar.edu.itba.paw.webapp.form.LocationForm;
import ar.edu.itba.paw.webapp.helpers.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @Autowired
    LocationCaching locationCaching;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getLocations(@QueryParam("page") @DefaultValue("0") Integer page,
                                 @Context Request request) {

        page = (page < 0) ? 0 : page;

        List<LocationDto> locations = locationService.getPaginatedObjects(page).stream()
                .map(LocationDto::fromLocation).collect(Collectors.toList());
        int maxPage = locationService.maxAvailablePage();

        Response.ResponseBuilder ret = CacheHelper.handleResponse(locations, locationCaching,
                new GenericEntity<List<LocationDto>>(locations) {}, "locations", request)
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(), "first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page - 1).build(), "prev")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(), "next")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxPage).build(), "last");
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
