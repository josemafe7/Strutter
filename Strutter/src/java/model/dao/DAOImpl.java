/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import model.entidades.Categoria;
import model.entidades.Cliente;
import model.entidades.Compra;
import model.entidades.Departamento;
import model.entidades.Empleado;
import model.entidades.Empresa;
import model.entidades.Oferta;
import model.entidades.Usuario;

/**
 *
 * @author Dani
 */
public class DAOImpl {

    public Usuario comprobarLogin(String user, String password) {
        UsuarioJerseyClient client = new UsuarioJerseyClient();
        GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {
        };
        List<Usuario> data = new ArrayList<Usuario>();
        data = client.findAll_XML(genericType);
        for (Usuario u : data) {
            if (u.getUsuario().equals(user) && u.getClave().equals(password)) {
                client.close();
                return u;
            }
        }
        client.close();
        return null;

    }

    public void altaUsuario(Usuario usuario) {
        UsuarioJerseyClient client = new UsuarioJerseyClient();
        client.create_XML(usuario);
        client.close();
    }

    public void altaCliente(Cliente cliente) {
        ClienteJerseyClient client = new ClienteJerseyClient();
        client.create_XML(cliente);
        client.close();
    }

    public static Usuario findUserByUsername(String username) {
        UsuarioJerseyClient client = new UsuarioJerseyClient();
        GenericType<Usuario> genericType = new GenericType<Usuario>() {
        };

        Usuario u = client.findByUserName_XML(genericType, username);
        client.close();
        return u;
    }

    public Cliente obtenerClientePorUsuario(Usuario usuario) {
        ClienteJerseyClient client = new ClienteJerseyClient();
        GenericType<List<Cliente>> genericType = new GenericType<List<Cliente>>() {
        };
        List<Cliente> data = new ArrayList<Cliente>();
        data = client.findAll_XML(genericType);
        for (Cliente c : data) {
            if (c.getUsuarioAsociado().equals(usuario)) {
                client.close();
                return c;
            }
        }
        client.close();
        return null;
    }

    public Empleado obtenerEmpleadoPorUsuario(Usuario usuario) {
        EmpleadoJerseyClient client = new EmpleadoJerseyClient();
        GenericType<List<Empleado>> genericType = new GenericType<List<Empleado>>() {
        };
        List<Empleado> data = new ArrayList<Empleado>();
        data = client.findAll_XML(genericType);
        for (Empleado e : data) {
            if (e.getUsuarioAsociado().equals(usuario)) {
                client.close();
                return e;
            }
        }
        client.close();
        return null;
    }

    public Empresa obtenerEmpresaPorUsuario(Usuario usuario) {
        EmpresaJerseyClient client = new EmpresaJerseyClient();
        GenericType<List<Empresa>> genericType = new GenericType<List<Empresa>>() {
        };
        List<Empresa> data = new ArrayList<Empresa>();
        data = client.findAll_XML(genericType);
        for (Empresa e : data) {
            if (e.getUsuarioAsociado().equals(usuario)) {
                client.close();
                return e;
            }
        }
        client.close();
        return null;
    }

    public List<Oferta> obtenerOfertas() {
        OfertaJerseyClient client = new OfertaJerseyClient();
        GenericType<List<Oferta>> genericType = new GenericType<List<Oferta>>() {
        };
        List<Oferta> data = new ArrayList<Oferta>();
        data = client.findAll_XML(genericType);
        client.close();
        return data;
    }

    public List<Empresa> obtenerEmpresas() {
        EmpresaJerseyClient client = new EmpresaJerseyClient();
        GenericType<List<Empresa>> genericType = new GenericType<List<Empresa>>() {
        };
        List<Empresa> data = new ArrayList<Empresa>();
        data = client.findAll_XML(genericType);
        client.close();
        return data;
    }

    public void altaOferta(Oferta oferta) {
        OfertaJerseyClient client = new OfertaJerseyClient();
        client.create_XML(oferta);
        client.close();
    }

