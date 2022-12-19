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

    @Column(nullable = false,unique = true,name = "dni_profesor")
    private String dniProfesor;

    public Profesor(Long id, String nombreProfesor, String apellidoProfesor, String emailProfesor, String telefonoProfesor, String direccionProfesor, String dniProfesor) {
        this.id = id;
        this.nombreProfesor = nombreProfesor;
        this.apellidoProfesor = apellidoProfesor;
        this.emailProfesor = emailProfesor;
        this.telefonoProfesor = telefonoProfesor;
        this.direccionProfesor = direccionProfesor;
        this.dniProfesor = dniProfesor;
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
        if (emailProfesor == null)
            return "no dispoble";
        return emailProfesor;
    }

    public void setEmailProfesor(String emailProfesor) {
        this.emailProfesor = emailProfesor;
    }

    public String getTelefonoProfesor() {
        if (telefonoProfesor==null)
            return "no dispoble";
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

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public void mostrar() {
        System.out.println("Profesor: " + nombreProfesor+apellidoProfesor+", DNI: "+dniProfesor+", email :"+ emailProfesor+", telefono: " +telefonoProfesor+", direccion personal: " + direccionProfesor +"\n");
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
                ", dniProfesor='" + dniProfesor + '\'' +
                '}';
    }
}
