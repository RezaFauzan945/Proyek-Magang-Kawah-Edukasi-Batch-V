package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee extends PanacheEntity {

    @Column(name = "name")
    String name;

    @ManyToOne
    Manager manager;

    @OneToOne
    EmployeeScore employeeScore;

    //setter getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
