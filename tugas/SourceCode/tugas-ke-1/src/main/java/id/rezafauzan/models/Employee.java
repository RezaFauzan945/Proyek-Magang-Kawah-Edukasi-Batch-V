package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
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

    @JsonbTransient
    //BUG! tanpa JsonbTransient akan membuat proses return employee jadi error manager unserializable
    //kekurangan pake @JsonbTransient adalah employee akan tampil tanpa data manager ditampilkan
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public EmployeeScore getEmployeeScore() {
        return employeeScore;
    }

    public void setEmployeeScore(EmployeeScore employeeScore) {
        this.employeeScore = employeeScore;
    }

}
