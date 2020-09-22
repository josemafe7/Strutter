/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.regex.Pattern;
import model.dao.DAOImpl;
import model.entidades.Categoria;

/**
 *
 * @author Dani
 */
public class CategoriasAction extends ActionSupport {

    /*Select*/
    private String id;
    private String categoria;
    private List<Categoria> categorias = null;


    /* Modify */
    private Categoria categoriaObject;

    /*Botones*/
    private String addCategoria;
    private String modifyCategoria;

    public CategoriasAction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Categoria getCategoriaObject() {
        return categoriaObject;
    }

    public void setCategoriaObject(Categoria categoriaObject) {
        this.categoriaObject = categoriaObject;
    }

    public String getAddCategoria() {
        return addCategoria;
    }

    public void setAddCategoria(String addCategoria) {
        this.addCategoria = addCategoria;
    }

    public String getModifyCategoria() {
        return modifyCategoria;
    }

    public void setModifyCategoria(String modifyCategoria) {
        this.modifyCategoria = modifyCategoria;
    }

    //Aqui preparamos lo que necesitamos para mostrar las categorias
    public String execute() throws Exception {
        try {

            categorias = new DAOImpl().obtenerCategorias();

            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Para dar de alta una categoria
    public String altaCategoria() {

        try {

            Categoria cat = new Categoria();
            cat.setCategoria(this.getCategoria().trim());

            new DAOImpl().altaCategoria(cat);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }

    }

    //Preparamos lo necesario para modificar una categoria
    public String goModifyCategoria() {

        categoriaObject = new DAOImpl().buscarCategoria(this.getId());

        return SUCCESS;

    }

    //Modificamos una categoria
    public String modificarCategoria() throws Exception {
        try {

            Categoria cat = new Categoria(Integer.parseInt(this.getId()), this.getCategoria().trim());

            new DAOImpl().modificarCategoria(cat);

            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Borrar una categoria
    public String borrarCategoria() {

        new DAOImpl().borrarCategoria(this.getId());
        return SUCCESS;

    }

    //Validacion
    public void validate() {

        if (this.getAddCategoria() != null || this.getModifyCategoria() != null) {
            if (this.getCategoria().trim().equals("")) {
                addFieldError("categoria", getText("campo.required"));
            } else if (this.getCategoria().trim().length() > 80) {
                addFieldError("categoria", "No puede superar los 80 caracteres");
            } else if (!Pattern.matches("[a-zA-Z, ]+", this.getCategoria())) {
                addFieldError("categoria", getText("formato.incorrecto"));
            }

        }

        if (this.getModifyCategoria() != null) {
            categoriaObject = new DAOImpl().buscarCategoria(this.getId());
        }

    }
}