    public void borrarOferta(int idOferta) {
        OfertaJerseyClient client = new OfertaJerseyClient();
        client.remove(String.valueOf(idOferta));
        client.close();
    }

    public void modificarOferta(Oferta o) {
        OfertaJerseyClient client = new OfertaJerseyClient();

        client.edit_XML(o, String.valueOf(o.getIdOferta()));
        client.close();
    }

    public List<Cliente> obtenerClientes() {
        ClienteJerseyClient client = new ClienteJerseyClient();
        GenericType<List<Cliente>> genericType = new GenericType<List<Cliente>>() {
        };
        List<Cliente> data = new ArrayList<Cliente>();
        data = client.findAll_XML(genericType);
        client.close();
        return data;
    }

    public List<Usuario> obtenerUsuarios() {
        UsuarioJerseyClient client = new UsuarioJerseyClient();
        GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {
        };
        List<Usuario> data = new ArrayList<Usuario>();
        data = client.findAll_XML(genericType);
        client.close();
        return data;
    }

    public Cliente buscarCliente(String dni) {
        ClienteJerseyClient client = new ClienteJerseyClient();
        GenericType<Cliente> genericType = new GenericType<Cliente>() {
        };

        Cliente c = client.find_XML(genericType, dni);
        client.close();
        return c;
    }

    public void modificarUsuario(Usuario usuario) {
        UsuarioJerseyClient client = new UsuarioJerseyClient();

        client.edit_XML(usuario, String.valueOf(usuario.getId()));
        client.close();
    }

    public void modificarCliente(Cliente cliente) {
        ClienteJerseyClient client = new ClienteJerseyClient();

        client.edit_XML(cliente, cliente.getDni());
        client.close();
    }

    public Usuario buscarUsuario(String usuarioid) {
        UsuarioJerseyClient client = new UsuarioJerseyClient();
        GenericType<Usuario> genericType = new GenericType<Usuario>() {
        };

        Usuario u = client.find_XML(genericType, usuarioid);
        client.close();
        return u;
    }

    public void borrarCliente(String dni) {
        ClienteJerseyClient client = new ClienteJerseyClient();
        client.remove(dni);
        client.close();
    }

    public void borrarUsuario(String id) {
        UsuarioJerseyClient client = new UsuarioJerseyClient();
        client.remove(id);
        client.close();
    }

    public List<Empleado> obtenerEmpleados() {
        EmpleadoJerseyClient client = new EmpleadoJerseyClient();
        GenericType<List<Empleado>> genericType = new GenericType<List<Empleado>>() {
        };
        List<Empleado> data = new ArrayList<Empleado>();
        data = client.findAll_XML(genericType);
        client.close();
        return data;
    }

    public List<Departamento> obtenerDepartamentos() {
        DepartamentoJerseyClient client = new DepartamentoJerseyClient();
        GenericType<List<Departamento>> genericType = new GenericType<List<Departamento>>() {
        };
        List<Departamento> data = new ArrayList<Departamento>();
        data = client.findAll_XML(genericType);
        client.close();
        return data;
    }

    public Departamento buscarDepartamento(String deptnoDepartamento) {
        DepartamentoJerseyClient client = new DepartamentoJerseyClient();
        GenericType<Departamento> genericType = new GenericType<Departamento>() {
        };

        Departamento d = client.find_XML(genericType, deptnoDepartamento);
        client.close();
        return d;
    }

    public void altaEmpleado(Empleado empleado) {
        EmpleadoJerseyClient client = new EmpleadoJerseyClient();
        client.create_XML(empleado);
        client.close();
    }

    public Empleado buscarEmpleado(String dni) {
        EmpleadoJerseyClient client = new EmpleadoJerseyClient();
        GenericType<Empleado> genericType = new GenericType<Empleado>() {
        };

        Empleado e = client.find_XML(genericType, dni);
        client.close();
        return e;
    }

    public void modificarEmpleado(Empleado empleado) {
        EmpleadoJerseyClient client = new EmpleadoJerseyClient();

        client.edit_XML(empleado, empleado.getDni());
        client.close();
    }

