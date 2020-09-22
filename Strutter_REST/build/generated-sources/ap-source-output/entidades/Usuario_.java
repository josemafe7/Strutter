package entidades;

import entidades.Cliente;
import entidades.Empleado;
import entidades.Empresa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-03T09:54:34")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, String> tipo;
    public static volatile CollectionAttribute<Usuario, Empleado> empleadoCollection;
    public static volatile CollectionAttribute<Usuario, Empresa> empresaCollection;
    public static volatile SingularAttribute<Usuario, String> usuario;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile CollectionAttribute<Usuario, Cliente> clienteCollection;

}