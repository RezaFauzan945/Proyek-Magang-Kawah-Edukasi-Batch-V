package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee extends PanacheEntity {

    @Column(name = "name")
    String name;

    @Column(name = "manager_id")
    int manager_id;

}
