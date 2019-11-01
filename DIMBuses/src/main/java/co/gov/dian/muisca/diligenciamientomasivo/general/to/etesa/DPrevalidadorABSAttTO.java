package co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa;

import java.sql.Date;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;



/**
 * Objeto de transporte que representa los atributos de un registro de la tabla EYS_DOCUMENTOS_BAN_SALIDA
 * 
 * @author nfontechar
 *
 */
public class DPrevalidadorABSAttTO implements IDTO {

	
	/*
	 * ATRIBUTOS
	 */
	private Long tipoArchivo;   
	private Long ideDocumento;
	private Integer numRepeticion; 
	private Date fecGeneracion;       
	private Long ideUsuarioCambio; 
	private Integer ideRecurso;
	
	
	/*
	 * CONSTRUCTORES
	 */
	
	public DPrevalidadorABSAttTO(Long numNIT, Long tipoArchivo,Long ideDocumento, Integer numRepeticion, Date fecGeneracion, Long ideUsuarioCambio, Integer ideRecurso) {
		this.tipoArchivo = tipoArchivo;
		this.ideDocumento = ideDocumento;
		this.numRepeticion = numRepeticion;
		this.fecGeneracion = fecGeneracion;
		this.ideUsuarioCambio = ideUsuarioCambio;
		this.ideRecurso = ideRecurso;
	}
	
	public DPrevalidadorABSAttTO() {
		this.tipoArchivo = null;
		this.ideDocumento = null;
		this.numRepeticion = null;
		this.fecGeneracion = null;
		this.ideUsuarioCambio = null;
		this.ideRecurso = null;
	}

	
	
	
	/*
	 * GETTERS Y SETTERS
	 */

	public Long getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(Long tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public Long getIdeDocumento() {
		return ideDocumento;
	}

	public void setIdeDocumento(Long ideDocumento) {
		this.ideDocumento = ideDocumento;
	}

	public Integer getNumRepeticion() {
		return numRepeticion;
	}

	public void setNumRepeticion(Integer numRepeticion) {
		this.numRepeticion = numRepeticion;
	}

	public Date getFecGeneracion() {
		return fecGeneracion;
	}

	public void setFecGeneracion(Date fecGeneracion) {
		this.fecGeneracion = fecGeneracion;
	}

	public Long getIdeUsuarioCambio() {
		return ideUsuarioCambio;
	}

	public void setIdeUsuarioCambio(Long ideUsuarioCambio) {
		this.ideUsuarioCambio = ideUsuarioCambio;
	}

	public Integer getIdeRecurso() {
		return ideRecurso;
	}

	public void setIdeRecurso(Integer ideRecurso) {
		this.ideRecurso = ideRecurso;
	}
	
	
	
	
}/*fin de class*/
