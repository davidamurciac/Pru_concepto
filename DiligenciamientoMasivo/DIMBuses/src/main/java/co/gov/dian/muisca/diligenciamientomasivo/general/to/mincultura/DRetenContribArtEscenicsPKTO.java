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
 * <p>Descripcion: Objeto de transporte para la PK de RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DRetenContribArtEscenicsPKTO implements IDTO {

	private static final long serialVersionUID = 632246596L; 

  // Campos de la PK
	private java.lang.Long ideDocumento;
	private java.lang.Integer numRepeticion;

	/**
	 * Construye un nuevo DRetenContribArtEscenicsPKTO por defecto.
	 */
	public DRetenContribArtEscenicsPKTO() { }

	/**
	 * Construye un nuevo DRetenContribArtEscenicsPKTO con los elementos de la llave primaria.
	 * @param ideDocumento java.lang.Long
	 * @param numRepeticion java.lang.Integer
	 */
	public DRetenContribArtEscenicsPKTO(java.lang.Long ideDocumento, java.lang.Integer numRepeticion) {
		setIdeDocumento(ideDocumento);
		setNumRepeticion(numRepeticion);
	}

	/**
	 * Devuelve el valor de ideDocumento.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeDocumento() {
		return ideDocumento;
	}

	/**
	 * Establece el valor de ideDocumento.
	 * @param ideDocumento El nuevo valor de ideDocumento
	 */
	public void setIdeDocumento(java.lang.Long ideDocumento) {
		this.ideDocumento = ideDocumento;
	}

	/**
	 * Devuelve el valor de numRepeticion.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumRepeticion() {
		return numRepeticion;
	}

	/**
	 * Establece el valor de numRepeticion.
	 * @param numRepeticion El nuevo valor de numRepeticion
	 */
	public void setNumRepeticion(java.lang.Integer numRepeticion) {
		this.numRepeticion = numRepeticion;
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
		if (!(objeto instanceof DRetenContribArtEscenicsPKTO)) {
			return false;
		}
		DRetenContribArtEscenicsPKTO otro = (DRetenContribArtEscenicsPKTO) objeto;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getIdeDocumento(),  otro.getIdeDocumento());
		builder.append(getNumRepeticion(),  otro.getNumRepeticion());
		return builder.isEquals();
	}

	/**
	 * Devuelve el hash code del objeto.
	 * @return int
	 */
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getIdeDocumento());
		builder.append(getNumRepeticion());
		return builder.toHashCode();
	}

	/**
	 * Devuelve una representación en String del objeto.
	 * @return String
	 */
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("ideDocumento", getIdeDocumento());
		builder.append("numRepeticion", getNumRepeticion());
		return builder.toString();
	}
}
