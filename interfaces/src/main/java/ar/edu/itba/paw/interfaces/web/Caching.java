package ar.edu.itba.paw.interfaces.web;

import javax.ws.rs.core.EntityTag;
import java.util.Collection;

public interface Caching<T> {
    int calculateHash(T element);

    default EntityTag calculateEtag(T element) {
        int h = calculateHash(element);
        return new EntityTag(Integer.toString(h));
    }

    default EntityTag calculateEtag(Collection<T> elements) {
        int hashCode = 1;
        int elemHash;
        for(T element : elements) {
            elemHash = calculateHash(element);
            hashCode = 31 * hashCode + elemHash;
        }
        return new EntityTag(Integer.toString(hashCode));
    }
}
