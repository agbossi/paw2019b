package ar.edu.itba.paw.webapp.helpers;

import ar.edu.itba.paw.interfaces.web.Caching;

import javax.ws.rs.core.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheHelper<T> {
    private static final Map<String, Integer> cacheTimes = getTimes();

    //TODO: ajustar tiempos
    private static Map<String, Integer> getTimes() {
        Map<String, Integer> map = new HashMap<>();
        map.put("doctors", 10);
        map.put("license", 10);
        map.put("profileImage", 10);
        map.put("doctorsClinics", 10);
        map.put("doctorsClinic", 10);
        map.put("schedule", 10);
        map.put("appointments", 10);
        map.put("clinics", 10);
        map.put("clinic", 10);
        map.put("clinicPrepaids", 10);
        map.put("locations", 10);
        map.put("specialties", 10);
        map.put("prepaids", 10);
        map.put("patient", 10);
        map.put("favorites", 10);

        return map;
    }

    private CacheHelper() {
        throw new UnsupportedOperationException();
    }

    private static CacheControl getCacheControl(String key) {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(cacheTimes.get(key));
        return cc;
    }

    private static <T> EntityTag getTag(T cacheable, Caching<T> service) {
        return service.calculateEtag(cacheable);
    }

    private static <T> EntityTag getTag(List<T> cacheable, Caching<T> service) {
        return service.calculateEtag(cacheable);
    }

    private static EntityTag verifyTag(EntityTag etag, Request request) {
        Response.ResponseBuilder builder = request.evaluatePreconditions(etag);

        //si etag no matchea, builder es null y hay que mandar entidad
        return builder == null ? etag : null;
    }

    private static <T> EntityTag evaluateTag(T cacheable, Caching<T> service, Request request) {
        EntityTag etag = getTag(cacheable, service);
        return verifyTag(etag, request);
    }

    private static <T> EntityTag evaluateTag(List<T> cacheable, Caching<T> service, Request request) {
        EntityTag etag = getTag(cacheable, service);
        return verifyTag(etag, request);
    }

    private static Response.ResponseBuilder handleCache(EntityTag etag, CacheControl cc) {
        if (etag == null) {
            return Response.status(Response.Status.NOT_MODIFIED).cacheControl(cc);
        }
        //tag no matchea o no hay header if none match
        return null;
    }

    public static <T> Response.ResponseBuilder handleResponse(T cacheable, Caching<T> service, String key, Request request) {
        CacheControl cc = getCacheControl(key);
        EntityTag etag = evaluateTag(cacheable, service, request);
        Response.ResponseBuilder isValid = handleCache(etag, cc);

        if(isValid != null) {
            return isValid;
        }

        return Response.ok(cacheable).cacheControl(cc).tag(etag);
    }

    public static <T> Response.ResponseBuilder handleResponse(List<T> cacheable, Caching<T> service, String key, Request request) {
        CacheControl cc = getCacheControl(key);
        EntityTag etag = evaluateTag(cacheable, service, request);
        Response.ResponseBuilder isValid = handleCache(etag, cc);

        if(isValid != null) {
            return isValid;
        }

        return Response.ok(new GenericEntity<List<T>>(cacheable) {}).cacheControl(cc).tag(etag);
    }
}
