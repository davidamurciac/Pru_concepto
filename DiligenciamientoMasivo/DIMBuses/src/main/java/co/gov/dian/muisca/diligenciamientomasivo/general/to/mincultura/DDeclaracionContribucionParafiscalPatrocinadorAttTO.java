/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;
import org.apache.commons.lang.builder.*;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para los atributos de DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDeclaracionContribucionParafiscalPatrocinadorAttTO implements IDTO {
	private static final long serialVersionUID = -2030509241L; 

	// Atributos
	private java.lang.String numEvento;
	private java.lang.Long numNit;
	private java.lang.Integer numDv;
	private java.lang.String valRazonSocial;
	private java.lang.Integer numBoletasPatrocinio;
	private java.lang.String valUbicacionLocalidad;
	private java.lang.Long valIndividualBoleta;
	private java.lang.Long ideUsuarioCambio;
	private java.sql.Timestamp fecCambio;

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalPatrocinadorAttTO por defecto.
	 */
	public DDeclaracionContribucionParafiscalPatrocinadorAttTO() { }

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalPatrocinadorAttTO con los atributos.
	 * @param numEvento java.lang.String
	 * @param numNit java.lang.Long
	 * @param numDv java.lang.Integer
	 * @param valRazonSocial java.lang.String
	 * @param numBoletasPatrocinio java.lang.Integer
	 * @param valUbicacionLocalidad java.lang.String
	 * @param valIndividualBoleta java.lang.Long
	 * @param ideUsuarioCambio java.lang.Long
	 * @param fecCambio java.sql.Timestamp
	 */
	public DDeclaracionContribucionParafiscalPatrocinadorAttTO(java.lang.String numEvento, java.lang.Long numNit, java.lang.Integer numDv, java.lang.String valRazonSocial, java.lang.Integer numBoletasPatrocinio, java.lang.String valUbicacionLocalidad, java.lang.Long valIndividualBoleta, java.lang.Long ideUsuarioCambio, java.sql.Timestamp fecCambio) {
		setNumEvento(numEvento);
		setNumNit(numNit);
		setNumDv(numDv);
		setValRazonSocial(valRazonSocial);
		setNumBoletasPatrocinio(numBoletasPatrocinio);
		setValUbicacionLocalidad(valUbicacionLocalidad);
		setValIndividualBoleta(valIndividualBoleta);
		setIdeUsuarioCambio(ideUsuarioCambio);
		setFecCambio(fecCambio);
	}

	/**
	 * Devuelve el valor de numEvento.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.String getNumEvento() {
		return numEvento;
	}

	/**
	 * Establece el valor de numEvento.
	 * @param numEvento El nuevo valor de numEvento
	 */
	public void setNumEvento(java.lang.String numEvento) {
		this.numEvento = numEvento;
	}

	/**
	 * Devuelve el valor de numNit.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getNumNit() {
		return numNit;
	}

	/**
	 * Establece el valor de numNit.
	 * @param numNit El nuevo valor de numNit
	 */
	public void setNumNit(java.lang.Long numNit) {
		this.numNit = numNit;
	}

	/**
	 * Devuelve el valor de numDv.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumDv() {
		return numDv;
	}

	/**
	 * Establece el valor de numDv.
	 * @param numDv El nuevo valor de numDv
	 */
	public void setNumDv(java.lang.Integer numDv) {
		this.numDv = numDv;
	}

	/**
	 * Devuelve el valor de valRazonSocial.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValRazonSocial() {
		return valRazonSocial;
	}

	/**
	 * Establece el valor de valRazonSocial.
	 * @param valRazonSocial El nuevo valor de valRazonSocial
	 */
	public void setValRazonSocial(java.lang.String valRazonSocial) {
		this.valRazonSocial = valRazonSocial;
	}

	/**
	 * Devuelve el valor de numBoletasPatrocinio.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumBoletasPatrocinio() {
		return numBoletasPatrocinio;
	}

	/**
	 * Establece el valor de numBoletasPatrocinio.
	 * @param numBoletasPatrocinio El nuevo valor de numBoletasPatrocinio
	 */
	public void setNumBoletasPatrocinio(java.lang.Integer numBoletasPatrocinio) {
		this.numBoletasPatrocinio = numBoletasPatrocinio;
	}

	/**
	 * Devuelve el valor de valUbicacionLocalidad.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValUbicacionLocalidad() {
		return valUbicacionLocalidad;
	}

	/**
	 * Establece el valor de valUbicacionLocalidad.
	 * @param valUbicacionLocalidad El nuevo valor de valUbicacionLocalidad
	 */
	public void setValUbicacionLocalidad(java.lang.String valUbicacionLocalidad) {
		this.valUbicacionLocalidad = valUbicacionLocalidad;
	}

	/**
	 * Devuelve el valor de valIndividualBoleta.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValIndividualBoleta() {
		return valIndividualBoleta;
	}

	/**
	 * Establece el valor de valIndividualBoleta.
	 * @param valIndividualBoleta El nuevo valor de valIndividualBoleta
	 */
	public void setValIndividualBoleta(java.lang.Long valIndividualBoleta) {
		this.valIndividualBoleta = valIndividualBoleta;
	}

	/**
	 * Devuelve el valor de ideUsuarioCambio.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeUsuarioCambio() {
		return ideUsuarioCambio;
	}

	/**
	 * Establece el valor de ideUsuarioCambio.
	 * @param ideUsuarioCambio El nuevo valor de ideUsuarioCambio
	 */
	public void setIdeUsuarioCambio(java.lang.Long ideUsuarioCambio) {
		this.ideUsuarioCambio = ideUsuarioCambio;
	}

	/**
	 * Devuelve el valor de fecCambio.
	 * @return Un objeto java.sql.Timestamp
	 */
	public java.sql.Timestamp getFecCambio() {
		return fecCambio;
	}

	/**
	 * Establece el valor de fecCambio.
	 * @param fecCambio El nuevo valor de fecCambio
	 */
	public void setFecCambio(java.sql.Timestamp fecCambio) {
		this.fecCambio = fecCambio;
	}

	/**
	 * Devuelve una representación en String del objeto.
	 * @return String
	 */
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("numEvento", getNumEvento());
		builder.append("numNit", getNumNit());
		builder.append("numDv", getNumDv());
		builder.append("valRazonSocial", getValRazonSocial());
		builder.append("numBoletasPatrocinio", getNumBoletasPatrocinio());
		builder.append("valUbicacionLocalidad", getValUbicacionLocalidad());
		builder.append("valIndividualBoleta", getValIndividualBoleta());
		builder.append("ideUsuarioCambio", getIdeUsuarioCambio());
		builder.append("fecCambio", getFecCambio());
		return builder.toString();
	}
}
