package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Manager extends PanacheEntity {

    @Column(name = "name")
    String name;

}
