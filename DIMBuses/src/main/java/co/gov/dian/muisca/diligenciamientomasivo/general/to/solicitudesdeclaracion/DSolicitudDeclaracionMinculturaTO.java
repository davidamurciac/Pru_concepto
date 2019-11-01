package co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


public class DSolicitudDeclaracionMinculturaTO implements IDTO {

	private static final long	serialVersionUID			= 4193575273608082224L;

	private Long				numNit						= null;
	private Long				ideDocumentoPresentado		= null;
	private Integer				numRepeticionPresentado		= null;
	private Integer				ideFormatoPresentado		= null;
	private Integer				numVersionFormatoPresentado	= null;
	private Long				valorPresentado				= null;
	private Long				ideSolicitud				= null;
	private Long				ideDocumentoCargado			= null;
	private Integer				numRepeticionCargado		= null;
	private Integer				ideFormatoCargado			= null;
	private Integer				numVersionFormatoCargado	= null;

	public DSolicitudDeclaracionMinculturaTO() {
	}

	public DSolicitudDeclaracionMinculturaTO(Long numNit, Long ideDocumentoPresentado, Integer numRepeticionPresentado, Integer ideFormatoPresentado, Integer numVersionFormatoPresentado,
			Long valorPresentado, Long ideSolicitud, Long ideDocumentoCargado, Integer numRepeticionCargado, Integer ideFormatoCargado, Integer numVersionFormatoCargado) {

		this.numNit = numNit;
		this.ideDocumentoPresentado = ideDocumentoPresentado;
		this.numRepeticionPresentado = numRepeticionPresentado;
		this.ideFormatoPresentado = ideFormatoPresentado;
		this.numVersionFormatoPresentado = numVersionFormatoPresentado;
		this.valorPresentado = valorPresentado;
		this.ideSolicitud = ideSolicitud;
		this.ideDocumentoCargado = ideDocumentoCargado;
		this.numRepeticionCargado = numRepeticionCargado;
		this.ideFormatoCargado = ideFormatoCargado;
		this.numVersionFormatoCargado = numVersionFormatoCargado;
	}

	public Long getNumNit() {
		return numNit;
	}

	public void setNumNit(Long numNit) {
		this.numNit = numNit;
	}

	public Long getIdeDocumentoPresentado() {
		return ideDocumentoPresentado;
	}

	public void setIdeDocumentoPresentado(Long ideDocumentoPresentado) {
		this.ideDocumentoPresentado = ideDocumentoPresentado;
	}

	public Integer getNumRepeticionPresentado() {
		return numRepeticionPresentado;
	}

	public void setNumRepeticionPresentado(Integer numRepeticionPresentado) {
		this.numRepeticionPresentado = numRepeticionPresentado;
	}

	public Integer getIdeFormatoPresentado() {
		return ideFormatoPresentado;
	}

	public void setIdeFormatoPresentado(Integer ideFormatoPresentado) {
		this.ideFormatoPresentado = ideFormatoPresentado;
	}

	public Integer getNumVersionFormatoPresentado() {
		return numVersionFormatoPresentado;
	}

	public void setNumVersionFormatoPresentado(Integer numVersionFormatoPresentado) {
		this.numVersionFormatoPresentado = numVersionFormatoPresentado;
	}

	public Long getValorPresentado() {
		return valorPresentado;
	}

	public void setValorPresentado(Long valorPresentado) {
		this.valorPresentado = valorPresentado;
	}

	public Long getIdeSolicitud() {
		return ideSolicitud;
	}

	public void setIdeSolicitud(Long ideSolicitud) {
		this.ideSolicitud = ideSolicitud;
	}

	public Long getIdeDocumentoCargado() {
		return ideDocumentoCargado;
	}

	public void setIdeDocumentoCargado(Long ideDocumentoCargado) {
		this.ideDocumentoCargado = ideDocumentoCargado;
	}

	public Integer getNumRepeticionCargado() {
		return numRepeticionCargado;
	}

	public void setNumRepeticionCargado(Integer numRepeticionCargado) {
		this.numRepeticionCargado = numRepeticionCargado;
	}

	public Integer getIdeFormatoCargado() {
		return ideFormatoCargado;
	}

	public void setIdeFormatoCargado(Integer ideFormatoCargado) {
		this.ideFormatoCargado = ideFormatoCargado;
	}

	public Integer getNumVersionFormatoCargado() {
		return numVersionFormatoCargado;
	}

	public void setNumVersionFormatoCargado(Integer numVersionFormatoCargado) {
		this.numVersionFormatoCargado = numVersionFormatoCargado;
	}

	@Override
	public String toString() {
		return "DSolicitudDeclaracionMinculturaTO [numNit=" + numNit + ", ideDocumentoPresentado=" + ideDocumentoPresentado + ", numRepeticionPresentado=" + numRepeticionPresentado
				+ ", ideFormatoPresentado=" + ideFormatoPresentado + ", numVersionFormatoPresentado=" + numVersionFormatoPresentado + ", valorPresentado=" + valorPresentado + ", ideSolicitud="
				+ ideSolicitud + ", ideDocumentoCargado=" + ideDocumentoCargado + ", numRepeticionCargado=" + numRepeticionCargado + ", ideFormatoCargado=" + ideFormatoCargado
				+ ", numVersionFormatoCargado=" + numVersionFormatoCargado + "]";
	}

}
