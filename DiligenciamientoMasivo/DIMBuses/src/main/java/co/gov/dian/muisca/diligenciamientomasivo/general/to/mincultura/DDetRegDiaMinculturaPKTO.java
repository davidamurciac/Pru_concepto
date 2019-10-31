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
 * <p>Descripcion: Objeto de transporte para la PK de DetRegDiaMincultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDetRegDiaMinculturaPKTO implements IDTO {

	private static final long serialVersionUID = -421474722L; 

  // Campos de la PK
	private java.lang.String ideDetalleDiario;

	/**
	 * Construye un nuevo DDetRegDiaMinculturaPKTO por defecto.
	 */
	public DDetRegDiaMinculturaPKTO() { }

	/**
	 * Construye un nuevo DDetRegDiaMinculturaPKTO con los elementos de la llave primaria.
	 * @param ideDetalleDiario java.lang.String
	 */
	public DDetRegDiaMinculturaPKTO(java.lang.String ideDetalleDiario) {
		setIdeDetalleDiario(ideDetalleDiario);
	}

	/**
	 * Devuelve el valor de ideDetalleDiario.
	 * @return Un objeto java.lang.String
	 */
	public java.lang.String getIdeDetalleDiario() {
		return ideDetalleDiario;
	}

	/**
	 * Establece el valor de ideDetalleDiario.
	 * @param ideDetalleDiario El nuevo valor de ideDetalleDiario
	 */
	public void setIdeDetalleDiario(java.lang.String ideDetalleDiario) {
		this.ideDetalleDiario = ideDetalleDiario;
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
		if (!(objeto instanceof DDetRegDiaMinculturaPKTO)) {
			return false;
		}
		DDetRegDiaMinculturaPKTO otro = (DDetRegDiaMinculturaPKTO) objeto;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getIdeDetalleDiario(),  otro.getIdeDetalleDiario());
		return builder.isEquals();
	}

	/**
	 * Devuelve el hash code del objeto.
	 * @return int
	 */
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getIdeDetalleDiario());
		return builder.toHashCode();
	}

	/**
	 * Devuelve una representación en String del objeto.
	 * @return String
	 */
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("ideDetalleDiario", getIdeDetalleDiario());
		return builder.toString();
	}
}
