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
                                <c:forEach items="${pagos}" var="pago" varStatus="status">
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

                                    <button class="btn btn-success btn-sm col-lg-4 col-md-6 ml-auto" id="btnAdd_aclaracion" type="submit">Guardar</button>
                                    <button class="btn btn-success btn-sm col-lg-4 col-md-6  ml-auto" >Notificar</button>

                                </div>
                            </form>
                            
                        </div>

                    </div>

                </div>

            </div>

        </div>




        <!-- Modal User register -->
        <div id="ModalExitoso" aria-labelledby="mediumModalLabel" class="modal fade" role="dialog" tabindex="-1" aria-hidden="true" >

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
        
        
        
        
        <!-- Modal User register -->
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

        
        
        
        
        

        <!-- Modal User register -->
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
<!--                            <form enctype="multipart/form-data" id="form_upload_files" method="POST">  -->
                                <!-- <form action="upload_resolutivo.htm" method="POST">-->
                                
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
                                <br>
                                <div class="row form-group col-lg-12">

                                    <button class="btn btn-success btn-sm col-lg-4 col-md-6 ml-auto" id="upload">Guardar</button>
                                    <button class="btn btn-success btn-sm col-lg-4 col-md-6  ml-auto" id="notificarTelefono">Notificar</button>

                                </div>
                            
                            <!--<div class="row form-group col-lg-12">
 
                                     
                                    
                                 </div>-->


                        </div>

                    </div>     






                </div>

            </div>

        </div>



        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>

        <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        <script>
                                                     
                                                    $(document).on('hidden.bs.modal', function (event) {
                                                        if ($('.modal:visible').length) {
                                                          $('body').addClass('modal-open');
                                                        }
                                                    });
                                                      
                                                      
                                                    //Add roles
                                                    function getRoles() {

                                                        console.log("roles_usuarios");




                                                        $.ajax({
                                                            method: "POST",
                                                            url: "get_roles.htm"

                                                        }).done(function (res) {
                                                            console.log("Datos encontrados.");
                                                            $("#tipo_usuarioNue").empty();
                                                            var obj = JSON.parse(res);
                                                            console.log(obj);


                                                            $.each(obj, function (key, data) {

                                                                $("#tipo_usuarioNue").append('<option value="' + data.id_rol + '">' + data.nombre_rol + '</option>');

                                                            });

                                                        });
                                                    };




                                                    //Search button
                                                    $("#btnBuscar").submit(function (event) {
                                                        event.preventDefault();
                                                        let parametros = {
                                                            "txtBuscar": $("#txtBuscar").val()
                                                        };

                                                        $.ajax({
                                                            url: "home.htm",
                                                            type: "GET",
                                                            data: parametros,
                                                            success: function () {
                                                                console.log("SUCCESS");
                                                                document.getElementById("getNum").value = $("#txtBuscar").val();
                                                            },
                                                            error: function (erorr) {
                                                                console.log(erorr);
                                                            }

                                                        });
                                                    });
                                                    
                                                   
                                                      
                                                 
                                                    
                                                    
                                                    
                                                    //Search button
                                                    $("#btnUpload_resolutivo").submit(function (event) {
                                                        event.preventDefault();
                                                        let parametros = {
                                                            "resolutivoMod": $("#resolutivoMod").val()
                                                        };

                                                        $.ajax({
                                                            url: "upload_resolutivo.htm",
                                                            type: "GET",
                                                            data: parametros,
                                                            success: function () {
                                                                console.log("SUCCESS");
                                                                 window.location.reload(true);
                                                            },
                                                            error: function (erorr) {
                                                                console.log(erorr);
                                                            }

                                                        });
                                                    });

                                                    $(".modal").on("shown.bs.modal", function () {
                                                        if ($(".modal-backdrop").length > 1) {
                                                            $(".modal-backdrop").not(':first').remove();
                                                        }
                                                    })

                                                    //Upload_files
                                                    $(document).ready(function () {
                                                        getRoles();

                                                       
                                                        $(function () {
                                                            $('#semana_pagoNue').datepicker({
                                                                dateFormat: "yy-mm-dd"

                                                            });
                                                        });





                                                        //dataTable settings
                                                        $('#dataTable').DataTable({
                                                            responsive: true,
                                                            language: {
                                                                "search": "Nombre de usuario",
                                                                "lengthMenu": "Mostrar _MENU_ registros por página",
                                                                "zeroRecords": "Sin resultados",
                                                                "info": "Mostrando página _PAGE_ de _PAGES_",
                                                                "infoEmpty": "No se encontrarón registros",
                                                                "infoFiltered": "(Filtrado desde el total de registros _MAX_)",
                                                                "paginate": {
                                                                    "first": "Primera",
                                                                    "last": "Última",
                                                                    "next": "Siguiente",
                                                                    "previous": "Anterior"
                                                                }
                                                            }
                                                        });


                                                        //Upload decesive and documents
                                                        $("#upload").click(function (event) {
                                                            event.preventDefault();
                                                            var f = $(this);
                                                            //var formData = new FormData(document.getElementById("form_upload_files"));
                                                            var formDataFile1 = new FormData(document.getElementById("form_file1"));
                                                            formDataFile1.append("id_aclaracion", $("#id_aclaracion").val());
                                                            
                                                            var formDataFile2 = new FormData(document.getElementById("form_file2"));
                                                            formDataFile2.append("id_aclaracion", $("#id_aclaracion").val());
                                                            
                                                            var formDataFile3 = new FormData(document.getElementById("form_file3"));
                                                            formDataFile3.append("id_aclaracion", $("#id_aclaracion").val());

                                                            $.ajax({
                                                                type: "POST",
                                                                url: "upload_resolutivo.htm",
                                                                data: {
                                                                    "resolutivoMod": $("#resolutivoMod").val(),
                                                                    "getId_aclaracion" : $("#id_aclaracion").val()
                                                                },
                                                                
                                                                
                                                               
                                                                success: function (result) {
                                                                    console.log(result);
                                                                    $('#ModalModificate').modal('toggle');
                                                                    $('#ModalExitoso').modal('toggle');

                                                                },
                                                                error: function (result) {
                                                                    console.log(result.responseText);
                                                                }
                                                            });
                                                            
                                                             $.ajax({
                                                                type: "POST",
                                                                url: "http://yimicol2020.ddns.net:3001/aclaracion_pagos_doc1",
                                                               
                                                                contentType: false,
                                                                processData: false,
                                                                cache: false,
                                                                data: formDataFile1,
                                                                /*beforeSend: function(xhr, settings) { 
                                                                 xhr.setRequestHeader("Content-Type", "multipart/form-data;boundary=gc0p4Jq0M2Yt08jU534c0p"); 
                                                                 settings.data = {name: "file", file: inputElement.files[0]};      
                                                                 },*/
                                                                success: function (result) {
                                                                    console.log(result);
                                                                    /*$('#ModalModificate').modal('toggle');
                                                                    $('#ModalExitoso').modal('toggle');*/
                                                                    window.location.reload(true);

                                                                },
                                                                error: function (result) {
                                                                    console.log(result.responseText);
                                                                }
                                                            });
                                                            
                                                            
                                                            $.ajax({
                                                                type: "POST",
                                                                url: "http://yimicol2020.ddns.net:3001/aclaracion_pagos_doc2",
                                                               
                                                                contentType: false,
                                                                processData: false,
                                                                cache: false,
                                                                data: formDataFile2,
                                                                /*beforeSend: function(xhr, settings) { 
                                                                 xhr.setRequestHeader("Content-Type", "multipart/form-data;boundary=gc0p4Jq0M2Yt08jU534c0p"); 
                                                                 settings.data = {name: "file", file: inputElement.files[0]};      
                                                                 },*/
                                                                success: function (result) {
                                                                    console.log(result);
                                                                    /*$('#ModalModificate').modal('toggle');
                                                                    $('#ModalExitoso').modal('toggle');*/
                                                                    window.location.reload(true);

                                                                },
                                                                error: function (result) {
                                                                    console.log(result.responseText);
                                                                }
                                                            });
                                                            
                                                            
                                                            $.ajax({
                                                                type: "POST",
                                                                url: "http://yimicol2020.ddns.net:3001/aclaracion_pagos_doc3",
                                                               
                                                                contentType: false,
                                                                processData: false,
                                                                cache: false,
                                                                data: formDataFile3,
                                                                /*beforeSend: function(xhr, settings) { 
                                                                 xhr.setRequestHeader("Content-Type", "multipart/form-data;boundary=gc0p4Jq0M2Yt08jU534c0p"); 
                                                                 settings.data = {name: "file", file: inputElement.files[0]};      
                                                                 },*/
                                                                success: function (result) {
                                                                    console.log(result);
                                                                    window.location.reload(true);
                                                                    /*$('#ModalModificate').modal('toggle');
                                                                    $('#ModalExitoso').modal('toggle');*/

                                                                },
                                                                error: function (result) {
                                                                    console.log(result.responseText);
                                                                }
                                                            });
                                                          
                                                           
                                                        });
                                                    });

                                                    var contador = 1;
                                                    //add files
                                                    /*$("#add_files").click(function (event) {
                                                        event.preventDefault();
                                                        if (contador < 3) {
                                                            $("#fileContainer").append("<div class='row ' id='file_" + contador.toString() + "'><input type='file' class='col-sm-11 col-md-11' name='file' /><button onclick='remove_file(" + contador.toString() + ");' type='button' class='btn btn-sm col-lg-1 col-md-1'><span class='fa fa-times' style='color:red;'></span></button></div>");

                                                            contador++;
                                                        } else {
                                                            alert("Has excedido el límite de archivos permitido");
                                                        }
                                                    });*/
                                                    //Remove Files
                                                    function remove_file(id_file_html,id_documento) {
                                                        
                                                         $.ajax({
                                                            url: "delete_docu.htm",
                                                            type: "POST",
                                                            data: {
                                                                "getId_aclaracion" : $("#id_aclaracion").val(),
                                                                "getId_documento" : id_documento
                                                            },
                                                            success: function () {
                                                                console.log("SUCCESS");
                                                                $("#file_" + id_file_html).remove();
                                                                $("#btn_remove_" + id_file_html).remove();
                                                               
                                                            },
                                                            error: function (erorr) {
                                                                console.log(erorr);
                                                            }

                                                        });
                                                        
                                                        
                                                    }



                                                    //Notifications
                                                    $("#notificarTelefono").click(function (event) {
                                                        event.preventDefault();
                                                        if ($("#getNumNot").val() !== '' && $("#resolutivoMod").val() !== '')
                                                            $.ajax({
                                                                type: 'POST',
                                                                url: 'http://yimicol2020.ddns.net:3000/sms_generic',
                                                                crossDomain: true,
                                                                //dataType: "text",
                                                                data: {
                                                                    'telefono': $("#getNumNot").val(),
                                                                    'body': $("#resolutivoMod").val() // <-- the $ sign in the parameter name seems unusual, I would avoid it
                                                                },
                                                                success: function (msg) {
                                                                    alert('wow' + msg);
                                                                    if (msg === "Mensaje enviado") {
                                                                        
                                                                    }
                                                                }
                                                            });
                                                        if ($("#getCorreoNot").val() !== '' && $("#resolutivoMod").val() !== '')
                                                            $.ajax({
                                                                type: 'POST',
                                                                url: 'http://yimicol2020.ddns.net:3000/mail_aclaracion',
                                                                crossDomain: true,
                                                                //dataType: "text",
                                                                data: {
                                                                    'correo': $("#getNumNot").val(),
                                                                    'bodyco': 'Resolutivo ' + $("#resolutivoMod").val(),
                                                                    'tipoco': 'Tipo de Usuario ' + $("#id_usuarioMod").val(),
                                                                    'semanaco': 'Semana de pago ' + $("#semana_pagoMod").val(),
                                                                    'comentarioco': 'Comentario ' + $("#comentarioMod").val(),
                                                                    'id_aclaracionco': 'Id aclaración ' + $("#getId_aclaracion").val(),
                                                                    // <-- the $ sign in the parameter name seems unusual, I would avoid it
                                                                },
                                                                success: function (msg) {
                                                                    alert('wow' + msg);
                                                                    
                                                                }
                                                            });
                                                            $('#ModalModificate').modal('toggle');
                                                            $('#ModalNotificacion').modal('toggle');

                                                    });

                                                    //Search button for add aclaracion
                                                    $("#getUserForm").submit(function (event) {
                                                        console.log("Buscando");
                                                        event.preventDefault();

                                                        var frm = $(this).serialize();

                                                        $.ajax({
                                                            method: "POST",
                                                            url: "get_usuario.htm",
                                                            data: frm
                                                        }).done(function (res) {
                                                            console.log("Datos encontrados.");
                                                            var obj = JSON.parse(res);
                                                            console.log(obj);
                                                            
                                                            document.getElementById("nombreNue").value = "";
                                                            document.getElementById("apellidoNue").value = "";
                                                            location.reload();

                                                            $.each(obj, function (key, data) {
                                                                document.getElementById("nombreNue").value = data.nombre;
                                                                document.getElementById("apellidoNue").value = data.apellido;

                                                            });
                                                            //console.log($("#getNum").val());
                                                        });
                                                    });


                                                    //Search button for add aclaracion
                                                    $("#getAdd_aclaracion").submit(function (event) {
                                                        console.log("Agregando Nuevo Usuario");
                                                        event.preventDefault();

                                                        var frm = $(this).serialize();

                                                        $.ajax({
                                                            method: "POST",
                                                            url: "add_aclaracion.htm",
                                                            data: frm,
                                                            async: false,
                                                            success: function (result) {
                                                                $('#ModalCreate').modal('toggle');
                                                                $('#ModalExitoso').modal('toggle');
                                                                location.reload();
                                                            },
                                                            error: function (result) {
                                                                console.log(result.responseText);
                                                            }
                                                        });
                                                    });


                                                    //Function for details button
                                                    var nombreInput = $("#nombreMod");//document.getElementById("nombreMod");
                                                    var apellidoInput = $("#apellidoMod");//document.getElementById("apellidoMod");
                                                    var id_usuarioInput = $("#id_usuarioMod");// document.getElementById("id_usuarioMod");
                                                    var semana_pagoInput = $("#semana_pagoMod");//document.getElementById("semana_pagoMod");
                                                    var comentarioInput = $("#comentarioMod");//document.getElementById("comentarioMod");
                                                    var resolutivoInput = $("#resolutivoMod");
                                                    var id_aclaracionInput = $("#getId_aclaracion");
                                                    
                                                    //input_hide
                                                    var id_aclaraciones   = $("#id_aclaracion");
                                                    function setTextToInput(
                                                            nombre,
                                                            apellido,
                                                            tipo_usuario,
                                                            semana_pago,
                                                            comentario,
                                                            resolutivo,
                                                            id_aclaracion
                                                            ) {

                                                        nombreInput.text(nombre);
                                                        apellidoInput.text(apellido);
                                                        id_usuarioInput.append("<option val=" + tipo_usuario + " selected>" + tipo_usuario + "</option>");
                                                        semana_pagoInput.val(semana_pago);
                                                        comentarioInput.val(comentario);
                                                        resolutivoInput.val(resolutivo);
                                                        id_aclaracionInput.text("Detalles de aclaracion de pago Aclaracion: "+id_aclaracion);
                                                        id_aclaraciones.val(id_aclaracion);
                                                        
                                                        //CONSULTA DE DOCUMENTOS EXISTENTES
                                                        let array_files = new Array();
                                                        let obj_documentos;
                                                        $("#fileContainerOld").html("");
                                                        
                                                        $.ajax({
                                                            url: "get_docu.htm",
                                                            type: "POST",
                                                            data: {"getId_aclaracion" : id_aclaracion},
                                                            beforeSend: function() {
                                                             console.log("Id aclaración PARA LUIS EJEMPLO" + id_aclaracion);
                                                                        },
                                                            success: function (data) {
                                                                console.log("RESPONSE DOCU",JSON.parse(data));
                                                                obj_documentos = JSON.parse(data);
                                                                for(let index_item in obj_documentos ){
                                                                    $("#fileContainerOld").append(
                                                                        `<div class="row col-md-12">`+
                                                                        `<button id="file_`+index_item+`"  onclick="visualizarDocumento('`+obj_documentos[index_item].archivo+`',`+index_item+`,`+obj_documentos[index_item].id_documento+`);" type='button' class="btn btn-sm col-md-4"><span class="fa fa-file-text" style="margin-right: 12px;"></span>`+obj_documentos[index_item].nombre_archivo+`</button>`+
                                                                        `<button id="btn_remove_`+index_item+`" onclick='remove_file(`+index_item+`,`+obj_documentos[index_item].id_documento+`);' type='button' class='btn btn-sm col-lg-1 col-md-1'><span class='fa fa-times' style='color:red;'></span></button>`+
                                                                        `</div>`
                                                                    );
                                                                }
                                                                
                                                                 
                                                                console.log("SUCCESS DE LUIS");
                                                                console.log("SUCCESS");
                                                                //Search ID with element files
                                                                $("button[id^='file_']").each(function(){
                                                                   console.log($(this).attr("id"),$(this).text());
                                                                   array_files.push({
                                                                       "id_file"     :$(this).attr("id"), 
                                                                       "doc_name"    :$(this).text().replace(" ","")
                                                                   });
                                                                });
                                                                console.log("SUCCESS PARA LUIS ENLISTANDO LOS FILES");
                                                                console.log("FILES_DIV:",array_files);
                                                                
                                                                $("#form_file1").show();
                                                                $("#form_file2").show();
                                                                $("#form_file3").show();
                                                                for(index in array_files){
//                                                                    if(array_files[index].id_file == "file_0"){
//                                                                        $("#form_file1").hide();
//                                                                    }else{
//                                                                        $("#form_file1").show();
//                                                                    }
//                                                                    if(array_files[index].id_file == "file_1"){
//                                                                         $("#form_file2").hide();
//                                                                    }
//                                                                    else{
//                                                                        $("#form_file2").show();
//                                                                    }
//                                                                    if(array_files[index].id_file == "file_2"){
//                                                                        $("#form_file3").hide();
//                                                                    }
//                                                                    else{
//                                                                        $("#form_file3").show();
//                                                                    }

                                                                      if(array_files[index].id_file === "file_0"){
                                                                        $("#form_file1").hide();
                                                                      }else if(array_files[index].id_file === "file_1"){
                                                                        $("#form_file2").hide();
                                                                      }else if(array_files[index].id_file === "file_2"){
                                                                        $("#form_file3").hide();
                                                                      }
                                                                }
                                                                
                                                                

                                                            },
                                                            error: function (erorr) {
                                                                console.log(erorr);
                                                            }

                                                        });
                                                    }
                                                    //Preview documents
                                                    function visualizarDocumento(uri_doc,id_file_html,id_documento){
                                                        $("#iframe_documento").attr("src",uri_doc);
                                                        $("#btn_download_doc").attr("href",uri_doc);
                                                        $("#btn_remove_doc").attr("onclick", "remove_file("+id_file_html+","+id_documento+")");
                                                        //$('#ModalModificate').modal('hide');
                                                        $('#ModalModificate').modal('toggle');   
                                                       //$('document.body').removeClass('modal-open');//eliminamos la clase del body para poder hacer scroll
                                                        //$('.modal-backdrop').remove();//eliminamos el backdrop del modal
                                                        $('#ModalDocumento').modal('toggle');
                                                      
                                                        
                                                    }
                                                    /*$('#ModalModificate').on('hidden.bs.modal', function () {
                                                        $("#fileContainerOld").empty();
//                                                        
                                                        
                                                        console.log("Modal cerrado");
                                                    });*/

                                        

        </script>

        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>

        <!-- Bootstrap 4 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


    </body>
</html>
