package id.rezafauzan.controllers;

import com.google.gson.Gson;
import id.rezafauzan.models.Employee;
import id.rezafauzan.models.EmployeeScore;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("/employee")
public class EmployeeResource {

    @GET
    public Collection<Employee> allemploye(){
        return Employee.listAll();
    }

    @Path("/{id_manager}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesByManagerId(@PathParam("id_manager") long id_manager) {
        //Mengembalikan semua employee dari manager yang dipilih berdasarkan id beserta bawahanya

        List<Employee> employees = Employee.list("SELECT e FROM Employee e WHERE e.id = ?1 OR e.id IN (SELECT id FROM Employee WHERE manager =?1) OR e.manager IN (SELECT id FROM Employee WHERE manager=?1)",id_manager);
        Employee manager = Employee.findById(id_manager);
        employees.add(0, manager);
        if (employees.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new NotFoundException("Tidak ditemukan employee dengan id_manager " + id_manager).getMessage()).build();
        }

        //Hitung rata-rata
            double sum = 0;
            int count = 0;
            for (Employee employee : employees) {
                sum += employee.getEmployeeScore().getScore();
                count++;
            }
            double avg = sum / count;

        List<Map<String, Object>> employeesJson = employees.stream()
                .map(employeez -> {
                    Map<String, Object> employeeJson = new HashMap<>();
                    employeeJson.put("id", employeez.getId());
                    employeeJson.put("name", employeez.getName());
                    employeeJson.put("score", employeez.getEmployeeScore().getScore());
                    return employeeJson;
                })
                .collect(Collectors.toList());

        return Response.ok().entity("{" + new Gson().toJson(employeesJson)+ "," + "\n \"rata-rata-score\" : "+ avg +"}").build();

    }

    @POST
    @Transactional
    public Response tambah(Employee employee) {

        //Menambahkan Data Employee dan membuat Employee Score bersamaan
        //EmployeScore.id = Employee.id

        EmployeeScore es = new EmployeeScore();
        es.setScore(0);
        es.setEmployee(employee);
        EmployeeScore.persist(es);

        employee.setEmployeeScore(es);
        Employee.persist(employee);


        List<Employee> employees = Employee.listAll();
        List<Map<String, Object>> employeesJson = employees.stream()
                .map(employeez -> {
                    Map<String, Object> employeeJson = new HashMap<>();
                    employeeJson.put("id", employeez.getId());
                    employeeJson.put("name", employeez.getName());
                    return employeeJson;
                })
                .collect(Collectors.toList());

        return Response.status(200).entity("{ \"messages\" : \"Employee successfully added\",\"employees\" : " + new Gson().toJson(employeesJson) + "}").build();
    }

    @PUT
    @Transactional
    public Response editManager(@QueryParam("id_employee") long id_employee, @QueryParam("id_manager") long id_manager) {
        //Mengubah Data Manager pada Data Employee
        //Misal Dari tanpa Manager Menjadi Ada , atau mengubah Manager dari employee yang dipilih berdasarkan id_employee

        Employee employee  = Employee.findById(id_employee);

        if(employee != null )
        {
            employee.setManager(id_manager);
            return Response.status(Response.Status.OK).entity(new NotFoundException("Sukses Menambahkan ID Manager Pada Employee dengan ID Employee : " + id_employee ).getMessage()).build();
        }
        else
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new NotFoundException("Employee dengan ID Employee : " + id_employee + " tidak ditemukan!").getMessage()).build();
        }
    }

    @Path("/score")
    @PUT
    @Transactional
    public Response editScore(@QueryParam("id_employee") long id_employee,@QueryParam("score") int score) {
        //Mengubah Data Manager pada Data Employee
        //Misal Dari tanpa Manager Menjadi Ada , atau mengubah Manager dari employee yang dipilih berdasarkan id_employee

        Employee employee  = Employee.findById(id_employee);

        if(employee != null )
        {
            EmployeeScore employeeScore = EmployeeScore.findById(employee.getEmployeeScore().getId());
            if(employeeScore != null )
            {
                employeeScore.setScore(score);
                employee.setEmployeeScore(employeeScore);
                return Response.status(Response.Status.OK).entity("Score untuk Employee Dengan ID " + id_employee + " Telah Diupdate ke " + score).build();
            }
            else
            {
                employeeScore.setId(id_employee);
                employeeScore.setScore(0);
                EmployeeScore.persist(employeeScore);
                return Response.status(Response.Status.BAD_REQUEST).entity(new NotFoundException("Employee dengan ID Employee : " + id_employee + " Belum Mempunyai Data Score Dan Sekarang Telah Dibuatkan silahkan ulangi proses update score").getMessage()).build();
            }
        }
        else
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new NotFoundException("Employee dengan ID Employee : " + id_employee + " tidak ditemukan!").getMessage()).build();
        }
    }

    @GET
    @Path("/average-score")
    public Response getAverageEmployeeScore() {
        //Menampilkan Rata rata dari semua nilai Score di table employeeScore
        List<EmployeeScore> employeeScores = EmployeeScore.listAll();
        double sum = 0;
        for (EmployeeScore score : employeeScores) {
            sum += score.getScore();
        }
        double average = sum / employeeScores.size();
        return Response.status(Response.Status.OK).entity(average).build();
    }

    @GET
    @Path("/average-score/{id_manager}")
    public Response getManagerEmployeesAverageScore(@PathParam("id_manager") long id_manager) {
        //Menampilkan Rata rata dari semua nilai Score di table employeeScore
        Collection<Employee> employees = Employee.find("manager.id", id_manager).list();
        if (employees.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new NotFoundException("Tidak ditemukan employee dengan id_manager " + id_manager).getMessage()).build();
        }

        double sum = 0;
        for (Employee employee : employees)
        {
            sum += employee.getEmployeeScore().getScore();
        }
        double average = sum / employees.size();
        return Response.status(Response.Status.OK).entity(average).build();
    }


}
