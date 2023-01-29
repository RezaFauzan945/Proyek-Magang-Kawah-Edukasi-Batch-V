package id.rezafauzan.controllers;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/factorial")
public class FactorialResource {

    @GET
    @Transactional
    public int factorial()
    {
        int factorial = 1;
        int bilangan =  6;
        for (int i = 1; i <= bilangan; i++) {
            factorial *= i;
        }

        return factorial;
    }

}
