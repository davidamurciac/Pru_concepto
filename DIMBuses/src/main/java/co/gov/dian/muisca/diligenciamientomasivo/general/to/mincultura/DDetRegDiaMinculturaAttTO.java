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
 * <p>Descripcion: Objeto de transporte para los atributos de DetRegDiaMincultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDetRegDiaMinculturaAttTO implements IDTO {
	private static final long serialVersionUID = -867599184L; 

	// Atributos
	private java.lang.String ideRegistroDiario;
	private java.sql.Timestamp fecRegistro;
	private java.lang.String numNit;
	private java.lang.String numDocumentoPresentado;
	private java.lang.String numDocumentoCarga;
	private java.lang.String numSolicitud;
	private java.sql.Timestamp fecCambio;
	private java.lang.String valDetalleProceso;

	/**
	 * Construye un nuevo DDetRegDiaMinculturaAttTO por defecto.
	 */
	public DDetRegDiaMinculturaAttTO() { }

	/**
	 * Construye un nuevo DDetRegDiaMinculturaAttTO con los atributos.
	 * @param ideRegistroDiario java.lang.String
	 * @param fecRegistro java.sql.Timestamp
	 * @param numNit java.lang.String
	 * @param numDocumentoPresentado java.lang.String
	 * @param numDocumentoCarga java.lang.String
	 * @param numSolicitud java.lang.String
	 * @param fecCambio java.sql.Timestamp
	 * @param valDetalleProceso java.lang.String
	 */
	public DDetRegDiaMinculturaAttTO(java.lang.String ideRegistroDiario, java.sql.Timestamp fecRegistro, java.lang.String numNit, java.lang.String numDocumentoPresentado, java.lang.String numDocumentoCarga, java.lang.String numSolicitud, java.sql.Timestamp fecCambio, java.lang.String valDetalleProceso) {
		setIdeRegistroDiario(ideRegistroDiario);
		setFecRegistro(fecRegistro);
		setNumNit(numNit);
		setNumDocumentoPresentado(numDocumentoPresentado);
		setNumDocumentoCarga(numDocumentoCarga);
		setNumSolicitud(numSolicitud);
		setFecCambio(fecCambio);
		setValDetalleProceso(valDetalleProceso);
	}

	/**
	 * Devuelve el valor de ideRegistroDiario.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getIdeRegistroDiario() {
		return ideRegistroDiario;
	}

	/**
	 * Establece el valor de ideRegistroDiario.
	 * @param ideRegistroDiario El nuevo valor de ideRegistroDiario
	 */
	public void setIdeRegistroDiario(java.lang.String ideRegistroDiario) {
		this.ideRegistroDiario = ideRegistroDiario;
	}

	/**
	 * Devuelve el valor de fecRegistro.
	 * @return Un objeto java.sql.Timestamp
	 */
	public java.sql.Timestamp getFecRegistro() {
		return fecRegistro;
	}

	/**
	 * Establece el valor de fecRegistro.
	 * @param fecRegistro El nuevo valor de fecRegistro
	 */
	public void setFecRegistro(java.sql.Timestamp fecRegistro) {
		this.fecRegistro = fecRegistro;
	}

	/**
	 * Devuelve el valor de numNit.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getNumNit() {
		return numNit;
	}

	/**
	 * Establece el valor de numNit.
	 * @param numNit El nuevo valor de numNit
	 */
	public void setNumNit(java.lang.String numNit) {
		this.numNit = numNit;
	}

	/**
	 * Devuelve el valor de numDocumentoPresentado.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getNumDocumentoPresentado() {
		return numDocumentoPresentado;
	}

	/**
	 * Establece el valor de numDocumentoPresentado.
	 * @param numDocumentoPresentado El nuevo valor de numDocumentoPresentado
	 */
	public void setNumDocumentoPresentado(java.lang.String numDocumentoPresentado) {
		this.numDocumentoPresentado = numDocumentoPresentado;
	}

	/**
	 * Devuelve el valor de numDocumentoCarga.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getNumDocumentoCarga() {
		return numDocumentoCarga;
	}

	/**
	 * Establece el valor de numDocumentoCarga.
	 * @param numDocumentoCarga El nuevo valor de numDocumentoCarga
	 */
	public void setNumDocumentoCarga(java.lang.String numDocumentoCarga) {
		this.numDocumentoCarga = numDocumentoCarga;
	}

	/**
	 * Devuelve el valor de numSolicitud.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getNumSolicitud() {
		return numSolicitud;
	}

	/**
	 * Establece el valor de numSolicitud.
	 * @param numSolicitud El nuevo valor de numSolicitud
	 */
	public void setNumSolicitud(java.lang.String numSolicitud) {
		this.numSolicitud = numSolicitud;
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
	 * Devuelve el valor de valDetalleProceso.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValDetalleProceso() {
		return valDetalleProceso;
	}

	/**
	 * Establece el valor de valDetalleProceso.
	 * @param valDetalleProceso El nuevo valor de valDetalleProceso
	 */
	public void setValDetalleProceso(java.lang.String valDetalleProceso) {
		this.valDetalleProceso = valDetalleProceso;
	}

	/**
	 * Devuelve una representación en String del objeto.
	 * @return String
	 */
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("ideRegistroDiario", getIdeRegistroDiario());
		builder.append("fecRegistro", getFecRegistro());
		builder.append("numNit", getNumNit());
		builder.append("numDocumentoPresentado", getNumDocumentoPresentado());
		builder.append("numDocumentoCarga", getNumDocumentoCarga());
		builder.append("numSolicitud", getNumSolicitud());
		builder.append("fecCambio", getFecCambio());
		builder.append("valDetalleProceso", getValDetalleProceso());
		return builder.toString();
	}
}
