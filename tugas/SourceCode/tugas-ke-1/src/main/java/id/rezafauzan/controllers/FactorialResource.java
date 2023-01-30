package id.rezafauzan.controllers;

import id.rezafauzan.models.Factorial;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/factorial")
public class FactorialResource {

    @GET
    @Transactional
    public Response factorial(@QueryParam("bilangan") int bilangan )
    {
        long factorial = 1;
        for (int i = 1; i <= bilangan; i++) {
            factorial *= i;
        }

        Factorial fact = new Factorial();

        fact.setN(bilangan);
        fact.setFactorial(factorial);
        Factorial.persist(fact);

        return Response.status(200).entity("{ \"messages\" : \"Factorial successfully calculated\",\"nilai-n\" : " + bilangan + ", \"factorial-dari-n\" : "+ factorial +"}").build();
    }

}
