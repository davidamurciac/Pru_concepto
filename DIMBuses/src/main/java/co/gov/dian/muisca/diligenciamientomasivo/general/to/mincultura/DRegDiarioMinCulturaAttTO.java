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
 * <p>Descripcion: Objeto de transporte para los atributos de RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DRegDiarioMinCulturaAttTO implements IDTO {
	private static final long serialVersionUID = -847683485L; 

	// Atributos
	private java.sql.Timestamp fecRegistro;
	private java.lang.String codEstado;
	private java.lang.String dirServidorProceso;
	private java.lang.String valDescripcionProceso;
	private java.sql.Timestamp fecCambio;

	/**
	 * Construye un nuevo DRegDiarioMinCulturaAttTO por defecto.
	 */
	public DRegDiarioMinCulturaAttTO() { }

	/**
	 * Construye un nuevo DRegDiarioMinCulturaAttTO con los atributos.
	 * @param fecRegistro java.sql.Timestamp
	 * @param codEstado java.lang.String
	 * @param dirServidorProceso java.lang.String
	 * @param valDescripcionProceso java.lang.String
	 * @param fecCambio java.sql.Timestamp
	 */
	public DRegDiarioMinCulturaAttTO(java.sql.Timestamp fecRegistro, java.lang.String codEstado, java.lang.String dirServidorProceso, java.lang.String valDescripcionProceso, java.sql.Timestamp fecCambio) {
		setFecRegistro(fecRegistro);
		setCodEstado(codEstado);
		setDirServidorProceso(dirServidorProceso);
		setValDescripcionProceso(valDescripcionProceso);
		setFecCambio(fecCambio);
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
	 * Devuelve el valor de codEstado.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getCodEstado() {
		return codEstado;
	}

	/**
	 * Establece el valor de codEstado.
	 * @param codEstado El nuevo valor de codEstado
	 */
	public void setCodEstado(java.lang.String codEstado) {
		this.codEstado = codEstado;
	}

	/**
	 * Devuelve el valor de dirServidorProceso.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getDirServidorProceso() {
		return dirServidorProceso;
	}

	/**
	 * Establece el valor de dirServidorProceso.
	 * @param dirServidorProceso El nuevo valor de dirServidorProceso
	 */
	public void setDirServidorProceso(java.lang.String dirServidorProceso) {
		this.dirServidorProceso = dirServidorProceso;
	}

	/**
	 * Devuelve el valor de valDescripcionProceso.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getValDescripcionProceso() {
		return valDescripcionProceso;
	}

	/**
	 * Establece el valor de valDescripcionProceso.
	 * @param valDescripcionProceso El nuevo valor de valDescripcionProceso
	 */
	public void setValDescripcionProceso(java.lang.String valDescripcionProceso) {
		this.valDescripcionProceso = valDescripcionProceso;
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
		builder.append("fecRegistro", getFecRegistro());
		builder.append("codEstado", getCodEstado());
		builder.append("dirServidorProceso", getDirServidorProceso());
		builder.append("valDescripcionProceso", getValDescripcionProceso());
		builder.append("fecCambio", getFecCambio());
		return builder.toString();
	}
}
