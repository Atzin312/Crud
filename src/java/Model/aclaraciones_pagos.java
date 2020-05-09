/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author atzin
 */
public class aclaraciones_pagos {
    private String fecha_solicitud; 
    
    private String fecha_resolutiva;
    
    private String nombre;
    
    private String tipo_usuario;
    
    private String semana_pago;
    
    private int id_aclaracion;
    
    private int id_resolutivo_aclaracion;
    
    private int id_usuario;
    
    private String Usuario;
    
    private String Apellido;
    
    private int id_rol;
    
    private String resolutivo;
    
    private String Comentario;
    
    private int id_ciudad;
    
    private String ciudad;

    public aclaraciones_pagos(String fecha_solicitud, String fecha_resolutiva, String nombre, String tipo_usuario, String semana_pago, int id_aclaracion, int id_resolutivo_aclaracion, int id_usuario, String Usuario, String Apellido, int id_rol, String resolutivo, String Comentario, int id_ciudad, String ciudad) {
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_resolutiva = fecha_resolutiva;
        this.nombre = nombre;
        this.tipo_usuario = tipo_usuario;
        this.semana_pago = semana_pago;
        this.id_aclaracion = id_aclaracion;
        this.id_resolutivo_aclaracion = id_resolutivo_aclaracion;
        this.id_usuario = id_usuario;
        this.Usuario = Usuario;
        this.Apellido = Apellido;
        this.id_rol = id_rol;
        this.resolutivo = resolutivo;
        this.Comentario = Comentario;
        this.id_ciudad = id_ciudad;
        this.ciudad = ciudad;
    }
    public aclaraciones_pagos(){
        
    }

   

    public String getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(String fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public String getFecha_resolutiva() {
        return fecha_resolutiva;
    }

    public void setFecha_resolutiva(String fecha_resolutiva) {
        this.fecha_resolutiva = fecha_resolutiva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getSemana_pago() {
        return semana_pago;
    }

    public void setSemana_pago(String semana_pago) {
        this.semana_pago = semana_pago;
    }

    public int getId_aclaracion() {
        return id_aclaracion;
    }

    public void setId_aclaracion(int id_aclaracion) {
        this.id_aclaracion = id_aclaracion;
    }

    public int getId_resolutivo_aclaracion() {
        return id_resolutivo_aclaracion;
    }

    public void setId_resolutivo_aclaracion(int id_resolutivo_aclaracion) {
        this.id_resolutivo_aclaracion = id_resolutivo_aclaracion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getResolutivo() {
        return resolutivo;
    }

    public void setResolutivo(String resolutivo) {
        this.resolutivo = resolutivo;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
}
