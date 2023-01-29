package id.rezafauzan.controllers;

import id.rezafauzan.models.Employee;
import id.rezafauzan.models.EmployeeScore;
import id.rezafauzan.models.Manager;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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

        //Menambahkan Data Employe dan membuat Employe Score bersamaan
        //EmployeScore.id = Employee.id

        EmployeeScore es = new EmployeeScore();
        es.setScore(0);
        EmployeeScore.persist(es);

        employee.setEmployeeScore(es);
        Employee.persist(employee);

        return Employee.listAll();
    }

    @PUT
    @Transactional
    public Response editManager(@QueryParam("id_employee") long id_employee, @QueryParam("id_manager") long id_manager) {
        //Menambahkan Data Employe dan membuat Employe Score bersamaan
        //EmployeScore.id = Employee.id

        Employee employee  = Employee.findById(id_employee);

        if(employee != null )
        {
            Manager manager = Manager.findById(id_manager);

            if(manager != null )
            {
                employee.setManager(manager);
                Employee.persist(employee);

                employee = Employee.findById(id_employee);
                manager = Manager.findById(id_manager);
                EmployeeScore employeeScore = EmployeeScore.findById(employee.getEmployeeScore().id);
                employee.setManager(manager);
                employee.setEmployeeScore(employeeScore);

                return Response.ok(employee).build();
            }
            else
            {
                return Response.status(Response.Status.BAD_REQUEST).entity(new NotFoundException("Manager dengan ID Manager : " + id_manager + " tidak ditemukan!").getMessage()).build();
            }

        }
        else
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new NotFoundException("Employee dengan ID Employee : " + id_employee + " tidak ditemukan!").getMessage()).build();
        }
    }

}
