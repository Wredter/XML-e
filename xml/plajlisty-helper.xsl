<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:my="plajlisty.xsd"
exclude-result-prefixes="my">
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
    
	<xsl:key name ="WykonawcaKey" match="wykonawca" use="@id"/>
	<xsl:key name ="PiosenkaKey" match="piosenka" use="@tytuł"/>
	<xsl:key name ="GatunekKey" match="gatunek" use="@id"/>
	
	<xsl:template match="/root">
        <xsl:element name="zestawienie">
			<xsl:element name="Dane">
				<xsl:for-each select="metadane/autor">
					<xsl:element name="autor">
						<xsl:element name="imie">
							<xsl:value-of select="imie"/>
						</xsl:element>
						<xsl:element name="nazwisko">
							<xsl:value-of select="nazwisko"/>
						</xsl:element>
						<xsl:element name="nrIndeksu">
							<xsl:value-of select="nr_indeksu"/>
						</xsl:element>
					</xsl:element>
				</xsl:for-each>
				<xsl:element name="Datamodyfikacji">
					<xsl:value-of  select="format-dateTime(current-dateTime(),'[D01]/[M01]/[Y0001],[H01]:[m01]')"/>
				</xsl:element>
			</xsl:element>
			<xsl:element name="biblioteka">
				<xsl:element name="liczbaWykonawców">
					<xsl:value-of select="count(lista_wykonawców/wykonawca)"/>
				</xsl:element>
				<xsl:element name="liczbaArtystów">
					<xsl:value-of select="count(lista_wykonawców/wykonawca/artysta)" />
				</xsl:element>
				<xsl:element name="liczbaPiosenek">
					<xsl:value-of select="count(lista_piosenek/piosenka)" />
				</xsl:element>
				<xsl:element name="liczbaPiosenekSmutnych">
					<xsl:value-of select="count(lista_piosenek/piosenka[@nastrój = 'smutna'])" />
				</xsl:element>
				<xsl:element name="liczbaPiosenekWesołych">
					<xsl:value-of select="count(lista_piosenek/piosenka[@nastrój = 'wesoła'])" />
				</xsl:element>
				<xsl:element name="liczbaPiosenekMieszanych">
					<xsl:value-of select="count(lista_piosenek/piosenka[@nastrój = 'mieszana'])" />
				</xsl:element>
				<xsl:element name="liczbaPiosenekNiezidentyfikowanych">
					<xsl:value-of select="count(lista_piosenek/piosenka[@nastrój = 'niezidentyfikowana'])" />
				</xsl:element>
			</xsl:element>
			<xsl:for-each select ="plajlista">
				<xsl:element name="plajlista">
					<xsl:element name="liczbaPiosenek">
						<xsl:value-of select="count(piosenkaRef)" />
					</xsl:element>
					<xsl:element name="nazwa">
						<xsl:value-of select="@nazwa" />
					</xsl:element>
					<xsl:for-each select = "piosenkaRef">
						<xsl:element name="piosenka">
							<xsl:variable name="piosenkaref" select="@tytulRef" />
							<xsl:element name="Tytuł">
								<xsl:value-of select="@tytulRef"/>
							</xsl:element>
							<xsl:variable name="wykonawcaref" select="key('PiosenkaKey',$piosenkaref)/@wykonawca" />
							<xsl:element name="artyści">
								<xsl:for-each select="key('WykonawcaKey',$wykonawcaref)/artysta">
								<xsl:sort select="nazwisko"/>
									<xsl:value-of select="imie"/><xsl:text> </xsl:text><xsl:value-of select="nazwisko"/>,
								</xsl:for-each>
							</xsl:element>
							<xsl:variable name="gatunekref" select="key('PiosenkaKey',$piosenkaref)/@gatunek" />
							<xsl:element name="gatunek">
								<xsl:value-of select="key('GatunekKey',$gatunekref)/."/>
							</xsl:element>
							<xsl:element name="nastrój">
								<xsl:value-of select="key('PiosenkaKey',$piosenkaref)/@nastrój"/>
							</xsl:element>
							<xsl:element name="rokWydania">
								<xsl:value-of select="key('PiosenkaKey',$piosenkaref)/rok_wydania"/>
							</xsl:element>
							<xsl:element name="album">
								<xsl:value-of select="key('PiosenkaKey',$piosenkaref)/album"/>
							</xsl:element>
							<xsl:element name="czasTrwania">
								<xsl:value-of select="key('PiosenkaKey',$piosenkaref)/czas_trwania"/>
							</xsl:element>
						</xsl:element>
					</xsl:for-each>
				</xsl:element>
			</xsl:for-each>
        </xsl:element>		
    </xsl:template>
</xsl:stylesheet>
