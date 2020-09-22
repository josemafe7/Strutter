/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import model.dao.DAOImpl;
import model.entidades.Categoria;
import model.entidades.Cliente;
import model.entidades.Compra;
import model.entidades.Empresa;
import model.entidades.Oferta;
import model.entidades.Usuario;
import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 *
 * @author Dani
 */
public class OfertasAction extends ActionSupport {

    /*Select*/
    private int idOferta;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Empresa empresaAsociada;
    private Categoria categoriaAsociada;
    private List<Oferta> ofertas = null;

    /* Add*/
    private List<Empresa> listEmpresas = null;
    private List<Categoria> listCategorias = null;
    private String idCategoria;
    private String nifEmpresa;
    private String precioString;

    /*Modify*/
    private Oferta oferta;

    /*Imagen*/
    private File uploadFichero;
    private String uploadFicheroFileName;
    private String uploadFicheroContentType;
    private List<String> validarImagen = null;
    private String imagen;

    /*Botones y busqueda*/
    private String btnBuscador;
    private String searchByCategoria;
    private String searchByEmpresa;
    private String searchByPalabra;
    private String palabraBuscar;
    private String addOferta;
    private String modifyOferta;

    public OfertasAction() {
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Empresa getEmpresaAsociada() {
        return empresaAsociada;
    }

    public void setEmpresaAsociada(Empresa empresaAsociada) {
        this.empresaAsociada = empresaAsociada;
    }

    public Categoria getCategoriaAsociada() {
        return categoriaAsociada;
    }

    public void setCategoriaAsociada(Categoria categoriaAsociada) {
        this.categoriaAsociada = categoriaAsociada;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public String getNifEmpresa() {
        return nifEmpresa;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa = nifEmpresa;
    }

    public List<Empresa> getListEmpresas() {
        return listEmpresas;
    }

    public void setListEmpresas(List<Empresa> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

    public String getPrecioString() {
        return precioString;
    }

    public void setPrecioString(String precioString) {
        this.precioString = precioString;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public File getUploadFichero() {
        return uploadFichero;
    }

    public void setUploadFichero(File uploadFichero) {
        this.uploadFichero = uploadFichero;
    }

    public String getUploadFicheroFileName() {
        return uploadFicheroFileName;
    }

    public void setUploadFicheroFileName(String uploadFicheroFileName) {
        this.uploadFicheroFileName = uploadFicheroFileName;
    }

    public String getUploadFicheroContentType() {
        return uploadFicheroContentType;
    }

    public void setUploadFicheroContentType(String uploadFicheroContentType) {
        this.uploadFicheroContentType = uploadFicheroContentType;
    }

    public List<Categoria> getListCategorias() {
        return listCategorias;
    }

    public void setListCategorias(List<Categoria> listCategorias) {
        this.listCategorias = listCategorias;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<String> getValidarImagen() {
        return validarImagen;
    }

    public void setValidarImagen(List<String> validarImagen) {
        this.validarImagen = validarImagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getBtnBuscador() {
        return btnBuscador;
    }

    public void setBtnBuscador(String btnBuscador) {
        this.btnBuscador = btnBuscador;
    }

    public String getSearchByCategoria() {
        return searchByCategoria;
    }

    public void setSearchByCategoria(String searchByCategoria) {
        this.searchByCategoria = searchByCategoria;
    }

    public String getSearchByEmpresa() {
        return searchByEmpresa;
    }

    public void setSearchByEmpresa(String searchByEmpresa) {
        this.searchByEmpresa = searchByEmpresa;
    }

    public String getSearchByPalabra() {
        return searchByPalabra;
    }

    public void setSearchByPalabra(String searchByPalabra) {
        this.searchByPalabra = searchByPalabra;
    }

    public String getPalabraBuscar() {
        return palabraBuscar;
    }

    public void setPalabraBuscar(String palabraBuscar) {
        this.palabraBuscar = palabraBuscar;
    }

    public String getAddOferta() {
        return addOferta;
    }

    public void setAddOferta(String addOferta) {
        this.addOferta = addOferta;
    }

    public String getModifyOferta() {
        return modifyOferta;
    }

    public void setModifyOferta(String modifyOferta) {
        this.modifyOferta = modifyOferta;
    }

    //Preparamos las vistas de las ofertas y las altas de nuevas ofertas
    public String execute() throws Exception {
        try {

            if (this.getBtnBuscador() != null) {
                listCategorias = new DAOImpl().obtenerCategorias();
                listEmpresas = new DAOImpl().obtenerEmpresas();
            } else {
                Map session = (Map) ActionContext.getContext().get("session");
                Usuario sesionUsuario = (Usuario) session.get("usuario");

                if (sesionUsuario.getTipo().equals("EMPLEADO")) {
                    ofertas = new DAOImpl().obtenerOfertas();
                    listEmpresas = new DAOImpl().obtenerEmpresas();
                } else if (sesionUsuario.getTipo().equals("EMPRESA")) {
                    Empresa sesionEmpresa = (Empresa) session.get("datosUser");
                    ofertas = new DAOImpl().obtenerOfertas(sesionEmpresa);

                } else if (sesionUsuario.getTipo().equals("CLIENTE")) {

                    if (this.getSearchByCategoria() != null) {
                        ofertas = new DAOImpl().obtenerOfertasActivasPorCategoria(this.getIdCategoria());
                    } else if (this.getSearchByEmpresa() != null) {
                        ofertas = new DAOImpl().obtenerOfertasActivasPorEmpresa(this.getNifEmpresa());
                    } else if (this.getSearchByPalabra() != null && !this.getPalabraBuscar().trim().equals("")) {
                        ofertas = new DAOImpl().obtenerOfertasActivasByPalabra(this.getPalabraBuscar());
                    } else {
                        ofertas = new DAOImpl().obtenerOfertasActivas();
                    }

                }

                listCategorias = new DAOImpl().obtenerCategorias();
            }
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    // Alta oferta
    public String altaOferta() throws Exception {
        try {

            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            String annio = Integer.toString(c.get(Calendar.YEAR));

            String fechaActual = ("" + annio + "-" + mes + "-" + dia + "");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaP = formato.parse(fechaActual);

            Oferta o = new Oferta();
            o.setTitulo(this.getTitulo().trim());
            o.setDescripcion(this.getDescripcion().trim());
            o.setFechaOferta(fechaP);
            if (this.getUploadFichero() == null) {
                o.setImagen(null);
            } else {
                String fullPath = getServletContext().getRealPath(File.separator).substring(0, getServletContext().getRealPath(File.separator).length() - 10) + ("web/files/ofertasImages");

                String ext = this.getUploadFicheroContentType().replace("image/", "");
                int aleatorio = (int) (Math.random() * 10000);
                String nombre = ("" + annio + "-" + mes + "-" + dia + "_" + c.get(Calendar.HOUR) + "-" + c.get(Calendar.MINUTE) + "-" + c.get(Calendar.SECOND) + "-" + c.get(Calendar.MILLISECOND) + "_" + String.valueOf(aleatorio));

                this.getUploadFichero().renameTo(new File(fullPath + "/" + nombre + "." + ext));
                o.setImagen(nombre + "." + ext);
            }
            o.setPrecio(Double.parseDouble(this.getPrecioString().trim()));
            o.setEstado("ACTIVA");

            Empresa e = new DAOImpl().buscarEmpresa(this.getNifEmpresa());
            o.setEmpresaAsociada(e);
            Categoria cat = new DAOImpl().buscarCategoria(this.getIdCategoria());
            o.setCategoriaAsociada(cat);

            new DAOImpl().altaOferta(o);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Modificar oferta
    public String goModifyOferta() {
        //Esto lo hacemos para que la primera opcion sea la empresa actual.
        oferta = new DAOImpl().buscarOferta(this.getIdOferta());
        listEmpresas = new DAOImpl().obtenerEmpresas();
        listEmpresas.remove(oferta.getEmpresaAsociada());
        listEmpresas.add(0, oferta.getEmpresaAsociada());

        listCategorias = new DAOImpl().obtenerCategorias();
        listCategorias.remove(oferta.getCategoriaAsociada());
        listCategorias.add(0, oferta.getCategoriaAsociada());

        validarImagen = new ArrayList<String>();
        validarImagen.add("Si");
        validarImagen.add("No");

        return SUCCESS;

    }
    
    // Modificar oferta
    public String modificarOferta() throws Exception {
        try {

            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            String annio = Integer.toString(c.get(Calendar.YEAR));

            String fechaActual = ("" + annio + "-" + mes + "-" + dia + "");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaP = formato.parse(fechaActual);

            Oferta o = new Oferta();
            o.setIdOferta(this.getIdOferta());
            o.setTitulo(this.getTitulo().trim());
            o.setDescripcion(this.getDescripcion().trim());
            o.setFechaOferta(fechaP);
            if (this.getImagen().equals("Si")) { //Quiere decir que si se tiene que cambiar
                if (this.getUploadFichero() == null) {

                    String fullPath = getServletContext().getRealPath(File.separator).substring(0, getServletContext().getRealPath(File.separator).length() - 10) + ("web/files/ofertasImages");

                    Oferta oldOferta = new DAOImpl().buscarOferta(this.getIdOferta());
                    File deleteFile = new File(fullPath + "/" + oldOferta.getImagen());
                    deleteFile.delete();

                    o.setImagen(null);
                } else {

                    String fullPath = getServletContext().getRealPath(File.separator).substring(0, getServletContext().getRealPath(File.separator).length() - 10) + ("web/files/ofertasImages");

                    Oferta oldOferta = new DAOImpl().buscarOferta(this.getIdOferta());
                    File deleteFile = new File(fullPath + "/" + oldOferta.getImagen());
                    deleteFile.delete();

                    String ext = this.getUploadFicheroContentType().replace("image/", "");
                    int aleatorio = (int) (Math.random() * 10000);
                    String nombre = ("" + annio + "-" + mes + "-" + dia + "_" + c.get(Calendar.HOUR) + "-" + c.get(Calendar.MINUTE) + "-" + c.get(Calendar.SECOND) + "-" + c.get(Calendar.MILLISECOND) + "_" + String.valueOf(aleatorio));

                    this.getUploadFichero().renameTo(new File(fullPath + "/" + nombre + "." + ext));
                    o.setImagen(nombre + "." + ext);
                }
            } else {
                Oferta oldOferta = new DAOImpl().buscarOferta(this.getIdOferta());
                o.setImagen(oldOferta.getImagen());
            }
            o.setPrecio(Double.parseDouble(this.getPrecioString().trim()));
            o.setEstado("ACTIVA");

            Empresa e = new DAOImpl().buscarEmpresa(this.getNifEmpresa());
            o.setEmpresaAsociada(e);
            Categoria cat = new DAOImpl().buscarCategoria(this.getIdCategoria());
            o.setCategoriaAsociada(cat);

            new DAOImpl().modificarOferta(o);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    // Desactivar oferta, cambia estado
    public String desactivarOferta() throws Exception {
        try {

            Oferta o = new DAOImpl().buscarOferta(this.getIdOferta());
            o.setEstado("DESACTIVADA");

            new DAOImpl().modificarOferta(o);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    // Desechar oferta, cambia estado
    public String desecharOferta() throws Exception {
        try {

            Oferta o = new DAOImpl().buscarOferta(this.getIdOferta());
            o.setEstado("BORRADA");

            new DAOImpl().modificarOferta(o);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Activar oferta, cambia stado
    public String activarOferta() throws Exception {
        try {

            Oferta o = new DAOImpl().buscarOferta(this.getIdOferta());
            o.setEstado("ACTIVA");

            new DAOImpl().modificarOferta(o);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Borrar oferta, implementada pero no se usará en la aplicacion
    public String borrarOferta() {

        new DAOImpl().borrarOferta(this.getIdOferta());
        return SUCCESS;

    }

    //Realiza la accion de compra de una oferta
    public String goComprar() throws Exception {
        try {

            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            String annio = Integer.toString(c.get(Calendar.YEAR));

            String fechaActual = ("" + annio + "-" + mes + "-" + dia + "");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaP = formato.parse(fechaActual);

            Map session = (Map) ActionContext.getContext().get("session");
            Cliente sesionCliente = (Cliente) session.get("datosUser");

            Oferta o = new DAOImpl().buscarOferta(this.getIdOferta());
            o.setEstado("COMPRADA");
            new DAOImpl().modificarOferta(o);

            Compra compra = new Compra();
            compra.setFechaCompra(fechaP);
            compra.setOfertaAsociada(o);
            compra.setClienteAsociado(sesionCliente);

            new DAOImpl().altaCompra(compra);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    // Validacion de formularios
    public void validate() {

        if (this.getSearchByPalabra() != null) {
            listCategorias = new DAOImpl().obtenerCategorias();
            listEmpresas = new DAOImpl().obtenerEmpresas();
            if (!Pattern.matches("[a-zA-Z ]+", this.getPalabraBuscar())) {
                addFieldError("palabraBuscar", getText("sololetras.incorrecto"));
            }
        } else if (this.getAddOferta() != null) {
            listCategorias = new DAOImpl().obtenerCategorias();
            Map session = (Map) ActionContext.getContext().get("session");
            Usuario sesionUsuario = (Usuario) session.get("usuario");
            if (sesionUsuario.getTipo().equals("EMPRESA")) {
                Empresa sesionEmpresa = (Empresa) session.get("datosUser");
            } else if(sesionUsuario.getTipo().equals("EMPLEADO")){
                listEmpresas = new DAOImpl().obtenerEmpresas();
            }

            if (this.getTitulo().trim().equals("")) {
                addFieldError("titulo", getText("campo.required"));
            } else if (this.getTitulo().trim().length() > 100) {
                addFieldError("titulo", "El titulo no puede ser mayor de 100 caracteres");
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getTitulo())) {
                addFieldError("titulo", getText("sololetras.incorrecto"));
            }

            if (this.getDescripcion().trim().equals("")) {
                addFieldError("descripcion", getText("campo.required"));
            } else if (this.getDescripcion().trim().length() > 800) {
                addFieldError("descripcion", "La descripcion no puede ser mayor de 800 caracteres");
            } else if (!Pattern.matches("[a-zA-Z0-9,.!¡¿? ]+", this.getDescripcion())) {
                addFieldError("descripcion", "Hay caracteres introducidos no permitidos");
            }

            if (this.getUploadFichero() != null && !this.getUploadFicheroContentType().equals("image/jpeg")) {
                addFieldError("uploadFichero", "Solo se permiten imagenes jpeg");
            }

            if (this.getPrecioString().equals("")) {
                addFieldError("precioString", getText("campo.required"));
            } else if (!Pattern.matches("[0-9]+([.][0-9]+)*", this.getPrecioString())) {
                addFieldError("precioString", "Escriba un numero real porfavor");
            }
        } else if (this.getModifyOferta() != null) {
            //Esto lo hacemos para que la primera opcion sea la empresa actual.
            oferta = new DAOImpl().buscarOferta(this.getIdOferta());
            listEmpresas = new DAOImpl().obtenerEmpresas();
            listEmpresas.remove(oferta.getEmpresaAsociada());
            listEmpresas.add(0, oferta.getEmpresaAsociada());

            listCategorias = new DAOImpl().obtenerCategorias();
            listCategorias.remove(oferta.getCategoriaAsociada());
            listCategorias.add(0, oferta.getCategoriaAsociada());

            validarImagen = new ArrayList<String>();
            validarImagen.add("Si");
            validarImagen.add("No");

            if (this.getTitulo().trim().equals("")) {
                addFieldError("titulo", getText("campo.required"));
            } else if (this.getTitulo().trim().length() > 100) {
                addFieldError("titulo", "El titulo no puede ser mayor de 100 caracteres");
            } else if (!Pattern.matches("[a-zA-Z ]+", this.getTitulo())) {
                addFieldError("titulo", getText("sololetras.incorrecto"));
            }

            if (this.getDescripcion().trim().equals("")) {
                addFieldError("descripcion", getText("campo.required"));
            } else if (this.getDescripcion().trim().length() > 800) {
                addFieldError("descripcion", "La descripcion no puede ser mayor de 800 caracteres");
            } else if (!Pattern.matches("[a-zA-Z0-9,.!¡¿? ]+", this.getDescripcion())) {
                addFieldError("descripcion", "Hay caracteres introducidos no permitidos");
            }

            if (this.getUploadFichero() != null && !this.getUploadFicheroContentType().equals("image/jpeg")) {
                addFieldError("uploadFichero", "Solo se permiten imagenes jpeg");
            }

            if (this.getImagen() == null) {
                addFieldError("imagen", "Debe indicar qué quiere hacer con la imagen");
            }

            if (this.getPrecioString().equals("")) {
                addFieldError("precioString", getText("campo.required"));
            } else if (!Pattern.matches("[0-9]+([.][0-9]+)*", this.getPrecioString())) {
                addFieldError("precioString", "Escriba un numero real porfavor");
            }
        }
    }

}
