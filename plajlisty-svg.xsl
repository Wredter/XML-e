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
	<xsl:key name ="WykonawcaKey" match="wykonawca" use="@id"/>
	<xsl:key name ="PiosenkaKey" match="piosenka" use="@tytuł"/>
	<xsl:key name ="GatunekKey" match="gatunek" use="@id"/>
    <xsl:template match="/root">
        <svg:svg width="950" height="1000" font-family="sans-serif">
            <svg:title>
                RAPORT
            </svg:title>
            <script type="text/javascript">
                <![CDATA[
                function onClickAutorzy(evt) {
                    var element = document.getElementById("metadane");
                    var atrybut = element.getAttribute("visibility");
                    if(atrybut === "visible"){
                        element.setAttribute("visibility", "hidden");
                    }else{
                        element.setAttribute("visibility", "visible");
                    }
                }]]>
            </script>
            
            <style>
                .bar:hover
                {
                	fill: #71B280;
                }
                .button:hover
                {
                	fill: #71B280;
                }
		.info
		{
			display: none;
		}
		.control:hover + .info
		{
			display: block;				
		}
            </style>
            
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
            
            <svg:rect x="0" y="50" width="950" height="1000" rx="10" ry="10" fill="url(#bg)"/>
            <xsl:apply-templates/>
            <xsl:apply-templates select="metadane"/>

	
	<g transform="translate(300 120)">
		<svg:rect width="600" height="330" y="0" fill="url(#bar)"/>
		<svg:text x="10" y="50" text-anchor="start" fill="white" font-size="20" font-weight="bold">
			Liczba utworów o określonym nastroju:
		</svg:text>
		<g class="bar" transform="translate(300 100)">
			<svg:text x="-10" y="27" text-anchor="end" fill="white" font-size="24" font-weight="bold">
				Wesołe:
			</svg:text>
			<xsl:variable name="liczbaWesołych" select="count(lista_piosenek/piosenka[@nastrój = 'wesoła'])"/>
			<rect width='{20 * $liczbaWesołych}' height="40" fill="url(#bg-darker)">
				<animateTransform 
					attributeName="transform"
					type="scale"
					from="0 1"
					to="1 1"
					begin="0s"
					dur="3s"/>
			</rect>
			<text x='{20 * $liczbaWesołych + 10}' y="30" fill="white" font-size="30" font-weight="bold">
				<xsl:value-of select="$liczbaWesołych"/>
			</text>
		</g>
		<g class="bar" transform="translate(300 160)">
			<svg:text x="-10" y="27" text-anchor="end" fill="white" font-size="24" font-weight="bold">
				Mieszane:
			</svg:text>
			<xsl:variable name="liczbaMieszanych" select="count(lista_piosenek/piosenka[@nastrój = 'mieszana'])"/>
			<rect width='{20 * $liczbaMieszanych}' height="40" fill="url(#bg-darker)">
				<animateTransform 
					attributeName="transform"
					type="scale"
					from="0 1"
					to="1 1"
					begin="0s"
					dur="3s"/>
			</rect>
			<text x='{20 * $liczbaMieszanych + 10}' y="30" fill="white" font-size="30" font-weight="bold">
				<xsl:value-of select="$liczbaMieszanych"/>
			</text>
		</g>
		<g class="bar" transform="translate(300 220)">
			<svg:text x="-10" y="27" text-anchor="end" fill="white" font-size="24" font-weight="bold">
				Smutne:
			</svg:text>
			<xsl:variable name="liczbaSmutnych" select="count(lista_piosenek/piosenka[@nastrój = 'smutna'])"/>
			<rect width='{20 * $liczbaSmutnych}' height="40" fill="url(#bg-darker)">
				<animateTransform 
					attributeName="transform"
					type="scale"
					from="0 1"
					to="1 1"
					begin="0s"
					dur="3s"/>
			</rect>
			<text x='{20 * $liczbaSmutnych + 10}' y="30" fill="white" font-size="30" font-weight="bold">
				<xsl:value-of select="$liczbaSmutnych"/>
			</text>
		</g>
		<g class="bar" transform="translate(300 280)">
			<svg:text x="-10" y="27" text-anchor="end" fill="white" font-size="24" font-weight="bold">
				Niezidentyfikowane:
			</svg:text>
			<xsl:variable name="liczbaMieszanych" select="count(lista_piosenek/piosenka[@nastrój = 'niezidentyfikowana'])"/>
			<rect width='{20 * $liczbaMieszanych}' height="40" fill="url(#bg-darker)">
				<animateTransform 
					attributeName="transform"
					type="scale"
					from="0 1"
					to="1 1"
					begin="0s"
					dur="3s"/>
			</rect>
			<text x='{20 * $liczbaMieszanych + 10}' y="30" fill="white" font-size="30" font-weight="bold">
				<xsl:value-of select="$liczbaMieszanych"/>
			</text>
		</g>
	</g>
	<xsl:for-each select="plajlista">
		<xsl:variable name="liczbaPiosenek" select="count(piosenkaRef)"/>
		<svg:rect x ='{(((position()-1))*300)+50}' y='{500}' width="250" height='{$liczbaPiosenek*40+30}' fill="url(#bar)"/>
		<svg:text x ='{(((position()-1))*300)+70}' y='{530}' text-anchor="start" fill="white" font-size="20" font-weight="bold">
			<xsl:value-of select="@nazwa"/>
		</svg:text>
		<xsl:variable name="PozycjaX" select="(((position()-1))*300)+70"/>
		<xsl:variable name="PozycjaX2" select="(((position()-1))*300)+50"/>
		<xsl:for-each select="piosenkaRef">
			<xsl:variable name="piosenkaref" select="@tytulRef" />
			<xsl:variable name="gatunekref" select="key('PiosenkaKey',$piosenkaref)/@gatunek" />
			<xsl:element name="svg:text">
                   		<xsl:attribute name="y">
                        		<xsl:value-of select="position()*35 + 550"/>
                    		</xsl:attribute>
                    		<xsl:attribute name="x">
                        		<xsl:value-of select="$PozycjaX"/>
                    		</xsl:attribute>
                    		<xsl:attribute name="fill">white</xsl:attribute>
                    		<xsl:attribute name="font-size">12</xsl:attribute>
                    		<xsl:attribute name="text-anchor">left</xsl:attribute>
				<xsl:value-of select="@tytulRef"/>
                	</xsl:element>
			<xsl:element name="svg:rect">
				<xsl:attribute name="class">
                        		control
                    		</xsl:attribute>
				<xsl:attribute name="cursor">
                        		pointer
                    		</xsl:attribute>
                   		<xsl:attribute name="y">
                        		<xsl:value-of select="position()*35 + 530"/>
                    		</xsl:attribute>
                    		<xsl:attribute name="x">
                        		<xsl:value-of select="$PozycjaX2"/>
                    		</xsl:attribute>
				<xsl:attribute name="width">
                        		<xsl:value-of select="250"/>
                    		</xsl:attribute>
				<xsl:attribute name="height">
                        		<xsl:value-of select="35"/>
                    		</xsl:attribute>
				<xsl:attribute name="opacity">
                        		<xsl:value-of select="0"/>
                    		</xsl:attribute>
				<xsl:attribute name="fill">
					<xsl:value-of select="white"/>
				</xsl:attribute>
                	</xsl:element>
			<svg:g class="info">
				<svg:rect x="645" y="500" width="270" height="220" fill="url(#bar)" stroke="white" stroke-width="2"/>
				<svg:text text-anchor="left" x="660" y="525" fill="white" font-size="12" font-weight="bold">
					Tytuł: <xsl:value-of select="@tytulRef"/>
				</svg:text>
				<svg:text text-anchor="left" x="660" y="555" fill="white" font-size="12" font-weight="bold">
					Wykonawca: <xsl:value-of select="key('PiosenkaKey',$piosenkaref)/@wykonawca"/>
				</svg:text>
				<svg:text text-anchor="left" x="660" y="585" fill="white" font-size="12" font-weight="bold">
					Gatunek: <xsl:value-of select="key('GatunekKey',$gatunekref)/."/>
				</svg:text>
				<svg:text text-anchor="left" x="660" y="615" fill="white" font-size="12" font-weight="bold">
					Nastrój: <xsl:value-of select="key('PiosenkaKey',$piosenkaref)/@nastrój"/>
				</svg:text>
				<svg:text text-anchor="left" x="660" y="645" fill="white" font-size="12" font-weight="bold">
					Czas trwania: <xsl:value-of select="key('PiosenkaKey',$piosenkaref)/czas_trwania"/>
				</svg:text>
				<svg:text text-anchor="left" x="660" y="675" fill="white" font-size="12" font-weight="bold">
					Album: <xsl:value-of select="key('PiosenkaKey',$piosenkaref)/album"/>
				</svg:text>
				<svg:text text-anchor="left" x="660" y="705" fill="white" font-size="12" font-weight="bold">
					Rok wydania: <xsl:value-of select="key('PiosenkaKey',$piosenkaref)/rok_wydania"/>
				</svg:text>
			</svg:g>
		</xsl:for-each>
	</xsl:for-each>
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
            <svg:rect x="130" y="70" width="800" height="30" fill="url(#bar)" stroke="black"/>
            <svg:text x="520" y="91" font-size="16" fill="white" text-anchor="middle">
                <xsl:apply-templates select="autor"/>
            </svg:text>
        </svg:g>
        </xsl:template>

    <xsl:template match="autor">
        <xsl:value-of select="concat(imie, ' ')"/>
        <xsl:value-of select="concat(nazwisko, ' ')"/>
        <xsl:value-of select="concat(nr_indeksu, ' ')"/>
    </xsl:template>
</xsl:stylesheet>
