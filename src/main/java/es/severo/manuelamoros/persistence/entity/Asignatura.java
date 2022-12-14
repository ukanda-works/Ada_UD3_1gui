package es.severo.manuelamoros.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private Long id;

    @Column(nullable = false, name = "nombre_asignatura")
    private String nombreAsignatura;



    public Asignatura(Long id, String nombreAsignatura) {
        this.id = id;
        this.nombreAsignatura = nombreAsignatura;
    }

    public Asignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Asignatura() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public void mostrar() {
        System.out.println( "Asignatura: "+nombreAsignatura +", id:"+ id+"\n");
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id=" + id +
                ", nombreAsignatura='" + nombreAsignatura + '\'' +
                '}';
    }
}
