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
public class aclaraciones_usuarios {
    public int id_aclaracion;
    public String fecha_aclaracion;
    public String comentario;
    public int id_usuario;
    public int id_unidad;
    public int id_rol;

    public int getId_aclaracion() {
        return id_aclaracion;
    }

    public void setId_aclaracion(int id_aclaracion) {
        this.id_aclaracion = id_aclaracion;
    }

    public String getFecha_aclaracion() {
        return fecha_aclaracion;
    }

    public void setFecha_aclaracion(String fecha_aclaracion) {
        this.fecha_aclaracion = fecha_aclaracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(int id_unidad) {
        this.id_unidad = id_unidad;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public aclaraciones_usuarios(int id_aclaracion, String fecha_aclaracion, String comentario, int id_usuario, int id_unidad, int id_rol) {
        this.id_aclaracion = id_aclaracion;
        this.fecha_aclaracion = fecha_aclaracion;
        this.comentario = comentario;
        this.id_usuario = id_usuario;
        this.id_unidad = id_unidad;
        this.id_rol = id_rol;
    }
    
    public aclaraciones_usuarios(){
        
    }
}
