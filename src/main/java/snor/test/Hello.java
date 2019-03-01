package snor.test;

import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class Hello {
    @Path("getDynamoDbTable")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDynamoDbTable() {
        List<String> table_names = AWSTest.getDynamoDBTables();
        JSONObject json = new JSONObject();
        if (table_names == null || table_names.size() == 0) {
            table_names = new ArrayList<>();
            table_names.add("No tables found!");
            return Response.status(Response.Status.OK).entity(json.toJSONString(table_names)).build();
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString(table_names)).build();
    }
}