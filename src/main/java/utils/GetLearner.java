package utils;


import entity.Learner;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("/")
public class GetLearner {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Learner getPageinJSON(@QueryParam("type") String type, @QueryParam("codeofdiplom") String codeofdiplom, @QueryParam("codeofdoc") String codeofdoc) throws IOException {
        Learner result = null;
        if (type == null | codeofdoc == null) {
            Response.status(404);
        } else {

            switch (type) {
                case "diplom":
                    result = LearnerDAO.getDiplomLearner(codeofdiplom, codeofdoc);
                    Response.status(200);
                    break;
                case "dodiplom":
                    result = LearnerDAO.getDODiplomLearner(codeofdoc);
                    Response.status(200);
                    break;
                case "udo":
                    result = LearnerDAO.getUdoLearner(codeofdiplom, codeofdoc);
                    Response.status(200);
                    break;
                case "doudo":
                    result = LearnerDAO.getDUUdoLearner(codeofdoc);
                    Response.status(200);
                    break;
            }
        }

        return result;
    }
}

