 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.entidades.Cliente;
import model.entidades.Usuario;
import java.util.Map;
import model.dao.DAOImpl;
import model.entidades.Empleado;
import model.entidades.Empresa;


/**
 *
 * @author Dani
 */
public class LoginAction extends ActionSupport {

    /* Login */
    String user;
    String password;
    public LoginAction() {
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

    
    // Comprobamos que las credenciales sean correctas y guardamos las sesiones de usuario y tipo de usuario
    public String execute() throws Exception {
        try {
            if (!this.getUser().equals("") && !this.getPassword().equals("")) {
                Usuario usuario = new DAOImpl().comprobarLogin(this.getUser(), this.getPassword());
                if (usuario != null) {
                    Map session = (Map) ActionContext.getContext().get("session");
                    session.put("usuario", usuario);
                    if (usuario.getTipo().equals("CLIENTE")) {
                        Cliente cliente = new DAOImpl().obtenerClientePorUsuario(usuario);
                        session.put("datosUser", cliente);
                    } else if (usuario.getTipo().equals("EMPLEADO")) {
                        Empleado empleado = new DAOImpl().obtenerEmpleadoPorUsuario(usuario);
                        session.put("datosUser", empleado);
                    } else if (usuario.getTipo().equals("EMPRESA")) {
                        Empresa empresa = new DAOImpl().obtenerEmpresaPorUsuario(usuario);
                        session.put("datosUser", empresa);
                    }
                    return SUCCESS;
                } else {
                    addFieldError("user", getText("login.error"));
                    return INPUT;
                }
            } else {
                return ERROR;
            }

        } catch (Exception ex) {
            return ERROR;
        }
    }

    //Logout
    public String logout() {
        try {
            Map sesion = (Map) ActionContext.getContext().getSession();

            sesion.put("usuario", null);
            sesion.put("datosUser", null);
            sesion.remove("usuario");
            sesion.remove("datosUser");

            return SUCCESS;
        } catch (Exception ex) {
            return ERROR;
        }
    }

}
