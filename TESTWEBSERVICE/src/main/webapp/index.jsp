<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        
        <p id="idMsg"/>
        
        <form>
            <table>
                <tr>
                    <td>
                        <label>Nombre del estudiante: </label>
                    </td>
                    <td>
                        <input type="text" id="idName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Nota 1: </label>
                    </td>
                    <td>
                        <input type="text" id="idNote1"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Nota 2: </label>
                    </td>
                    <td>
                        <input type="text" id="idNote2"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Nota 3: </label>
                    </td>
                    <td>
                        <input type="text" id="idNote3"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Nota 4: </label>
                    </td>
                    <td>
                        <input type="text" id="idNote4"/>
                    </td>
                </tr>
            </table>
            <button type="button" id="idSend">
                Validar
            </button>
        </form>
        
        <script 
            src="https://code.jquery.com/jquery-2.2.4.min.js">
        </script>
        
        <script>
            $('#idSend').on('click', function (e) {
                e.preventDefault();
                $.get('http://localhost:8084/TESTWEBSERVICE/rest/notes/validateNotes', {
                    note1: $('#idNote1').val(),
                    note2: $('#idNote2').val(),
                    note3: $('#idNote3').val(),
                    note4: $('#idNote4').val()
                }, function(resp) {
                    if (resp === true) {
                        $('#idMsg').text('El estudiante ' + $('#idName').val() + ' pasó el curso');
                    } else {
                        $('#idMsg').text('El estudiante ' + $('#idName').val() + ' no pasó el curso');
                    }
                }).fail(function() {
                    alert('Error en el llamado del servicio');
                });
            });
            
        </script>
        
    </body>
</html>

