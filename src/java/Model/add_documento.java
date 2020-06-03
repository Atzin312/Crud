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
public class add_documento {
    public int id_aclaracion;
    
    public String url;
    
    public int id_documento;

    public int getId_aclaracion() {
        return id_aclaracion;
    }

    public void setId_aclaracion(int id_aclaracion) {
        this.id_aclaracion = id_aclaracion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public add_documento(int id_aclaracion, String url, int id_documento) {
        this.id_aclaracion = id_aclaracion;
        this.url = url;
        this.id_documento = id_documento;
    }
    public add_documento(){
        
    }
}
