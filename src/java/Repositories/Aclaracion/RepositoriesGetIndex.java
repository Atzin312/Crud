/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.Aclaracion;

import Conection.ConnectBD;
import Model.aclaraciones_pagos;
import Model.add_documento;
import Model.get_documento;
import Model.get_usuarios;
import Model.resolutivo_aclaracion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author atzin
 */
public class RepositoriesGetIndex {
    private ConnectBD dbSource = null;
    public int id_usuario;
    public RepositoriesGetIndex() {
        this.dbSource = new ConnectBD();
    }
    
    public int id_documento;
    public int id_aclaracion;
    public int id_aclara;
    public int id_aclaraEli;
    public int id_documentoEli;
    public int id_aclaracionRes;

    public get_usuarios spGetUsuario(String txtBuscar) {
        String sql = "{call sp_getusuario(?)}";
        get_usuarios usuario = new get_usuarios();
        
        System.out.println(txtBuscar);

        try (Connection dbConnection = dbSource.conectar().getConnection();
                CallableStatement newService = dbConnection.prepareCall(sql);) {

            newService.setString(1, txtBuscar);
            newService.execute();
            System.out.println(newService);

            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
                System.out.println("RESPONSE:" + servicesRs);
                while (servicesRs.next()) {
                    
                    usuario.setId_usuario(servicesRs.getInt(1));
                    usuario.setNum_telefono(servicesRs.getString(4));
                    usuario.setCorreo(servicesRs.getString(5));
//                    Add value to user id
                    id_usuario = servicesRs.getInt(1);

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }

    public String getListAclaraciones(int getId_usuario) throws JsonProcessingException {
        List<aclaraciones_pagos> pagos = new ArrayList<aclaraciones_pagos>();
        String sql2 = "{call sp_indexaclaracionespagos(?)}";
            try (Connection dbConnection2 = dbSource.conectar().getConnection();
                    //Tipo CallableStatement, otra variante tambien es usar PrepareStatement
                    CallableStatement newService2 = dbConnection2.prepareCall(sql2);) {
                newService2.setInt(1, getId_usuario);
                System.out.println("ID USUARIO EN INDEX " + getId_usuario);
//            dbConnection.setAutoCommit(false);
                newService2.execute();

                try (ResultSet servicesRs = (ResultSet) newService2.getResultSet();) {
                    while (servicesRs.next()) {

                        //System.out.println(servicesRs.getString(1));
                        aclaraciones_pagos pago = new aclaraciones_pagos();
                        pago.setFecha_solicitud(servicesRs.getString(1));
                        pago.setFecha_resolutiva(servicesRs.getString(2));
                        pago.setNombre(servicesRs.getString(3));
                        pago.setTipo_usuario(servicesRs.getString(4));
                        pago.setSemana_pago(servicesRs.getString(5));
                        pago.setId_aclaracion(servicesRs.getInt(6));
                        pago.setId_resolutivo_aclaracion(servicesRs.getInt(7));
                        pago.setId_usuario(servicesRs.getInt(8));
                        pago.setUsuario(servicesRs.getString(9));
                        pago.setApellido(servicesRs.getString(10));
                        pago.setId_rol(servicesRs.getInt(11));
                        pago.setResolutivo(servicesRs.getString(12));
                        pago.setComentario(servicesRs.getString(13));
                        pago.setId_ciudad(servicesRs.getInt(14));
                        pago.setCiudad(servicesRs.getString(15));
                        id_aclaracion = servicesRs.getInt(6);
                        pagos.add(pago);
                        
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(pagos);
            return data;
            //System.out.println("ID ACLA " + id_aclaracion);
    }
    
     public List<get_documento> getDocumentos(int id_aclara_docus) {
         List<get_documento> docus = new ArrayList<>();
        String sql6 = "{call sp_get_documentos_aclaracion(?)}";
        
        id_aclara = id_aclara_docus;
         System.out.println("ID_ ACLARA dOCUS" +id_aclara_docus);
        System.out.println("GETID_ACLARACION "+id_aclara);
        try (Connection dbConnection6 = dbSource.conectar().getConnection();
                CallableStatement newService6 = dbConnection6.prepareCall(sql6);) {

            newService6.setInt(1, id_aclara);
            newService6.execute();
            System.out.println(newService6);

            try (ResultSet servicesRs = (ResultSet) newService6.getResultSet();) {
                System.out.println("RESPONSE:" + servicesRs);
                while (servicesRs.next()) {
                    get_documento docu = new get_documento();
                    
                    docu.setId_documento(servicesRs.getInt(1));
                    docu.setId_aclaracion(servicesRs.getInt(2));
                    docu.setId_tipo_documento(servicesRs.getInt(3));
                    docu.setTipo_documento(servicesRs.getString(4));
                    docu.setArchivo(servicesRs.getString(5));
                     docu.setEstado(servicesRs.getString(6));
                    docu.setComentario(servicesRs.getString(7));
                    id_documento = servicesRs.getInt(1);

                    String str = servicesRs.getString(5); 
                    String[] arrOfStr = str.split("/", 0); 

                    System.out.println("FILENAME:"+arrOfStr[arrOfStr.length - 1 ]);
                    docu.setNombre_archivo(arrOfStr[arrOfStr.length - 1 ]);
                 
                    docus.add(docu);
       
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("ID DOCUMENTO Aparte " + id_documento);

       
        return docus;
     }
     
     public List<add_documento> DeleteDocumentos(int  getId_aclaracion, int getId_documento) {
         List<add_documento> eliminar_documentos = new ArrayList<>();
        String sql6 = "{call sp_eliminar_documentos_aclaracion (?,?)}";
        
        System.out.println("GETID_ACLARACION_ELIMINAR "+id_aclara);
        try (Connection dbConnection6 = dbSource.conectar().getConnection();
                CallableStatement newService = dbConnection6.prepareCall(sql6);) {

            newService.setInt(1, getId_aclaracion);
            newService.setInt(2, getId_documento);
            newService.execute();
            System.out.println(newService);

            try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
                System.out.println("RESPONSE:" + servicesRs);
                while (servicesRs.next()) {
                    add_documento eliminar_documento = new add_documento();
                    
                     eliminar_documento.setId_aclaracion(servicesRs.getInt(1));
                     eliminar_documento.setUrl(servicesRs.getString(2));
                     eliminar_documento.setId_documento(servicesRs.getInt(3));
         
                    eliminar_documentos.add(eliminar_documento);
      
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eliminar_documentos;
     }
      public List<resolutivo_aclaracion> uploadResolutivo(int  getId_aclaracion, String resolutivoMod) {
           List<resolutivo_aclaracion> aclaraciones = new ArrayList<>();
            
            System.out.println(resolutivoMod);
            System.out.println("Entre en add Resolutivo");
            
            System.out.println("RESOLUTIVO " + resolutivoMod);
            if (resolutivoMod != null){
                String sql ="{call sp_registrar_resolutivo_aclaracion(?,?) }";
                try (Connection dbConnection = dbSource.conectar().getConnection();
                    CallableStatement newService = dbConnection.prepareCall(sql);) {

                    newService.setInt(1, getId_aclaracion);
                    newService.setString(2, resolutivoMod);
                    System.out.println(newService);
                    newService.execute();
                    System.out.println(newService);

                    try (ResultSet servicesRs = (ResultSet) newService.getResultSet();) {
                        System.out.println("RESPONSE:" + servicesRs);
                        while (servicesRs.next()) {
                            resolutivo_aclaracion aclaracion = new resolutivo_aclaracion();
                            aclaracion.setId_resolutivo(servicesRs.getInt(1));
                            aclaracion.setResolutivo(servicesRs.getString(2));
                            aclaraciones.add(aclaracion);

                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                
            }
        
        
        return aclaraciones;
      }
}
