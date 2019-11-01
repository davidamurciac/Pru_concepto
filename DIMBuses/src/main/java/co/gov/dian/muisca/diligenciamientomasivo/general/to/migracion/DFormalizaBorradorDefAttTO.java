/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Objeto de transporte para los atributos de FormalizaBorradorDef.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: DIAN
 * </p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 *
 *          <pre>
 * $Log[10]:$
 *          </pre>
 */
public class DFormalizaBorradorDefAttTO implements IDTO {
	private static final long serialVersionUID = 473942471L;

	// Atributos
	private java.lang.Long ideDocumento;
	private java.lang.Integer numRepeticion;
	private java.lang.Integer ideFormato;
	private java.lang.Byte numVersionFormato;
	private java.lang.Long ideDocumentoRecibo;
	private java.lang.Integer numRepeticionRecibo;
	private java.lang.Integer ideFormatoRecibo;
	private java.lang.Byte numVersionFormatoRecibo;
	private java.lang.Long idePersonaRut;
	private Integer indEstado;
	private java.lang.String txtObservaciones;
	private java.sql.Timestamp fecCambio;
	private java.lang.Long ideUsuarioCambio;

	/**
	 * Construye un nuevo DFormalizaBorradorDefAttTO por defecto.
	 */
	public DFormalizaBorradorDefAttTO() {
	}

	/**
	 * Construye un nuevo DFormalizaBorradorDefAttTO con los atributos.
	 *
	 * @param ideDocumento
	 *            java.lang.Long
	 * @param numRepeticion
	 *            java.lang.Integer
	 * @param ideFormato
	 *            java.lang.Integer
	 * @param numVersionFormato
	 *            java.lang.Byte
	 * @param ideDocumentoRecibo
	 *            java.lang.Long
	 * @param numRepeticionRecibo
	 *            java.lang.Integer
	 * @param ideFormatoRecibo
	 *            java.lang.Integer
	 * @param numVersionFormatoRecibo
	 *            java.lang.Byte
	 * @param idePersonaRut
	 *            java.lang.Long
	 * @param indEstado
	 *            java.lang.String
	 * @param txtObservaciones
	 *            java.lang.String
	 * @param fecCambio
	 *            java.sql.Timestamp
	 * @param ideUsuarioCambio
	 *            java.lang.Long
	 */
	public DFormalizaBorradorDefAttTO(java.lang.Long ideDocumento, java.lang.Integer numRepeticion,
			java.lang.Integer ideFormato, java.lang.Byte numVersionFormato, java.lang.Long ideDocumentoRecibo,
			java.lang.Integer numRepeticionRecibo, java.lang.Integer ideFormatoRecibo,
			java.lang.Byte numVersionFormatoRecibo, java.lang.Long idePersonaRut, Integer indEstado,
			java.lang.String txtObservaciones, java.sql.Timestamp fecCambio, java.lang.Long ideUsuarioCambio) {
		setIdeDocumento(ideDocumento);
		setNumRepeticion(numRepeticion);
		setIdeFormato(ideFormato);
		setNumVersionFormato(numVersionFormato);
		setIdeDocumentoRecibo(ideDocumentoRecibo);
		setNumRepeticionRecibo(numRepeticionRecibo);
		setIdeFormatoRecibo(ideFormatoRecibo);
		setNumVersionFormatoRecibo(numVersionFormatoRecibo);
		setIdePersonaRut(idePersonaRut);
		setIndEstado(indEstado);
		setTxtObservaciones(txtObservaciones);
		setFecCambio(fecCambio);
		setIdeUsuarioCambio(ideUsuarioCambio);
	}

	/**
	 * Devuelve el valor de fecCambio.
	 *
	 * @return Un objeto java.sql.Timestamp
	 */
	public java.sql.Timestamp getFecCambio() {
		return fecCambio;
	}

	/**
	 * Devuelve el valor de ideDocumento.
	 *
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeDocumento() {
		return ideDocumento;
	}

	/**
	 * Devuelve el valor de ideDocumentoRecibo.
	 *
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeDocumentoRecibo() {
		return ideDocumentoRecibo;
	}

	/**
	 * Devuelve el valor de ideFormato.
	 *
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeFormato() {
		return ideFormato;
	}

	/**
	 * Devuelve el valor de ideFormatoRecibo.
	 *
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeFormatoRecibo() {
		return ideFormatoRecibo;
	}

	/**
	 * Devuelve el valor de idePersonaRut.
	 *
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdePersonaRut() {
		return idePersonaRut;
	}

	/**
	 * Devuelve el valor de ideUsuarioCambio.
	 *
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeUsuarioCambio() {
		return ideUsuarioCambio;
	}

	/**
	 * Devuelve el valor de indEstado.
	 *
	 * @return Un objeto java.lang.String
	 */
	public Integer getIndEstado() {
		return indEstado;
	}

