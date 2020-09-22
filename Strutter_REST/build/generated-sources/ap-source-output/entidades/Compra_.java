package entidades;

import entidades.Cliente;
import entidades.Oferta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-03T09:54:34")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Date> fechaCompra;
    public static volatile SingularAttribute<Compra, Cliente> clienteAsociado;
    public static volatile SingularAttribute<Compra, Oferta> ofertaAsociada;
    public static volatile SingularAttribute<Compra, Integer> idCompra;

}