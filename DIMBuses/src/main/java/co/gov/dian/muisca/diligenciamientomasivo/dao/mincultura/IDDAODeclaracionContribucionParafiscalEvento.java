/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura;

import java.util.*;

import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAODeclaracionContribucionParafiscalEvento extends IDDAO {
	static final int CONSULTAR_POR_PK = 0;
	static final int CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL = 1;
	static final int CREAR = 2;
	static final int ACTUALIZAR = 3;
	static final int ELIMINAR = 4;
	static final int CONSULTA_GENERICA = 5;
	
	int CREAR_CON_COLECCION = 6;
	int ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL = 7;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDeclaracionContribucionParafiscalEvento Llave primaria de DeclaracionContribucionParafiscalEvento
	 */
	void inicializarConsultarPorPK(DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento);

	/**
	 * Inicializa la consulta por DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	void inicializarConsultarPorDeclaracionContribucionParafiscal(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal);
	
	/**
	 * Inicializa la eliminación por DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	void inicializarEliminarPorDeclaracionContribucionParafiscal(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal);	

	/**
	 * Inicializa la creación de DeclaracionContribucionParafiscalEvento.
	 * @param toDeclaracionContribucionParafiscalEvento Objeto de Transporte de DeclaracionContribucionParafiscalEvento
	 */
	void inicializarCrear(DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento);
	
	/**
	 * Inicializa la creación de DeclaracionContribucionParafiscalEvento con colección.
	 * @param tosDeclaracionContribucionParafiscalEvento Collection de Objetos de Transporte de DeclaracionContribucionParafiscalEvento
	 */
	void inicializarCrear(Collection<DDeclaracionContribucionParafiscalEventoTO> tosDeclaracionContribucionParafiscalEvento);

	/**
	 * Inicializa la actualización de DeclaracionContribucionParafiscalEvento.
	 * @param toDeclaracionContribucionParafiscalEvento Objeto de Transporte de DeclaracionContribucionParafiscalEvento
	 */
	void inicializarActualizar(DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento);

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscalEvento.
	 * @param pkDeclaracionContribucionParafiscalEvento Llave primaria de DeclaracionContribucionParafiscalEvento
	 */
	void inicializarEliminar(DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento);

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscalEvento.
	 * @param attDeclaracionContribucionParafiscalEvento Atributos de DeclaracionContribucionParafiscalEvento
	 */
	void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento);

	/**
	 * Devuelve el objeto DeclaracionContribucionParafiscalEvento que se haya consultado.
	 * @return Un objeto DDeclaracionContribucionParafiscalEventoTO
	 */
	DDeclaracionContribucionParafiscalEventoTO getDeclaracionContribucionParafiscalEvento();

	/**
	 * Devuelve la colección de objetos DeclaracionContribucionParafiscalEvento que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalEventoTO
	 */
	Collection<DDeclaracionContribucionParafiscalEventoTO> getColeccionDeclaracionContribucionParafiscalEvento();
}
