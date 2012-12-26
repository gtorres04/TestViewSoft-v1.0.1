package com.testviewsoft.entity;

import com.testviewsoft.entity.DocumentosIdentidad;
import com.testviewsoft.entity.UsuariosPaises;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-12-25T23:57:45")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, Integer> id;
    public static volatile SingularAttribute<Usuarios, String> apellido;
    public static volatile SingularAttribute<Usuarios, String> mail;
    public static volatile SingularAttribute<Usuarios, Boolean> estado;
    public static volatile SingularAttribute<Usuarios, DocumentosIdentidad> tipoIdentificacion;
    public static volatile SingularAttribute<Usuarios, String> referenciaIdentificacion;
    public static volatile SingularAttribute<Usuarios, Date> tiempoEstado;
    public static volatile SingularAttribute<Usuarios, String> sexo;
    public static volatile SingularAttribute<Usuarios, Integer> nacionalidad;
    public static volatile SingularAttribute<Usuarios, Date> fechaNacimiento;
    public static volatile ListAttribute<Usuarios, UsuariosPaises> usuariosPaisesList;

}