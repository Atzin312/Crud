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
public class add_aclaracion {
   
   public int id_usuario;
   
   public String fecha;
   
   public String comentario;
   
   public int id_rol;
   
   
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
   

    public add_aclaracion(int id_usuario, String fecha, String Comentarios) {
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.comentario = comentario;
        this.id_rol = id_rol;
    }
   
   public add_aclaracion(){
       
   }
          
}
