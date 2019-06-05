<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format">
  	<xsl:output method="xml" indent="yes" encoding="utf-8"/>
	
	<xsl:key name ="WykonawcaKey" match="wykonawca" use="@id"/>
	<xsl:key name ="PiosenkaKey" match="piosenka" use="@tytuł"/>
	<xsl:key name ="GatunekKey" match="gatunek" use="@id"/>

  	<xsl:template match="/">
  		<fo:root font-family="Times">
  			<fo:layout-master-set>
  				<fo:simple-page-master master-name="Strona" page-width="210mm" page-height="297mm" margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
  					<fo:region-body margin="3cm" />
  				</fo:simple-page-master>
  			</fo:layout-master-set>

  			<fo:page-sequence master-reference="Strona">
  				<fo:flow flow-name="xsl-region-body">
				  	<fo:block text-align="right">
  							<xsl:template name="data">
								<xsl:value-of select="/root/metadane/data_modyfikacji" />
							</xsl:template>
					</fo:block>
  					<fo:block font-weight="bold" linefeed-treatment="preserve">
  						<xsl:value-of select="'&#xa;'"/>
  						<xsl:for-each select="/root/metadane/autor">
							<xsl:value-of select="concat(' * ', ., '&#xA;')"/>
						</xsl:for-each>
  						<xsl:value-of select="'&#xa;'"/>
  					</fo:block>

  				</fo:flow>
  			</fo:page-sequence>
  		</fo:root>
  	</xsl:template>

	<xsl:variable name='newline'><xsl:text></xsl:text></xsl:variable>


</xsl:stylesheet>