package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
public class EmployeeScore extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Score")
    int score;

    @OneToOne
    Employee employee;

    //setter getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @JsonbTransient
    //BUG! tanpa JsonbTransient akan membuat proses return employeeScore jadi error employee unserializable
    //kekurangan pake @JsonbTransient adalah employeeScore akan tampil tanpa data employee ditampilkan
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
