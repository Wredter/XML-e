<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:my="plajlisty.xsd" version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="text" />
	
	<xsl:key name ="WykonawcaKey" match="wykonawca" use="@id"/>
	<xsl:key name ="PiosenkaKey" match="piosenka" use="@tytuł"/>
	<xsl:key name ="GatunekKey" match="gatunek" use="@id"/>

	<xsl:template name="playlisty" match="/root" >
		<xsl:element name="Root">
			<xsl:call-template name="info" />
			<xsl:call-template name="biblioteka" />
			<xsl:call-template name="plajlisty" />
		</xsl:element>
	</xsl:template>

	<xsl:template name="info">
		<xsl:element name="Info" >
			<xsl:element name="autorzy">
				<xsl:for-each select="metadane/autor">
					<xsl:element name="Autor">
						<xsl:value-of select="imie/concat('Autor:                     ', ., ' ')"/>
						<xsl:value-of select="nazwisko/concat(., '&#xa;')"/>
						<xsl:value-of select="nr_indeksu/concat('numer indeksu:             ', ., '&#xa;', '&#xa;')"/>
					</xsl:element>
				</xsl:for-each>
			</xsl:element>
			<xsl:element name="Date" >
				<xsl:value-of select="metadane/data_modyfikacji/concat('Data:                      ', ., '&#xa;', '&#xa;--------------------------------------------------------------------', '&#xa;')" />
			</xsl:element>
			<xsl:element name="Title">--------------------------------------------------------------------&#xa;</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template name="biblioteka">
		<xsl:element name="Biblioteka">
			<xsl:element name="Wykonawcy">
				<xsl:element name="Title">lista wykonawców:&#xa;</xsl:element>
				<xsl:for-each select="lista_wykonawców/wykonawca">
					<xsl:element name="Wykonawca">
						<xsl:value-of select="concat('&#xa;', 'nazwa zaspołu:             ', @nazwa, '&#xa;')"/>
						<xsl:for-each select="artysta">
							<xsl:element name="Artysta">
								<xsl:value-of select="concat('  -artysta:                ', ./imie, ' ', ./nazwisko, '&#xa;')"/>
							</xsl:element>
						</xsl:for-each>
					</xsl:element>
				</xsl:for-each>
			</xsl:element>
			<xsl:element name="Title">&#xa;--------------------------------------------------------------------&#xa;</xsl:element>
			<xsl:element name="Title">--------------------------------------------------------------------&#xa;</xsl:element>
			<xsl:element name="Gatunki">
				<xsl:element name="Title">gatunki:&#xa;</xsl:element>
				<xsl:for-each select="lista_gatunków/gatunek">
					<xsl:element name="Gatunek">
						<xsl:value-of select="concat('&#xa;', '  -', .)"/>
					</xsl:element>
				</xsl:for-each>
			</xsl:element>
			<xsl:element name="Piosenki">
				<xsl:element name="Title">&#xa;&#xa;--------------------------------------------------------------------&#xa;</xsl:element>
				<xsl:element name="Title">--------------------------------------------------------------------&#xa;</xsl:element>
				<xsl:element name="Title">lista piosenek:&#xa;</xsl:element>
				<xsl:value-of select="concat('&#xa;', 'tytuł                          | ', 'wykonawca         | ', 'gatunek | ', 'nastrój                | ', 'czas trwania | ', 'album                       | ', 'rok wydania')"/>
				<xsl:for-each select="lista_piosenek/piosenka">
					<xsl:variable name="wykonawcaref" select="@wykonawca" />
					<xsl:variable name="wykonawca" select="key('WykonawcaKey',$wykonawcaref)/@nazwa" />
					<xsl:variable name="gatunekref" select="@gatunek" />
					<xsl:variable name="gatunek" select="key('GatunekKey',$gatunekref)" />					
					<xsl:element name="Piosenka">
						<xsl:value-of select="concat('&#xa;', '------------------------------------------------------------------------------------------------------------------------------------------------', '&#xa;')"/>
						<xsl:value-of select="substring(concat(@tytuł, '                              '), 1, 30)"/>
						<xsl:value-of select="substring(concat(' | ', $wykonawca, '                    '), 1, 20)"/>
						<xsl:value-of select="substring(concat(' | ', $gatunek, '          '), 1, 10)"/>
						<xsl:value-of select="substring(concat(' | ', @nastrój, '                         '), 1, 25)"/>
						<xsl:value-of select="substring(concat(' | ', ./czas_trwania, '               '), 1, 15)"/>
						<xsl:value-of select="substring(concat(' | ', ./album, '                              '), 1, 30)"/>
						<xsl:value-of select="substring(concat(' | ', ./rok_wydania, '               '), 1, 15)"/>
					</xsl:element>
				</xsl:for-each>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="plajlisty">
		<xsl:element name="Plajlisty">
			<xsl:element name="Title">&#xa;&#xa;--------------------------------------------------------------------&#xa;</xsl:element>
			<xsl:element name="Title">--------------------------------------------------------------------&#xa;</xsl:element>
			<xsl:element name="Title">lista playlist:&#xa;</xsl:element>
			<xsl:for-each select="plajlista">
				<xsl:element name="Plajlista">
					<xsl:value-of select="concat('&#xa;', 'nazwa: ', @nazwa, '&#xa;')"/>
					<xsl:for-each select="piosenkaRef">
						<xsl:variable name="piosenkaref" select="@tytulRef" />
						<xsl:variable name="piosenka" select="key('PiosenkaKey',$piosenkaref)/@tytuł" />
						<xsl:element name="Piosenka">
							<xsl:value-of select="concat('  -tytuł piosenki: ', $piosenka, '&#xa;')"/>
						</xsl:element>
					</xsl:for-each>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>

</xsl:stylesheet>