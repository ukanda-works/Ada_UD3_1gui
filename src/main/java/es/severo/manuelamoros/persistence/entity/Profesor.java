package es.severo.manuelamoros.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long id;

    @Column(nullable = false, name = "nombre_profesor")
    private String nombreProfesor;

    @Column(nullable = false,name = "apellido_profesor")
    private String apellidoProfesor;

    @Column(name = "email_profesor")
    private String emailProfesor;

    @Column(unique = true, name = "telefono_profesor")
    private String telefonoProfesor;

    @Column(nullable = false, name = "direccion_profesor")
    private String direccionProfesor;

    public Profesor(Long id, String nombreProfesor, String apellidoProfesor, String emailProfesor, String telefonoProfesor, String direccionProfesor) {
        this.id = id;
        this.nombreProfesor = nombreProfesor;
        this.apellidoProfesor = apellidoProfesor;
        this.emailProfesor = emailProfesor;
        this.telefonoProfesor = telefonoProfesor;
        this.direccionProfesor = direccionProfesor;
    }

    public Profesor() {
    }

    public Profesor(String nombreProfesor, String apellidoProfesor, String emailProfesor, String telefonoProfesor, String direccionProfesor) {
        this.nombreProfesor = nombreProfesor;
        this.apellidoProfesor = apellidoProfesor;
        this.emailProfesor = emailProfesor;
        this.telefonoProfesor = telefonoProfesor;
        this.direccionProfesor = direccionProfesor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    public String getEmailProfesor() {
        return emailProfesor;
    }

    public void setEmailProfesor(String emailProfesor) {
        this.emailProfesor = emailProfesor;
    }

    public String getTelefonoProfesor() {
        return telefonoProfesor;
    }

    public void setTelefonoProfesor(String telefonoProfesor) {
        this.telefonoProfesor = telefonoProfesor;
    }

    public String getDireccionProfesor() {
        return direccionProfesor;
    }

    public void setDireccionProfesor(String direccionProfesor) {
        this.direccionProfesor = direccionProfesor;
    }

    public void mostrar() {
        System.out.println("Profesor: " + nombreProfesor+apellidoProfesor+", DNI: "+id+", email :"+ emailProfesor+", telefono: " +telefonoProfesor+", direccion personal: " + direccionProfesor +"\n");
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombreProfesor='" + nombreProfesor + '\'' +
                ", apellidoProfesor='" + apellidoProfesor + '\'' +
                ", emailProfesor='" + emailProfesor + '\'' +
                ", telefonoProfesor='" + telefonoProfesor + '\'' +
                ", direccionProfesor='" + direccionProfesor + '\'' +
                '}';
    }
}
