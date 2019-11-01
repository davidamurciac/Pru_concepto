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
 * <p>Descripcion: Objeto de acceso a datos para DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAODetRetContrArtEscenics extends IDDAO {
	static final int CONSULTAR_POR_PK = 0;
	static final int CONSULTAR_POR_RETENCONTRIBARTESCENICS = 1;
	static final int CREAR = 2;
	static final int ACTUALIZAR = 3;
	static final int ELIMINAR = 4;
	static final int CONSULTA_GENERICA = 5;
	static final int CREAR_EN_BATCH = 6;
	static final int ELIMINAR_EN_BATCH = 7;
	static final int CONSULTAR_POR_DOCUMENTO_CARGA = 8;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDetRetContrArtEscenics Llave primaria de DetRetContrArtEscenics
	 */
	void inicializarConsultarPorPK(DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics);

	/**
	 * Inicializa la consulta por RetenContribArtEscenics.
	 * @param pkRetenContribArtEscenics Llave primaria de RetenContribArtEscenics
	 */
	void inicializarConsultarPorRetenContribArtEscenics(DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics);
	
	
	/**
	 * Inicializa la consulta por documento carga.
	 * @param ideDocumentoCarga identificador de documento de carga
	 * @param numRepeticionCarga numero de repetición de documento de carga
	 */
	void inicializarConsultarPorDocumentoCarga(Long ideDocumentoCarga, Integer numRepeticionCarga);

	/**
	 * Inicializa la creación de DetRetContrArtEscenics.
	 * @param toDetRetContrArtEscenics Objeto de Transporte de DetRetContrArtEscenics
	 */
	void inicializarCrear(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics);
	
	/**
	 * Inicializa la creación en batch de DetRetContrArtEscenics.
	 * @param Collection<DDetRetContrArtEscenicsTO> lista de Objetos de Transporte de DetRetContrArtEscenics
	 */
	void inicializarCrearEnBatch(Collection<DDetRetContrArtEscenicsTO> objetosDetRetContrArtEscenics);

	/**
	 * Inicializa la actualización de DetRetContrArtEscenics.
	 * @param toDetRetContrArtEscenics Objeto de Transporte de DetRetContrArtEscenics
	 */
	void inicializarActualizar(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics);

	/**
	 * Inicializa la eliminación de DetRetContrArtEscenics.
	 * @param pkDetRetContrArtEscenics Llave primaria de DetRetContrArtEscenics
	 */
	void inicializarEliminar(DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics);
	
	/**
	 * Inicializa la eliminación en batch de DetRetContrArtEscenics.
	 * @param Collection<DDetRetContrArtEscenicsPKTO> lista de Objetos pk de DetRetContrArtEscenics
	 */
	void inicializarEliminarEnBatch(Collection<DDetRetContrArtEscenicsPKTO> objetosPkDetRetContrArtEscenics);

	/**
	 * Inicializa la eliminación de DetRetContrArtEscenics.
	 * @param attDetRetContrArtEscenics Atributos de DetRetContrArtEscenics
	 */
	void inicializarConsultaGenerica(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics);

	/**
	 * Devuelve el objeto DetRetContrArtEscenics que se haya consultado.
	 * @return Un objeto DDetRetContrArtEscenicsTO
	 */
	DDetRetContrArtEscenicsTO getDetRetContrArtEscenics();

	/**
	 * Devuelve la colección de objetos DetRetContrArtEscenics que se hayan consultado.
	 * @return Un Collection con objetos DDetRetContrArtEscenicsTO
	 */
	Collection<DDetRetContrArtEscenicsTO> getColeccionDetRetContrArtEscenics();
	
	/**
	 * Devuelve la colección de Pks de DetRetContrArtEscenics que se hayan consultado.
	 * @return Un Collection con objetos DDetRetContrArtEscenicsPKTO
	 */
	Collection<DDetRetContrArtEscenicsPKTO> getColeccionPkDetRetContrArtEscenics();
}