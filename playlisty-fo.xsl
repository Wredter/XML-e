<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format">
  	<xsl:output method="xml" indent="yes" encoding="utf-8"/>
	
	<xsl:key name ="WykonawcaKey" match="wykonawca" use="@id"/>
	<xsl:key name ="PiosenkaKey" match="piosenka" use="@tytuł"/>
	<xsl:key name ="GatunekKey" match="gatunek" use="@id"/>

  	<xsl:template match="/root">
  		<fo:root font-family="Times">
  			<fo:layout-master-set>
  				<fo:simple-page-master master-name="Strona" page-width="210mm" page-height="297mm" margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
  					<fo:region-body margin="3cm" />
  				</fo:simple-page-master>
  			</fo:layout-master-set>

  			<fo:page-sequence master-reference="Strona">
  				<fo:flow flow-name="xsl-region-body">
				  	<fo:block text-align="left">
						<xsl:value-of select="metadane/data_modyfikacji" />
					</fo:block>
  					<fo:block text-align="center" font-weight="bold" linefeed-treatment="preserve">
  						<xsl:value-of select="'&#xa;'"/>
  						<xsl:for-each select="metadane/autor">
							<xsl:value-of select="concat(' * ', ., '&#xA;')"/>
						</xsl:for-each>
  						<xsl:value-of select="'&#xa;'"/>
  					</fo:block>
					<fo:table-and-caption text-align="center">
					<fo:table>

					<xsl:variable name="wykonawcyNo" select="count(lista_wykonawców/wykonawca)" />

					<xsl:for-each select="lista_wykonawców/wykonawca">
					<fo:table-column column-width="{concat(160 div $wykonawcyNo, 'mm')}"/>
					</xsl:for-each>	

					

					<fo:table-header>
						<fo:table-row>
							<fo:table-cell border-style="solid" number-columns-spanned="{$wykonawcyNo}">
								<fo:block text-align="center" font-weight="bold">Wykonawcy</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body border-width="4px" border-style="solid">
						<fo:table-row>
							<xsl:for-each select="lista_wykonawców/wykonawca">
							<fo:table-cell border-style="solid">
								<fo:block padding-start="10px" font-weight="bold"><xsl:value-of select="concat('nazwa: ', @nazwa)"/></fo:block>
							</fo:table-cell>
							</xsl:for-each>
						</fo:table-row>
				
						<fo:table-row>
							<xsl:for-each select="lista_wykonawców/wykonawca">
							<fo:table-cell border-style="solid">
								<xsl:for-each select="artysta">
								<fo:block padding-start="10px" font-weight="bold"><xsl:value-of select="concat(./imie, ' ', ./nazwisko)"/></fo:block>
								</xsl:for-each>
							</fo:table-cell>
							</xsl:for-each>
						</fo:table-row>
														
					</fo:table-body>
					</fo:table>
					</fo:table-and-caption>
  				</fo:flow>
  			</fo:page-sequence>
  		</fo:root>
  	</xsl:template>


</xsl:stylesheet>
