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
 * <p>Descripcion: Objeto de transporte para FormalizaBorradorDef.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DFormalizaBorradorDefTO implements IDTO {

	private static final long serialVersionUID = 373270195L; 

	/**
	 * Llave primaria del objeto
	 */
	private DFormalizaBorradorDefPKTO pk;
	/**
	 * Atributos del objeto
	 */
	private DFormalizaBorradorDefAttTO att;

	/**
	 * Construye un nuevo DFormalizaBorradorDefTO por defecto.
	 */
	public DFormalizaBorradorDefTO() {
		this.pk  = new DFormalizaBorradorDefPKTO();
		this.att = new DFormalizaBorradorDefAttTO();
	}

	/**
	 * Construye un nuevo DFormalizaBorradorDefTO con la PK y el Att.
	 * @param pk Llave primaria
	 * @param att Atributos
	 */
	public DFormalizaBorradorDefTO(DFormalizaBorradorDefPKTO pk, DFormalizaBorradorDefAttTO att) {
		this.pk = pk;
		this.att = att;
	}

	/**
	 * Devuelve la llave primaria del objeto.
	 * @return Un objeto FormalizaBorradorDefPKTO
	 */
	public DFormalizaBorradorDefPKTO getPK() {
		return pk;
	}

	/**
	 * Devuelve los atributos del objeto.
	 * @return Un objeto DFormalizaBorradorDefAttTO
	 */
	public DFormalizaBorradorDefAttTO getAtt() {
		return att;
	}

	/**
	 * Establece la llave primaria del objeto.
	 * @param pk Llave primaria
	 */
	public void setPK(DFormalizaBorradorDefPKTO pk) {
		this.pk = pk;
	}

	/**
	 * Establece los atributos del objeto.
	 * @param att Atributos
	 */
	public void setAtt(DFormalizaBorradorDefAttTO att) {
		this.att = att;
	}
}
