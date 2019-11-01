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
 * <p>Descripcion: Objeto de acceso a datos para DeclaracionContribucionParafiscal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAODeclaracionContribucionParafiscal extends IDDAO {
	static final int CONSULTAR_POR_PK = 0;
	static final int CREAR = 1;
	static final int ACTUALIZAR = 2;
	static final int ELIMINAR = 3;
	static final int CONSULTA_GENERICA = 4;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	void inicializarConsultarPorPK(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal);

	/**
	 * Inicializa la creación de DeclaracionContribucionParafiscal.
	 * @param toDeclaracionContribucionParafiscal Objeto de Transporte de DeclaracionContribucionParafiscal
	 */
	void inicializarCrear(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal);

	/**
	 * Inicializa la actualización de DeclaracionContribucionParafiscal.
	 * @param toDeclaracionContribucionParafiscal Objeto de Transporte de DeclaracionContribucionParafiscal
	 */
	void inicializarActualizar(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal);

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	void inicializarEliminar(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal);

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscal.
	 * @param attDeclaracionContribucionParafiscal Atributos de DeclaracionContribucionParafiscal
	 */
	void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal);

	/**
	 * Devuelve el objeto DeclaracionContribucionParafiscal que se haya consultado.
	 * @return Un objeto DDeclaracionContribucionParafiscalTO
	 */
	DDeclaracionContribucionParafiscalTO getDeclaracionContribucionParafiscal();

	/**
	 * Devuelve la colección de objetos DeclaracionContribucionParafiscal que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalTO
	 */
	Collection<DDeclaracionContribucionParafiscalTO> getColeccionDeclaracionContribucionParafiscal();
}
