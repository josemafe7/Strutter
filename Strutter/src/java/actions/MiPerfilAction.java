/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import model.dao.DAOImpl;
import model.entidades.Cliente;
import model.entidades.Departamento;
import model.entidades.Empleado;
import model.entidades.Empresa;
import model.entidades.Usuario;

/**
 *
 * @author Dani
 */
public class MiPerfilAction extends ActionSupport {

    private Usuario usuarioObject;
    private Cliente cliente;
    private Empresa empresa;
    private Empleado empleado;
    private List<String> genders = null;
    private String fechaNacimiento;
    private String usuarioid;

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
    private String usuario;
    private String clave;

    private String salario;
    private Departamento departamento;
    private String deptnoDepartamento;

    private String nif;
    private String descripcion;

    /*Botones*/
    private String modifyPerfil;

    public MiPerfilAction() {
    }

    public Usuario getUsuarioObject() {
        return usuarioObject;
    }

    public void setUsuarioObject(Usuario usuarioObject) {
        this.usuarioObject = usuarioObject;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<String> getGenders() {
        return genders;
    }

    public void setGenders(List<String> genders) {
        this.genders = genders;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
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

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getDeptnoDepartamento() {
        return deptnoDepartamento;
    }

    public void setDeptnoDepartamento(String deptnoDepartamento) {
        this.deptnoDepartamento = deptnoDepartamento;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModifyPerfil() {
        return modifyPerfil;
    }

    public void setModifyPerfil(String modifyPerfil) {
        this.modifyPerfil = modifyPerfil;
    }


    
    //Preparamos el formulario de modificacion de perfil para cada tipo de usuario
    public String execute() throws Exception {
        try {

            genders = new ArrayList<String>();
            genders.add("H");
            genders.add("M");

            Map session = (Map) ActionContext.getContext().get("session");

            usuarioObject = (Usuario) session.get("usuario");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            if (usuarioObject.getTipo().equals("CLIENTE")) {
                cliente = (Cliente) session.get("datosUser");
                setFechaNacimiento(formato.format(cliente.getFechaNac()));
            } else if (usuarioObject.getTipo().equals("EMPLEADO")) {
                empleado = (Empleado) session.get("datosUser");
                setFechaNacimiento(formato.format(empleado.getFechaNac()));
            } else if (usuarioObject.getTipo().equals("EMPRESA")) {
                empresa = (Empresa) session.get("datosUser");
            }

            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    
    //Modificar perfil
    public String modificarPerfil() throws Exception {
        try {

            Map session = (Map) ActionContext.getContext().get("session");

            usuarioObject = (Usuario) session.get("usuario");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            if (new DAOImpl().findUserByUsername(this.getUsuario().trim()) == null || this.getUsuario().trim().equals(usuarioObject.getUsuario())) {
                if (usuarioObject.getTipo().equals("CLIENTE")) {
                    Usuario newUsuario = new Usuario(Integer.parseInt(this.getUsuarioid()), this.getUsuario().trim(), this.getClave(), "CLIENTE");

                    new DAOImpl().modificarUsuario(newUsuario);

                    Date fechaP = formato.parse(this.getFechaNac());

                    Cliente newCliente = new Cliente(this.getDni());
                    newCliente.setNombre(this.getNombre().trim().toUpperCase());
                    newCliente.setApellido1(this.getApellido1().trim().toUpperCase());
                    if (!this.getApellido2().trim().equals("")) {
                        newCliente.setApellido2(this.getApellido2().trim().toUpperCase());
                    } else {
                        newCliente.setApellido2(null);
                    }
                    newCliente.setSexo(this.getSexo());
                    newCliente.setFechaNac(fechaP);
                    if (!this.getDireccion().trim().equals("")) {
                        newCliente.setDireccion(this.getDireccion().trim().toUpperCase());
                    } else {
                        newCliente.setDireccion(null);
                    }
                    if (!this.getLocalidad().trim().equals("")) {
                        newCliente.setLocalidad(this.getLocalidad().trim().toUpperCase());
                    } else {
                        newCliente.setLocalidad(null);
                    }
                    if (!this.getCodpostal().trim().equals("")) {
                        newCliente.setCodpostal(11700);
                    } else {
                        newCliente.setCodpostal(null);
                    }

                    if (!this.getTelefono().trim().equals("")) {
                        newCliente.setTelefono(956177875);
                    } else {
                        newCliente.setTelefono(null);
                    }
                    newCliente.setCorreo(this.getCorreo().trim().toUpperCase());
                    newCliente.setUsuarioAsociado(newUsuario);

                    new DAOImpl().modificarCliente(newCliente);
                } else if (usuarioObject.getTipo().equals("EMPLEADO")) {
                    Usuario newUsuario = new Usuario(Integer.parseInt(this.getUsuarioid()), this.getUsuario().trim(), this.getClave(), "EMPLEADO");

                    new DAOImpl().modificarUsuario(newUsuario);

                    Date fechaP = formato.parse(this.getFechaNac());

                    Departamento dept = new DAOImpl().buscarDepartamento(this.getDeptnoDepartamento());

                    Empleado newEmpleado = new Empleado(this.getDni().trim().toUpperCase());
                    newEmpleado.setNombre(this.getNombre().trim().toUpperCase());
                    newEmpleado.setApellido1(this.getApellido1().trim().toUpperCase());
                    if (!this.getApellido2().trim().equals("")) {
                        newEmpleado.setApellido2(this.getApellido2().trim().toUpperCase());
                    } else {
                        newEmpleado.setApellido2(null);
                    }
                    newEmpleado.setSexo(this.getSexo());
                    newEmpleado.setFechaNac(fechaP);
                    newEmpleado.setDireccion(this.getDireccion().trim().toUpperCase());
                    newEmpleado.setLocalidad(this.getLocalidad().trim().toUpperCase());
                    newEmpleado.setCodpostal(Integer.parseInt(this.getCodpostal().trim()));
                    newEmpleado.setTelefono(Integer.parseInt(this.getTelefono().trim()));
                    newEmpleado.setCorreo(this.getCorreo().trim().toUpperCase());
                    newEmpleado.setSalario(Double.parseDouble(this.getSalario().trim()));
                    newEmpleado.setUsuarioAsociado(newUsuario);
                    newEmpleado.setDepartamento(dept);

                    new DAOImpl().modificarEmpleado(newEmpleado);
                } else if (usuarioObject.getTipo().equals("EMPRESA")) {
                    Usuario newUsuario = new Usuario(Integer.parseInt(this.getUsuarioid()), this.getUsuario().trim(), this.getClave(), "CLIENTE");

                    new DAOImpl().modificarUsuario(newUsuario);

                    Empresa newEmpresa = new Empresa(this.getNif().trim().toUpperCase());
                    newEmpresa.setNombre(this.getNombre().trim().toUpperCase());
                    newEmpresa.setDescripcion(this.getDescripcion().trim().toUpperCase());
                    newEmpresa.setDireccion(this.getDireccion().trim().toUpperCase());
                    newEmpresa.setLocalidad(this.getLocalidad().trim().toUpperCase());
                    newEmpresa.setCodpostal(Integer.parseInt(this.getCodpostal().trim()));
                    newEmpresa.setTelefono(Integer.parseInt(this.getTelefono().trim()));
                    newEmpresa.setCorreo(this.getCorreo().trim().toUpperCase());
                    newEmpresa.setUsuarioAsociado(newUsuario);

                    new DAOImpl().modificarEmpresa(newEmpresa);
                }
            } else {
                addFieldError("usuario", "El usuario ya existe");
                return INPUT;
            }

            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }
    

    // Vaidaciones de formularios
    public void validate() {

        Map session = (Map) ActionContext.getContext().get("session");

        usuarioObject = (Usuario) session.get("usuario");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        if (this.getModifyPerfil() != null && usuarioObject.getTipo().equals("CLIENTE")) {
            cliente = (Cliente) session.get("datosUser");
            setFechaNacimiento(formato.format(cliente.getFechaNac()));
            if (this.getUsuario().trim().equals("")) {
                addFieldError("usuario", getText("user.required"));
            } else if (this.getUsuario().trim().length() > 60) {
                addFieldError("user", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z0-9@._-]+", this.getUsuario())) {
                addFieldError("user", getText("formato.incorrecto"));
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
                addFieldError("fecha_nac", getText("campo.required"));
            } else if (!Pattern.matches("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$", this.getFechaNac())) {
                addFieldError("fecha_nac", getText("formatofecha.incorrecto"));
            } else if (calcularEdad(this.getFechaNac()) < 18) {
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
        } else if (this.getModifyPerfil() != null && usuarioObject.getTipo().equals("EMPRESA")) {
            empresa = (Empresa) session.get("datosUser");
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

            if (this.getNif().trim().equals("")) {
                addFieldError("nif", getText("campo.required"));
            } else if (this.getNif().trim().length() != 9) {
                addFieldError("nif", getText("dnitelf.length"));
            } else if (!Pattern.matches("[0-9]{8}[a-zA-Z]{1}", this.getNif())) {
                addFieldError("nif", getText("dni.incorrecto"));
            }

            if (this.getNombre().trim().equals("")) {
                addFieldError("nombre", getText("campo.required"));
            } else if (this.getNombre().trim().length() > 60) {
                addFieldError("nombre", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getNombre())) {
                addFieldError("nombre", getText("sololetras.incorrecto"));
            }

            if (this.getDescripcion().trim().length() > 500) {
                addFieldError("descripcion", "La descripcion no puede ser mayor de 500 caracteres");
            } else if (!Pattern.matches("[a-zA-Z0-9,.!¡¿? ]+", this.getDescripcion()) && !this.getDescripcion().equals("")) {
                addFieldError("descripcion", "Hay caracteres introducidos no permitidos");
            }

            if (this.getDireccion().trim().equals("")) {
                addFieldError("direccion", getText("campo.required"));
            } else if (this.getDireccion().trim().length() > 60) {
                addFieldError("direccion", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z0-9.,/º -]+", this.getDireccion())) {
                addFieldError("direccion", getText("formato.incorrecto"));
            }

            if (this.getLocalidad().trim().equals("")) {
                addFieldError("localidad", getText("campo.required"));
            } else if (this.getLocalidad().trim().length() > 60) {
                addFieldError("localidad", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getLocalidad())) {
                addFieldError("localidad", getText("formato.incorrecto"));
            }

            if (this.getCodpostal().trim().equals("")) {
                addFieldError("codpostal", getText("campo.required"));
            } else if (this.getCodpostal().trim().length() != 5) {
                addFieldError("codpostal", getText("codpostal.length"));
            } else if (!Pattern.matches("[0-9]{5}", this.getCodpostal())) {
                addFieldError("codpostal", getText("codpostal.length"));
            }

            if (this.getTelefono().trim().equals("")) {
                addFieldError("telefono", getText("campo.required"));
            } else if (this.getTelefono().trim().length() != 9) {
                addFieldError("telefono", getText("dnitelf.length"));
            } else if (!Pattern.matches("[0-9]{9}", this.getTelefono())) {
                addFieldError("telefono", getText("telf.incorrecto"));
            }

            if (this.getCorreo().trim().equals("")) {
                addFieldError("correo", getText("campo.required"));
            } else if (this.getCorreo().trim().length() > 60) {
                addFieldError("correo", getText("sesenta.length"));
            } else if (!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", this.getCorreo())) {
                addFieldError("correo", getText("correo.incorrecto"));
            }

        } else if (this.getModifyPerfil() != null && usuarioObject.getTipo().equals("EMPLEADO")) {

            empleado = (Empleado) session.get("datosUser");
            setFechaNacimiento(formato.format(empleado.getFechaNac()));

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

            if (this.getDireccion().trim().equals("")) {
                addFieldError("direccion", getText("campo.required"));
            } else if (this.getDireccion().trim().length() > 60) {
                addFieldError("direccion", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z0-9.,/º -]+", this.getDireccion())) {
                addFieldError("direccion", getText("formato.incorrecto"));
            }

            if (this.getLocalidad().trim().equals("")) {
                addFieldError("localidad", getText("campo.required"));
            } else if (this.getLocalidad().trim().length() > 60) {
                addFieldError("localidad", getText("sesenta.length"));
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getLocalidad())) {
                addFieldError("localidad", getText("formato.incorrecto"));
            }

            if (this.getCodpostal().trim().equals("")) {
                addFieldError("codpostal", getText("campo.required"));
            } else if (this.getCodpostal().trim().length() != 5 && !this.getTelefono().trim().equals("")) {
                addFieldError("codpostal", getText("codpostal.length"));
            } else if (!Pattern.matches("[0-9]{5}", this.getCodpostal())) {
                addFieldError("codpostal", getText("codpostal.length"));
            }

            if (this.getTelefono().trim().equals("")) {
                addFieldError("telefono", getText("campo.required"));
            } else if (this.getTelefono().trim().length() != 9 && !this.getTelefono().trim().equals("")) {
                addFieldError("telefono", getText("dnitelf.length"));
            } else if (!Pattern.matches("[0-9]{9}", this.getTelefono())) {
                addFieldError("telefono", getText("telf.incorrecto"));
            }

            if (this.getCorreo().trim().equals("")) {
                addFieldError("correo", getText("campo.required"));
            } else if (this.getCorreo().trim().length() > 60) {
                addFieldError("correo", getText("sesenta.length"));
            } else if (!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", this.getCorreo())) {
                addFieldError("correo", getText("correo.incorrecto"));
            }

            if (this.getSalario().equals("")) {
                addFieldError("salario", getText("campo.required"));
            } else if (!Pattern.matches("[0-9]+([.][0-9]+)*", this.getSalario())) {
                addFieldError("precioString", "Escriba un numero real porfavor");
            }

            genders = new ArrayList<String>();
            genders.add("H");
            genders.add("M");
        }
    }

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
