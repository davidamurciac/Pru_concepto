/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDeclaracionContribucionParafiscalPatrocinadorTO implements IDTO {

	private static final long serialVersionUID = 1654427765L; 

	/**
	 * Llave primaria del objeto
	 */
	private DDeclaracionContribucionParafiscalPatrocinadorPKTO pk;
	/**
	 * Atributos del objeto
	 */
	private DDeclaracionContribucionParafiscalPatrocinadorAttTO att;

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalPatrocinadorTO por defecto.
	 */
	public DDeclaracionContribucionParafiscalPatrocinadorTO() {
		this.pk  = new DDeclaracionContribucionParafiscalPatrocinadorPKTO();
		this.att = new DDeclaracionContribucionParafiscalPatrocinadorAttTO();
	}

	/**
	 * Construye un nuevo DDeclaracionContribucionParafiscalPatrocinadorTO con la PK y el Att.
	 * @param pk Llave primaria
	 * @param att Atributos
	 */
	public DDeclaracionContribucionParafiscalPatrocinadorTO(DDeclaracionContribucionParafiscalPatrocinadorPKTO pk, DDeclaracionContribucionParafiscalPatrocinadorAttTO att) {
		this.pk = pk;
		this.att = att;
	}

	/**
	 * Devuelve la llave primaria del objeto.
	 * @return Un objeto DeclaracionContribucionParafiscalPatrocinadorPKTO
	 */
	public DDeclaracionContribucionParafiscalPatrocinadorPKTO getPK() {
		return pk;
	}

	/**
	 * Devuelve los atributos del objeto.
	 * @return Un objeto DDeclaracionContribucionParafiscalPatrocinadorAttTO
	 */
	public DDeclaracionContribucionParafiscalPatrocinadorAttTO getAtt() {
		return att;
	}

	/**
	 * Establece la llave primaria del objeto.
	 * @param pk Llave primaria
	 */
	public void setPK(DDeclaracionContribucionParafiscalPatrocinadorPKTO pk) {
		this.pk = pk;
	}

	/**
	 * Establece los atributos del objeto.
	 * @param att Atributos
	 */
	public void setAtt(DDeclaracionContribucionParafiscalPatrocinadorAttTO att) {
		this.att = att;
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
		if (!(objeto instanceof DDeclaracionContribucionParafiscalPatrocinadorTO)) {
			return false;
		}
		DDeclaracionContribucionParafiscalPatrocinadorTO otro = (DDeclaracionContribucionParafiscalPatrocinadorTO) objeto;
		EqualsBuilder builder = new EqualsBuilder();
		return builder.append(this.getPK(), otro.getPK()).isEquals();
	}

	/**
	 * Devuelve el hash code del objeto.
	 * @return int
	 */
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder.append(getPK()).toHashCode();
	}

	/**
	 * Devuelve una representación en String del objeto.
	 * @return String
	 */
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("LLAVE PRIMARIA", getPK());
		builder.append("ATRIBUTOS", getAtt());
		return builder.toString();
	}
}
