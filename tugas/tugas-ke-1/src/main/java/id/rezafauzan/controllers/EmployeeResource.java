package id.rezafauzan.controllers;

import id.rezafauzan.models.Employee;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Collection;


@Path("/employee")
public class EmployeeResource {

    @GET
    public Collection<Employee> allemploye(){
        return Employee.listAll();
    }

    @POST
    @Transactional
    public Collection<Employee> tambah(Employee employee) {
        Employee.persist(employee);
        return Employee.listAll();
    }

}
