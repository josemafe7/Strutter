/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dani
 */
@Entity
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByDeptNo", query = "SELECT d FROM Departamento d WHERE d.deptNo = :deptNo")
    , @NamedQuery(name = "Departamento.findByDeptNombre", query = "SELECT d FROM Departamento d WHERE d.deptNombre = :deptNombre")
    , @NamedQuery(name = "Departamento.findByDeptDescripcion", query = "SELECT d FROM Departamento d WHERE d.deptDescripcion = :deptDescripcion")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dept_no")
    private Integer deptNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "dept_nombre")
    private String deptNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "dept_descripcion")
    private String deptDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private Collection<Empleado> empleadoCollection;

    public Departamento() {
    }

    public Departamento(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public Departamento(Integer deptNo, String deptNombre, String deptDescripcion) {
        this.deptNo = deptNo;
        this.deptNombre = deptNombre;
        this.deptDescripcion = deptDescripcion;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
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

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptNo != null ? deptNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.deptNo == null && other.deptNo != null) || (this.deptNo != null && !this.deptNo.equals(other.deptNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Departamento[ deptNo=" + deptNo + " ]";
    }
    
}
