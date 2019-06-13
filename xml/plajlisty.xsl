<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xhtml" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" indent="yes"/>


<xsl:template name="Plajlisty" match="/">
  <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
  <head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"/>
  <title>Plajlisty</title>
  </head>
  <body>
  <div class="pure-g">
  <div class="pure-u-3-5">
	  <h2>Autorzy</h2>
	  <table border="1">
		<tr>
		  <th>Imię</th>
		  <th>Nazwisko</th>
		  <th>Nr indeksu</th>
		</tr>
		<xsl:for-each select="/zestawienie/Dane/autor">
		<tr>
		  <td><xsl:value-of select="./imie" /></td>
		  <td><xsl:value-of select="./nazwisko" /></td>
		  <td><xsl:value-of select="./nrIndeksu" /></td>
		</tr>
		</xsl:for-each>
	  </table>
  </div>
  
  
  <div class="pure-u-3-5">
  <h2>Dane biblioteki</h2>
  <table border="1">
    <tr>
      <th>Liczba Wykonawców</th>
      <th>Liczba Artystów</th>
      <th>Liczba Piosenek</th>
      <th>Liczba Piosenek Smutnych</th>
      <th>Liczba Piosenek Wesołych</th>
	  <th>Liczba Piosenek Mieszanych</th>
	  <th>Liczba Piosenek Niezidentyfikowanych</th>
    </tr>
    <tr>
      <td><xsl:value-of select="/zestawienie/biblioteka/liczbaWykonawców" /></td>
      <td><xsl:value-of select="/zestawienie/biblioteka/liczbaArtystów" /></td>
      <td><xsl:value-of select="/zestawienie/biblioteka/liczbaPiosenek" /></td>
	  <td><xsl:value-of select="/zestawienie/biblioteka/liczbaPiosenekSmutnych" /></td>
	  <td><xsl:value-of select="/zestawienie/biblioteka/liczbaPiosenekWesołych" /></td>
	  <td><xsl:value-of select="/zestawienie/biblioteka/liczbaPiosenekMieszanych" /></td>
	  <td><xsl:value-of select="/zestawienie/biblioteka/liczbaPiosenekNiezidentyfikowanych" /></td>
    </tr>
  </table>
  </div>


  <div class="pure-u-3-5">
  <h2>Dane plajlist</h2>
  <table border="1">
    <xsl:for-each select="/zestawienie/plajlista">
    <tr>
      <td colspan="7"><b><font size="5"> nazwa: <xsl:value-of select="nazwa"/> | liczba piosenek: <xsl:value-of select="liczbaPiosenek"/></font></b></td>
    </tr>
    <tr>
      <td><b>Tytuł</b></td>
      <td><b>Artyści</b></td>
      <td><b>Gatunek</b></td>
      <td><b>Nastrój</b></td>
      <td><b>Czas Trwania</b></td>
      <td><b>Album</b></td>
      <td><b>Rok wydania</b></td>
    </tr>
    <xsl:for-each select="piosenka">
    <tr>
	  <td><xsl:value-of select="Tytuł" /></td>
	  <td><xsl:value-of select="artyści" /></td>
	  <td><xsl:value-of select="gatunek" /></td>
	  <td><xsl:value-of select="nastrój" /></td>
	  <td><xsl:value-of select="czasTrwania" /></td>
	  <td><xsl:value-of select="album" /></td>
	  <td><xsl:value-of select="rokWydania" /></td>
    </tr>
    </xsl:for-each>
    </xsl:for-each>
  </table>
  <br/>
  <table>
    <tr>
      <td><xsl:text>Data modyfikacji: </xsl:text></td>
      <td><xsl:value-of select="/zestawienie/Dane/Datamodyfikacji"/></td>
    </tr>
  </table>
  </div>
  </div>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet> 

