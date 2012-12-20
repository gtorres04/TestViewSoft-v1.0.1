package com.testviewsoft.entity;

import com.testviewsoft.entity.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-12-20T18:51:21")
@StaticMetamodel(DocumentosIdentidad.class)
public class DocumentosIdentidad_ { 

    public static volatile SingularAttribute<DocumentosIdentidad, Integer> id;
    public static volatile SingularAttribute<DocumentosIdentidad, String> nombre;
    public static volatile SingularAttribute<DocumentosIdentidad, Boolean> estado;
    public static volatile SingularAttribute<DocumentosIdentidad, Date> tiempoEstado;
    public static volatile ListAttribute<DocumentosIdentidad, Usuarios> usuariosList;

}