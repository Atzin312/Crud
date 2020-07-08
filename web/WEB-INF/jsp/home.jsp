<%-- 
    Document   : home
    Author     : atzin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Aclaracion de pagos</title>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">      
        <meta charset="UTF-8">


        <!-- JQUERY CDN -->
        <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

        <!-- Fontawesome  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="css/home.css"/>
        <style>
            .pac-container {
                z-index: 100000;
            }
            .modal-backdrop {
                visibility: hidden !important;
            }
            .modal.in {
                background-color: rgba(0,0,0,0.5);
            }
            
        </style>
    </head>


    <body>


        <!-- Header section -->
        <div class="row mx-auto col-md-12 p-4">

            <!-- Home and plus buttons -->
            <div class="row col-md-2 ml-2">

                <!-- Home Button -->
                <div
                    class="
                    d-flex
                    justify-content-center
                    align-items-center
                    rounded-circle
                    bg-white"
                    style="
                    width: 40px;
                    height: 40px; color: black;">

                    <button type="button" class="btn">
                        <span style="font-size: 150%">
                            <i class="fa fa-home"></i>
                        </span>
                    </button>

                </div>

                <!-- Arrow left button -->
                <div class="
                     d-flex
                     justify-content-center
                     align-items-center
                     ml-3"
                     style="
                     width: 40px;
                     height: 40px;">

                    <button type="button" class="btn">
                        <span style="font-size: 150%">
                            <i class="fa fa-arrow-left"></i>
                        </span>
                    </button>

                </div>

            </div>

            <!-- Header title -->
            <div class="col-md-8">

                <h1 class="text-center">Aclaración de Pagos</h1>

            </div>



            <div class="col-md-12 col-sm-12 col-lg-12">

                <div class="card">
                    <div class="card-header text-black">
                        <div class="row col-sm-12 col-lg-12 col-md-12">
                            <div class="row col-md-3">
                                <h5>Nombre de usuario</h5>
                            </div>
                            <div class="row col-sm-3 col-lg-3 col-md-3">
                                <form class="form-inline">
                                    <input name="txtBuscar" id="txtBuscar" type="search"  class="form-control">
                                    <!-- Arrow left button -->
                                    <div class="
                                         d-flex
                                         justify-content-center
                                         align-items-center
                                         ml-4"
                                         style="
                                         width: 40px;
                                         height: 40px;">

                                        <button type="submit" id="btnBuscar" class="btn">
                                            <span style="font-size: 150%">
                                                <i class="fa fa-search"></i>
                                            </span>
                                        </button>

                                    </div>
                                </form>
                            </div>

                            <div class="col-md-5">

                            </div>

                            <!-- Plus button -->
                            <div  class="
                                  d-flex
                                  justify-content-center
                                  align-items-center
                                  ml-4"
                                  style="
                                  width: 40px;
                                  height: 40px;">
                                <!-- Add button -->
                                <div
                                    class="
                                    d-flex
                                    justify-content-center
                                    align-items-center
                                    rounded-circle
                                    bg-white"
                                    style="
                                    width: 40px;
                                    height: 40px; color: black;">

                                    <button type="submit" id="btnAdd" class="btn" data-toggle="modal" data-target="#ModalCreate">
                                        <span style="font-size: 150%">
                                            <i class="fa fa-plus-circle"></i>
                                        </span>
                                    </button>

                                </div>
                                <c:forEach items="${usuarios}" var="usuario" varStatus="status">
                                    <input id="getNumNot" type="text" hidden="false" value="${usuario.num_telefono}"/>
                                    <input id="getCorreoNot" type="text" hidden="false" value="${usuario.correo}"/>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!-- Index Table -->
                    <div class="card-body">
                        <table id="dataTable" class="table table-striped table-bordered table-hover">


                            <thead class="thead-dark">
                                <tr>
                                    <th>Aclaración</th>
                                    <th>Fecha de solicitud</th>
                                    <th>Fecha resolutiva</th>
                                    <th>Nombre</th>
                                    <th>Tipo de usuarios</th>
                                    <th>Semana de pago</th>
                                    <th>Ver</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="pago" items="${pagos}" varStatus="tempago">
                                    <tr >
                                        <td>${pago.id_aclaracion}</td>
                                        <td>${pago.fecha_solicitud}</td>
                                        <td>${pago.fecha_resolutiva}</td>
                                        <td>${pago.nombre}</td>
                                        <td>${pago.tipo_usuario}</td>
                                        <td>${pago.semana_pago}</td>
                                        <!-- details button -->
                                        <td <button 
                                                type="button" 
                                                onclick="setTextToInput(
                                                                '${pago.nombre}',
                                                                '${pago.apellido}',
                                                                '${pago.tipo_usuario}',
                                                                '${pago.semana_pago}',
                                                                '${pago.comentario}',
                                                                '${pago.resolutivo}',
                                                                ${pago.id_aclaracion})"
                                                data-toggle="modal" 
                                                data-target="#ModalModificate" 
                                                class="btn ml-3">
                                                <span style="font-size: 150%">
                                                    <i class="fa fa-search"></i>
                                                </span>
                                            </button> </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal Create -->
        <div id="ModalCreate" aria-labelledby="mediumModalLabel" class="modal fade" role="dialog" tabindex="-1" aria-hidden="true" >

            <div class="modal-dialog modal-md modal-dialog-centered">

                <!-- Modal content-->
                <div class="modal-content ">

                    <div class="modal-header">
                        <h4 class="modal-title">Nueva aclaracion de pago</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="d-flex align-items-center justify-content-center modal-body">

                        <div class="form-group col-md-12 col-sm-12">
                            <form id="getUserForm">
                                <div class="row col-md-12 col-sm-12">

                                    <label class="control-label col-md-6">Correo/Telefono</label>
                                    <div class="col-md-6 input-group" style="padding:0;">
                                        <input id="txtGetUser" name="txtGetUser" type="text" class="form-control" placeholder="" maxlength="100">
                                        <button id="btnGetUser" type="submit"  class="input-group-addon btn">
                                            <span class="fa fa-1x fa-search"></span>
                                        </button>

                                    </div>
                                </div>
                            </form>
                            <br>
                            <form id="getAdd_aclaracion">
                                <div class="row col-md-12 col-sm-12">
                                    <label id="txtNombre" class="control-label col-md-6">Nombres: </label>
                                    <input name="nombreNue" id="nombreNue" class="form-control form-control-sm col-md-6"/>
                                </div>

                                <div class="row col-md-12 col-sm-12">
                                    <label id="txtApellido" class="control-label col-md-6">Apellidos: </label>
                                    <input name="apellidoNue" id="apellidoNue" class="form-control form-control-sm col-md-6"/>
                                </div>
                                <br>
                                <div class="row col-md-12 col-sm-12">
                                    <label class="control-label col-md-6">Tipo de usuario:</label>
                                    <select name="tipo_usuarioNue" id="tipo_usuarioNue" type="text" class="form-control form-control-sm col-md-6" placeholder="" maxlength="100">
                                        <option>Seleccionar</option>
                                    </select>
                                </div>
                                <br>
                                <div class="row col-md-12 col-sm-12">
                                    <label class="control-label col-md-6">Semana de pago</label>
                                    <input name="semana_pagoNue" id="semana_pagoNue" data-provide="datepicker" type="search" class="form-control form-control-sm col-md-6" >
                                </div>
                                <br>
                                <div class="row col-md-12 col-sm-12">
                                    <label class="control-label col-md-6">Comentarios</label>
                                    <textarea class="form-control col-md-6" name="comentarioNue" id="comentarioNue" cols="30" rows="3"></textarea>
                                </div>
                                <br>
                                <br>
                                <div class="row form-group col-lg-12">

                                    <button class="btn btn-success btn-sm col-lg-12 col-md-12 ml-auto align-items-center" id="btnAdd_aclaracion" type="submit">Guardar</button>

                                </div>
                            </form>
                            
                        </div>

                    </div>

                </div>

            </div>

        </div>




        <!-- Modal Data Register -->
        <div id="ModalExitoso" aria-labelledby="mediumModalLabel" class="modal fade" role="dialog" tabindex="-1" aria-hidden="true" >

            <div class="modal-dialog modal-md modal-dialog-centered">

                <!-- Modal content-->
                <div class="modal-content ">

                    <div class="modal-header">
                        <h4 class="modal-title">Datos Guardados</h4>
                        <button type="button" class="close" data-dismiss="modal" data-backdrop="false">&times;</button>
                    </div>

                    <div class="d-flex align-items-center justify-content-center modal-body">

                        <div class="form-group col-md-12 col-sm-12 justify-content-center">
                            <form>
                                <div class="row col-md-12 col-sm-12 justify-content-center text-center">

                                    <label class="control-label col-md-10">Datos guardados con éxito</label>
                                </div>
                            </form>
                            <br>
                            <form>
                                <div class="row form-group col-lg-12 justify-content-center" >
                                    <button class="btn btn-success btn-sm col-lg-4 col-md-6 justify-content-center" id="aceptarExitoso" href="home.htm" type="submit">Aceptar</button>
                                </div>
                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>
        
        
        
        
        <!-- Modal Document Preview -->
        <div id="ModalDocumento" aria-labelledby="mediumModalLabel" class="modal fade" role="dialog" tabindex="-1" aria-hidden="true" >

            <div class="modal-dialog modal-lg modal-dialog-centered">

                <!-- Modal content-->
                <div class="modal-content ">

                    <div class="modal-header">
                        <h4 class="modal-title">Aclaracion de Pagos</h4>
                        <button type="button" class="close" data-dismiss="modal" data-backdrop="false" >&times;</button>
                    </div>

                   <div class="d-flex align-items-center justify-content-center modal-body">
                        <div class="col-md-10">
                            <iframe width="100%" height="400px" id="iframe_documento"></iframe>
                        </div>
                        <div class="col-md-1">
                            <div class="row col-md-12">
                                <a id="btn_download_doc" class="btn btn-sm btn-success" href="" download="ACLARACION.pdf "><span class="fa fa-1x fa-download"></span></a>
                            </div>
                            <br>
                            <div class="row col-md-12">
                                <button id="btn_remove_doc" class="btn btn-sm btn-danger" data-dismiss="modal"><span class="fa fa-1x fa-trash"></span></button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

       </div>

        
        
        
        
        

        <!-- Modal Notification Message  -->
        <div id="ModalNotificacion" aria-labelledby="mediumModalLabel" class="modal fade" role="dialog" tabindex="-1" aria-hidden="true" >

            <div class="modal-dialog modal-md modal-dialog-centered">

                <!-- Modal content-->
                <div class="modal-content ">

                    <div class="modal-header">
                        <h4 class="modal-title">Notificación Enviada</h4>
                        <button type="button" class="close" data-dismiss="modal" data-backdrop="false">&times;</button>
                    </div>

                    <div class="d-flex align-items-center justify-content-center modal-body">

                        <div class="form-group col-md-12 col-sm-12 justify-content-center">
                            <form>
                                <div class="row col-md-12 col-sm-12 justify-content-center text-center">

                                    <label class="control-label col-md-10">Notificación enviada con éxito</label>
                                </div>
                            </form>
                            <br>
                            <form>
                                <div class="row form-group col-lg-12 justify-content-center" >
                                    <button class="btn btn-success btn-sm col-lg-4 col-md-6 justify-content-center" id="aceptarNotificacion" href="home.htm" type="submit">Aceptar</button>
                                </div>
                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>





        <!-- Modal Create for Modificate -->
        <div id="ModalModificate" aria-labelledby="mediumModalLabel" class="modal fade" role="dialog" tabindex="-1" aria-hidden="true" >

            <div class="modal-dialog modal-md modal-dialog-centered">

                <!-- Modal content-->
                <div class="modal-content ">

                    <div class="modal-header"> 
                      
                            
                        <br>
                        <h4 id="getId_aclaracion"></h4>
                        <input  id="id_aclaracion" name="id_aclaracion" hidden="true" type="text" class="form-control form-control-sm col-md-8" >
                        <button type="button" class="close" data-dismiss="modal" data-backdrop="false">&times;</button>
                    </div>

                    <div class="d-flex align-items-center justify-content-center modal-body">
                        <div class="form-group col-md-12 col-sm-12">

                            <br>
                            <div class="row col-md-12 col-sm-12">
                                <label id="txtNombre" class="control-label col-md-4">Nombres: </label>
                                <label id="nombreMod" class="control-label col-md-8"></label>
                            </div>

                            <div class="row col-md-12 col-sm-12">
                                <label id="txtApellido" class="control-label col-md-4">Apellidos: </label>
                                <label id="apellidoMod" class="control-label col-md-8"></label>
                            </div>
                            <br>
                            <div class="row col-md-12 col-sm-12">
                                <label class="control-label col-md-4">Tipo de usuario:</label>
                                <select id="id_usuarioMod" type="text" class="form-control form-control-sm col-md-8" placeholder="" maxlength="100">Seleccionar</select>
                            </div>
                            <br>
                            <div class="row col-md-12 col-sm-12">
                                <label class="control-label col-md-4">Semana de pago</label>
                                <input  id="semana_pagoMod" data-provide="datepicker" name="semana_pago" type="text" class="form-control form-control-sm col-md-8" >
                            </div>
                            <br>
                            <div class="row col-md-12 col-sm-12">
                                <label class="control-label col-md-4">Comentarios</label>
                                <textarea class="form-control col-md-8" rows="10" cols="30" name="comentarios" id="comentarioMod" cols="30" rows="3"></textarea>
                            </div>

                            <br>

                                <div class="row col-md-12 col-sm-12">
                                    <label class="control-label col-md-4">Resolutivo</label>
                                    <textarea class="form-control col-md-8" rows="10" cols="30" name="resolutivoMod" id="resolutivoMod" cols="30" rows="3"></textarea>

                                </div>
