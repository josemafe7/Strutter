/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.dao.DAOImpl;
import model.entidades.Cliente;
import model.entidades.Compra;
import model.entidades.Empresa;
import model.entidades.Oferta;
import model.entidades.Usuario;

/**
 *
 * @author Dani
 */
public class ComprasAction extends ActionSupport {

    /*Select*/
    private String idCompra;
    private String fechaCompra;
    private Oferta ofertaAsociada;
    private Cliente clienteAsociado;
    private List<Compra> compras;

    /*Add*/
    private List<Oferta> listOfertas;
    private List<Cliente> listClientes;
    private int idOfertaCompra;
    private String dniCompra;

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Oferta getOfertaAsociada() {
        return ofertaAsociada;
    }

    public void setOfertaAsociada(Oferta ofertaAsociada) {
        this.ofertaAsociada = ofertaAsociada;
    }

    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Oferta> getListOfertas() {
        return listOfertas;
    }

    public void setListOfertas(List<Oferta> listOfertas) {
        this.listOfertas = listOfertas;
    }

    public List<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public int getIdOfertaCompra() {
        return idOfertaCompra;
    }

    public void setIdOfertaCompra(int idOfertaCompra) {
        this.idOfertaCompra = idOfertaCompra;
    }

    public String getDniCompra() {
        return dniCompra;
    }

    public void setDniCompra(String dniCompra) {
        this.dniCompra = dniCompra;
    }

    public ComprasAction() {
    }

    //Preparamos lo necesario para ir a ver las compras pertinenetes de cada usuario, así como preparar el formulario de alta
    public String execute() throws Exception {

        try {

            Map session = (Map) ActionContext.getContext().get("session");
            Usuario sesionUsuario = (Usuario) session.get("usuario");

            if (sesionUsuario.getTipo().equals("EMPLEADO")) {
                compras = new DAOImpl().obtenerCompras();
                listClientes = new DAOImpl().obtenerClientes();
                listOfertas = new DAOImpl().obtenerOfertasActivas();
            } else if (sesionUsuario.getTipo().equals("CLIENTE")) {
                Cliente sesionCliente = (Cliente) session.get("datosUser");
                compras = new DAOImpl().obtenerCompras(sesionCliente);
            } else if (sesionUsuario.getTipo().equals("EMPRESA")) {
                Empresa sesionEmpresa = (Empresa) session.get("datosUser");
                compras = new DAOImpl().obtenerCompras(sesionEmpresa);
            }

            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Alta compra
    public String altaCompra() throws Exception {
        try {

            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            String annio = Integer.toString(c.get(Calendar.YEAR));

            String fechaActual = ("" + annio + "-" + mes + "-" + dia + "");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaP = formato.parse(fechaActual);

            Oferta o = new DAOImpl().buscarOferta(this.getIdOfertaCompra());
            o.setEstado("COMPRADA");
            new DAOImpl().modificarOferta(o);
            Cliente cli = new DAOImpl().buscarCliente(String.valueOf(this.getDniCompra()));

            Compra co = new Compra();
            co.setFechaCompra(fechaP);
            co.setOfertaAsociada(o);
            co.setClienteAsociado(cli);

            new DAOImpl().altaCompra(co);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    // Prepara el formulario de modificar compra
    public String goModifyCompra() {
        //Esto lo hacemos para que la primera opcion sea la empresa actual.
        Compra c = new DAOImpl().buscarCompra(this.getIdCompra());
        listOfertas = new DAOImpl().obtenerOfertasActivas();
        listOfertas.add(0, c.getOfertaAsociada());

        listClientes = new DAOImpl().obtenerClientes();
        listClientes.remove(c.getClienteAsociado());
        listClientes.add(0, c.getClienteAsociado());

        return SUCCESS;

    }

    // Modificar compra
    public String modificarCompra() throws Exception {
        try {

            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            String annio = Integer.toString(c.get(Calendar.YEAR));

            String fechaActual = ("" + annio + "-" + mes + "-" + dia + "");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaP = formato.parse(fechaActual);

            Compra oldCompra = new DAOImpl().buscarCompra(this.getIdCompra());
            Oferta o = new DAOImpl().buscarOferta(this.getIdOfertaCompra());

            if (!oldCompra.getOfertaAsociada().equals(o)) {
                oldCompra.getOfertaAsociada().setEstado("ACTIVA");
                o.setEstado("COMPRADA");
                new DAOImpl().modificarOferta(oldCompra.getOfertaAsociada());
            }

            new DAOImpl().modificarOferta(o);
            Cliente cli = new DAOImpl().buscarCliente(String.valueOf(this.getDniCompra()));

            Compra co = new Compra();
            co.setIdCompra(Integer.parseInt(this.getIdCompra()));
            co.setFechaCompra(fechaP);
            co.setOfertaAsociada(o);
            co.setClienteAsociado(cli);

            new DAOImpl().modificarCompra(co);
            return SUCCESS;

        } catch (Exception ex) {
            return ERROR;
        }
    }

    // Borrar compra, implementada, pero no se usará en la aplicación
    public String borrarCompra() {

        new DAOImpl().borrarCompra(this.getIdCompra());
        return SUCCESS;

    }
}
