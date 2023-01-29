package id.rezafauzan.controllers;

import id.rezafauzan.models.Manager;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Collection;


@Path("/manager")
public class ManagerResource {

    @GET
    public Collection<Manager> allmanager(){
        return Manager.listAll();
    }

    @POST
    @Transactional
    public Collection<Manager> tambah(Manager manager) {
        Manager.persist(manager);
        return Manager.listAll();
    }

}
