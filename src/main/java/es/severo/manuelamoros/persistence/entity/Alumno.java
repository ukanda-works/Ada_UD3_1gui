package es.severo.manuelamoros.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_alumno")
    private Long id;

    @Column(nullable = false, name = "nombre_alumno")
    private String nombreAlumno;

    @Column(name = "apellidos_alumno")
    private String apellidosAlumno;

    @Column(unique = true,name = "telefono_alumno")
    private String telefonoAlumno;

    @Column(name = "email_alumno")
    private String emailAlumno;

    @Column(nullable = false,name = "direccion_alumno")
    private String direccionAlumno;

    @Column(nullable = false,unique = true,name = "nia_alumno")
    private String niaAlumno;

    @ManyToOne
    @JoinColumn(name = "id_clase")
    private Clase clase;

    @ManyToMany(fetch=FetchType.EAGER,
            cascade = {
                    CascadeType.ALL
            })
    @JoinTable(name = "alumnos_has_Asignatura",
            joinColumns = {@JoinColumn(name = "id_alumno")},
            inverseJoinColumns = {@JoinColumn(name = "id_asignatura")}
    )
    private List<Asignatura> asignaturas;

    public Alumno(Long id, String nombreAlumno, String apellidosAlumno, String telefonoAlumno, String emailAlumno, String direccionAlumno, String niaAlumno, Clase clase, List<Asignatura> asignaturas) {
        this.id = id;
        this.nombreAlumno = nombreAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.telefonoAlumno = telefonoAlumno;
        this.emailAlumno = emailAlumno;
        this.direccionAlumno = direccionAlumno;
        this.niaAlumno = niaAlumno;
        this.clase = clase;
        this.asignaturas = asignaturas;
    }

    public Alumno(String nombreAlumno, String direccionAlumno, String telefonoAlumno, Clase clase) {
        this.nombreAlumno = nombreAlumno;
        this.direccionAlumno = direccionAlumno;
        this.clase = clase;
        this.telefonoAlumno = telefonoAlumno;
    }

    public Alumno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidosAlumno() {
        return apellidosAlumno;
    }

    public void setApellidosAlumno(String apellidosAlumno) {
        this.apellidosAlumno = apellidosAlumno;
    }

    public String getTelefonoAlumno() {
        return telefonoAlumno;
    }

    public void setTelefonoAlumno(String telefonoAlumno) {
        this.telefonoAlumno = telefonoAlumno;
    }

    public String getEmailAlumno() {
        return emailAlumno;
    }

    public void setEmailAlumno(String emailAlumno) {
        this.emailAlumno = emailAlumno;
    }

    public String getNiaAlumno() {
        return niaAlumno;
    }

    public void setNiaAlumno(String niaAlumno) {
        this.niaAlumno = niaAlumno;
    }

    public String getDireccionAlumno() {
        return direccionAlumno;
    }

    public void setDireccionAlumno(String direccionAlumno) {
        this.direccionAlumno = direccionAlumno;
    }
    public Clase getClaseClase(){
        return clase;
    }
    public String getClase() {
        return clase.getNombreClase();
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public String getAsignaturas() {
        if (asignaturas==null){
            return "sin matriculaciones";
        }else{
            String listAsignaturas = "";
            for (Asignatura a: asignaturas) {
                listAsignaturas+= a.getNombreAsignatura();
                listAsignaturas+= " ,";
            }
            return listAsignaturas;
        }
    }
    public List<Asignatura> getAsignaturasAsig() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void addAsigantura(Asignatura a){
        this.asignaturas.add(a);

    }

    public void mostrar() {
        String listAsignaturas = "";
        for (Asignatura a: asignaturas) {
            listAsignaturas+= a.getNombreAsignatura();
            listAsignaturas+= " ,";
        }
        System.out.println( "Alumno: "+nombreAlumno+" "+apellidosAlumno+" NIA: "+id+
                ", telefono:" + telefonoAlumno+
                ", email:" + emailAlumno+
                ", direccion:" + direccionAlumno+
                ", clase="+clase.getNombreClase()+
                ", asignaturas=" +listAsignaturas);
    }


    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombreAlumno='" + nombreAlumno + '\'' +
                ", apellidosAlumno='" + apellidosAlumno + '\'' +
                ", telefonoAlumno='" + telefonoAlumno + '\'' +
                ", emailAlumno='" + emailAlumno + '\'' +
                ", direccionAlumno='" + direccionAlumno + '\'' +
                ", clase=" + clase +
                ", asignaturas=" + asignaturas +
                '}';
    }
    public void delAsig(Asignatura asignatura){
        this.asignaturas = this.asignaturas.stream().filter(asignatura1 -> !asignatura1.getNombreAsignatura().equals(asignatura.getNombreAsignatura())).collect(Collectors.toList());

    }
}
