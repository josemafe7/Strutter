package entidades;

import entidades.Departamento;
import entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-03T09:54:34")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> apellido2;
    public static volatile SingularAttribute<Empleado, String> apellido1;
    public static volatile SingularAttribute<Empleado, Double> salario;
    public static volatile SingularAttribute<Empleado, String> direccion;
    public static volatile SingularAttribute<Empleado, String> nombre;
    public static volatile SingularAttribute<Empleado, Date> fechaNac;
    public static volatile SingularAttribute<Empleado, Usuario> usuarioAsociado;
    public static volatile SingularAttribute<Empleado, String> correo;
    public static volatile SingularAttribute<Empleado, Departamento> departamento;
    public static volatile SingularAttribute<Empleado, String> localidad;
    public static volatile SingularAttribute<Empleado, Integer> codpostal;
    public static volatile SingularAttribute<Empleado, String> sexo;
    public static volatile SingularAttribute<Empleado, Integer> telefono;
    public static volatile SingularAttribute<Empleado, String> dni;

}