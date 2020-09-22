package entidades;

import entidades.Oferta;
import entidades.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-03T09:54:34")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> descripcion;
    public static volatile SingularAttribute<Empresa, Usuario> usuarioAsociado;
    public static volatile SingularAttribute<Empresa, String> correo;
    public static volatile SingularAttribute<Empresa, String> direccion;
    public static volatile SingularAttribute<Empresa, String> nif;
    public static volatile SingularAttribute<Empresa, String> localidad;
    public static volatile SingularAttribute<Empresa, Integer> codpostal;
    public static volatile SingularAttribute<Empresa, Integer> telefono;
    public static volatile CollectionAttribute<Empresa, Oferta> ofertaCollection;
    public static volatile SingularAttribute<Empresa, String> nombre;

}