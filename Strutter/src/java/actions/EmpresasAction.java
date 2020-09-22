/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.regex.Pattern;
import model.dao.DAOImpl;
import model.entidades.Empresa;
import model.entidades.Usuario;

/**
 *
 * @author Dani
 */
public class EmpresasAction extends ActionSupport {

    /*Select*/
    private String nif;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String localidad;
    private String codpostal;
    private String telefono;
    private String correo;
    private List<Empresa> empresas = null;

    /* Add */
    private String usuario;
    private String clave;

    /* Modify */
    private String usuarioid;
    private Empresa empresa;
    private Usuario usuarioObject;

    /*Botones*/
    private String addEmpresa;
    private String modifyEmpresa;

    public EmpresasAction() {
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
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

    public String getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuarioObject() {
        return usuarioObject;
    }

    public void setUsuarioObject(Usuario usuarioObject) {
        this.usuarioObject = usuarioObject;
    }

    public String getAddEmpresa() {
        return addEmpresa;
    }

    public void setAddEmpresa(String addEmpresa) {
        this.addEmpresa = addEmpresa;
    }

    public String getModifyEmpresa() {
        return modifyEmpresa;
    }

    public void setModifyEmpresa(String modifyEmpresa) {
        this.modifyEmpresa = modifyEmpresa;
    }

    //Preparamos la lista de empresas para visualizarlas
    public String execute() throws Exception {
        try {

            empresas = new DAOImpl().obtenerEmpresas();

            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Alta empresa
    public String altaEmpresa() {

        try {

            if (new DAOImpl().findUserByUsername(this.getUsuario()) == null) {

                Usuario usu = new Usuario();
                usu.setUsuario(this.getUsuario().trim());
                usu.setClave(this.getClave());
                usu.setTipo("EMPRESA");
                new DAOImpl().altaUsuario(usu);
                Usuario usuarioAlta = new DAOImpl().findUserByUsername(usu.getUsuario());

                Empresa empresa = new Empresa(this.getNif().trim().toUpperCase());
                empresa.setNombre(this.getNombre().trim().toUpperCase());
                if (!this.getDescripcion().trim().equals("")) {
                    empresa.setDescripcion(this.getDescripcion().trim().toUpperCase());
                } else {
                    empresa.setDescripcion(null);
                }
                empresa.setDireccion(this.getDireccion().trim().toUpperCase());
                empresa.setLocalidad(this.getLocalidad().trim().toUpperCase());
                empresa.setCodpostal(Integer.parseInt(this.getCodpostal().trim()));
                empresa.setTelefono(Integer.parseInt(this.getTelefono().trim()));
                empresa.setCorreo(this.getCorreo().trim().toUpperCase());
                empresa.setUsuarioAsociado(usuarioAlta);

                new DAOImpl().altaEmpresa(empresa);
                return SUCCESS;
            } else {
                return ERROR;
            }
        } catch (Exception ex) {
            return ERROR;
        }

    }

    //Preparamos el formulario de modificar empresa
    public String goModifyEmpresa() {

        empresa = new DAOImpl().buscarEmpresa(this.getNif());
        usuarioObject = new DAOImpl().buscarUsuario(this.getUsuarioid());

        return SUCCESS;

    }

    //Modificar empresa
    public String modificarEmpresa() throws Exception {
        try {

            Usuario usuario = new Usuario(Integer.parseInt(this.getUsuarioid()), this.getUsuario().trim(), this.getClave(), "CLIENTE");

            new DAOImpl().modificarUsuario(usuario);

            Empresa empresa = new Empresa(this.getNif().trim().toUpperCase());
            empresa.setNombre(this.getNombre().trim().toUpperCase());
            if (!this.getDescripcion().trim().equals("")) {
                empresa.setDescripcion(this.getDescripcion().trim().toUpperCase());
            } else {
                empresa.setDescripcion(null);
            }
            empresa.setDireccion(this.getDireccion().trim().toUpperCase());
            empresa.setLocalidad(this.getLocalidad().trim().toUpperCase());
            empresa.setCodpostal(Integer.parseInt(this.getCodpostal().trim()));
            empresa.setTelefono(Integer.parseInt(this.getTelefono().trim()));
            empresa.setCorreo(this.getCorreo().trim().toUpperCase());
            empresa.setUsuarioAsociado(usuario);

            new DAOImpl().modificarEmpresa(empresa);

            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    // Borrar empresa
    public String borrarEmpresa() {

        // La bbdd está en on delete cascade por tanto si borro usuario, borro id, pero se hizo así para hacer todas las CRUD y que funcione igualmente si no estuviera on delete cascade
        new DAOImpl().borrarEmpresa(this.getNif());
        new DAOImpl().borrarUsuario(this.getUsuarioid());
        return SUCCESS;

    }

    //Validaciones de formularios
    public void validate() {

        if (this.getAddEmpresa() != null || this.getModifyEmpresa() != null) {
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

        }

        if (this.getModifyEmpresa() != null) {
            empresa = new DAOImpl().buscarEmpresa(this.getNif());
            usuarioObject = new DAOImpl().buscarUsuario(this.getUsuarioid());
        }

    }
}
