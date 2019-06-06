<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:svg="http://www.w3.org/2000/svg" 
                xmlns="http://www.w3.org/2000/svg" 
                xmlns:xlink="http://www.w3.org/1999/xlink">
    
    <xsl:output method="xml" 
                media-type="image/svg" 
                encoding="utf-8" 
                indent="yes"
                doctype-public="-//W3C//DTD SVG 1.1//EN"
                doctype-system="http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd"/>

    <xsl:template match="/">
        <svg:svg width="950" height="960" font-family="sans-serif">
            <svg:title>
                RAPORT
            </svg:title>
            <script type="text/javascript">
                <![CDATA[
                function onClickAutorzy(evt) {
                    var element = document.getElementById("autor");
                    var atrybut = element.getAttribute("visibility");
                    if(atrybut === "visible"){
                        element.setAttribute("visibility", "hidden");
                    }else{
                        element.setAttribute("visibility", "visible");
                    }
                }]]>
            </script>
            
            <svg:defs>
				<svg:linearGradient id="gradient">
					<svg:stop offset="0%" style="stop-color: #c4e1b5;"/>
				</svg:linearGradient>
			</svg:defs>

			<svg:rect x="0" y="0" width="950" height="50" fill="#ff7d38" stroke="white"/>
			<svg:text x="475" y="36" font-size="40" fill="white" font-weight="bold" text-anchor="middle">
				Playlisty
			</svg:text>
			<svg:text x="810" y="20" font-size="12" fill="white" font-weight="bold" text-anchor="middle">
				Ostatnia aktualizacja: <xsl:value-of select="/metadane/data_modyfikacji"/>
			</svg:text>
        </svg:svg>


    </xsl:template>
</xsl:stylesheet>