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
 * <p>Descripcion: Objeto de transporte para los atributos de DeclaracionContribucionParafiscal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDeclaracionContribucionParafiscalAttTO implements IDTO {
	private static final long serialVersionUID = 99889498L; 

	// Atributos
	private java.lang.Long idePersonaRut;
	private java.lang.Integer numAnio;
	private java.lang.Integer numPeriodo;
	private java.lang.Long ideSolicitud;
	private java.lang.Integer ideTipoDocumento;
	private java.lang.String numIdentificacion;
	private java.lang.String valPrimerApellido;
	private java.lang.String valSegundoApellido;
	private java.lang.String valPrimerNombre;
	private java.lang.String valOtrosNombres;
	private java.lang.String valRazonSocial;
	private java.lang.Long valTotalBolVendida;
	private java.lang.Long valTotalDerCortesia;
	private java.lang.Long valTotalDerPatrocinio;
	private java.lang.Long valTotalCruces;
	private java.lang.Long valSubtotalRetenciones;
	private java.lang.Long valSubtotalDevoluciones;
	private java.lang.Integer valTipoDeclaracion;
	private java.lang.Long numFormularioAnterior;
	private java.lang.String valClaseProductor;
	private java.lang.String valNumRegistroProductor;
	private java.lang.Long valFechaPresentacion;
	private java.lang.Long ideDocumento480;
	private java.lang.Integer numRepeticionDoc480;
	private java.lang.Long ideUsuarioCambio;
	private java.sql.Timestamp fecCambio;

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalAttTO por defecto.
	 */
	public DDeclaracionContribucionParafiscalAttTO() { }

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalAttTO con los atributos.
	 * @param idePersonaRut java.lang.Long
	 * @param numAnio java.lang.Integer
	 * @param numPeriodo java.lang.Integer
	 * @param ideSolicitud java.lang.Long
	 * @param ideTipoDocumento java.lang.Integer
	 * @param numIdentificacion java.lang.String
	 * @param valPrimerApellido java.lang.String
	 * @param valSegundoApellido java.lang.String
	 * @param valPrimerNombre java.lang.String
	 * @param valOtrosNombres java.lang.String
	 * @param valRazonSocial java.lang.String
	 * @param valTotalBolVendida java.lang.Long
	 * @param valTotalDerCortesia java.lang.Long
	 * @param valTotalDerPatrocinio java.lang.Long
	 * @param valTotalCruces java.lang.Long
	 * @param valSubtotalRetenciones java.lang.Long
	 * @param valSubtotalDevoluciones java.lang.Long
	 * @param valTipoDeclaracion java.lang.Integer
	 * @param numFormularioAnterior java.lang.Long
	 * @param valClaseProductor java.lang.String
	 * @param valNumRegistroProductor java.lang.String
	 * @param valFechaPresentacion java.lang.Long
	 * @param ideDocumento480 java.lang.Long
	 * @param numRepeticionDoc480 java.lang.Integer
	 * @param ideUsuarioCambio java.lang.Long
	 * @param fecCambio java.sql.Timestamp
	 */
	public DDeclaracionContribucionParafiscalAttTO(java.lang.Long idePersonaRut, java.lang.Integer numAnio, java.lang.Integer numPeriodo, java.lang.Long ideSolicitud, java.lang.Integer ideTipoDocumento, java.lang.String numIdentificacion, java.lang.String valPrimerApellido, java.lang.String valSegundoApellido, java.lang.String valPrimerNombre, java.lang.String valOtrosNombres, java.lang.String valRazonSocial, java.lang.Long valTotalBolVendida, java.lang.Long valTotalDerCortesia, java.lang.Long valTotalDerPatrocinio, java.lang.Long valTotalCruces, java.lang.Long valSubtotalRetenciones, java.lang.Long valSubtotalDevoluciones, java.lang.Integer valTipoDeclaracion, java.lang.Long numFormularioAnterior, java.lang.String valClaseProductor, java.lang.String valNumRegistroProductor, java.lang.Long valFechaPresentacion, java.lang.Long ideDocumento480, java.lang.Integer numRepeticionDoc480, java.lang.Long ideUsuarioCambio, java.sql.Timestamp fecCambio) {
		setIdePersonaRut(idePersonaRut);
		setNumAnio(numAnio);
		setNumPeriodo(numPeriodo);
		setIdeSolicitud(ideSolicitud);
		setIdeTipoDocumento(ideTipoDocumento);
		setNumIdentificacion(numIdentificacion);
		setValPrimerApellido(valPrimerApellido);
		setValSegundoApellido(valSegundoApellido);
		setValPrimerNombre(valPrimerNombre);
		setValOtrosNombres(valOtrosNombres);
		setValRazonSocial(valRazonSocial);
		setValTotalBolVendida(valTotalBolVendida);
		setValTotalDerCortesia(valTotalDerCortesia);
		setValTotalDerPatrocinio(valTotalDerPatrocinio);
		setValTotalCruces(valTotalCruces);
		setValSubtotalRetenciones(valSubtotalRetenciones);
		setValSubtotalDevoluciones(valSubtotalDevoluciones);
		setValTipoDeclaracion(valTipoDeclaracion);
		setNumFormularioAnterior(numFormularioAnterior);
		setValClaseProductor(valClaseProductor);
		setValNumRegistroProductor(valNumRegistroProductor);
		setValFechaPresentacion(valFechaPresentacion);
		setIdeDocumento480(ideDocumento480);
		setNumRepeticionDoc480(numRepeticionDoc480);
		setIdeUsuarioCambio(ideUsuarioCambio);
		setFecCambio(fecCambio);
	}

	/**
	 * Devuelve el valor de idePersonaRut.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdePersonaRut() {
		return idePersonaRut;
	}

	/**
	 * Establece el valor de idePersonaRut.
	 * @param idePersonaRut El nuevo valor de idePersonaRut
	 */
	public void setIdePersonaRut(java.lang.Long idePersonaRut) {
		this.idePersonaRut = idePersonaRut;
	}

	/**
	 * Devuelve el valor de numAnio.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumAnio() {
		return numAnio;
	}

	/**
	 * Establece el valor de numAnio.
	 * @param numAnio El nuevo valor de numAnio
	 */
	public void setNumAnio(java.lang.Integer numAnio) {
		this.numAnio = numAnio;
	}

	/**
	 * Devuelve el valor de numPeriodo.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumPeriodo() {
		return numPeriodo;
	}

	/**
	 * Establece el valor de numPeriodo.
	 * @param numPeriodo El nuevo valor de numPeriodo
	 */
	public void setNumPeriodo(java.lang.Integer numPeriodo) {
		this.numPeriodo = numPeriodo;
	}

	/**
	 * Devuelve el valor de ideSolicitud.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeSolicitud() {
		return ideSolicitud;
	}

	/**
	 * Establece el valor de ideSolicitud.
	 * @param ideSolicitud El nuevo valor de ideSolicitud
	 */
	public void setIdeSolicitud(java.lang.Long ideSolicitud) {
		this.ideSolicitud = ideSolicitud;
	}

	/**
	 * Devuelve el valor de ideTipoDocumento.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeTipoDocumento() {
		return ideTipoDocumento;
	}

	/**
	 * Establece el valor de ideTipoDocumento.
	 * @param ideTipoDocumento El nuevo valor de ideTipoDocumento
	 */
	public void setIdeTipoDocumento(java.lang.Integer ideTipoDocumento) {
		this.ideTipoDocumento = ideTipoDocumento;
	}

	/**
	 * Devuelve el valor de numIdentificacion.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getNumIdentificacion() {
		return numIdentificacion;
	}

	/**
	 * Establece el valor de numIdentificacion.
	 * @param numIdentificacion El nuevo valor de numIdentificacion
	 */
	public void setNumIdentificacion(java.lang.String numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}

	/**
	 * Devuelve el valor de valPrimerApellido.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValPrimerApellido() {
		return valPrimerApellido;
	}

	/**
	 * Establece el valor de valPrimerApellido.
	 * @param valPrimerApellido El nuevo valor de valPrimerApellido
	 */
	public void setValPrimerApellido(java.lang.String valPrimerApellido) {
		this.valPrimerApellido = valPrimerApellido;
	}

	/**
	 * Devuelve el valor de valSegundoApellido.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValSegundoApellido() {
		return valSegundoApellido;
	}

	/**
	 * Establece el valor de valSegundoApellido.
	 * @param valSegundoApellido El nuevo valor de valSegundoApellido
	 */
	public void setValSegundoApellido(java.lang.String valSegundoApellido) {
		this.valSegundoApellido = valSegundoApellido;
	}

	/**
	 * Devuelve el valor de valPrimerNombre.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValPrimerNombre() {
		return valPrimerNombre;
	}

	/**
	 * Establece el valor de valPrimerNombre.
	 * @param valPrimerNombre El nuevo valor de valPrimerNombre
	 */
	public void setValPrimerNombre(java.lang.String valPrimerNombre) {
		this.valPrimerNombre = valPrimerNombre;
	}

	/**
	 * Devuelve el valor de valOtrosNombres.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValOtrosNombres() {
		return valOtrosNombres;
	}

	/**
	 * Establece el valor de valOtrosNombres.
	 * @param valOtrosNombres El nuevo valor de valOtrosNombres
	 */
	public void setValOtrosNombres(java.lang.String valOtrosNombres) {
		this.valOtrosNombres = valOtrosNombres;
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
	 * Devuelve el valor de valTotalBolVendida.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValTotalBolVendida() {
		return valTotalBolVendida;
	}

	/**
	 * Establece el valor de valTotalBolVendida.
	 * @param valTotalBolVendida El nuevo valor de valTotalBolVendida
	 */
	public void setValTotalBolVendida(java.lang.Long valTotalBolVendida) {
		this.valTotalBolVendida = valTotalBolVendida;
	}

	/**
	 * Devuelve el valor de valTotalDerCortesia.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValTotalDerCortesia() {
		return valTotalDerCortesia;
	}

	/**
	 * Establece el valor de valTotalDerCortesia.
	 * @param valTotalDerCortesia El nuevo valor de valTotalDerCortesia
	 */
	public void setValTotalDerCortesia(java.lang.Long valTotalDerCortesia) {
		this.valTotalDerCortesia = valTotalDerCortesia;
	}

	/**
	 * Devuelve el valor de valTotalDerPatrocinio.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValTotalDerPatrocinio() {
		return valTotalDerPatrocinio;
	}

	/**
	 * Establece el valor de valTotalDerPatrocinio.
	 * @param valTotalDerPatrocinio El nuevo valor de valTotalDerPatrocinio
	 */
	public void setValTotalDerPatrocinio(java.lang.Long valTotalDerPatrocinio) {
		this.valTotalDerPatrocinio = valTotalDerPatrocinio;
	}

	/**
	 * Devuelve el valor de valTotalCruces.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValTotalCruces() {
		return valTotalCruces;
	}

	/**
	 * Establece el valor de valTotalCruces.
	 * @param valTotalCruces El nuevo valor de valTotalCruces
	 */
	public void setValTotalCruces(java.lang.Long valTotalCruces) {
		this.valTotalCruces = valTotalCruces;
	}

	/**
	 * Devuelve el valor de valSubtotalRetenciones.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValSubtotalRetenciones() {
		return valSubtotalRetenciones;
	}

	/**
	 * Establece el valor de valSubtotalRetenciones.
	 * @param valSubtotalRetenciones El nuevo valor de valSubtotalRetenciones
	 */
	public void setValSubtotalRetenciones(java.lang.Long valSubtotalRetenciones) {
		this.valSubtotalRetenciones = valSubtotalRetenciones;
	}

	/**
	 * Devuelve el valor de valSubtotalDevoluciones.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValSubtotalDevoluciones() {
		return valSubtotalDevoluciones;
	}

	/**
	 * Establece el valor de valSubtotalDevoluciones.
	 * @param valSubtotalDevoluciones El nuevo valor de valSubtotalDevoluciones
	 */
	public void setValSubtotalDevoluciones(java.lang.Long valSubtotalDevoluciones) {
		this.valSubtotalDevoluciones = valSubtotalDevoluciones;
	}

	/**
	 * Devuelve el valor de valTipoDeclaracion.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getValTipoDeclaracion() {
		return valTipoDeclaracion;
	}

	/**
	 * Establece el valor de valTipoDeclaracion.
	 * @param valTipoDeclaracion El nuevo valor de valTipoDeclaracion
	 */
	public void setValTipoDeclaracion(java.lang.Integer valTipoDeclaracion) {
		this.valTipoDeclaracion = valTipoDeclaracion;
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
	 * Devuelve el valor de valClaseProductor.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValClaseProductor() {
		return valClaseProductor;
	}

	/**
	 * Establece el valor de valClaseProductor.
	 * @param valClaseProductor El nuevo valor de valClaseProductor
	 */
	public void setValClaseProductor(java.lang.String valClaseProductor) {
		this.valClaseProductor = valClaseProductor;
	}

	/**
	 * Devuelve el valor de valNumRegistroProductor.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValNumRegistroProductor() {
		return valNumRegistroProductor;
	}

	/**
	 * Establece el valor de valNumRegistroProductor.
	 * @param valNumRegistroProductor El nuevo valor de valNumRegistroProductor
	 */
	public void setValNumRegistroProductor(java.lang.String valNumRegistroProductor) {
		this.valNumRegistroProductor = valNumRegistroProductor;
	}

	/**
	 * Devuelve el valor de valFechaPresentacion.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getValFechaPresentacion() {
		return valFechaPresentacion;
	}

	/**
	 * Establece el valor de valFechaPresentacion.
	 * @param valFechaPresentacion El nuevo valor de valFechaPresentacion
	 */
	public void setValFechaPresentacion(java.lang.Long valFechaPresentacion) {
		this.valFechaPresentacion = valFechaPresentacion;
	}

	/**
	 * Devuelve el valor de ideDocumento480.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeDocumento480() {
		return ideDocumento480;
	}

	/**
	 * Establece el valor de ideDocumento480.
	 * @param ideDocumento480 El nuevo valor de ideDocumento480
	 */
	public void setIdeDocumento480(java.lang.Long ideDocumento480) {
		this.ideDocumento480 = ideDocumento480;
	}

	/**
	 * Devuelve el valor de numRepeticionDoc480.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumRepeticionDoc480() {
		return numRepeticionDoc480;
	}

	/**
	 * Establece el valor de numRepeticionDoc480.
	 * @param numRepeticionDoc480 El nuevo valor de numRepeticionDoc480
	 */
	public void setNumRepeticionDoc480(java.lang.Integer numRepeticionDoc480) {
		this.numRepeticionDoc480 = numRepeticionDoc480;
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
		builder.append("idePersonaRut", getIdePersonaRut());
		builder.append("numAnio", getNumAnio());
		builder.append("numPeriodo", getNumPeriodo());
		builder.append("ideSolicitud", getIdeSolicitud());
		builder.append("ideTipoDocumento", getIdeTipoDocumento());
		builder.append("numIdentificacion", getNumIdentificacion());
		builder.append("valPrimerApellido", getValPrimerApellido());
		builder.append("valSegundoApellido", getValSegundoApellido());
		builder.append("valPrimerNombre", getValPrimerNombre());
		builder.append("valOtrosNombres", getValOtrosNombres());
		builder.append("valRazonSocial", getValRazonSocial());
		builder.append("valTotalBolVendida", getValTotalBolVendida());
		builder.append("valTotalDerCortesia", getValTotalDerCortesia());
		builder.append("valTotalDerPatrocinio", getValTotalDerPatrocinio());
		builder.append("valTotalCruces", getValTotalCruces());
		builder.append("valSubtotalRetenciones", getValSubtotalRetenciones());
		builder.append("valSubtotalDevoluciones", getValSubtotalDevoluciones());
		builder.append("valTipoDeclaracion", getValTipoDeclaracion());
		builder.append("numFormularioAnterior", getNumFormularioAnterior());
		builder.append("valClaseProductor", getValClaseProductor());
		builder.append("valNumRegistroProductor", getValNumRegistroProductor());
		builder.append("valFechaPresentacion", getValFechaPresentacion());
		builder.append("ideDocumento480", getIdeDocumento480());
		builder.append("numRepeticionDoc480", getNumRepeticionDoc480());
		builder.append("ideUsuarioCambio", getIdeUsuarioCambio());
		builder.append("fecCambio", getFecCambio());
		return builder.toString();
	}
}