<!--                                </form>-->
                                <div class="row col-md-12 col-sm-12" enctype="multipart/form-data">
                                    <label class="control-label col-md-5">Adjuntar Archivos</label>
                                    <button id="add_files" class="btn  btn-sm col-lg-4 col-md-6">
                                        <span style="font-size: 150%">
                                            <i class="fa fa-upload"></i>
                                        </span>
                                    </button>
                                    <!--<button class="btn btn-success btn-sm col-lg-4 col-md-6 ml-auto" id="add_files">Agregar</button>-->
                                </div>
                                <div class="row col-md-12 col-sm-12">

                                </div>
                                <div class="row col-md-12 col-sm-12">
                                    <!--<div id="fileContainer">-->
                                    <form id="form_file1" enctype="multipart/form-data" method="POST"><input type="file" class="file"  name="file"></form>
                                    <form id="form_file2" enctype="multipart/form-data" method="POST"><input type="file" class="file"  name="file"></form>
                                    <form id="form_file3" enctype="multipart/form-data" method="POST"><input type="file" class="file"  name="file"></form>
                                    <!--</div>-->
                                </div>
                                <div id="fileContainerOld" class="row col-md-12 col-sm-12">
                                    
                                </div>
                                <br>
                                <div class="row col-md-12 col-sm-12 justify-content-center" >
                                    <td><input type="checkbox" id="resolucion_procedente"  value="" >Aclaración procedente</td>
                                </div>

                                <br>
                                <br>
                                <div class="row form-group col-lg-12">

                                    <button class="btn btn-success btn-sm col-lg-12 col-md-12 ml-auto align-items-center" id="upload">Guardar</button>

                                </div>

                        </div>

                    </div>     






                </div>

            </div>

        </div>



        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>

        <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        

        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>

        <!-- Bootstrap 4 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        
        <!-- CUSTOM JS -->
        <script src="../pagos/resources/jsLib/aclaracion/aclaraciones.js"></script>

    </body>
</html>
