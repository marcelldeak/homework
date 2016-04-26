package hu.codingmentor.working.simulation.rest;

import hu.codingmentor.working.simulation.service.StatisticsService;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/statistics")
public class StatisticsRESTService {

    @Inject
    StatisticsService statisticsService;

    @GET
    public String getStatistics() {
        StringBuilder sb = new StringBuilder("{ ");
        for (Map.Entry<Long, Boolean> entry : statisticsService.getStatistics().entrySet()) {
            sb.append("{");
            sb.append(" \"id\" : ").append(entry.getKey());
            sb.append(" \"success\" : ").append(entry.getValue());
            sb.append(" } ");
        }
        sb.append("}");
        
        return sb.toString();
    }
}
