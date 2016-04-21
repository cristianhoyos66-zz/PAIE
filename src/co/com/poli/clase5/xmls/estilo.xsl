<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">
    <xsl:output 
        method="html"/>
    <xsl:template 
        match="/">
        <html>
            <head>
                <title>
                    Reunión
                </title>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"/>
                <!-- Optional theme -->
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"/>
                <!-- Latest compiled and minified JavaScript -->
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
            </head>
            <body>
                <div class="container">
                    <div class="header clearfix">
                        <h3 class="text-muted">Reunión</h3>
                    </div>

                    <div class="jumbotron">
                        <h2>Información general</h2>
                        <h3>Lugar: <xsl:value-of select="reunion/informacionGeneral/lugar"/></h3>
                        <h3>Fecha: <xsl:value-of select="reunion/informacionGeneral/fecha"/></h3>
                        <h3>Hora inicio: <xsl:value-of select="reunion/informacionGeneral/horaInicio"/></h3>
                        <h3>Hora fin: <xsl:value-of select="reunion/informacionGeneral/horaFinaliza"/></h3>
                        <h3>Secretari@: <xsl:value-of select="reunion/informacionGeneral/secretario"/></h3>
                        
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                            <div class="panel-heading">Asistentes</div>
                            <table class="table">
                                <xsl:for-each select="reunion/informacionGeneral/asistentes/asistente">
                                    <xsl:sort select="node()" order="ascending"/>
                                    <tr class="success">
                                        <td>
                                            <xsl:value-of select="node()"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                        </div>

                    </div>

                    <div class="row marketing">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <h2>
                                    <div class="panel-heading">Agenda</div>
                                </h2>
                                
                                <table class="table">
                                    <thead>
                                        <h3>Aspecto</h3>
                                    </thead>
                                    <tr>
                                        <td>
                                            Descripción
                                        </td>
                                        <td>
                                            Propuesta
                                        </td>
                                    </tr>
                                    <xsl:for-each select="reunion/agenda/aspecto">
                                        <xsl:sort select="propuesta" order="ascending"/>
                                        <tr class="success">
                                            <td>
                                                <xsl:value-of select="descripcion"/>                                                                               
                                            </td>
                                            <td>
                                                <xsl:for-each select="propuesta">      
                                                    <xsl:value-of select="node()"/>
                                                    - 
                                                    <xsl:value-of select="@aprobada"/>
                                                    <br></br>
                                                </xsl:for-each>      
                                            </td>
                                        </tr>
                                    </xsl:for-each>
                                </table>
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <h2>
                                    <div class="panel-heading">Informes</div>
                                </h2>
                                
                                <table class="table">
                                    <thead>
                                    </thead>
                                    <tr>
                                        <td>
                                            Rol
                                        </td>
                                        <td>
                                            Descripción
                                        </td>
                                    </tr>
                                    <xsl:for-each select="reunion/informes/anotacion">
                                        <xsl:sort select="node()" order="ascending"/>
                                        <tr class="success">
                                            <td>
                                                <xsl:value-of select="@rol"/>                                                                               
                                            </td>
                                            <td>
                                                <xsl:value-of select="node()"/> 
                                            </td>
                                        </tr>
                                    </xsl:for-each>
                                </table>
                            </div>
                        </div>
                    </div>

                    <footer class="footer">
                        <p>2015 Company, Inc.</p>
                    </footer>

                </div> <!-- /container -->
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
