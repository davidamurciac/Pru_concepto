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
 * <p>Descripcion: Objeto de transporte para la PK de RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DRegDiarioMinCulturaPKTO implements IDTO {

	private static final long serialVersionUID = 1320035599L; 

  // Campos de la PK
	private java.lang.String ideRegistroDiario;

	/**
	 * Construye un nuevo DRegDiarioMinCulturaPKTO por defecto.
	 */
	public DRegDiarioMinCulturaPKTO() { }

	/**
	 * Construye un nuevo DRegDiarioMinCulturaPKTO con los elementos de la llave primaria.
	 * @param ideRegistroDiario java.lang.String
	 */
	public DRegDiarioMinCulturaPKTO(java.lang.String ideRegistroDiario) {
		setIdeRegistroDiario(ideRegistroDiario);
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
	 * Compara el objeto actual con el objeto especificado.
	 * @param objeto Objeto con el cual se compara
	 * @return true si los objetos son iguales; false de lo contrario
	 */
	public boolean equals(Object objeto) {
		if (this == objeto) {
			return true;
		}
		if (!(objeto instanceof DRegDiarioMinCulturaPKTO)) {
			return false;
		}
		DRegDiarioMinCulturaPKTO otro = (DRegDiarioMinCulturaPKTO) objeto;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getIdeRegistroDiario(),  otro.getIdeRegistroDiario());
		return builder.isEquals();
	}

	/**
	 * Devuelve el hash code del objeto.
	 * @return int
	 */
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getIdeRegistroDiario());
		return builder.toHashCode();
	}

	/**
	 * Devuelve una representación en String del objeto.
	 * @return String
	 */
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("ideRegistroDiario", getIdeRegistroDiario());
		return builder.toString();
	}
}