	/**
	 * Devuelve el valor de numRepeticion.
	 *
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumRepeticion() {
		return numRepeticion;
	}

	/**
	 * Devuelve el valor de numRepeticionRecibo.
	 *
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumRepeticionRecibo() {
		return numRepeticionRecibo;
	}

	/**
	 * Devuelve el valor de numVersionFormato.
	 *
	 * @return Un objeto java.lang.Byte
	 */
	public java.lang.Byte getNumVersionFormato() {
		return numVersionFormato;
	}

	/**
	 * Devuelve el valor de numVersionFormatoRecibo.
	 *
	 * @return Un objeto java.lang.Byte
	 */
	public java.lang.Byte getNumVersionFormatoRecibo() {
		return numVersionFormatoRecibo;
	}

	/**
	 * Devuelve el valor de txtObservaciones.
	 *
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getTxtObservaciones() {
		return txtObservaciones;
	}

	/**
	 * Establece el valor de fecCambio.
	 *
	 * @param fecCambio
	 *            El nuevo valor de fecCambio
	 */
	public void setFecCambio(java.sql.Timestamp fecCambio) {
		this.fecCambio = fecCambio;
	}

	/**
	 * Establece el valor de ideDocumento.
	 *
	 * @param ideDocumento
	 *            El nuevo valor de ideDocumento
	 */
	public void setIdeDocumento(java.lang.Long ideDocumento) {
		this.ideDocumento = ideDocumento;
	}

	/**
	 * Establece el valor de ideDocumentoRecibo.
	 *
	 * @param ideDocumentoRecibo
	 *            El nuevo valor de ideDocumentoRecibo
	 */
	public void setIdeDocumentoRecibo(java.lang.Long ideDocumentoRecibo) {
		this.ideDocumentoRecibo = ideDocumentoRecibo;
	}

	/**
	 * Establece el valor de ideFormato.
	 *
	 * @param ideFormato
	 *            El nuevo valor de ideFormato
	 */
	public void setIdeFormato(java.lang.Integer ideFormato) {
		this.ideFormato = ideFormato;
	}

	/**
	 * Establece el valor de ideFormatoRecibo.
	 *
	 * @param ideFormatoRecibo
	 *            El nuevo valor de ideFormatoRecibo
	 */
	public void setIdeFormatoRecibo(java.lang.Integer ideFormatoRecibo) {
		this.ideFormatoRecibo = ideFormatoRecibo;
	}

	/**
	 * Establece el valor de idePersonaRut.
	 *
	 * @param idePersonaRut
	 *            El nuevo valor de idePersonaRut
	 */
	public void setIdePersonaRut(java.lang.Long idePersonaRut) {
		this.idePersonaRut = idePersonaRut;
	}

	/**
	 * Establece el valor de ideUsuarioCambio.
	 *
	 * @param ideUsuarioCambio
	 *            El nuevo valor de ideUsuarioCambio
	 */
	public void setIdeUsuarioCambio(java.lang.Long ideUsuarioCambio) {
		this.ideUsuarioCambio = ideUsuarioCambio;
	}

	/**
	 * Establece el valor de indEstado.
	 *
	 * @param indEstado
	 *            El nuevo valor de indEstado
	 */
	public void setIndEstado(java.lang.Integer indEstado) {
		this.indEstado = indEstado;
	}

	/**
	 * Establece el valor de numRepeticion.
	 *
	 * @param numRepeticion
	 *            El nuevo valor de numRepeticion
	 */
	public void setNumRepeticion(java.lang.Integer numRepeticion) {
		this.numRepeticion = numRepeticion;
	}

	/**
	 * Establece el valor de numRepeticionRecibo.
	 *
	 * @param numRepeticionRecibo
	 *            El nuevo valor de numRepeticionRecibo
	 */
	public void setNumRepeticionRecibo(java.lang.Integer numRepeticionRecibo) {
		this.numRepeticionRecibo = numRepeticionRecibo;
	}

	/**
	 * Establece el valor de numVersionFormato.
	 *
	 * @param numVersionFormato
	 *            El nuevo valor de numVersionFormato
	 */
	public void setNumVersionFormato(java.lang.Byte numVersionFormato) {
		this.numVersionFormato = numVersionFormato;
	}

	/**
	 * Establece el valor de numVersionFormatoRecibo.
	 *
	 * @param numVersionFormatoRecibo
	 *            El nuevo valor de numVersionFormatoRecibo
	 */
	public void setNumVersionFormatoRecibo(java.lang.Byte numVersionFormatoRecibo) {
		this.numVersionFormatoRecibo = numVersionFormatoRecibo;
	}

	/**
	 * Establece el valor de txtObservaciones.
	 *
	 * @param txtObservaciones
	 *            El nuevo valor de txtObservaciones
	 */
	public void setTxtObservaciones(java.lang.String txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}
}