    public void borrarEmpleado(String dni) {
        EmpleadoJerseyClient client = new EmpleadoJerseyClient();
        client.remove(dni);
        client.close();
    }

    public void altaEmpresa(Empresa empresa) {
        EmpresaJerseyClient client = new EmpresaJerseyClient();
        client.create_XML(empresa);
        client.close();
    }

    public Empresa buscarEmpresa(String nif) {
        EmpresaJerseyClient client = new EmpresaJerseyClient();
        GenericType<Empresa> genericType = new GenericType<Empresa>() {
        };

        Empresa e = client.find_XML(genericType, nif);
        client.close();
        return e;
    }

    public void modificarEmpresa(Empresa empresa) {
        EmpresaJerseyClient client = new EmpresaJerseyClient();

        client.edit_XML(empresa, empresa.getNif());
        client.close();
    }

    public void borrarEmpresa(String nif) {
        EmpresaJerseyClient client = new EmpresaJerseyClient();
        client.remove(nif);
        client.close();
    }

    public List<Categoria> obtenerCategorias() {
        CategoriaJerseyClient client = new CategoriaJerseyClient();
        GenericType<List<Categoria>> genericType = new GenericType<List<Categoria>>() {
        };
        List<Categoria> data = new ArrayList<Categoria>();
        data = client.findAll_XML(genericType);
        client.close();
        return data;
    }

    public void altaCategoria(Categoria cat) {
        CategoriaJerseyClient client = new CategoriaJerseyClient();
        client.create_XML(cat);
        client.close();
    }

    public Categoria buscarCategoria(String id) {
        CategoriaJerseyClient client = new CategoriaJerseyClient();
        GenericType<Categoria> genericType = new GenericType<Categoria>() {
        };

        Categoria c = client.find_XML(genericType, id);
        client.close();
        return c;
    }

    public void modificarCategoria(Categoria cat) {
        CategoriaJerseyClient client = new CategoriaJerseyClient();

        client.edit_XML(cat, String.valueOf(cat.getId()));
        client.close();
    }

    public void borrarCategoria(String id) {
        CategoriaJerseyClient client = new CategoriaJerseyClient();
        client.remove(id);
        client.close();
    }

    public List<Compra> obtenerCompras() {
        CompraJerseyClient client = new CompraJerseyClient();
        GenericType<List<Compra>> genericType = new GenericType<List<Compra>>() {
        };
        List<Compra> data = new ArrayList<Compra>();
        data = client.findAll_XML(genericType);
        client.close();
        return data;
    }

    public void altaCompra(Compra co) {
        CompraJerseyClient client = new CompraJerseyClient();
        client.create_XML(co);
        client.close();
    }

    public Compra buscarCompra(String idCompra) {
        CompraJerseyClient client = new CompraJerseyClient();
        GenericType<Compra> genericType = new GenericType<Compra>() {
        };

        Compra c = client.find_XML(genericType, idCompra);
        client.close();
        return c;
    }

    public void modificarCompra(Compra co) {
        CompraJerseyClient client = new CompraJerseyClient();

        client.edit_XML(co, String.valueOf(co.getIdCompra()));
        client.close();
    }

    public void borrarCompra(String idCompra) {
        CompraJerseyClient client = new CompraJerseyClient();
        client.remove(idCompra);
        client.close();
    }

    public List<Compra> obtenerCompras(Cliente cliente) {
        CompraJerseyClient client = new CompraJerseyClient();
        GenericType<List<Compra>> genericType = new GenericType<List<Compra>>() {
        };
        List<Compra> data = new ArrayList<Compra>();
        data = client.findAll_XML(genericType);
        client.close();
        List<Compra> res = new ArrayList<Compra>();
        for (Compra c : data) {
            if (c.getClienteAsociado().equals(cliente)) {
                res.add(c);
            }
        }

        return res;
    }

