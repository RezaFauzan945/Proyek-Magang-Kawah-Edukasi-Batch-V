package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Manager extends PanacheEntity {

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "manager")
    public List<Employee> employees;

    //setter getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
