package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
public class Employee extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "id_manager")
    long manager;

    @OneToOne
    EmployeeScore employeeScore;


    //setter getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    @JsonbTransient
    //BUG! tanpa JsonbTransient akan membuat proses return employee jadi error EmployeeScore unserializable
    //kekurangan pake @JsonbTransient adalah employee akan tampil tanpa data EmployeeScore ditampilkan
    public EmployeeScore getEmployeeScore() {
        return employeeScore;
    }

    public void setEmployeeScore(EmployeeScore employeeScore) {
        this.employeeScore = employeeScore;
    }

}
