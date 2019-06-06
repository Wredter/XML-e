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

    <xsl:template match="/root">
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
                <svg:linearGradient id="bg-darker">
                    <svg:stop offset="0%" style="stop-color: #000000;"/>
                    <svg:stop offset="100%" style="stop-color: #434343;"/>
                </svg:linearGradient>
                <svg:linearGradient id="bg">
                    <svg:stop offset="0%" style="stop-color: #000000;"/>
                    <svg:stop offset="100%" style="stop-color: #323232;"/>
                </svg:linearGradient>
                <svg:linearGradient id="bar">
                    <svg:stop offset="0%" style="stop-color: #134E5E;"/>
                    <svg:stop offset="100%" style="stop-color: #71B280;"/>
                </svg:linearGradient>
            </svg:defs>

			<svg:rect x="0" y="0" width="950" height="50" fill="#323232" stroke="black"/>
			<svg:text x="475" y="36" font-size="40" fill="white" font-weight="bold" text-anchor="middle">
				Playlisty
			</svg:text>
			<svg:text x="810" y="20" font-size="12" fill="white" font-weight="bold" text-anchor="middle">
				Ostatnia aktualizacja: <xsl:value-of select="metadane/data_modyfikacji"/>
			</svg:text>
            
            <svg:rect x="0" y="50" width="950" height="910" rx="10" ry="10" fill="url(#bg)"/>
            <xsl:apply-templates/>
            <xsl:apply-templates select="metadane"/>
        </svg:svg>
    </xsl:template>
        
        <xsl:template match="metadane">
        <svg:g id="autorzy_pliku" width="100" height="60" onclick="onClickAutorzy(evt)"
               cursor="pointer">
            <svg:rect x="15" y="70" class="button" width="100" height="32" fill="url(#bar)" stroke="black" rx="10"
                      ry="10"/>
            <svg:text x="35" y="91" fill="white" font-size="16">Autorzy</svg:text>
        </svg:g>
        <svg:g id="metadane" visibility="hidden">
            <svg:rect x="130" y="20" width="600" height="30" fill="url(#bar)" stroke="black"/>
            <svg:text x="420" y="40" font-size="16" fill="white" text-anchor="middle">
                <xsl:apply-templates/>
            </svg:text>
        </svg:g>
        </xsl:template>

    <xsl:template match="autor">
        <xsl:value-of select="concat(imie, ' ')"/>
        <xsl:value-of select="concat(nazwisko, ' ')"/>
        <xsl:value-of select="concat(indeks, ' ')"/>
    </xsl:template>
</xsl:stylesheet>