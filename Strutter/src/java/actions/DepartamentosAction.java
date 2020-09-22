/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import model.dao.DAOImpl;
import model.entidades.Departamento;

/**
 *
 * @author Dani
 */
public class DepartamentosAction extends ActionSupport {
    
    /*Select*/
    private String deptNo;
    private String deptNombre;
    private String deptDescripcion;
    private List<Departamento> departamentos;
    
    public DepartamentosAction() {
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptNombre() {
        return deptNombre;
    }

    public void setDeptNombre(String deptNombre) {
        this.deptNombre = deptNombre;
    }

    public String getDeptDescripcion() {
        return deptDescripcion;
    }

    public void setDeptDescripcion(String deptDescripcion) {
        this.deptDescripcion = deptDescripcion;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    
    
    // Prepara para visualizar los departamentos
    public String execute() throws Exception {
        try {

            departamentos = new DAOImpl().obtenerDepartamentos();
            
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }

    }
    
}
