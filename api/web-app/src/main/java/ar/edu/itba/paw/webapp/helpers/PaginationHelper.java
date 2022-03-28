package ar.edu.itba.paw.webapp.helpers;
import ar.edu.itba.paw.interfaces.web.Caching;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public class PaginationHelper {
    private PaginationHelper() { throw new UnsupportedOperationException(); }

    public static<T> Response handlePagination(int page, int maxPage,
                                            String key, UriInfo uriInfo,
                                            List<T> dtos, Caching<T> caching,
                                            GenericEntity<List<T>> listGenericEntity,
                                            Request request) {
        Response.ResponseBuilder resp = CacheHelper.handleResponse(dtos, caching,
                listGenericEntity, key, request)
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(),"first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxPage).build(),"last")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", Math.max(page - 1, 0)).build(),"prev");
        if(page < maxPage) {
            resp.link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(),"next");
        }
        return resp.build();
    }

}
