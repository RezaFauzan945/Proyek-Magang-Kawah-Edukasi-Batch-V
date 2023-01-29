package id.rezafauzan.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Factorial extends PanacheEntity {

    @Column(name = "nilai_n")
    int n ;

    @Column(name = "factorial_dari_n")
    long factorial;


    //setter getter
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public long getFactorial() {
        return factorial;
    }

    public void setFactorial(long factorial) {
        this.factorial = factorial;
    }

}
