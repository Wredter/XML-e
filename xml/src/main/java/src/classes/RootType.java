//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.13 at 07:42:23 PM CEST 
//


package src.classes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rootType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rootType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadane" type="{}metadaneType"/>
 *         &lt;element name="lista_gatunk�w" type="{}lista_gatunk�wType"/>
 *         &lt;element name="lista_wykonawc�w" type="{}lista_wykonawc�wType"/>
 *         &lt;element name="lista_piosenek" type="{}lista_piosenekType"/>
 *         &lt;element name="plajlista" type="{}plajlistaType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rootType", propOrder = {
    "metadane",
    "listaGatunk\u00f3w",
    "listaWykonawc\u00f3w",
    "listaPiosenek",
    "plajlista"
})
public class RootType {

    @XmlElement(required = true)
    protected MetadaneType metadane;
    @XmlElement(name = "lista_gatunk\u00f3w", required = true)
    protected ListaGatunk�wType listaGatunk�w;
    @XmlElement(name = "lista_wykonawc\u00f3w", required = true)
    protected ListaWykonawc�wType listaWykonawc�w;
    @XmlElement(name = "lista_piosenek", required = true)
    protected ListaPiosenekType listaPiosenek;
    @XmlElement(required = true)
    protected List<PlajlistaType> plajlista;

    /**
     * Gets the value of the metadane property.
     * 
     * @return
     *     possible object is
     *     {@link MetadaneType }
     *     
     */
    public MetadaneType getMetadane() {
        return metadane;
    }

    /**
     * Sets the value of the metadane property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetadaneType }
     *     
     */
    public void setMetadane(MetadaneType value) {
        this.metadane = value;
    }

    /**
     * Gets the value of the listaGatunk�w property.
     * 
     * @return
     *     possible object is
     *     {@link ListaGatunk�wType }
     *     
     */
    public ListaGatunk�wType getListaGatunk�w() {
        return listaGatunk�w;
    }

    /**
     * Sets the value of the listaGatunk�w property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaGatunk�wType }
     *     
     */
    public void setListaGatunk�w(ListaGatunk�wType value) {
        this.listaGatunk�w = value;
    }

    /**
     * Gets the value of the listaWykonawc�w property.
     * 
     * @return
     *     possible object is
     *     {@link ListaWykonawc�wType }
     *     
     */
    public ListaWykonawc�wType getListaWykonawc�w() {
        return listaWykonawc�w;
    }

    /**
     * Sets the value of the listaWykonawc�w property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaWykonawc�wType }
     *     
     */
    public void setListaWykonawc�w(ListaWykonawc�wType value) {
        this.listaWykonawc�w = value;
    }

    /**
     * Gets the value of the listaPiosenek property.
     * 
     * @return
     *     possible object is
     *     {@link ListaPiosenekType }
     *     
     */
    public ListaPiosenekType getListaPiosenek() {
        return listaPiosenek;
    }

    /**
     * Sets the value of the listaPiosenek property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPiosenekType }
     *     
     */
    public void setListaPiosenek(ListaPiosenekType value) {
        this.listaPiosenek = value;
    }

    /**
     * Gets the value of the plajlista property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plajlista property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlajlista().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlajlistaType }
     * 
     * 
     */
    public List<PlajlistaType> getPlajlista() {
        if (plajlista == null) {
            plajlista = new ArrayList<PlajlistaType>();
        }
        return this.plajlista;
    }

}
