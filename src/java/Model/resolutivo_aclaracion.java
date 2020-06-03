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
public class resolutivo_aclaracion {
    
    public int id_resolutivo;
    
    public String resolutivo;
    
    
    
    public resolutivo_aclaracion(int id_resolutivo, String resolutivo) {
        this.id_resolutivo = id_resolutivo;
        this.resolutivo = resolutivo;
    }
    
    public resolutivo_aclaracion(){
    }
    
    
    public int getId_resolutivo() {
        return id_resolutivo;
    }

    public void setId_resolutivo(int id_resolutivo) {
        this.id_resolutivo = id_resolutivo;
    }

    public String getResolutivo() {
        return resolutivo;
    }

    public void setResolutivo(String resolutivo) {
        this.resolutivo = resolutivo;
    }
   
   
   
}
