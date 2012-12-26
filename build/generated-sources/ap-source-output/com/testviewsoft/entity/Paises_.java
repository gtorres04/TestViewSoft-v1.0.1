package com.testviewsoft.entity;

import com.testviewsoft.entity.UsuariosPaises;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-12-25T23:57:45")
@StaticMetamodel(Paises.class)
public class Paises_ { 

    public static volatile SingularAttribute<Paises, Integer> id;
    public static volatile SingularAttribute<Paises, String> nombre;
    public static volatile SingularAttribute<Paises, Boolean> estado;
    public static volatile SingularAttribute<Paises, Date> tiempoEstado;
    public static volatile ListAttribute<Paises, UsuariosPaises> usuariosPaisesList;

}