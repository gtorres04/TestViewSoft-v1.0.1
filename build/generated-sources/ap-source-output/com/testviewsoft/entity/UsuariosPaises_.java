package com.testviewsoft.entity;

import com.testviewsoft.entity.Paises;
import com.testviewsoft.entity.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-12-20T10:09:15")
@StaticMetamodel(UsuariosPaises.class)
public class UsuariosPaises_ { 

    public static volatile SingularAttribute<UsuariosPaises, Integer> id;
    public static volatile SingularAttribute<UsuariosPaises, Boolean> estado;
    public static volatile SingularAttribute<UsuariosPaises, Usuarios> usuariosId;
    public static volatile SingularAttribute<UsuariosPaises, String> gentilicio;
    public static volatile SingularAttribute<UsuariosPaises, Date> tiempoEstado;
    public static volatile SingularAttribute<UsuariosPaises, Paises> paisesId;

}