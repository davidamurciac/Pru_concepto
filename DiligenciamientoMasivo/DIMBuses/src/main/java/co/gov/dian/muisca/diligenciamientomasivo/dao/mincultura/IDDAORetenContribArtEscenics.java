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
 * <p>Descripcion: Objeto de acceso a datos para RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAORetenContribArtEscenics extends IDDAO {
	static final int CONSULTAR_POR_PK = 0;
	static final int CREAR = 1;
	static final int ACTUALIZAR = 2;
	static final int ELIMINAR = 3;
	static final int CONSULTA_GENERICA = 4;
	static final int CREAR_CON_DETALLE = 5;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkRetenContribArtEscenics Llave primaria de RetenContribArtEscenics
	 */
	void inicializarConsultarPorPK(DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics);

	/**
	 * Inicializa la creación de RetenContribArtEscenics.
	 * @param toRetenContribArtEscenics Objeto de Transporte de RetenContribArtEscenics
	 */
	void inicializarCrear(DRetenContribArtEscenicsTO toRetenContribArtEscenics);

	/**
	 * Inicializa la actualización de RetenContribArtEscenics.
	 * @param toRetenContribArtEscenics Objeto de Transporte de RetenContribArtEscenics
	 */
	void inicializarActualizar(DRetenContribArtEscenicsTO toRetenContribArtEscenics);

	/**
	 * Inicializa la eliminación de RetenContribArtEscenics.
	 * @param pkRetenContribArtEscenics Llave primaria de RetenContribArtEscenics
	 */
	void inicializarEliminar(DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics);

	/**
	 * Inicializa la eliminación de RetenContribArtEscenics.
	 * @param attRetenContribArtEscenics Atributos de RetenContribArtEscenics
	 */
	void inicializarConsultaGenerica(DRetenContribArtEscenicsTO toRetenContribArtEscenics);

	/**
	 * Devuelve el objeto RetenContribArtEscenics que se haya consultado.
	 * @return Un objeto DRetenContribArtEscenicsTO
	 */
	DRetenContribArtEscenicsTO getRetenContribArtEscenics();

	/**
	 * Devuelve la colección de objetos RetenContribArtEscenics que se hayan consultado.
	 * @return Un Collection con objetos DRetenContribArtEscenicsTO
	 */
	Collection<DRetenContribArtEscenicsTO> getColeccionRetenContribArtEscenics();
}
