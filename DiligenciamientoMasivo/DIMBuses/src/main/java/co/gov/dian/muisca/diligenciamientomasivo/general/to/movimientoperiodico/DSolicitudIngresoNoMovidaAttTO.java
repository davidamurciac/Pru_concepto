package co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico;

import java.sql.Date;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;




/**
 * TO que representa los campos de atributos del registro de una solicitud no movida
 * 
 * @author nfontechar
 *
 */
public class DSolicitudIngresoNoMovidaAttTO implements IDTO {

	/*
	 * ATRIBUTOS
	 */
	
	
	
	/***
	 * El estado de la solicitud
	 */
	private Integer codEstado;
	
	
	
	
	/**
	 * El concepto de la solicitud
	 */
	private Integer codConcepto;
	
	
	
	
	/**
	 * El período de vigencia de la solicitud
	 */
	private Integer numPeriodoVigencia;
	
	
	
	
	/**
	 * El año de vigencia de la solicitud
	 */
	private Integer anioVigencia;
	
	
	
	
	/**
	 * Ide de persona asociado a la solicitud
	 */
	private Long idePersona;
	
	
	
	
	/**
	 * Ide de organización asociado a la solicitud
	 */
	private Integer ideOrganizacion;
	
	
	
	
	/**
	 * Ide de formato del documento asociado a la solicitud
	 */
	private Integer ideFormato;
	
	
	
	
	/***
	 * Versión del formato del documento asociado a la solicitud
	 */
	private Integer numVersionFormato;
	
	
	
	
	/**
	 * Número total de archivos que conforman la solicitud
	 */
	private Integer numTotalArchivos;
	
	
	
	
	/**
	 * Número total de registros en la solicitud
	 */
	private Integer numTotalRegistros;
	
	
	
	
	/**
	 * Ide de usuario asociado a la solicitud
	 */
	private Integer ideUsuarioSolicitud;
	
	
	
	
	/**
	 * Fecha de la solicitud
	 */
	private Date fecSolicitud;
	
	
	
	
	/**
	 * Ide de usuario que realiza cambios en el registro de la solicitud
	 */
	private Integer ideUsuarioCambio;
	
	
	
	
	/**
	 * Fecha en que se realizan cambios al registro de la solicitud 
	 */
	private Date fecCambio;
	
	
	
	
	/**
	 * Ide de solicitud que se está corrigiendo (o reemplazando), cuando hay lugar
	 */
	private Long ideSolicitudCorregida;
	
	
	
	
	/**
	 * Número de registros de la solicitud que no presentan errores luego de validaciones
	 */
	private Integer numRegistrosOk;
	
	
	
	
	/**
	 * Número de registros de la solicitud que presentan errores luego de validaciones
	 */
	private Integer numRegistrosConError;
	
	
	
	
	/**
	 * Cógigo de concepto cuando hay reemplazo o corrección de solicitud
	 */
	private Integer codConceptoCambio;
	
	
	
	
	/**
	 * Ide de concepto de cancelación de una solicitud
	 */
	private Integer ideConceptoCancelacion;
	
	
	
	
	/**
	 * Indicador de solicitud movida
	 */
	private String indMovida;
	
	
	
	
	/**
	 * Número de registros de la solicitud que fueron movidos con éxito
	 */
	private Integer numRegistrosMovidos;
	
	
	
	
	/**
	 * Identificador de tanda de procesamiento de registros en la solicitud
	 */
	private Integer ideTanda;
	
	
	
	
	
	
	/*
	 * CONSTRUCTORES
	 */
	public DSolicitudIngresoNoMovidaAttTO(){
		this.codEstado=null;
		this.codConcepto=null;
		this.numPeriodoVigencia=null;
		this.anioVigencia=null;
		this.idePersona=null;
		this.ideOrganizacion=null;
		this.ideFormato=null;
		this.numVersionFormato=null;
		this.numTotalArchivos=null;
		this.numTotalRegistros=null;
		this.ideUsuarioSolicitud=null;
		this.fecSolicitud=null;
		this.ideUsuarioCambio=null;
		this.fecCambio=null;
		this.ideSolicitudCorregida=null;
		this.numRegistrosOk=null;
		this.numRegistrosConError=null;
		this.codConceptoCambio=null;
		this.ideConceptoCancelacion=null;
		this.indMovida=null;
		this.numRegistrosMovidos=null;
		this.ideTanda=null;
	}

