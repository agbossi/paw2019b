package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.LocationService;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.exceptions.DuplicateEntityException;
import ar.edu.itba.paw.model.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.webapp.caching.LocationCaching;
import ar.edu.itba.paw.webapp.dto.LocationDto;;
import ar.edu.itba.paw.webapp.form.LocationForm;
import ar.edu.itba.paw.webapp.helpers.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @Autowired
    MessageSource messageSource;

    @Context
    private UriInfo uriInfo;

    //TODO: Use: for admin locations
    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getLocations(@QueryParam("page") @DefaultValue("0") Integer page,
                                 @Context Request request) {

        page = (page < 0) ? 0 : page;

        List<LocationDto> locations = locationService.getPaginatedObjects(page).stream()
                .map(LocationDto::fromLocation).collect(Collectors.toList());
        int maxPage = locationService.maxAvailablePage() - 1;

        Response.ResponseBuilder ret = CacheHelper.handleResponse(locations, locationCaching,
                new GenericEntity<List<LocationDto>>(locations) {}, "locations", request)
                .header("Access-Control-Expose-Headers", "X-max-page")
                .header("X-max-page", maxPage);
        return ret.build();
    }

    // TODO: Use: In admin clinics
    @GET
    @Path("/all")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getAllLocations(@Context Request request) {
        List<LocationDto> locations = locationService.getLocations().stream()
                .map(LocationDto::fromLocation).collect(Collectors.toList());

        Response.ResponseBuilder ret = CacheHelper.handleResponse(locations, locationCaching,
                        new GenericEntity<List<LocationDto>>(locations) {}, "locations", request);
        return ret.build();
    }


    //TODO: Use: for admin to delete location
    @DELETE
    @Path("/{name}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteLocation(@PathParam("name") final String name) throws EntityNotFoundException {
        locationService.deleteLocation(name);
        return Response.noContent().build();
    }

    // TODO: Use: for admin to add loaction
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLocation(@Valid final LocationForm form) throws DuplicateEntityException {
        Location location = locationService.createLocation(form.getName());
        return Response.created(uriInfo.getAbsolutePathBuilder().
                path(location.getLocationName()).build()).build();
    }
}
