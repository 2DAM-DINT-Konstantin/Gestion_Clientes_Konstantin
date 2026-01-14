package dto;

import java.util.Date;

public class Cliente {
    private String nombre;
    private String apellidos;
    private Date fechaAlta;
    private String provincia;
    
    public Cliente(String nombre, String apellidos, Date fechaAlta, String provincia) {
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.fechaAlta = fechaAlta;
    this.provincia = provincia;
 }
    
    
 // Getters y setters (puedes generarlos con Alt+Insert)
 public String getNombre() { return nombre; }
 public void setNombre(String nombre) { this.nombre = nombre; }
 public String getApellidos() { return apellidos; }
 public void setApellidos(String apellidos) { this.apellidos = apellidos; }
 public Date getFechaAlta() { return fechaAlta; }
 public void setFechaAlta(Date fechaAlta) { this.fechaAlta = fechaAlta; }
 public String getProvincia() { return provincia; }
 public void setProvincia(String provincia) { this.provincia = provincia; }
 // Para volcar el objeto en la tabla:
 public String[] toArrayString() {
 return new String[]{ nombre, apellidos, fechaAlta.toString(), provincia };
 }
}