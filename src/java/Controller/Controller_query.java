/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author atzin
 */
public class Controller_query {
    
    public String spGetUsuario() {
        String sql = "{call sp_getusuario(?)}";
        return sql;
    }
    public String spGetIndex() {
         String sql2 = "{call sp_indexaclaracionespagos(?) }";
        return sql2;
    }
    public String spGetDocumentos() {
         String sql6 = "{call sp_get_documentos_aclaracion(?)}";
        return sql6;
      }
    public String spDeleteDocumentos() {
         String sql5 = "{call sp_eliminar_documentos_aclaracion (?,?)}";
        return sql5;
      }
    public String spUploadResolutivo() {
         String sql4 ="{call sp_registrar_resolutivo_aclaracion(?,?) }";
        return sql4;
      }
     public String spGetRoles() {
         String sql3 =  "SELECT id_rol, nombre_rol FROM public.rol";
        return sql3;
    }
     
    
       
       
     
    
    
    
}
