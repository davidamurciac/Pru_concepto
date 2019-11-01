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
 * <p>Descripcion: Objeto de transporte para los atributos de RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DRetenContribArtEscenicsAttTO implements IDTO {
	private static final long serialVersionUID = 921756372L; 

	// Atributos
	private java.lang.Integer ideFormato;
	private java.lang.Byte numVersionFormato;
	private java.lang.Long ideDocumentoCarga;
	private java.lang.Integer numRepeticionCarga;
	private java.lang.Integer ideFormatoCarga;
	private java.lang.Byte numVersionFormatoCarga;
	private java.lang.Long ideDocumentoDeclaracion;
	private java.lang.Integer numRepeticionDeclaracion;
	private java.lang.Integer ideFormatoDeclaracion;
	private java.lang.Byte numVersionFormatoDecla;	
	private java.lang.Long idePersonaRutAgente;
	private java.lang.Integer codEstado;
	private java.lang.Short numAnio;
	private java.lang.Byte numPeriodo;
	private java.math.BigDecimal valSubtBolsPrecIgSup3uvt;
	private java.math.BigDecimal valSubtDerAsistIgSup3uvt;
	private java.math.BigDecimal valSubtDevsRetensEvenAnt;
	private java.lang.Integer numTipoDeclaracion;
	private java.lang.Long numFormularioAnterior;
	private java.lang.Long fecTransaccion;
	private java.lang.Long ideUsuarioCambio;
	private java.sql.Timestamp fecCambio;

	/**
	 * Construye un nuevo DRetenContribArtEscenicsAttTO por defecto.
	 */
	public DRetenContribArtEscenicsAttTO() { }

	/**
	 * Construye un nuevo DRetenContribArtEscenicsAttTO con los atributos.
	 * @param ideFormato java.lang.Integer
	 * @param numVersionFormato java.lang.Byte
	 * @param ideDocumentoCarga java.lang.Long
	 * @param numRepeticionCarga java.lang.Integer
	 * @param ideFormatoCarga java.lang.Integer
	 * @param numVersionFormatoCarga java.lang.Byte
	 * @param ideDocumentoDeclaracion Long
	 * @param numRepeticionDeclaracion Integer
	 * @param ideFormatoDeclaracion Integer 
	 * @param numVersionFormatoDecla Byte
	 * @param idePersonaRutAgente java.lang.Long
	 * @param codEstado java.lang.Integer
	 * @param numAnio java.lang.Short
	 * @param numPeriodo java.lang.Byte
	 * @param valSubtBolsPrecIgSup3uvt java.math.BigDecimal
	 * @param valSubtDerAsistIgSup3uvt java.math.BigDecimal
	 * @param valSubtDevsRetensEvenAnt java.math.BigDecimal
	 * @param numTipoDeclaracion java.lang.Integer
	 * @param numFormularioAnterior java.lang.Long
	 * @param fecTransaccion Long
	 * @param ideUsuarioCambio java.lang.Long
	 * @param fecCambio java.sql.Timestamp
	 */
	public DRetenContribArtEscenicsAttTO(java.lang.Integer ideFormato, java.lang.Byte numVersionFormato, java.lang.Long ideDocumentoCarga, java.lang.Integer numRepeticionCarga, 
			java.lang.Integer ideFormatoCarga, java.lang.Byte numVersionFormatoCarga, java.lang.Long idePersonaRutAgente, java.lang.Integer codEstado, java.lang.Short numAnio, 
			java.lang.Long ideDocumentoDeclaracion, java.lang.Integer numRepeticionDeclaracion, java.lang.Integer ideFormatoDeclaracion, java.lang.Byte numVersionFormatoDecla,
			java.lang.Byte numPeriodo, java.math.BigDecimal valSubtBolsPrecIgSup3uvt, java.math.BigDecimal valSubtDerAsistIgSup3uvt, java.math.BigDecimal valSubtDevsRetensEvenAnt, 
			java.lang.Integer numTipoDeclaracion, java.lang.Long numFormularioAnterior, Long fecTransaccion, java.lang.Long ideUsuarioCambio, 
			java.sql.Timestamp fecCambio) {
		setIdeFormato(ideFormato);
		setNumVersionFormato(numVersionFormato);
		setIdeDocumentoCarga(ideDocumentoCarga);
		setNumRepeticionCarga(numRepeticionCarga);
		setIdeFormatoCarga(ideFormatoCarga);
		setNumVersionFormatoCarga(numVersionFormatoCarga);
		setIdeDocumentoDeclaracion(ideDocumentoDeclaracion);
		setNumRepeticionDeclaracion(numRepeticionDeclaracion);
		setIdeFormatoDeclaracion(ideFormatoDeclaracion);
		setNumVersionFormatoDecla(numVersionFormatoDecla);
		setIdePersonaRutAgente(idePersonaRutAgente);
		setCodEstado(codEstado);
		setNumAnio(numAnio);
		setNumPeriodo(numPeriodo);
		setValSubtBolsPrecIgSup3uvt(valSubtBolsPrecIgSup3uvt);
		setValSubtDerAsistIgSup3uvt(valSubtDerAsistIgSup3uvt);
		setValSubtDevsRetensEvenAnt(valSubtDevsRetensEvenAnt);
		setNumTipoDeclaracion(numTipoDeclaracion);
		setNumFormularioAnterior(numFormularioAnterior);
		setFecTransaccion(fecTransaccion);
		setIdeUsuarioCambio(ideUsuarioCambio);
		setFecCambio(fecCambio);
	}

	/**
	 * Devuelve el valor de ideFormato.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeFormato() {
		return ideFormato;
	}

	/**
	 * Establece el valor de ideFormato.
	 * @param ideFormato El nuevo valor de ideFormato
	 */
	public void setIdeFormato(java.lang.Integer ideFormato) {
		this.ideFormato = ideFormato;
	}

	/**
	 * Devuelve el valor de numVersionFormato.
	 * @return Un objeto java.lang.Byte
	 */
	public java.lang.Byte getNumVersionFormato() {
		return numVersionFormato;
	}

	/**
	 * Establece el valor de numVersionFormato.
	 * @param numVersionFormato El nuevo valor de numVersionFormato
	 */
	public void setNumVersionFormato(java.lang.Byte numVersionFormato) {
		this.numVersionFormato = numVersionFormato;
	}

	/**
	 * Devuelve el valor de ideDocumentoCarga.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeDocumentoCarga() {
		return ideDocumentoCarga;
	}

	/**
	 * Establece el valor de ideDocumentoCarga.
	 * @param ideDocumentoCarga El nuevo valor de ideDocumentoCarga
	 */
	public void setIdeDocumentoCarga(java.lang.Long ideDocumentoCarga) {
		this.ideDocumentoCarga = ideDocumentoCarga;
	}

	/**
	 * Devuelve el valor de numRepeticionCarga.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumRepeticionCarga() {
		return numRepeticionCarga;
	}

	/**
	 * Establece el valor de numRepeticionCarga.
	 * @param numRepeticionCarga El nuevo valor de numRepeticionCarga
	 */
	public void setNumRepeticionCarga(java.lang.Integer numRepeticionCarga) {
		this.numRepeticionCarga = numRepeticionCarga;
	}

	/**
	 * Devuelve el valor de ideFormatoCarga.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeFormatoCarga() {
		return ideFormatoCarga;
	}

	/**
	 * Establece el valor de ideFormatoCarga.
	 * @param ideFormatoCarga El nuevo valor de ideFormatoCarga
	 */
	public void setIdeFormatoCarga(java.lang.Integer ideFormatoCarga) {
		this.ideFormatoCarga = ideFormatoCarga;
	}

	/**
	 * Devuelve el valor de numVersionFormatoCarga.
	 * @return Un objeto java.lang.Byte
	 */
	public java.lang.Byte getNumVersionFormatoCarga() {
		return numVersionFormatoCarga;
	}

	/**
	 * Establece el valor de numVersionFormatoCarga.
	 * @param numVersionFormatoCarga El nuevo valor de numVersionFormatoCarga
	 */
	public void setNumVersionFormatoCarga(java.lang.Byte numVersionFormatoCarga) {
		this.numVersionFormatoCarga = numVersionFormatoCarga;
	}

	/**
	 * Devuelve el valor de idePersonaRutAgente.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdePersonaRutAgente() {
		return idePersonaRutAgente;
	}

	/**
	 * Establece el valor de idePersonaRutAgente.
	 * @param idePersonaRutAgente El nuevo valor de idePersonaRutAgente
	 */
	public void setIdePersonaRutAgente(java.lang.Long idePersonaRutAgente) {
		this.idePersonaRutAgente = idePersonaRutAgente;
	}

	/**
	 * Devuelve el valor de codEstado.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getCodEstado() {
		return codEstado;
	}

	/**
	 * Establece el valor de codEstado.
	 * @param codEstado El nuevo valor de codEstado
	 */
	public void setCodEstado(java.lang.Integer codEstado) {
		this.codEstado = codEstado;
	}

	/**
	 * Devuelve el valor de numAnio.
	 * @return Un objeto java.lang.Short
	 */
	public java.lang.Short getNumAnio() {
		return numAnio;
	}

	/**
	 * Establece el valor de numAnio.
	 * @param numAnio El nuevo valor de numAnio
	 */
	public void setNumAnio(java.lang.Short numAnio) {
		this.numAnio = numAnio;
	}

	/**
	 * Devuelve el valor de numPeriodo.
	 * @return Un objeto java.lang.Byte
	 */
	public java.lang.Byte getNumPeriodo() {
		return numPeriodo;
	}

	/**
	 * Establece el valor de numPeriodo.
	 * @param numPeriodo El nuevo valor de numPeriodo
	 */
	public void setNumPeriodo(java.lang.Byte numPeriodo) {
		this.numPeriodo = numPeriodo;
	}

	/**
	 * Devuelve el valor de valSubtBolsPrecIgSup3uvt.
	 * @return Un objeto java.math.BigDecimal
	 */
	public java.math.BigDecimal getValSubtBolsPrecIgSup3uvt() {
		return valSubtBolsPrecIgSup3uvt;
	}

	/**
	 * Establece el valor de valSubtBolsPrecIgSup3uvt.
	 * @param valSubtBolsPrecIgSup3uvt El nuevo valor de valSubtBolsPrecIgSup3uvt
	 */
	public void setValSubtBolsPrecIgSup3uvt(java.math.BigDecimal valSubtBolsPrecIgSup3uvt) {
		this.valSubtBolsPrecIgSup3uvt = valSubtBolsPrecIgSup3uvt;
	}

	/**
	 * Devuelve el valor de valSubtDerAsistIgSup3uvt.
	 * @return Un objeto java.math.BigDecimal
	 */
	public java.math.BigDecimal getValSubtDerAsistIgSup3uvt() {
		return valSubtDerAsistIgSup3uvt;
	}

	/**
	 * Establece el valor de valSubtDerAsistIgSup3uvt.
	 * @param valSubtDerAsistIgSup3uvt El nuevo valor de valSubtDerAsistIgSup3uvt
	 */
	public void setValSubtDerAsistIgSup3uvt(java.math.BigDecimal valSubtDerAsistIgSup3uvt) {
		this.valSubtDerAsistIgSup3uvt = valSubtDerAsistIgSup3uvt;
	}

	/**
	 * Devuelve el valor de valSubtDevsRetensEvenAnt.
	 * @return Un objeto java.math.BigDecimal
	 */
	public java.math.BigDecimal getValSubtDevsRetensEvenAnt() {
		return valSubtDevsRetensEvenAnt;
	}

	/**
	 * Establece el valor de valSubtDevsRetensEvenAnt.
	 * @param valSubtDevsRetensEvenAnt El nuevo valor de valSubtDevsRetensEvenAnt
	 */
	public void setValSubtDevsRetensEvenAnt(java.math.BigDecimal valSubtDevsRetensEvenAnt) {
		this.valSubtDevsRetensEvenAnt = valSubtDevsRetensEvenAnt;
	}

	/**
	 * Devuelve el valor de numTipoDeclaracion.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumTipoDeclaracion() {
		return numTipoDeclaracion;
	}

	/**
	 * Establece el valor de numTipoDeclaracion.
	 * @param numTipoDeclaracion El nuevo valor de numTipoDeclaracion
	 */
	public void setNumTipoDeclaracion(java.lang.Integer numTipoDeclaracion) {
		this.numTipoDeclaracion = numTipoDeclaracion;
	}

	/**
	 * Devuelve el valor de numFormularioAnterior.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getNumFormularioAnterior() {
		return numFormularioAnterior;
	}

	/**
	 * Establece el valor de numFormularioAnterior.
	 * @param numFormularioAnterior El nuevo valor de numFormularioAnterior
	 */
	public void setNumFormularioAnterior(java.lang.Long numFormularioAnterior) {
		this.numFormularioAnterior = numFormularioAnterior;
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

	public java.lang.Long getIdeDocumentoDeclaracion() {
		return ideDocumentoDeclaracion;
	}

	public void setIdeDocumentoDeclaracion(java.lang.Long ideDocumentoDeclaracion) {
		this.ideDocumentoDeclaracion = ideDocumentoDeclaracion;
	}

	public java.lang.Integer getNumRepeticionDeclaracion() {
		return numRepeticionDeclaracion;
	}

	public void setNumRepeticionDeclaracion(
			java.lang.Integer numRepeticionDeclaracion) {
		this.numRepeticionDeclaracion = numRepeticionDeclaracion;
	}

	public java.lang.Integer getIdeFormatoDeclaracion() {
		return ideFormatoDeclaracion;
	}

	public void setIdeFormatoDeclaracion(java.lang.Integer ideFormatoDeclaracion) {
		this.ideFormatoDeclaracion = ideFormatoDeclaracion;
	}

	public java.lang.Byte getNumVersionFormatoDecla() {
		return numVersionFormatoDecla;
	}

	public void setNumVersionFormatoDecla(java.lang.Byte numVersionFormatoDecla) {
		this.numVersionFormatoDecla = numVersionFormatoDecla;
	}
	
	
	public java.lang.Long getFecTransaccion() {
		return fecTransaccion;
	}

	public void setFecTransaccion(java.lang.Long fecTransaccion) {
		this.fecTransaccion = fecTransaccion;
	}

	/**
	 * Devuelve una representación en String del objeto.
	 * @return String
	 */
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("ideFormato", getIdeFormato());
		builder.append("numVersionFormato", getNumVersionFormato());
		builder.append("ideDocumentoCarga", getIdeDocumentoCarga());
		builder.append("numRepeticionCarga", getNumRepeticionCarga());
		builder.append("ideFormatoCarga", getIdeFormatoCarga());
		builder.append("numVersionFormatoCarga", getNumVersionFormatoCarga());
		builder.append("idePersonaRutAgente", getIdePersonaRutAgente());
		builder.append("ideDocumentoDeclaracion", getIdeDocumentoDeclaracion());
		builder.append("numRepeticionDeclaracion", getNumRepeticionDeclaracion());
		builder.append("ideFormatoDeclaracion", getIdeFormatoDeclaracion());
		builder.append("numVersionFormatoDecla", getNumVersionFormatoDecla());		
		builder.append("codEstado", getCodEstado());
		builder.append("numAnio", getNumAnio());
		builder.append("numPeriodo", getNumPeriodo());
		builder.append("valSubtBolsPrecIgSup3uvt", getValSubtBolsPrecIgSup3uvt());
		builder.append("valSubtDerAsistIgSup3uvt", getValSubtDerAsistIgSup3uvt());
		builder.append("valSubtDevsRetensEvenAnt", getValSubtDevsRetensEvenAnt());
		builder.append("numTipoDeclaracion", getNumTipoDeclaracion());
		builder.append("numFormularioAnterior", getNumFormularioAnterior());
		builder.append("fecTransaccion", getFecTransaccion());
		builder.append("ideUsuarioCambio", getIdeUsuarioCambio());
		builder.append("fecCambio", getFecCambio());
		return builder.toString();
	}
}