package entidades;

import entidades.Oferta;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-03T09:54:34")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> categoria;
    public static volatile SingularAttribute<Categoria, Integer> id;
    public static volatile CollectionAttribute<Categoria, Oferta> ofertaCollection;

}