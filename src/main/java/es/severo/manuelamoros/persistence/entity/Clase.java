package es.severo.manuelamoros.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clase")
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase")
    private Long id;

    @Column(nullable = false, name = "nombre_clase")
    private String nombreClase;

    @Column(nullable = false)
    private String aula;

    @OneToOne
    private Profesor tutor;

    @OneToMany(mappedBy = "clase", orphanRemoval = true)
    private List<Alumno> alumnos = new ArrayList<>();

    public Clase(Long id, String nombreClase, String aula, Profesor tutor, List<Alumno> alumnos) {
        this.id = id;
        this.nombreClase = nombreClase;
        this.aula = aula;
        this.tutor = tutor;
        this.alumnos = alumnos;
    }

    public Clase(String nombreClase, String aula, Profesor tutor) {
        this.nombreClase = nombreClase;
        this.aula = aula;
        this.tutor = tutor;
    }

    public Clase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getTutor() {
        return tutor.getNombreProfesor()+" "+tutor.getApellidoProfesor();
    }
    public Profesor getTutorTutor() {
        return tutor;
    }
    public void setTutor(Profesor tutor) {
        this.tutor = tutor;
    }



    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void mostrar() {
        System.out.println( "Clase: "+ nombreClase+", aula: "+aula+", Tutor: " + tutor.getNombreProfesor()+ tutor.getApellidoProfesor()+", id=" + id+"\n");
    }
/*
    @Override
    public String toString() {
        return "Clase{" +
                "id=" + id +
                ", nombreClase='" + nombreClase + '\'' +
                ", aula='" + aula + '\'' +
                ", tutor=" + tutor +
                ", alumnos=" + alumnos +
                '}';
    }*/
}
