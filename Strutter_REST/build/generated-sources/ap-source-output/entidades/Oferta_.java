package entidades;

import entidades.Categoria;
import entidades.Compra;
import entidades.Empresa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-03T09:54:34")
@StaticMetamodel(Oferta.class)
public class Oferta_ { 

    public static volatile SingularAttribute<Oferta, String> descripcion;
    public static volatile SingularAttribute<Oferta, Double> precio;
    public static volatile SingularAttribute<Oferta, String> estado;
    public static volatile SingularAttribute<Oferta, Categoria> categoriaAsociada;
    public static volatile CollectionAttribute<Oferta, Compra> compraCollection;
    public static volatile SingularAttribute<Oferta, String> titulo;
    public static volatile SingularAttribute<Oferta, String> imagen;
    public static volatile SingularAttribute<Oferta, Integer> idOferta;
    public static volatile SingularAttribute<Oferta, Date> fechaOferta;
    public static volatile SingularAttribute<Oferta, Empresa> empresaAsociada;

}