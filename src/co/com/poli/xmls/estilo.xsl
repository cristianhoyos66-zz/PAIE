<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template
        match="/">
        <html>
            <head>
                <title>
                    Tienda
                </title>
            </head>
            <body>
                <h1>
                    Nombre tienda: <xsl:value-of select="tienda/nombre"/>
                    <br></br>
                    <h2>
                        Ciudad: <xsl:value-of select="tienda/ciudad"/>   
                    </h2>
                    <br></br>
                    <table 
                        border="1">
                        <tr>
                            <td>
                               Código 
                            </td>
                            <td>
                                Nombre
                            </td>
                            <td>
                                Cantidad
                            </td>
                            <td>
                                Valor
                            </td>
                        </tr>
                        <xsl:for-each 
                            select="tienda/poductos/producto">
                            <tr>
                                <td>
                                    <xsl:value-of select="@codigo"/>   
                                </td>
                                <td>
                                    <xsl:value-of select="nombre"/>   
                                </td>
                                <td>
                                    <xsl:value-of select="cantidad"/>   
                                </td>
                                <td>
                                    <xsl:value-of select="valor"/>   
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>                   
                </h1>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
