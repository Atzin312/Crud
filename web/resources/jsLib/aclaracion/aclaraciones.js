/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

                                                     $("#btnAdd").click(function(){
                                                        $("#txtGetUser").val("");
                                                        $("#nombreNue").val("");
                                                        $("#apellidoNue").val("");
                                                        $("#semana_pagoNue").val("");
                                                        $("#comentarioNue").val("");
                                                     });
                                                     
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
                                                                if ($("#getNumNot").val() !== '' && $("#resolutivoMod").val() !== '')
                                                            $.ajax({
                                                                type: 'POST',
                                                                url: 'http://yimicol2020.ddns.net:3000/sms_generic',
                                                                crossDomain: true,
                                                                //dataType: "text",
                                                                data: {
                                                                    'telefono': $("#getNumNot").val(),
                                                                    'body': $("#resolutivoMod").val() // <-- Parameters for send message
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
                                                                    // <-- Parameters for Send Email
                                                                },
                                                                success: function (msg) {
                                                                    alert('wow' + msg);
                                                                    
                                                                }
                                                            });
                                                                 
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
                                                                    
                                                         if ($("#getNumNot").val() !== '' && $("#resolutivoMod").val() !== '')
                                                            $.ajax({
                                                                type: 'POST',
                                                                url: 'http://yimicol2020.ddns.net:3000/sms_generic',
                                                                crossDomain: true,
                                                                //dataType: "text",
                                                                data: {
                                                                    'telefono': $("#getNumNot").val(),
                                                                    'body': $("#resolutivoMod").val() // <-- Parameters for send message
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
                                                                    // <-- Parameters for Send Email
                                                                },
                                                                success: function (msg) {
                                                                    alert('wow' + msg);
                                                                    
                                                                }
                                                            });
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
                                                                
                                                                success: function (result) {
                                                                    console.log(result);
                                                                   

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
                                                  
                                                                success: function (result) {
                                                                    
                                                        

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
                                                        
                                                                success: function (result) {
                                                                    console.log(result);
            
                                                                },
                                                                error: function (result) {
                                                                    console.log(result.responseText);
                                                                }
                                                            });
                                                          
                                                           
                                                        });
                                                    });

                                                    var contador = 1;
                                                    
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
                                                 /*   $("#notificarTelefono").click(function (event) {
                                                        event.preventDefault();
                                                        if ($("#getNumNot").val() !== '' && $("#resolutivoMod").val() !== '')
                                                            $.ajax({
                                                                type: 'POST',
                                                                url: 'http://yimicol2020.ddns.net:3000/sms_generic',
                                                                crossDomain: true,
                                                                //dataType: "text",
                                                                data: {
                                                                    'telefono': $("#getNumNot").val(),
                                                                    'body': $("#resolutivoMod").val() // <-- Parameters for send message
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
                                                                    // <-- Parameters for Send Email
                                                                },
                                                                success: function (msg) {
                                                                    alert('wow' + msg);
                                                                    
                                                                }
                                                            });
                                                            $('#ModalModificate').modal('toggle');
                                                            $('#ModalNotificacion').modal('toggle');

                                                    });*/

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
                                                        
                                                        if($("#resolutivoMod").val() !== '')
                                                        {
                                                            $("#resolucion_procedente").prop('checked',true);
                                                        }else {
                                                            $("#resolucion_procedente").prop('checked',false);
                                                        }
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