    public List<Oferta> obtenerOfertas(Empresa empresa) {
        OfertaJerseyClient client = new OfertaJerseyClient();
        GenericType<List<Oferta>> genericType = new GenericType<List<Oferta>>() {
        };
        List<Oferta> data = new ArrayList<Oferta>();
        data = client.findAll_XML(genericType);
        client.close();
        List<Oferta> res = new ArrayList<Oferta>();
        for (Oferta o : data) {
            if (o.getEmpresaAsociada().equals(empresa) && !o.getEstado().equals("BORRADA")) {
                res.add(o);
            }
        }

        return res;
    }

    public Oferta buscarOferta(int idOferta) {
        OfertaJerseyClient client = new OfertaJerseyClient();
        GenericType<Oferta> genericType = new GenericType<Oferta>() {
        };

        Oferta o = client.find_XML(genericType, String.valueOf(idOferta));
        client.close();
        return o;
    }

    public List<Oferta> obtenerOfertasActivas() {
        OfertaJerseyClient client = new OfertaJerseyClient();
        GenericType<List<Oferta>> genericType = new GenericType<List<Oferta>>() {
        };
        List<Oferta> data = new ArrayList<Oferta>();
        data = client.findAll_XML(genericType);
        client.close();
        List<Oferta> res = new ArrayList<Oferta>();
        for (Oferta o : data) {
            if (o.getEstado().equals("ACTIVA")) {
                res.add(o);
            }
        }

        return res;
    }

    public List<Oferta> obtenerOfertasActivasByPalabra(String palabraBuscar) {
        List<Oferta> data = new ArrayList<Oferta>();
        data = this.obtenerOfertasActivas();
        List<Oferta> res = new ArrayList<Oferta>();
        for (Oferta o : data) {
            if (o.getTitulo().toUpperCase().contains(palabraBuscar.trim().toUpperCase()) || o.getDescripcion().toUpperCase().contains(palabraBuscar.trim().toUpperCase())) {
                res.add(o);
            }
        }

        return res;
    }

    public List<Oferta> obtenerOfertasActivasPorEmpresa(String nifEmpresa) {
        List<Oferta> data = new ArrayList<Oferta>();
        data = this.obtenerOfertasActivas();
        List<Oferta> res = new ArrayList<Oferta>();
        for (Oferta o : data) {
            if (o.getEmpresaAsociada().getNif().equals(nifEmpresa)) {
                res.add(o);
            }
        }

        return res;
    }

    public List<Oferta> obtenerOfertasActivasPorCategoria(String idCategoria) {
        List<Oferta> data = new ArrayList<Oferta>();
        data = this.obtenerOfertasActivas();
        List<Oferta> res = new ArrayList<Oferta>();
        for (Oferta o : data) {
            if (String.valueOf(o.getCategoriaAsociada().getId()).equals(idCategoria)) {
                res.add(o);
            }
        }

        return res;
    }


    public List<Compra> obtenerCompras(Empresa empresa) {
        CompraJerseyClient client = new CompraJerseyClient();
        GenericType<List<Compra>> genericType = new GenericType<List<Compra>>() {
        };
        List<Compra> data = new ArrayList<Compra>();
        data = client.findAll_XML(genericType);
        client.close();
        List<Compra> res = new ArrayList<Compra>();
        for (Compra c : data) {
            if (c.getOfertaAsociada().getEmpresaAsociada().equals(empresa)) {
                res.add(c);
            }
        }

        return res;
    }


    public List<Empleado> obtenerEmpleadosAdmin() {
        EmpleadoJerseyClient client = new EmpleadoJerseyClient();
        GenericType<List<Empleado>> genericType = new GenericType<List<Empleado>>() {
        };
        List<Empleado> data = new ArrayList<Empleado>();
        data = client.findAll_XML(genericType);
        client.close();
        List<Empleado> empleados = new ArrayList<Empleado>();
        for (Empleado e : data) {
            if (e.getDepartamento().getDeptNo() == 1) {
                empleados.add(e);
            }
        }
        return empleados;
    }

}
