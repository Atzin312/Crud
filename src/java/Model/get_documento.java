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
public class get_documento {

    public int id_aclaracion;
    public int id_documento;
    public int id_tipo_documento;
    public String tipo_documento;
    public String archivo;
    public String estado;
    public String comentario;
    public String nombre_archivo;
    
    
    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }
    
    
    public get_documento(int id_aclaracion, int id_documento, int id_tipo_documento, String tipo_documento, String archivo, String estado, String comentario, String nombre_archivo) {
        this.id_aclaracion = id_aclaracion;
        this.id_documento = id_documento;
        this.id_tipo_documento = id_tipo_documento;
        this.tipo_documento = tipo_documento;
        this.archivo = archivo;
        this.estado = estado;
        this.comentario = comentario;
        this.nombre_archivo = nombre_archivo;
    }
    public get_documento(){
        
    }
    
    public int getId_aclaracion() {
        return id_aclaracion;
    }

    public void setId_aclaracion(int id_aclaracion) {
        this.id_aclaracion = id_aclaracion;
    }

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public int getId_tipo_documento() {
        return id_tipo_documento;
    }

    public void setId_tipo_documento(int id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
    
    
}
