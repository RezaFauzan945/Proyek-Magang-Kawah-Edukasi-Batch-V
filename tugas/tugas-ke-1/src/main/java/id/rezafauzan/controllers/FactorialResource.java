package id.rezafauzan.controllers;

import id.rezafauzan.models.Factorial;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/factorial")
public class FactorialResource {

    @GET
    @Transactional
    public int factorial(@QueryParam("bilangan") int bilangan )
    {
        int factorial = 1;
        for (int i = 1; i <= bilangan; i++) {
            factorial *= i;
        }

        Factorial fact = new Factorial();

        fact.setN(bilangan);
        fact.setFactorial(factorial);
        Factorial.persist(fact);

        return factorial;
    }

}
