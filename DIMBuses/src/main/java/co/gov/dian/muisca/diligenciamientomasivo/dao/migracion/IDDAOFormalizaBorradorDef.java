/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.migracion;

import java.util.Collection;

import co.gov.dian.muisca.arquitectura.interfaces.IDDAO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Objeto de acceso a datos para FormalizaBorradorDef.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: DIAN
 * </p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 *
 *          <pre>
 * $Log[10]:$
 *          </pre>
 */
public interface IDDAOFormalizaBorradorDef extends IDDAO {
	static final int CONSULTAR_POR_PK = 0;
	static final int CREAR = 1;
	static final int ACTUALIZAR = 2;
	static final int ELIMINAR = 3;
	static final int CONSULTA_GENERICA = 4;
	static final int CONSULTA_TODOS = 5;
	static final int ACTUALIZAR_ESTADO = 6;

	// Secuencia
	static final String SEQ_FORMALIZA_BORRADOR = "DIM_FOBD_SEQ";

	/**
	 * Devuelve la colección de objetos FormalizaBorradorDef que se hayan
	 * consultado.
	 *
	 * @return Un Collection con objetos DFormalizaBorradorDefTO
	 */
	Collection<DFormalizaBorradorDefTO> getColeccionFormalizaBorradorDef();

	/**
	 * Devuelve el objeto FormalizaBorradorDef que se haya consultado.
	 *
	 * @return Un objeto DFormalizaBorradorDefTO
	 */
	DFormalizaBorradorDefTO getFormalizaBorradorDef();

	/**
	 * Devuelve el objeto FormalizaBorradorDefPK que se haya consultado.
	 *
	 * @return Un objeto DFormalizaBorradorDefPKTO
	 */
	DFormalizaBorradorDefPKTO getPkFormalizaBorradorDef();

	/**
	 * Inicializa la actualización de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	void inicializarActualizar(DFormalizaBorradorDefTO toFormalizaBorradorDef);

	/**
	 * Inicializa la actualización del estado de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	void inicializarActualizarEstado(DFormalizaBorradorDefTO toFormalizaBorradorDef);

	/**
	 * Inicializa la eliminación de FormalizaBorradorDef.
	 *
	 * @param attFormalizaBorradorDef
	 *            Atributos de FormalizaBorradorDef
	 */
	void inicializarConsultaGenerica(DFormalizaBorradorDefTO toFormalizaBorradorDef);

	/**
	 * Inicializa la consulta por llave primaria.
	 *
	 * @param pkFormalizaBorradorDef
	 *            Llave primaria de FormalizaBorradorDef
	 */
	void inicializarConsultarPorPK(DFormalizaBorradorDefPKTO pkFormalizaBorradorDef);

	/**
	 * Inicializa la consulta de todos los borradores definitivos a formalizar.
	 *
	 * @param attFormalizaBorradorDef
	 *            Atributos de FormalizaBorradorDef
	 */
	void inicializarConsultaTodos();

	/**
	 * Inicializa la creación de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	void inicializarCrear(DFormalizaBorradorDefTO toFormalizaBorradorDef);

	/**
	 * Inicializa la eliminación de FormalizaBorradorDef.
	 *
	 * @param pkFormalizaBorradorDef
	 *            Llave primaria de FormalizaBorradorDef
	 */
	void inicializarEliminar(DFormalizaBorradorDefPKTO pkFormalizaBorradorDef);
}
