/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para la PK de FormalizaBorradorDef.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DFormalizaBorradorDefPKTO implements IDTO {

	private static final long serialVersionUID = -1932802076L; 

  // Campos de la PK
	private java.lang.Long ideProceso;

	/**
	 * Construye un nuevo DFormalizaBorradorDefPKTO por defecto.
	 */
	public DFormalizaBorradorDefPKTO() { }

	/**
	 * Construye un nuevo DFormalizaBorradorDefPKTO con los elementos de la llave primaria.
	 * @param ideProceso java.lang.Long
	 */
	public DFormalizaBorradorDefPKTO(java.lang.Long ideProceso) {
		setIdeProceso(ideProceso);
	}

	/**
	 * Devuelve el valor de ideProceso.
	 * @return Un objeto java.lang.Long
	 */
	public java.lang.Long getIdeProceso() {
		return ideProceso;
	}

	/**
	 * Establece el valor de ideProceso.
	 * @param ideProceso El nuevo valor de ideProceso
	 */
	public void setIdeProceso(java.lang.Long ideProceso) {
		this.ideProceso = ideProceso;
	}
}
