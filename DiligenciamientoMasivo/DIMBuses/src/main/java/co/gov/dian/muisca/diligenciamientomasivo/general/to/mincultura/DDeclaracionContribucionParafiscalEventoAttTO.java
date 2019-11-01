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
 * <p>Descripcion: Objeto de transporte para los atributos de DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDeclaracionContribucionParafiscalEventoAttTO implements IDTO {
	private static final long serialVersionUID = -1062727894L; 

	// Atributos
	private java.lang.String valNumEvento;
	private java.lang.Integer ideMunicipioEvento;
	private java.lang.Integer ideDepartamentoEvento;
	private java.lang.String valNombLugarEvento;
	private java.lang.Integer fecRealizacionEvento;
	private java.lang.Integer numBoletasVendidas;
	private java.lang.Long valBoletasVendidas;
	private java.lang.Integer numBolVendidasInf3uvt;
	private java.lang.Long valBolVendidasInf3uvt;
	private java.lang.Integer numBolVendidasSup3uvt;
	private java.lang.Long valBolVendidasSup3uvt;
	private java.lang.Integer numCortesiasEntregadas;
	private java.lang.Long valCortesiasEntregadas;
	private java.lang.Integer numCortEntregInf3uvt;
	private java.lang.Long valCortEntregInf3uvt;
	private java.lang.Integer numCortEntregSup3uvt;
	private java.lang.Long valCortEntregSup3uvt;
	private java.lang.Integer numBoletasPatrocinio;
	private java.lang.Long valBoletasPatrocinio;
	private java.lang.Integer numBolPatrocInf3uvt;
	private java.lang.Long valBolPatrocInf3uvt;
	private java.lang.Integer numBolPatrocSup3uvt;
	private java.lang.Long valBolPatrocSup3uvt;
	private java.lang.Long valBaseGravableCp;
	private java.lang.Long valContribParafiscal;
	private java.lang.Long valRetencionCp;
	private java.lang.Integer numBoletasPagoExceso;
	private java.lang.Long valBoletasPagoExceso;
	private java.lang.String valPrimerApellidoOp;
	private java.lang.String valSegundoApellidoOp;
	private java.lang.String valPrimerNombreOp;
	private java.lang.String valOtrosNombresOp;
	private java.lang.String valRazonSocialOp;
	private java.lang.Long ideUsuarioCambio;
	private java.sql.Timestamp fecCambio;

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalEventoAttTO por defecto.
	 */
	public DDeclaracionContribucionParafiscalEventoAttTO() { }

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalEventoAttTO con los atributos.
	 * @param valNumEvento java.lang.Long
	 * @param ideMunicipioEvento java.lang.Integer
	 * @param ideDepartamentoEvento java.lang.Integer
	 * @param valNombLugarEvento java.lang.String
	 * @param fecRealizacionEvento java.lang.Integer
	 * @param numBoletasVendidas java.lang.Integer
	 * @param valBoletasVendidas java.lang.Long
	 * @param numBolVendidasInf3uvt java.lang.Integer
	 * @param valBolVendidasInf3uvt java.lang.Long
	 * @param numBolVendidasSup3uvt java.lang.Integer
	 * @param valBolVendidasSup3uvt java.lang.Long
	 * @param numCortesiasEntregadas java.lang.Integer
	 * @param valCortesiasEntregadas java.lang.Long
	 * @param numCortEntregInf3uvt java.lang.Integer
	 * @param valCortEntregInf3uvt java.lang.Long
	 * @param numCortEntregSup3uvt java.lang.Integer
	 * @param valCortEntregSup3uvt java.lang.Long
	 * @param numBoletasPatrocinio java.lang.Integer
	 * @param valBoletasPatrocinio java.lang.Long
	 * @param numBolPatrocInf3uvt java.lang.Integer
	 * @param valBolPatrocInf3uvt java.lang.Long
	 * @param numBolPatrocSup3uvt java.lang.Integer
	 * @param valBolPatrocSup3uvt java.lang.Long
	 * @param valBaseGravableCp java.lang.Long
	 * @param valContribParafiscal java.lang.Long
	 * @param valRetencionCp java.lang.Long
	 * @param valPrimerApellidoOp java.lang.String
	 * @param valSegundoApellidoOp java.lang.String
	 * @param valPrimerNombreOp java.lang.String
	 * @param valOtrosNombresOp java.lang.String
	 * @param valRazonSocialOp java.lang.String
	 * @param numBoletasPagoExceso java.lang.Integer
	 * @param valBoletasPagoExceso java.lang.Long
	 * @param ideUsuarioCambio java.lang.Long
	 * @param fecCambio java.sql.Timestamp
	 */
	public DDeclaracionContribucionParafiscalEventoAttTO(java.lang.String valNumEvento, java.lang.Integer ideMunicipioEvento, java.lang.Integer ideDepartamentoEvento, java.lang.String valNombLugarEvento, java.lang.Integer fecRealizacionEvento, java.lang.Integer numBoletasVendidas, java.lang.Long valBoletasVendidas, java.lang.Integer numBolVendidasInf3uvt, java.lang.Long valBolVendidasInf3uvt, java.lang.Integer numBolVendidasSup3uvt, java.lang.Long valBolVendidasSup3uvt, java.lang.Integer numCortesiasEntregadas, java.lang.Long valCortesiasEntregadas, java.lang.Integer numCortEntregInf3uvt, java.lang.Long valCortEntregInf3uvt, java.lang.Integer numCortEntregSup3uvt, java.lang.Long valCortEntregSup3uvt, java.lang.Integer numBoletasPatrocinio, java.lang.Long valBoletasPatrocinio, java.lang.Integer numBolPatrocInf3uvt, java.lang.Long valBolPatrocInf3uvt, java.lang.Integer numBolPatrocSup3uvt, java.lang.Long valBolPatrocSup3uvt, java.lang.Long valBaseGravableCp, java.lang.Long valContribParafiscal, java.lang.Long valRetencionCp, java.lang.String valPrimerApellidoOp, java.lang.String valSegundoApellidoOp, java.lang.String valPrimerNombreOp, java.lang.String valOtrosNombresOp, java.lang.String valRazonSocialOp, java.lang.Integer numBoletasPagoExceso, java.lang.Long valBoletasPagoExceso, java.lang.Long ideUsuarioCambio, java.sql.Timestamp fecCambio) {
		setValNumEvento(valNumEvento);
		setIdeMunicipioEvento(ideMunicipioEvento);
		setIdeDepartamentoEvento(ideDepartamentoEvento);
		setValNombLugarEvento(valNombLugarEvento);
		setFecRealizacionEvento(fecRealizacionEvento);
		setNumBoletasVendidas(numBoletasVendidas);
		setValBoletasVendidas(valBoletasVendidas);
		setNumBolVendidasInf3uvt(numBolVendidasInf3uvt);
		setValBolVendidasInf3uvt(valBolVendidasInf3uvt);
		setNumBolVendidasSup3uvt(numBolVendidasSup3uvt);
		setValBolVendidasSup3uvt(valBolVendidasSup3uvt);
		setNumCortesiasEntregadas(numCortesiasEntregadas);
		setValCortesiasEntregadas(valCortesiasEntregadas);
		setNumCortEntregInf3uvt(numCortEntregInf3uvt);
		setValCortEntregInf3uvt(valCortEntregInf3uvt);
		setNumCortEntregSup3uvt(numCortEntregSup3uvt);
		setValCortEntregSup3uvt(valCortEntregSup3uvt);
		setNumBoletasPatrocinio(numBoletasPatrocinio);
		setValBoletasPatrocinio(valBoletasPatrocinio);
		setNumBolPatrocInf3uvt(numBolPatrocInf3uvt);
		setValBolPatrocInf3uvt(valBolPatrocInf3uvt);
		setNumBolPatrocSup3uvt(numBolPatrocSup3uvt);
		setValBolPatrocSup3uvt(valBolPatrocSup3uvt);
		setValBaseGravableCp(valBaseGravableCp);
		setValContribParafiscal(valContribParafiscal);
		setValRetencionCp(valRetencionCp);
		setValPrimerApellidoOp(valPrimerApellidoOp);
		setValSegundoApellidoOp(valSegundoApellidoOp);
		setValPrimerNombreOp(valPrimerNombreOp);
		setValOtrosNombresOp(valOtrosNombresOp);
		setValRazonSocialOp(valRazonSocialOp);
		setNumBoletasPagoExceso(numBoletasPagoExceso);
		setValBoletasPagoExceso(valBoletasPagoExceso);
		setIdeUsuarioCambio(ideUsuarioCambio);
		setFecCambio(fecCambio);
	}

	/**
	 * Devuelve el valor de valNumEvento.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValNumEvento() {
		return valNumEvento;
	}

	/**
	 * Establece el valor de valNumEvento.
	 * @param valNumEvento El nuevo valor de valNumEvento
	 */
	public void setValNumEvento(java.lang.String valNumEvento) {
		this.valNumEvento = valNumEvento;
	}

	/**
	 * Devuelve el valor de ideMunicipioEvento.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeMunicipioEvento() {
		return ideMunicipioEvento;
	}

	/**
	 * Establece el valor de ideMunicipioEvento.
	 * @param ideMunicipioEvento El nuevo valor de ideMunicipioEvento
	 */
	public void setIdeMunicipioEvento(java.lang.Integer ideMunicipioEvento) {
		this.ideMunicipioEvento = ideMunicipioEvento;
	}

	/**
	 * Devuelve el valor de ideDepartamentoEvento.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeDepartamentoEvento() {
		return ideDepartamentoEvento;
	}

	/**
	 * Establece el valor de ideDepartamentoEvento.
	 * @param ideDepartamentoEvento El nuevo valor de ideDepartamentoEvento
	 */
	public void setIdeDepartamentoEvento(java.lang.Integer ideDepartamentoEvento) {
		this.ideDepartamentoEvento = ideDepartamentoEvento;
	}

	/**
	 * Devuelve el valor de valNombLugarEvento.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValNombLugarEvento() {
		return valNombLugarEvento;
	}

	/**
	 * Establece el valor de valNombLugarEvento.
	 * @param valNombLugarEvento El nuevo valor de valNombLugarEvento
	 */
	public void setValNombLugarEvento(java.lang.String valNombLugarEvento) {
		this.valNombLugarEvento = valNombLugarEvento;
	}

	/**
	 * Devuelve el valor de fecRealizacionEvento.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getFecRealizacionEvento() {
		return fecRealizacionEvento;
	}

	/**
	 * Establece el valor de fecRealizacionEvento.
	 * @param fecRealizacionEvento El nuevo valor de fecRealizacionEvento
	 */
	public void setFecRealizacionEvento(java.lang.Integer fecRealizacionEvento) {
		this.fecRealizacionEvento = fecRealizacionEvento;
	}

	/**
	 * Devuelve el valor de numBoletasVendidas.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumBoletasVendidas() {
		return numBoletasVendidas;
	}

	/**
	 * Establece el valor de numBoletasVendidas.
	 * @param numBoletasVendidas El nuevo valor de numBoletasVendidas
	 */
	public void setNumBoletasVendidas(java.lang.Integer numBoletasVendidas) {
		this.numBoletasVendidas = numBoletasVendidas;
	}

	/**
	 * Devuelve el valor de valBoletasVendidas.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValBoletasVendidas() {
		return valBoletasVendidas;
	}

	/**
	 * Establece el valor de valBoletasVendidas.
	 * @param valBoletasVendidas El nuevo valor de valBoletasVendidas
	 */
	public void setValBoletasVendidas(java.lang.Long valBoletasVendidas) {
		this.valBoletasVendidas = valBoletasVendidas;
	}

	/**
	 * Devuelve el valor de numBolVendidasInf3uvt.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumBolVendidasInf3uvt() {
		return numBolVendidasInf3uvt;
	}

	/**
	 * Establece el valor de numBolVendidasInf3uvt.
	 * @param numBolVendidasInf3uvt El nuevo valor de numBolVendidasInf3uvt
	 */
	public void setNumBolVendidasInf3uvt(java.lang.Integer numBolVendidasInf3uvt) {
		this.numBolVendidasInf3uvt = numBolVendidasInf3uvt;
	}

	/**
	 * Devuelve el valor de valBolVendidasInf3uvt.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValBolVendidasInf3uvt() {
		return valBolVendidasInf3uvt;
	}

	/**
	 * Establece el valor de valBolVendidasInf3uvt.
	 * @param valBolVendidasInf3uvt El nuevo valor de valBolVendidasInf3uvt
	 */
	public void setValBolVendidasInf3uvt(java.lang.Long valBolVendidasInf3uvt) {
		this.valBolVendidasInf3uvt = valBolVendidasInf3uvt;
	}

	/**
	 * Devuelve el valor de numBolVendidasSup3uvt.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumBolVendidasSup3uvt() {
		return numBolVendidasSup3uvt;
	}

	/**
	 * Establece el valor de numBolVendidasSup3uvt.
	 * @param numBolVendidasSup3uvt El nuevo valor de numBolVendidasSup3uvt
	 */
	public void setNumBolVendidasSup3uvt(java.lang.Integer numBolVendidasSup3uvt) {
		this.numBolVendidasSup3uvt = numBolVendidasSup3uvt;
	}

	/**
	 * Devuelve el valor de valBolVendidasSup3uvt.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValBolVendidasSup3uvt() {
		return valBolVendidasSup3uvt;
	}

	/**
	 * Establece el valor de valBolVendidasSup3uvt.
	 * @param valBolVendidasSup3uvt El nuevo valor de valBolVendidasSup3uvt
	 */
	public void setValBolVendidasSup3uvt(java.lang.Long valBolVendidasSup3uvt) {
		this.valBolVendidasSup3uvt = valBolVendidasSup3uvt;
	}

	/**
	 * Devuelve el valor de numCortesiasEntregadas.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumCortesiasEntregadas() {
		return numCortesiasEntregadas;
	}

	/**
	 * Establece el valor de numCortesiasEntregadas.
	 * @param numCortesiasEntregadas El nuevo valor de numCortesiasEntregadas
	 */
	public void setNumCortesiasEntregadas(java.lang.Integer numCortesiasEntregadas) {
		this.numCortesiasEntregadas = numCortesiasEntregadas;
	}

	/**
	 * Devuelve el valor de valCortesiasEntregadas.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValCortesiasEntregadas() {
		return valCortesiasEntregadas;
	}

	/**
	 * Establece el valor de valCortesiasEntregadas.
	 * @param valCortesiasEntregadas El nuevo valor de valCortesiasEntregadas
	 */
	public void setValCortesiasEntregadas(java.lang.Long valCortesiasEntregadas) {
		this.valCortesiasEntregadas = valCortesiasEntregadas;
	}

	/**
	 * Devuelve el valor de numCortEntregInf3uvt.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumCortEntregInf3uvt() {
		return numCortEntregInf3uvt;
	}

	/**
	 * Establece el valor de numCortEntregInf3uvt.
	 * @param numCortEntregInf3uvt El nuevo valor de numCortEntregInf3uvt
	 */
	public void setNumCortEntregInf3uvt(java.lang.Integer numCortEntregInf3uvt) {
		this.numCortEntregInf3uvt = numCortEntregInf3uvt;
	}

	/**
	 * Devuelve el valor de valCortEntregInf3uvt.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValCortEntregInf3uvt() {
		return valCortEntregInf3uvt;
	}

	/**
	 * Establece el valor de valCortEntregInf3uvt.
	 * @param valCortEntregInf3uvt El nuevo valor de valCortEntregInf3uvt
	 */
	public void setValCortEntregInf3uvt(java.lang.Long valCortEntregInf3uvt) {
		this.valCortEntregInf3uvt = valCortEntregInf3uvt;
	}

	/**
	 * Devuelve el valor de numCortEntregSup3uvt.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumCortEntregSup3uvt() {
		return numCortEntregSup3uvt;
	}

	/**
	 * Establece el valor de numCortEntregSup3uvt.
	 * @param numCortEntregSup3uvt El nuevo valor de numCortEntregSup3uvt
	 */
	public void setNumCortEntregSup3uvt(java.lang.Integer numCortEntregSup3uvt) {
		this.numCortEntregSup3uvt = numCortEntregSup3uvt;
	}

	/**
	 * Devuelve el valor de valCortEntregSup3uvt.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValCortEntregSup3uvt() {
		return valCortEntregSup3uvt;
	}

	/**
	 * Establece el valor de valCortEntregSup3uvt.
	 * @param valCortEntregSup3uvt El nuevo valor de valCortEntregSup3uvt
	 */
	public void setValCortEntregSup3uvt(java.lang.Long valCortEntregSup3uvt) {
		this.valCortEntregSup3uvt = valCortEntregSup3uvt;
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
	 * Devuelve el valor de valBoletasPatrocinio.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValBoletasPatrocinio() {
		return valBoletasPatrocinio;
	}

	/**
	 * Establece el valor de valBoletasPatrocinio.
	 * @param valBoletasPatrocinio El nuevo valor de valBoletasPatrocinio
	 */
	public void setValBoletasPatrocinio(java.lang.Long valBoletasPatrocinio) {
		this.valBoletasPatrocinio = valBoletasPatrocinio;
	}

	/**
	 * Devuelve el valor de numBolPatrocInf3uvt.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumBolPatrocInf3uvt() {
		return numBolPatrocInf3uvt;
	}

	/**
	 * Establece el valor de numBolPatrocInf3uvt.
	 * @param numBolPatrocInf3uvt El nuevo valor de numBolPatrocInf3uvt
	 */
	public void setNumBolPatrocInf3uvt(java.lang.Integer numBolPatrocInf3uvt) {
		this.numBolPatrocInf3uvt = numBolPatrocInf3uvt;
	}

	/**
	 * Devuelve el valor de valBolPatrocInf3uvt.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValBolPatrocInf3uvt() {
		return valBolPatrocInf3uvt;
	}

	/**
	 * Establece el valor de valBolPatrocInf3uvt.
	 * @param valBolPatrocInf3uvt El nuevo valor de valBolPatrocInf3uvt
	 */
	public void setValBolPatrocInf3uvt(java.lang.Long valBolPatrocInf3uvt) {
		this.valBolPatrocInf3uvt = valBolPatrocInf3uvt;
	}

	/**
	 * Devuelve el valor de numBolPatrocSup3uvt.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumBolPatrocSup3uvt() {
		return numBolPatrocSup3uvt;
	}

	/**
	 * Establece el valor de numBolPatrocSup3uvt.
	 * @param numBolPatrocSup3uvt El nuevo valor de numBolPatrocSup3uvt
	 */
	public void setNumBolPatrocSup3uvt(java.lang.Integer numBolPatrocSup3uvt) {
		this.numBolPatrocSup3uvt = numBolPatrocSup3uvt;
	}

	/**
	 * Devuelve el valor de valBolPatrocSup3uvt.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValBolPatrocSup3uvt() {
		return valBolPatrocSup3uvt;
	}

	/**
	 * Establece el valor de valBolPatrocSup3uvt.
	 * @param valBolPatrocSup3uvt El nuevo valor de valBolPatrocSup3uvt
	 */
	public void setValBolPatrocSup3uvt(java.lang.Long valBolPatrocSup3uvt) {
		this.valBolPatrocSup3uvt = valBolPatrocSup3uvt;
	}

	/**
	 * Devuelve el valor de valBaseGravableCp.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValBaseGravableCp() {
		return valBaseGravableCp;
	}

	/**
	 * Establece el valor de valBaseGravableCp.
	 * @param valBaseGravableCp El nuevo valor de valBaseGravableCp
	 */
	public void setValBaseGravableCp(java.lang.Long valBaseGravableCp) {
		this.valBaseGravableCp = valBaseGravableCp;
	}

	/**
	 * Devuelve el valor de valContribParafiscal.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValContribParafiscal() {
		return valContribParafiscal;
	}

	/**
	 * Establece el valor de valContribParafiscal.
	 * @param valContribParafiscal El nuevo valor de valContribParafiscal
	 */
	public void setValContribParafiscal(java.lang.Long valContribParafiscal) {
		this.valContribParafiscal = valContribParafiscal;
	}

	/**
	 * Devuelve el valor de valRetencionCp.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValRetencionCp() {
		return valRetencionCp;
	}

	/**
	 * Establece el valor de valRetencionCp.
	 * @param valRetencionCp El nuevo valor de valRetencionCp
	 */
	public void setValRetencionCp(java.lang.Long valRetencionCp) {
		this.valRetencionCp = valRetencionCp;
	}

	/**
	 * Devuelve el valor de valPrimerApellidoOp.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValPrimerApellidoOp() {
		return valPrimerApellidoOp;
	}

	/**
	 * Establece el valor de valPrimerApellidoOp.
	 * @param valPrimerApellidoOp El nuevo valor de valPrimerApellidoOp
	 */
	public void setValPrimerApellidoOp(java.lang.String valPrimerApellidoOp) {
		this.valPrimerApellidoOp = valPrimerApellidoOp;
	}

	/**
	 * Devuelve el valor de valSegundoApellidoOp.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValSegundoApellidoOp() {
		return valSegundoApellidoOp;
	}

	/**
	 * Establece el valor de valSegundoApellidoOp.
	 * @param valSegundoApellidoOp El nuevo valor de valSegundoApellidoOp
	 */
	public void setValSegundoApellidoOp(java.lang.String valSegundoApellidoOp) {
		this.valSegundoApellidoOp = valSegundoApellidoOp;
	}

	/**
	 * Devuelve el valor de valPrimerNombreOp.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValPrimerNombreOp() {
		return valPrimerNombreOp;
	}

	/**
	 * Establece el valor de valPrimerNombreOp.
	 * @param valPrimerNombreOp El nuevo valor de valPrimerNombreOp
	 */
	public void setValPrimerNombreOp(java.lang.String valPrimerNombreOp) {
		this.valPrimerNombreOp = valPrimerNombreOp;
	}

	/**
	 * Devuelve el valor de valOtrosNombresOp.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValOtrosNombresOp() {
		return valOtrosNombresOp;
	}

	/**
	 * Establece el valor de valOtrosNombresOp.
	 * @param valOtrosNombresOp El nuevo valor de valOtrosNombresOp
	 */
	public void setValOtrosNombresOp(java.lang.String valOtrosNombresOp) {
		this.valOtrosNombresOp = valOtrosNombresOp;
	}

	/**
	 * Devuelve el valor de valRazonSocialOp.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValRazonSocialOp() {
		return valRazonSocialOp;
	}

	/**
	 * Establece el valor de valRazonSocialOp.
	 * @param valRazonSocialOp El nuevo valor de valRazonSocialOp
	 */
	public void setValRazonSocialOp(java.lang.String valRazonSocialOp) {
		this.valRazonSocialOp = valRazonSocialOp;
	}

	/**
	 * Devuelve el valor de numBoletasPagoExceso.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumBoletasPagoExceso() {
		return numBoletasPagoExceso;
	}

	/**
	 * Establece el valor de numBoletasPagoExceso.
	 * @param numBoletasPagoExceso El nuevo valor de numBoletasPagoExceso
	 */
	public void setNumBoletasPagoExceso(java.lang.Integer numBoletasPagoExceso) {
		this.numBoletasPagoExceso = numBoletasPagoExceso;
	}

	/**
	 * Devuelve el valor de valBoletasPagoExceso.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValBoletasPagoExceso() {
		return valBoletasPagoExceso;
	}

	/**
	 * Establece el valor de valBoletasPagoExceso.
	 * @param valBoletasPagoExceso El nuevo valor de valBoletasPagoExceso
	 */
	public void setValBoletasPagoExceso(java.lang.Long valBoletasPagoExceso) {
		this.valBoletasPagoExceso = valBoletasPagoExceso;
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
		builder.append("valNumEvento", getValNumEvento());
		builder.append("ideMunicipioEvento", getIdeMunicipioEvento());
		builder.append("ideDepartamentoEvento", getIdeDepartamentoEvento());
		builder.append("valNombLugarEvento", getValNombLugarEvento());
		builder.append("fecRealizacionEvento", getFecRealizacionEvento());
		builder.append("numBoletasVendidas", getNumBoletasVendidas());
		builder.append("valBoletasVendidas", getValBoletasVendidas());
		builder.append("numBolVendidasInf3uvt", getNumBolVendidasInf3uvt());
		builder.append("valBolVendidasInf3uvt", getValBolVendidasInf3uvt());
		builder.append("numBolVendidasSup3uvt", getNumBolVendidasSup3uvt());
		builder.append("valBolVendidasSup3uvt", getValBolVendidasSup3uvt());
		builder.append("numCortesiasEntregadas", getNumCortesiasEntregadas());
		builder.append("valCortesiasEntregadas", getValCortesiasEntregadas());
		builder.append("numCortEntregInf3uvt", getNumCortEntregInf3uvt());
		builder.append("valCortEntregInf3uvt", getValCortEntregInf3uvt());
		builder.append("numCortEntregSup3uvt", getNumCortEntregSup3uvt());
		builder.append("valCortEntregSup3uvt", getValCortEntregSup3uvt());
		builder.append("numBoletasPatrocinio", getNumBoletasPatrocinio());
		builder.append("valBoletasPatrocinio", getValBoletasPatrocinio());
		builder.append("numBolPatrocInf3uvt", getNumBolPatrocInf3uvt());
		builder.append("valBolPatrocInf3uvt", getValBolPatrocInf3uvt());
		builder.append("numBolPatrocSup3uvt", getNumBolPatrocSup3uvt());
		builder.append("valBolPatrocSup3uvt", getValBolPatrocSup3uvt());
		builder.append("valBaseGravableCp", getValBaseGravableCp());
		builder.append("valContribParafiscal", getValContribParafiscal());
		builder.append("valRetencionCp", getValRetencionCp());
		builder.append("valPrimerApellidoOp", getValPrimerApellidoOp());
		builder.append("valSegundoApellidoOp", getValSegundoApellidoOp());
		builder.append("valPrimerNombreOp", getValPrimerNombreOp());
		builder.append("valOtrosNombresOp", getValOtrosNombresOp());
		builder.append("valRazonSocialOp", getValRazonSocialOp());
		builder.append("numBoletasPagoExceso", getNumBoletasPagoExceso());
		builder.append("valBoletasPagoExceso", getValBoletasPagoExceso());
		builder.append("ideUsuarioCambio", getIdeUsuarioCambio());
		builder.append("fecCambio", getFecCambio());
		return builder.toString();
	}
}
