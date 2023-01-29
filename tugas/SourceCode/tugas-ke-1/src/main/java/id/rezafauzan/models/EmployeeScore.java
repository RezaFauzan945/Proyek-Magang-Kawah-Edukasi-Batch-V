package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class EmployeeScore extends PanacheEntity {

    @Column(name = "Score")
    int score;

    @OneToOne
    Employee employee;

    //setter getter
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
