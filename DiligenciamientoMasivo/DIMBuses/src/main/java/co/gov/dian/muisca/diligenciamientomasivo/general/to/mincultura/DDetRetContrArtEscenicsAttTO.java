/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura;

import java.math.BigDecimal;
import java.sql.Timestamp;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para los atributos de DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDetRetContrArtEscenicsAttTO implements IDTO {
	private static final long serialVersionUID = -276480903L; 

	// Atributos
	private java.lang.Integer ideFormato;
	private java.lang.Byte numVersionFormato;
	private java.lang.String numEvento;
	private java.lang.String nomEspectaculo;
	private java.lang.Integer codMunicipioEspectaculo;
	private java.lang.Integer codDepartamentoEspectaculo;
	private java.lang.Integer fecRealizacion;
	private java.lang.Long idePersonaRutProductor;
	private java.lang.String valDireccionProductor;
	private java.lang.Integer codMunicipioProductor;
	private java.lang.Integer codDepartamentoProductor;
	private java.lang.Long numTelefonoProductor;
	private String valLugarEvento;
	private java.lang.Integer numTotBoletasVendidas;
	private java.math.BigDecimal valTotBoletasVendidas;
	private java.math.BigDecimal valServDistComerBoleteria;
	private java.lang.Integer numTotBolPrecIgSup3uvt;
	private java.math.BigDecimal valTotBolPrecIgSup3uvt;
	private java.lang.Integer numTotDerAsPreIgSup3uvt;
	private java.math.BigDecimal valTotDerAsPreIgSup3uvt;
	private java.lang.Integer numBoletasRetencionExceso;
	private java.math.BigDecimal valBoletasRetencionExceso;
	private java.lang.Long ideUsuarioCambio;
	private java.sql.Timestamp fecCambio;

	/**
	 * Construye un nuevo DDetRetContrArtEscenicsAttTO por defecto.
	 */
	public DDetRetContrArtEscenicsAttTO() { }

	/**
	 * Construye un nuevo DDetRetContrArtEscenicsAttTO con los atributos.
	 * @param ideFormato java.lang.Integer
	 * @param numVersionFormato java.lang.Byte
	 * @param numEvento java.lang.String
	 * @param nomEspectaculo java.lang.String
	 * @param codMunicipioEspectaculo java.lang.Integer
	 * @param codDepartamentoEspectaculo java.lang.Integer
	 * @param fecRealizacion java.lang.Integer
	 * @param idePersonaRutProductor java.lang.Long
	 * @param valDireccionProductor java.lang.String
	 * @param codMunicipioProductor java.lang.Integer
	 * @param codDepartamentoProductor java.lang.Integer
	 * @param numTelefonoProductor java.lang.Long
	 * @param valLugarEvento String
	 * @param numTotBoletasVendidas java.lang.Integer
	 * @param valTotBoletasVendidas java.math.BigDecimal
	 * @param numTotBolPrecIgSup3uvt java.lang.Integer
	 * @param valTotBolPrecIgSup3uvt java.math.BigDecimal
	 * @param numTotDerAsPreIgSup3uvt java.lang.Integer
	 * @param valTotDerAsPreIgSup3uvt java.math.BigDecimal
	 * @param numBoletasRetencionExceso java.lang.Integer
	 * @param valBoletasRetencionExceso java.math.BigDecimal
	 */
	public DDetRetContrArtEscenicsAttTO(java.lang.Integer ideFormato, java.lang.Byte numVersionFormato, java.lang.String numEvento, java.lang.String nomEspectaculo, java.lang.Integer codMunicipioEspectaculo, java.lang.Integer codDepartamentoEspectaculo, Integer fecRealizacion, java.lang.Long idePersonaRutProductor, java.lang.String valDireccionProductor, java.lang.Integer codMunicipioProductor, java.lang.Integer codDepartamentoProductor, java.lang.Long numTelefonoProductor, String valLugarEvento, java.lang.Integer numTotBoletasVendidas, java.math.BigDecimal valTotBoletasVendidas, BigDecimal valServDistComerBoleteria, java.lang.Integer numTotBolPrecIgSup3uvt, java.math.BigDecimal valTotBolPrecIgSup3uvt, java.lang.Integer numTotDerAsPreIgSup3uvt, java.math.BigDecimal valTotDerAsPreIgSup3uvt, java.lang.Integer numBoletasRetencionExceso, java.math.BigDecimal valBoletasRetencionExceso, Long ideUsuarioCambio, Timestamp fecCambio) {
		setIdeFormato(ideFormato);
		setNumVersionFormato(numVersionFormato);
		setNumEvento(numEvento);
		setNomEspectaculo(nomEspectaculo);
		setCodMunicipioEspectaculo(codMunicipioEspectaculo);
		setCodDepartamentoEspectaculo(codDepartamentoEspectaculo);
		setFecRealizacion(fecRealizacion);
		setIdePersonaRutProductor(idePersonaRutProductor);
		setValDireccionProductor(valDireccionProductor);
		setCodMunicipioProductor(codMunicipioProductor);
		setCodDepartamentoProductor(codDepartamentoProductor);
		setNumTelefonoProductor(numTelefonoProductor);
		setValLugarEvento(valLugarEvento);
		setNumTotBoletasVendidas(numTotBoletasVendidas);
		setValTotBoletasVendidas(valTotBoletasVendidas);
		setValServDistComerBoleteria(valServDistComerBoleteria);
		setNumTotBolPrecIgSup3uvt(numTotBolPrecIgSup3uvt);
		setValTotBolPrecIgSup3uvt(valTotBolPrecIgSup3uvt);
		setNumTotDerAsPreIgSup3uvt(numTotDerAsPreIgSup3uvt);
		setValTotDerAsPreIgSup3uvt(valTotDerAsPreIgSup3uvt);
		setNumBoletasRetencionExceso(numBoletasRetencionExceso);
		setValBoletasRetencionExceso(valBoletasRetencionExceso);
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
	 * Devuelve el valor de numEvento.
	 * @return Un objeto java.lang.String
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
	 * Devuelve el valor de nomEspectaculo.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getNomEspectaculo() {
		return nomEspectaculo;
	}

	/**
	 * Establece el valor de nomEspectaculo.
	 * @param nomEspectaculo El nuevo valor de nomEspectaculo
	 */
	public void setNomEspectaculo(java.lang.String nomEspectaculo) {
		this.nomEspectaculo = nomEspectaculo;
	}

	/**
	 * Devuelve el valor de codMunicipioEspectaculo.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getCodMunicipioEspectaculo() {
		return codMunicipioEspectaculo;
	}

	/**
	 * Establece el valor de codMunicipioEspectaculo.
	 * @param codMunicipioEspectaculo El nuevo valor de codMunicipioEspectaculo
	 */
	public void setCodMunicipioEspectaculo(java.lang.Integer codMunicipioEspectaculo) {
		this.codMunicipioEspectaculo = codMunicipioEspectaculo;
	}

	/**
	 * Devuelve el valor de codDepartamentoEspectaculo.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getCodDepartamentoEspectaculo() {
		return codDepartamentoEspectaculo;
	}

	/**
	 * Establece el valor de codDepartamentoEspectaculo.
	 * @param codDepartamentoEspectaculo El nuevo valor de codDepartamentoEspectaculo
	 */
	public void setCodDepartamentoEspectaculo(java.lang.Integer codDepartamentoEspectaculo) {
		this.codDepartamentoEspectaculo = codDepartamentoEspectaculo;
	}

	/**
	 * Devuelve el valor de fecRealizacion.
	 * @return Un objeto java.sql.Timestamp
	 */
	public Integer getFecRealizacion() {
		return fecRealizacion;
	}

	/**
	 * Establece el valor de fecRealizacion.
	 * @param fecRealizacion El nuevo valor de fecRealizacion
	 */
	public void setFecRealizacion(Integer fecRealizacion) {
		this.fecRealizacion = fecRealizacion;
	}

	/**
	 * Devuelve el valor de idePersonaRutProductor.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdePersonaRutProductor() {
		return idePersonaRutProductor;
	}

	/**
	 * Establece el valor de idePersonaRutProductor.
	 * @param idePersonaRutProductor El nuevo valor de idePersonaRutProductor
	 */
	public void setIdePersonaRutProductor(java.lang.Long idePersonaRutProductor) {
		this.idePersonaRutProductor = idePersonaRutProductor;
	}

	/**
	 * Devuelve el valor de valDireccionProductor.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValDireccionProductor() {
		return valDireccionProductor;
	}

	/**
	 * Establece el valor de valDireccionProductor.
	 * @param valDireccionProductor El nuevo valor de valDireccionProductor
	 */
	public void setValDireccionProductor(java.lang.String valDireccionProductor) {
		this.valDireccionProductor = valDireccionProductor;
	}

	/**
	 * Devuelve el valor de codMunicipioProductor.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getCodMunicipioProductor() {
		return codMunicipioProductor;
	}

	/**
	 * Establece el valor de codMunicipioProductor.
	 * @param codMunicipioProductor El nuevo valor de codMunicipioProductor
	 */
	public void setCodMunicipioProductor(java.lang.Integer codMunicipioProductor) {
		this.codMunicipioProductor = codMunicipioProductor;
	}

	/**
	 * Devuelve el valor de codDepartamentoProductor.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getCodDepartamentoProductor() {
		return codDepartamentoProductor;
	}

	/**
	 * Establece el valor de codDepartamentoProductor.
	 * @param codDepartamentoProductor El nuevo valor de codDepartamentoProductor
	 */
	public void setCodDepartamentoProductor(java.lang.Integer codDepartamentoProductor) {
		this.codDepartamentoProductor = codDepartamentoProductor;
	}

	/**
	 * Devuelve el valor de numTelefonoProductor.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getNumTelefonoProductor() {
		return numTelefonoProductor;
	}

	/**
	 * Establece el valor de numTelefonoProductor.
	 * @param numTelefonoProductor El nuevo valor de numTelefonoProductor
	 */
	public void setNumTelefonoProductor(java.lang.Long numTelefonoProductor) {
		this.numTelefonoProductor = numTelefonoProductor;
	}

	/**
	 * Devuelve el valor de numTotBoletasVendidas.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumTotBoletasVendidas() {
		return numTotBoletasVendidas;
	}

	/**
	 * Establece el valor de numTotBoletasVendidas.
	 * @param numTotBoletasVendidas El nuevo valor de numTotBoletasVendidas
	 */
	public void setNumTotBoletasVendidas(java.lang.Integer numTotBoletasVendidas) {
		this.numTotBoletasVendidas = numTotBoletasVendidas;
	}

	/**
	 * Devuelve el valor de valTotBoletasVendidas.
	 * @return Un objeto java.math.BigDecimal
	 */
	public java.math.BigDecimal getValTotBoletasVendidas() {
		return valTotBoletasVendidas;
	}

	/**
	 * Establece el valor de valTotBoletasVendidas.
	 * @param valTotBoletasVendidas El nuevo valor de valTotBoletasVendidas
	 */
	public void setValTotBoletasVendidas(java.math.BigDecimal valTotBoletasVendidas) {
		this.valTotBoletasVendidas = valTotBoletasVendidas;
	}

	/**
	 * Devuelve el valor de numTotBolPrecIgSup3uvt.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumTotBolPrecIgSup3uvt() {
		return numTotBolPrecIgSup3uvt;
	}

	/**
	 * Establece el valor de numTotBolPrecIgSup3uvt.
	 * @param numTotBolPrecIgSup3uvt El nuevo valor de numTotBolPrecIgSup3uvt
	 */
	public void setNumTotBolPrecIgSup3uvt(java.lang.Integer numTotBolPrecIgSup3uvt) {
		this.numTotBolPrecIgSup3uvt = numTotBolPrecIgSup3uvt;
	}

	/**
	 * Devuelve el valor de valTotBolPrecIgSup3uvt.
	 * @return Un objeto java.math.BigDecimal
	 */
	public java.math.BigDecimal getValTotBolPrecIgSup3uvt() {
		return valTotBolPrecIgSup3uvt;
	}

	/**
	 * Establece el valor de valTotBolPrecIgSup3uvt.
	 * @param valTotBolPrecIgSup3uvt El nuevo valor de valTotBolPrecIgSup3uvt
	 */
	public void setValTotBolPrecIgSup3uvt(java.math.BigDecimal valTotBolPrecIgSup3uvt) {
		this.valTotBolPrecIgSup3uvt = valTotBolPrecIgSup3uvt;
	}

	/**
	 * Devuelve el valor de numTotDerAsPreIgSup3uvt.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumTotDerAsPreIgSup3uvt() {
		return numTotDerAsPreIgSup3uvt;
	}

	/**
	 * Establece el valor de numTotDerAsPreIgSup3uvt.
	 * @param numTotDerAsPreIgSup3uvt El nuevo valor de numTotDerAsPreIgSup3uvt
	 */
	public void setNumTotDerAsPreIgSup3uvt(java.lang.Integer numTotDerAsPreIgSup3uvt) {
		this.numTotDerAsPreIgSup3uvt = numTotDerAsPreIgSup3uvt;
	}

	/**
	 * Devuelve el valor de valTotDerAsPreIgSup3uvt.
	 * @return Un objeto java.math.BigDecimal
	 */
	public java.math.BigDecimal getValTotDerAsPreIgSup3uvt() {
		return valTotDerAsPreIgSup3uvt;
	}

	/**
	 * Establece el valor de valTotDerAsPreIgSup3uvt.
	 * @param valTotDerAsPreIgSup3uvt El nuevo valor de valTotDerAsPreIgSup3uvt
	 */
	public void setValTotDerAsPreIgSup3uvt(java.math.BigDecimal valTotDerAsPreIgSup3uvt) {
		this.valTotDerAsPreIgSup3uvt = valTotDerAsPreIgSup3uvt;
	}

	/**
	 * Devuelve el valor de numBoletasRetencionExceso.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumBoletasRetencionExceso() {
		return numBoletasRetencionExceso;
	}

	/**
	 * Establece el valor de numBoletasRetencionExceso.
	 * @param numBoletasRetencionExceso El nuevo valor de numBoletasRetencionExceso
	 */
	public void setNumBoletasRetencionExceso(java.lang.Integer numBoletasRetencionExceso) {
		this.numBoletasRetencionExceso = numBoletasRetencionExceso;
	}

	/**
	 * Devuelve el valor de valBoletasRetencionExceso.
	 * @return Un objeto java.math.BigDecimal
	 */
	public java.math.BigDecimal getValBoletasRetencionExceso() {
		return valBoletasRetencionExceso;
	}

	/**
	 * Establece el valor de valBoletasRetencionExceso.
	 * @param valBoletasRetencionExceso El nuevo valor de valBoletasRetencionExceso
	 */
	public void setValBoletasRetencionExceso(java.math.BigDecimal valBoletasRetencionExceso) {
		this.valBoletasRetencionExceso = valBoletasRetencionExceso;
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

	public String getValLugarEvento() {
		return valLugarEvento;
	}

	public void setValLugarEvento(String valLugarEvento) {
		this.valLugarEvento = valLugarEvento;
	}

	public java.math.BigDecimal getValServDistComerBoleteria() {
		return valServDistComerBoleteria;
	}

	public void setValServDistComerBoleteria(
			java.math.BigDecimal valServDistComerBoleteria) {
		this.valServDistComerBoleteria = valServDistComerBoleteria;
	}	
}