	public DSolicitudIngresoNoMovidaAttTO(Integer codEstado,Integer codConcepto, Integer numPeriodoVigencia,Integer anioVigencia, Long idePersona, Integer ideOrganizacion,Integer ideFormato, Integer numVersionFormato,Integer numTotalArchivos, Integer numTotalRegistros,Integer ideUsuarioSolicitud, Date fecSolicitud,Integer ideUsuarioCambio, Date fecCambio,Long ideSolicitudCorregida, Integer numRegistrosOk,Integer numRegistrosConError, Integer codConceptoCambio,Integer ideConceptoCancelacion, String indMovida,Integer numRegistrosMovidos, Integer ideTanda) {
		this.codEstado = codEstado;
		this.codConcepto = codConcepto;
		this.numPeriodoVigencia = numPeriodoVigencia;
		this.anioVigencia = anioVigencia;
		this.idePersona = idePersona;
		this.ideOrganizacion = ideOrganizacion;
		this.ideFormato = ideFormato;
		this.numVersionFormato = numVersionFormato;
		this.numTotalArchivos = numTotalArchivos;
		this.numTotalRegistros = numTotalRegistros;
		this.ideUsuarioSolicitud = ideUsuarioSolicitud;
		this.fecSolicitud = fecSolicitud;
		this.ideUsuarioCambio = ideUsuarioCambio;
		this.fecCambio = fecCambio;
		this.ideSolicitudCorregida = ideSolicitudCorregida;
		this.numRegistrosOk = numRegistrosOk;
		this.numRegistrosConError = numRegistrosConError;
		this.codConceptoCambio = codConceptoCambio;
		this.ideConceptoCancelacion = ideConceptoCancelacion;
		this.indMovida = indMovida;
		this.numRegistrosMovidos = numRegistrosMovidos;
		this.ideTanda = ideTanda;
	}



	
	/*
	 * GETTERS Y SETTERS
	 */
	
	
	
	
	/**
	 * Devuelve el valor del atributo codEstado
	 * 
	 * @return codEstado
	 */
	public Integer getCodEstado() {
		return codEstado;
	}

	
	
	
	/**
	 * Asigna un valor al atributo codEstado
	 * 
	 * @param codEstado Objeto de tipo Integer para asignar al atributo codEstado
	 */
	public void setCodEstado(Integer codEstado) {
		this.codEstado = codEstado;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo codConcepto
	 * 
	 * @return codConcepto
	 */
	public Integer getCodConcepto() {
		return codConcepto;
	}

	
	
	
	/**
	 * Asigna un valor al atributo codConcepto
	 * 
	 * @param codConcepto Objeto de tipo Integer para asignar al atributo codConcepto
	 */
	public void setCodConcepto(Integer codConcepto) {
		this.codConcepto = codConcepto;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo numPeriodoVigencia
	 * 
	 * @return numPeriodoVigencia
	 */
	public Integer getNumPeriodoVigencia() {
		return numPeriodoVigencia;
	}

	
	
	
	/**
	 * Asigna un valor al atributo numPeriodoVigencia
	 * 
	 * @param numPeriodoVigencia Objeto de tipo Integer para asignar al atributo numPeriodoVigencia
	 */
	public void setNumPeriodoVigencia(Integer numPeriodoVigencia) {
		this.numPeriodoVigencia = numPeriodoVigencia;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo anioVigencia
	 * 
	 * @return anioVigencia
	 */
	public Integer getAnioVigencia() {
		return anioVigencia;
	}

	
	
	
	/**
	 * Asigna un valor al atributo anioVigencia
	 * 
	 * @param anioVigencia Objeto de tipo Integer para asignar al atributo anioVigencia
	 */
	public void setAnioVigencia(Integer anioVigencia) {
		this.anioVigencia = anioVigencia;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo idePersona
	 * 
	 * @return idePersona
	 */
	public Long getIdePersona() {
		return idePersona;
	}

	
	
	
	/**
	 * Asigna un valor al atributo idePersona
	 * 
	 * @param idePersona Objeto de tipo Integer para asignar al atributo idePersona
	 */
	public void setIdePersona(Long idePersona) {
		this.idePersona = idePersona;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo ideOrganizacion
	 * 
	 * @return ideOrganizacion
	 */
	public Integer getIdeOrganizacion() {
		return ideOrganizacion;
	}

	
	
	
	/**
	 * Asigna un valor al atributo ideOrganizacion
	 * 
	 * @param ideOrganizacion Objeto de tipo Integer para asignar al atributo ideOrganizacion
	 */
	public void setIdeOrganizacion(Integer ideOrganizacion) {
		this.ideOrganizacion = ideOrganizacion;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo ideFormato
	 * 
	 * @return ideFormato
	 */
	public Integer getIdeFormato() {
		return ideFormato;
	}

	
	

	/**
	 * Asigna un valor al atributo ideFormato
	 * 
	 * @param ideFormato Objeto de tipo Integer para asignar al atributo ideFormato
	 */
	public void setIdeFormato(Integer ideFormato) {
		this.ideFormato = ideFormato;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo numVersionFormato
	 * 
	 * @return numVersionFormato
	 */
	public Integer getNumVersionFormato() {
		return numVersionFormato;
	}

	
	
	
	/**
	 * Asigna un valor al atributo numVersionFormato
	 * 
	 * @param numVersionFormato Objeto de tipo Integer para asignar al atributo numVersionFormato
	 */
	public void setNumVersionFormato(Integer numVersionFormato) {
		this.numVersionFormato = numVersionFormato;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo numTotalArchivos
	 * 
	 * @return numTotalArchivos
	 */
	public Integer getNumTotalArchivos() {
		return numTotalArchivos;
	}

	
	
	
	/**
	 * Asigna un valor al atributo numTotalArchivos
	 * 
	 * @param numTotalArchivos Objeto de tipo Integer para asignar al atributo numTotalArchivos
	 */
	public void setNumTotalArchivos(Integer numTotalArchivos) {
		this.numTotalArchivos = numTotalArchivos;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo numTotalRegistros
	 * 
	 * @return numTotalRegistros
	 */
	public Integer getNumTotalRegistros() {
		return numTotalRegistros;
	}

	
	
	
	/**
	 * Asigna un valor al atributo numTotalRegistros
	 * 
	 * @param numTotalRegistros Objeto de tipo Integer para asignar al atributo numTotalRegistros
	 */
	public void setNumTotalRegistros(Integer numTotalRegistros) {
		this.numTotalRegistros = numTotalRegistros;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo ideUsuarioSolicitud
	 * 
	 * @return ideUsuarioSolicitud
	 */
	public Integer getIdeUsuarioSolicitud() {
		return ideUsuarioSolicitud;
	}

	
	
	
	/**
	 * Asigna un valor al atributo ideUsuarioSolicitud
	 * 
	 * @param ideUsuarioSolicitud Objeto de tipo Integer para asignar al atributo ideUsuarioSolicitud
	 */
	public void setIdeUsuarioSolicitud(Integer ideUsuarioSolicitud) {
		this.ideUsuarioSolicitud = ideUsuarioSolicitud;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo fecSolicitud
	 * 
	 * @return fecSolicitud
	 */
	public Date getFecSolicitud() {
		return fecSolicitud;
	}

	
	
	
	/**
	 * Asigna un valor al atributo fecSolicitud
	 * 
	 * @param fecSolicitud Objeto de tipo Integer para asignar al atributo fecSolicitud
	 */
	public void setFecSolicitud(Date fecSolicitud) {
		this.fecSolicitud = fecSolicitud;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo ideUsuarioCambio
	 * 
	 * @return ideUsuarioCambio
	 */
	public Integer getIdeUsuarioCambio() {
		return ideUsuarioCambio;
	}

	
	
	
	/**
	 * Asigna un valor al atributo ideUsuarioCambio
	 * 
	 * @param ideUsuarioCambio Objeto de tipo Integer para asignar al atributo ideUsuarioCambio
	 */
	public void setIdeUsuarioCambio(Integer ideUsuarioCambio) {
		this.ideUsuarioCambio = ideUsuarioCambio;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo fecCambio
	 * 
	 * @return fecCambio
	 */
	public Date getFecCambio() {
		return fecCambio;
	}

	
	
	
	/**
	 * Asigna un valor al atributo fecCambio
	 * 
	 * @param fecCambio Objeto de tipo Date para asignar al atributo fecCambio
	 */
	public void setFecCambio(Date fecCambio) {
		this.fecCambio = fecCambio;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo ideSolicitudCorregida
	 * 
	 * @return ideSolicitudCorregida
	 */
	public Long getIdeSolicitudCorregida() {
		return ideSolicitudCorregida;
	}

	
	
	
	/**
	 * Asigna un valor al atributo ideSolicitudCorregida
	 * 
	 * @param ideSolicitudCorregida Objeto de tipo Long para asignar al atributo ideSolicitudCorregida
	 */
	public void setIdeSolicitudCorregida(Long ideSolicitudCorregida) {
		this.ideSolicitudCorregida = ideSolicitudCorregida;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo numRegistrosOk
	 * 
	 * @return numRegistrosOk
	 */
	public Integer getNumRegistrosOk() {
		return numRegistrosOk;
	}

	
	
	
	/**
	 * Asigna un valor al atributo numRegistrosOk
	 * 
	 * @param numRegistrosOk Objeto de tipo Integer para asignar al atributo numRegistrosOk
	 */
	public void setNumRegistrosOk(Integer numRegistrosOk) {
		this.numRegistrosOk = numRegistrosOk;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo numRegistrosConError
	 * 
	 * @return numRegistrosConError
	 */
	public Integer getNumRegistrosConError() {
		return numRegistrosConError;
	}

	
	
	
	/**
	 * Asigna un valor al atributo numRegistrosConError
	 * 
	 * @param numRegistrosConError Objeto de tipo Integer para asignar al atributo numRegistrosConError
	 */
	public void setNumRegistrosConError(Integer numRegistrosConError) {
		this.numRegistrosConError = numRegistrosConError;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo codConceptoCambio
	 * 
	 * @return codConceptoCambio
	 */
	public Integer getCodConceptoCambio() {
		return codConceptoCambio;
	}

	
	
	
	/**
	 * Asigna un valor al atributo codConceptoCambio
	 * 
	 * @param codConceptoCambio Objeto de tipo Integer para asignar al atributo codConceptoCambio
	 */
	public void setCodConceptoCambio(Integer codConceptoCambio) {
		this.codConceptoCambio = codConceptoCambio;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo ideConceptoCancelacion
	 * 
	 * @return ideConceptoCancelacion
	 */
	public Integer getIdeConceptoCancelacion() {
		return ideConceptoCancelacion;
	}

	
	
	
	/**
	 * Asigna un valor al atributo ideConceptoCancelacion
	 * 
	 * @param ideConceptoCancelacion Objeto de tipo Integer para asignar al atributo ideConceptoCancelacion
	 */
	public void setIdeConceptoCancelacion(Integer ideConceptoCancelacion) {
		this.ideConceptoCancelacion = ideConceptoCancelacion;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo indMovida
	 * 
	 * @return indMovida
	 */
	public String getIndMovida() {
		return indMovida;
	}

	
	
	
	/**
	 * Asigna un valor al atributo indMovida
	 * 
	 * @param indMovida Objeto de tipo String para asignar al atributo indMovida
	 */
	public void setIndMovida(String indMovida) {
		this.indMovida = indMovida;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo numRegistrosMovidos
	 * 
	 * @return numRegistrosMovidos
	 */
	public Integer getNumRegistrosMovidos() {
		return numRegistrosMovidos;
	}

	
	
	
	/**
	 * Asigna un valor al atributo numRegistrosMovidos
	 * 
	 * @param numRegistrosMovidos Objeto de tipo Integer para asignar al atributo numRegistrosMovidos
	 */
	public void setNumRegistrosMovidos(Integer numRegistrosMovidos) {
		this.numRegistrosMovidos = numRegistrosMovidos;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo ideTanda
	 * 
	 * @return ideTanda
	 */
	public Integer getIdeTanda() {
		return ideTanda;
	}

	
	
	
	/**
	 * Asigna un valor al atributo ideTanda
	 * 
	 * @param ideTanda Objeto de tipo Integer para asignar al atributo ideTanda
	 */
	public void setIdeTanda(Integer ideTanda) {
		this.ideTanda = ideTanda;
	}
		
	
	
	
}/*fin de class*/
