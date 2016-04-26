package hu.codingmentor.working.simulation.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("statistics/format")
public class StatisticsMapWriter implements MessageBodyWriter<Map<Long, Boolean>> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Map.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Map<Long, Boolean> statistics, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return 1;
    }

    @Override
    public void writeTo(Map<Long, Boolean> statistics, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
        StringBuilder sb = new StringBuilder("{ ");
        for (Map.Entry<Long, Boolean> entry : statistics.entrySet()) {
            sb.append("{");
            sb.append(" \"id\" : ").append(entry.getKey());
            sb.append(" \"success\" : ").append(entry.getValue());
            sb.append(" } ");
        }
        sb.append("}");

        entityStream.write(sb.toString().getBytes());
    }

}
