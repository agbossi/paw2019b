package ar.edu.itba.paw.webapp.controller;


import ar.edu.itba.paw.interfaces.service.ClinicService;
import ar.edu.itba.paw.interfaces.service.LocationService;
import ar.edu.itba.paw.interfaces.service.PrepaidToClinicService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.webapp.caching.ClinicCaching;
import ar.edu.itba.paw.webapp.dto.ClinicDto;
import ar.edu.itba.paw.webapp.dto.PrepaidDto;
import ar.edu.itba.paw.webapp.form.ClinicForm;
import ar.edu.itba.paw.webapp.form.PrepaidForm;
import ar.edu.itba.paw.webapp.form.PrepaidToClinicForm;
import ar.edu.itba.paw.webapp.helpers.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("clinics")
public class ClinicController {

    @Autowired
    ClinicService clinicService;

    @Autowired
    PrepaidToClinicService prepaidToClinicService;

    @Autowired
    LocationService locationService;

    @Autowired
    ClinicCaching clinicCaching;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getClinics(@QueryParam("page") @DefaultValue("0") Integer page) {
        page = (page < 0) ? 0 : page;

        List<ClinicDto> clinics = clinicService.getPaginatedObjects(page).stream()
                .map(c -> ClinicDto.fromClinic(c, uriInfo)).collect(Collectors.toList());
        int maxPage = clinicService.maxAvailablePage();
        return Response.ok(new GenericEntity<List<ClinicDto>>(clinics) {})
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(), "first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page - 1).build(), "prev")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(), "next")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxPage).build(), "last")
                .build();
    }

    //TODO me esta devolviendo ids negativos wtf
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response createClinic(final ClinicForm clinicForm) {
        Location location = locationService.getLocationByName(clinicForm.getLocation());
        Clinic clinic = clinicService.createClinic(clinicForm.getName(), clinicForm.getAddress(), location);
        return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(clinic.getId())).build()).build();
    }

    @PUT
    @Path("{clinicId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response updateClinic(@PathParam("clinicId") final Integer clinicId, final ClinicForm clinicForm) {
        Clinic clinic = clinicService.getClinicById(clinicId);
        if(clinic != null) {
            clinicService.updateClinic(clinicId, clinicForm.getName(), clinicForm.getAddress(), clinicForm.getLocation());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @GET
    @Path("{clinicId}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getClinic(@PathParam("clinicId") final Integer clinicId,
                              @Context Request request) {
        Clinic clinic = clinicService.getClinicById(clinicId);
        if(clinic != null) {
            ClinicDto dto = ClinicDto.fromClinic(clinic, uriInfo);
            return CacheHelper.handleResponse(dto, clinicCaching, "clinic", request).build();
            //return Response.ok(dto).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @DELETE
    @Path("{clinicId}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteClinic(@PathParam("clinicId") final Integer clinicId) {
        clinicService.deleteClinic(clinicId);
        return Response.noContent().build();
    }

    @GET
    @Path("{clinicId}/clinicPrepaids")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getClinicPrepaids(@PathParam("clinicId") final Integer clinicId,
                                      @QueryParam("page") @DefaultValue("0") Integer page) {
        page = (page < 0) ? 0 : page;

        Clinic clinic = clinicService.getClinicById(clinicId);
        if(clinic != null) {
            List<PrepaidDto> prepaids = prepaidToClinicService.getPrepaidsForClinic(clinicId, page)
                    .stream().map(PrepaidDto::fromPrepaid).collect(Collectors.toList());
            return Response.ok(new GenericEntity<List<PrepaidDto>>(prepaids) {}).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @POST
    @Path("{clinicId}/clinicPrepaids")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response addPrepaidToClinic(@PathParam("clinicId") final Integer clinicId,
                                       final PrepaidToClinicForm form) {
        Clinic clinic = clinicService.getClinicById(clinicId);
        if(clinic != null) {
            if(clinicId == form.getClinic()) {
                prepaidToClinicService.addPrepaidToClinic(form.getPrepaid(), clinicId);
                return Response.created(uriInfo.getAbsolutePath()).build();
            }
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{clinicId}/clinicPrepaids")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response removePrepaidFromClinic(@PathParam("clinicId") final Integer clinicId,
                                            @QueryParam("prepaid") final String prepaid) {
        Clinic clinic = clinicService.getClinicById(clinicId);
        if(clinic != null) {
            prepaidToClinicService.deletePrepaidFromClinic(prepaid, clinicId);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
