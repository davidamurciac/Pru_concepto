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
 * <p>Descripcion: Objeto de acceso a datos para DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAODeclaracionContribucionParafiscalPatrocinador extends IDDAO {
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
	 * @param pkDeclaracionContribucionParafiscalPatrocinador Llave primaria de DeclaracionContribucionParafiscalPatrocinador
	 */
	void inicializarConsultarPorPK(DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador);

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
	 * Inicializa la creación de DeclaracionContribucionParafiscalPatrocinador.
	 * @param toDeclaracionContribucionParafiscalPatrocinador Objeto de Transporte de DeclaracionContribucionParafiscalPatrocinador
	 */
	void inicializarCrear(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador);
	
	/**
	 * Inicializa la creación de DeclaracionContribucionParafiscalPatrocinador con colección.
	 * @param tosDeclaracionContribucionParafiscalPatrocinador Collection de Objetos de Transporte de DeclaracionContribucionParafiscalPatrocinador
	 */
	void inicializarCrear(Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> tosDeclaracionContribucionParafiscalPatrocinador);

	/**
	 * Inicializa la actualización de DeclaracionContribucionParafiscalPatrocinador.
	 * @param toDeclaracionContribucionParafiscalPatrocinador Objeto de Transporte de DeclaracionContribucionParafiscalPatrocinador
	 */
	void inicializarActualizar(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador);

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscalPatrocinador.
	 * @param pkDeclaracionContribucionParafiscalPatrocinador Llave primaria de DeclaracionContribucionParafiscalPatrocinador
	 */
	void inicializarEliminar(DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador);

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscalPatrocinador.
	 * @param attDeclaracionContribucionParafiscalPatrocinador Atributos de DeclaracionContribucionParafiscalPatrocinador
	 */
	void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador);

	/**
	 * Devuelve el objeto DeclaracionContribucionParafiscalPatrocinador que se haya consultado.
	 * @return Un objeto DDeclaracionContribucionParafiscalPatrocinadorTO
	 */
	DDeclaracionContribucionParafiscalPatrocinadorTO getDeclaracionContribucionParafiscalPatrocinador();

	/**
	 * Devuelve la colección de objetos DeclaracionContribucionParafiscalPatrocinador que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalPatrocinadorTO
	 */
	Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> getColeccionDeclaracionContribucionParafiscalPatrocinador();
}
