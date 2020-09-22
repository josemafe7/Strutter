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
public class ClientesAction extends ActionSupport {

    /*Select*/
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String sexo;
    private String fechaNac;
    private String direccion;
    private String localidad;
    private String codpostal;
    private String telefono;
    private String correo;
    private List<Cliente> clientes = null;

    /* Add */
    private String usuario;
    private String clave;
    private List<String> genders = null;

    /* Modify */
    private String usuarioid;
    private Cliente cliente;
    private Usuario usuarioObject;
    private String fechaNacimiento;

    /*Botones*/
    private String addCliente;
    private String modifyCliente;

    public ClientesAction() {
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

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<String> getGenders() {
        return genders;
    }

    public void setGenders(List<String> genders) {
        this.genders = genders;
    }

    public String getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuarioObject() {
        return usuarioObject;
    }

    public void setUsuarioObject(Usuario usuarioObject) {
        this.usuarioObject = usuarioObject;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAddCliente() {
        return addCliente;
    }

    public void setAddCliente(String addCliente) {
        this.addCliente = addCliente;
    }

    public String getModifyCliente() {
        return modifyCliente;
    }

    public void setModifyCliente(String modifyCliente) {
        this.modifyCliente = modifyCliente;
    }

    //Preparamos lo necesario para mostrar los clientes y para ir al formulario de alta donde usaremos la lista de generos
    public String execute() throws Exception {
        try {

            clientes = new DAOImpl().obtenerClientes();
            genders = new ArrayList<String>();
            genders.add("H");
            genders.add("M");
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }

    }

    // Dar de alta un cliente
    public String altaCliente() {

        try {

            if (new DAOImpl().findUserByUsername(this.getUsuario()) == null) {

                Usuario usu = new Usuario();
                usu.setUsuario(this.getUsuario().trim());
                usu.setClave(this.getClave());
                usu.setTipo("CLIENTE");
                new DAOImpl().altaUsuario(usu);
                Usuario usuarioAlta = new DAOImpl().findUserByUsername(usu.getUsuario());

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaP = formato.parse(this.getFechaNac());

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
                return ERROR;
            }
        } catch (Exception ex) {
            return ERROR;
        }

    }

    //Preparar lo necesario para mostrar el formulario de modificar un cliente
    public String goModifyCliente() {

        cliente = new DAOImpl().buscarCliente(this.getDni());
        usuarioObject = new DAOImpl().buscarUsuario(this.getUsuarioid());
        genders = new ArrayList<String>();
        genders.add("H");
        genders.add("M");

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        fechaNacimiento = formato.format(cliente.getFechaNac());

        return SUCCESS;

    }

    //Modificar cliente
    public String modificarCliente() throws Exception {
        try {

            Usuario usuario = new Usuario(Integer.parseInt(this.getUsuarioid()), this.getUsuario().trim(), this.getClave(), "CLIENTE");

            new DAOImpl().modificarUsuario(usuario);

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaP = formato.parse(this.getFechaNac());

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
            cliente.setUsuarioAsociado(usuario);

            new DAOImpl().modificarCliente(cliente);

            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    
    //Borrar cliente
    public String borrarCliente() {

        // La bbdd está en on delete cascade por tanto si borro usuario, borro id, pero se hizo así para hacer todas las CRUD y que funcione igualmente si no estuviera on delete cascade
        new DAOImpl().borrarCliente(this.getDni());
        new DAOImpl().borrarUsuario(this.getUsuarioid());
        return SUCCESS;

    }

    //Validacion de formularios
    public void validate() {

        if (this.getAddCliente() != null) {
            if (this.getUsuario().trim().equals("")) {
                addFieldError("usuario", getText("user.required"));
            } else if (this.getUsuario().trim().length() > 60) {
                addFieldError("usuario", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z0-9@._-]+", this.getUsuario())) {
                addFieldError("usuario", getText("formato.incorrecto"));
            }

            if (this.getClave().equals("")) {
                addFieldError("clave", getText("password.required"));
            } else if (this.getClave().length() > 255) {
                addFieldError("clave", getText("doscientoscincuentaycinco.length"));
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

            if (this.getFechaNac().trim().equals("")) {
                addFieldError("fechaNac", getText("campo.required"));
            } else if (!Pattern.matches("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$", this.getFechaNac())) {
                addFieldError("fechaNac", getText("formatofecha.incorrecto"));
            } else if (calcularEdad(this.getFechaNac()) < 18) {
                addFieldError("fechaNac", getText("menor.edad"));
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

        } else if (this.getModifyCliente() != null) {
            cliente = new DAOImpl().buscarCliente(this.getDni());
            usuarioObject = new DAOImpl().buscarUsuario(this.getUsuarioid());
            genders = new ArrayList<String>();
            genders.add("H");
            genders.add("M");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaNacimiento = formato.format(cliente.getFechaNac());

            if (this.getUsuario().trim().equals("")) {
                addFieldError("usuario", getText("user.required"));
            } else if (this.getUsuario().trim().length() > 60) {
                addFieldError("usuario", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z0-9@._-]+", this.getUsuario())) {
                addFieldError("usuario", getText("formato.incorrecto"));
            }

            if (this.getClave().equals("")) {
                addFieldError("clave", getText("password.required"));
            } else if (this.getClave().length() > 255) {
                addFieldError("clave", getText("doscientoscincuentaycinco.length"));
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

            if (this.getFechaNac().trim().equals("")) {
                addFieldError("fechaNac", getText("campo.required"));
            } else if (!Pattern.matches("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$", this.getFechaNac())) {
                addFieldError("fechaNac", getText("formatofecha.incorrecto"));
            } else if (calcularEdad(this.getFechaNac()) < 18) {
                addFieldError("fechaNac", getText("menor.edad"));
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
        }

    }

    //Solo aceptaremos mayores de edad
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
