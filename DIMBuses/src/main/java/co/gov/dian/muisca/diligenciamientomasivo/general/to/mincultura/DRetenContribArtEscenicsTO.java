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
 * <p>Descripcion: Objeto de transporte para RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DRetenContribArtEscenicsTO implements IDTO {

	private static final long serialVersionUID = 160891052L; 

	/**
	 * Llave primaria del objeto
	 */
	private DRetenContribArtEscenicsPKTO pk;
	/**
	 * Atributos del objeto
	 */
	private DRetenContribArtEscenicsAttTO att;

	/**
	 * Construye un nuevo DRetenContribArtEscenicsTO por defecto.
	 */
	public DRetenContribArtEscenicsTO() {
		this.pk  = new DRetenContribArtEscenicsPKTO();
		this.att = new DRetenContribArtEscenicsAttTO();
	}

	/**
	 * Construye un nuevo DRetenContribArtEscenicsTO con la PK y el Att.
	 * @param pk Llave primaria
	 * @param att Atributos
	 */
	public DRetenContribArtEscenicsTO(DRetenContribArtEscenicsPKTO pk, DRetenContribArtEscenicsAttTO att) {
		this.pk = pk;
		this.att = att;
	}

	/**
	 * Devuelve la llave primaria del objeto.
	 * @return Un objeto RetenContribArtEscenicsPKTO
	 */
	public DRetenContribArtEscenicsPKTO getPK() {
		return pk;
	}

	/**
	 * Devuelve los atributos del objeto.
	 * @return Un objeto DRetenContribArtEscenicsAttTO
	 */
	public DRetenContribArtEscenicsAttTO getAtt() {
		return att;
	}

	/**
	 * Establece la llave primaria del objeto.
	 * @param pk Llave primaria
	 */
	public void setPK(DRetenContribArtEscenicsPKTO pk) {
		this.pk = pk;
	}

	/**
	 * Establece los atributos del objeto.
	 * @param att Atributos
	 */
	public void setAtt(DRetenContribArtEscenicsAttTO att) {
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
		if (!(objeto instanceof DRetenContribArtEscenicsTO)) {
			return false;
		}
		DRetenContribArtEscenicsTO otro = (DRetenContribArtEscenicsTO) objeto;
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
