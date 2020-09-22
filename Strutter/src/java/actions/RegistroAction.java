/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import model.dao.DAOImpl;
import model.entidades.Cliente;
import model.entidades.Usuario;

/**
 *
 * @author Dani
 */
public class RegistroAction extends ActionSupport {

    /* Registro */
    private String user;
    private String password;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String sexo;
    private String fecha_nac;
    private String direccion;
    private String localidad;
    private String codpostal;
    private String telefono;
    private String correo;

    private List<String> genders = null;
    
    /*Boton*/
    private String registro;

    public RegistroAction() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<String> getGenders() {
        return genders;
    }

    public void setGenders(List<String> genders) {
        this.genders = genders;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    // Cargamos la lista de generos para los botones radio del registro
    public String genders() {
        try {
            genders = new ArrayList<String>();
            genders.add("H");
            genders.add("M");

            return SUCCESS;
        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Alta cliente
    public String altaCliente() {

        try {

            if (new DAOImpl().findUserByUsername(this.getUser()) == null) {

                Usuario usu = new Usuario();
                usu.setUsuario(this.getUser().trim());
                usu.setClave(this.getPassword());
                usu.setTipo("CLIENTE");
                new DAOImpl().altaUsuario(usu);
                Usuario usuarioAlta = new DAOImpl().findUserByUsername(usu.getUsuario());

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaP = formato.parse(this.getFecha_nac());

                Cliente cliente = new Cliente(this.getDni());
                cliente.setNombre(this.getNombre().trim().toUpperCase());
                cliente.setApellido1(this.getApellido1().trim().toUpperCase());
                if (!this.getApellido2().trim().equals("")) {
                    cliente.setApellido2(this.getApellido2().trim().toUpperCase());
                } else {
                    cliente.setApellido2(null);
                }
                cliente.setSexo(this.getSexo());
                cliente.setFechaNac(fechaP);
                if (!this.getDireccion().trim().equals("")) {
                    cliente.setDireccion(this.getDireccion().trim().toUpperCase());
                } else {
                    cliente.setDireccion(null);
                }
                if (!this.getLocalidad().trim().equals("")) {
                    cliente.setLocalidad(this.getLocalidad().trim().toUpperCase());
                } else {
                    cliente.setLocalidad(null);
                }
                if (!this.getCodpostal().trim().equals("")) {
                    cliente.setCodpostal(Integer.parseInt(this.getCodpostal().trim()));
                } else {
                    cliente.setCodpostal(null);
                }

                if (!this.getTelefono().trim().equals("")) {
                    cliente.setTelefono(Integer.parseInt(this.getTelefono().trim()));
                } else {
                    cliente.setTelefono(null);
                }
                cliente.setCorreo(this.getCorreo().trim().toUpperCase());
                cliente.setUsuarioAsociado(usuarioAlta);

                new DAOImpl().altaCliente(cliente);
                return SUCCESS;
            } else {
                addFieldError("user", "El usuario ya existe");
                return INPUT;
            }
        } catch (Exception ex) {
            return ERROR;
        }

    }

    //Validaciones de formulario
    public void validate() {

        if (registro != null) {
            if (this.getUser().trim().equals("")) {
                addFieldError("user", getText("user.required"));
            } else if (this.getUser().trim().length() > 60) {
                addFieldError("user", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z0-9@._-]+", this.getUser())) {
                addFieldError("user", getText("formato.incorrecto"));
            }

            if (this.getPassword().equals("")) {
                addFieldError("password", getText("password.required"));
            } else if (this.getPassword().length() > 255) {
                addFieldError("password", getText("doscientoscincuentaycinco.length"));
            }

            if (this.getDni().trim().equals("")) {
                addFieldError("dni", getText("campo.required"));
            } else if (this.getDni().trim().length() != 9) {
                addFieldError("dni", getText("dnitelf.length"));
            } else if (!Pattern.matches("[0-9]{8}[a-zA-Z]{1}", this.getDni())) {
                addFieldError("dni", getText("dni.incorrecto"));
            }

            if (this.getNombre().trim().equals("")) {
                addFieldError("nombre", getText("campo.required"));
            } else if (this.getNombre().trim().length() > 60) {
                addFieldError("nombre", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getNombre())) {
                addFieldError("nombre", getText("sololetras.incorrecto"));
            }

            if (this.getApellido1().trim().equals("")) {
                addFieldError("apellido1", getText("campo.required"));
            } else if (this.getApellido1().trim().length() > 60) {
                addFieldError("apellido1", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getApellido1())) {
                addFieldError("apellido1", getText("sololetras.incorrecto"));
            }

            if (this.getApellido2().trim().length() > 60) {
                addFieldError("apellido2", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getApellido2()) && !this.getApellido2().trim().equals("")) {
                addFieldError("apellido2", getText("sololetras.incorrecto"));
            }

            if (this.getFecha_nac().trim().equals("")) {
                addFieldError("fecha_nac", getText("campo.required"));
            } else if (!Pattern.matches("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$", this.getFecha_nac())) {
                addFieldError("fecha_nac", getText("formatofecha.incorrecto"));
            } else if (calcularEdad(this.getFecha_nac()) < 18) {
                addFieldError("fecha_nac", getText("menor.edad"));
            }

            if (this.getDireccion().trim().length() > 60) {
                addFieldError("direccion", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z0-9.,/º -]+", this.getDireccion()) && !this.getDireccion().trim().equals("")) {
                addFieldError("direccion", getText("formato.incorrecto"));
            }

            if (this.getLocalidad().trim().length() > 60) {
                addFieldError("localidad", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getLocalidad()) && !this.getLocalidad().trim().equals("")) {
                addFieldError("localidad", getText("formato.incorrecto"));
            }

            if (this.getCodpostal().trim().length() != 5 && !this.getTelefono().trim().equals("")) {
                addFieldError("codpostal", getText("codpostal.length"));
            } else if (!Pattern.matches("[0-9]{5}", this.getCodpostal()) && !this.getCodpostal().trim().equals("")) {
                addFieldError("codpostal", getText("codpostal.length"));
            }

            if (this.getTelefono().trim().length() != 9 && !this.getTelefono().trim().equals("")) {
                addFieldError("telefono", getText("dnitelf.length"));
            } else if (!Pattern.matches("[0-9]{9}", this.getTelefono()) && !this.getTelefono().trim().equals("")) {
                addFieldError("telefono", getText("telf.incorrecto"));
            }

            if (this.getCorreo().trim().equals("")) {
                addFieldError("correo", getText("campo.required"));
            } else if (this.getCorreo().trim().length() > 60) {
                addFieldError("correo", getText("sesenta.length"));
            } else if (!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", this.getCorreo())) {
                addFieldError("correo", getText("correo.incorrecto"));
            }

            genders = new ArrayList<String>();
            genders.add("H");
            genders.add("M");
        }

    }

    //Solo se aceptaran mayores de edad
    private int calcularEdad(String fecha) {
        Date fechaNac = null;
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return año;
    }

}
