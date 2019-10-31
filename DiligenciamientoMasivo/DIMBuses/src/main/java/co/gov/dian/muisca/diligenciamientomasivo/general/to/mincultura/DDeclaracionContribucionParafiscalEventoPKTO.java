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
 * <p>Descripcion: Objeto de transporte para la PK de DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDeclaracionContribucionParafiscalEventoPKTO implements IDTO {

	private static final long serialVersionUID = 133224736L; 

  // Campos de la PK
	private java.lang.Long ideDocumentoContParaf;
	private java.lang.Integer numRepeticionDocContParf;
	private java.lang.Integer ideOcurrencia;
	private java.lang.Integer ideItem;

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalEventoPKTO por defecto.
	 */
	public DDeclaracionContribucionParafiscalEventoPKTO() { }

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalEventoPKTO con los elementos de la llave primaria.
	 * @param ideDocumentoContParaf java.lang.Long
	 * @param numRepeticionDocContParf java.lang.Integer
	 * @param ideOcurrencia java.lang.Integer
	 * @param ideItem java.lang.Integer
	 */
	public DDeclaracionContribucionParafiscalEventoPKTO(java.lang.Long ideDocumentoContParaf, java.lang.Integer numRepeticionDocContParf, java.lang.Integer ideOcurrencia, java.lang.Integer ideItem) {
		setIdeDocumentoContParaf(ideDocumentoContParaf);
		setNumRepeticionDocContParf(numRepeticionDocContParf);
		setIdeOcurrencia(ideOcurrencia);
		setIdeItem(ideItem);
	}

	/**
	 * Devuelve el valor de ideDocumentoContParaf.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeDocumentoContParaf() {
		return ideDocumentoContParaf;
	}

	/**
	 * Establece el valor de ideDocumentoContParaf.
	 * @param ideDocumentoContParaf El nuevo valor de ideDocumentoContParaf
	 */
	public void setIdeDocumentoContParaf(java.lang.Long ideDocumentoContParaf) {
		this.ideDocumentoContParaf = ideDocumentoContParaf;
	}

	/**
	 * Devuelve el valor de numRepeticionDocContParf.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumRepeticionDocContParf() {
		return numRepeticionDocContParf;
	}

	/**
	 * Establece el valor de numRepeticionDocContParf.
	 * @param numRepeticionDocContParf El nuevo valor de numRepeticionDocContParf
	 */
	public void setNumRepeticionDocContParf(java.lang.Integer numRepeticionDocContParf) {
		this.numRepeticionDocContParf = numRepeticionDocContParf;
	}

	/**
	 * Devuelve el valor de ideOcurrencia.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeOcurrencia() {
		return ideOcurrencia;
	}

	/**
	 * Establece el valor de ideOcurrencia.
	 * @param ideOcurrencia El nuevo valor de ideOcurrencia
	 */
	public void setIdeOcurrencia(java.lang.Integer ideOcurrencia) {
		this.ideOcurrencia = ideOcurrencia;
	}

	/**
	 * Devuelve el valor de ideItem.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getIdeItem() {
		return ideItem;
	}

	/**
	 * Establece el valor de ideItem.
	 * @param ideItem El nuevo valor de ideItem
	 */
	public void setIdeItem(java.lang.Integer ideItem) {
		this.ideItem = ideItem;
	}

	/**
	 * Compara el objeto actual con el objeto especificado.
	 * @param objeto Objeto con el cual se compara
	 * @return true si los objetos son iguales; false de lo contrario
	 */
	public boolean equals(Object objeto) {
		if (this == objeto) {
			return true;
		}
		if (!(objeto instanceof DDeclaracionContribucionParafiscalEventoPKTO)) {
			return false;
		}
		DDeclaracionContribucionParafiscalEventoPKTO otro = (DDeclaracionContribucionParafiscalEventoPKTO) objeto;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getIdeDocumentoContParaf(),  otro.getIdeDocumentoContParaf());
		builder.append(getNumRepeticionDocContParf(),  otro.getNumRepeticionDocContParf());
		builder.append(getIdeOcurrencia(),  otro.getIdeOcurrencia());
		builder.append(getIdeItem(),  otro.getIdeItem());
		return builder.isEquals();
	}

	/**
	 * Devuelve el hash code del objeto.
	 * @return int
	 */
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getIdeDocumentoContParaf());
		builder.append(getNumRepeticionDocContParf());
		builder.append(getIdeOcurrencia());
		builder.append(getIdeItem());
		return builder.toHashCode();
	}

	/**
	 * Devuelve una representación en String del objeto.
	 * @return String
	 */
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("ideDocumentoContParaf", getIdeDocumentoContParaf());
		builder.append("numRepeticionDocContParf", getNumRepeticionDocContParf());
		builder.append("ideOcurrencia", getIdeOcurrencia());
		builder.append("ideItem", getIdeItem());
		return builder.toString();
	}
}
