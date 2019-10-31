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
 * <p>Descripcion: Objeto de acceso a datos para DetRegDiaMincultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAODetRegDiaMincultura extends IDDAO {
	static final int CONSULTAR_POR_PK = 0;
	static final int CONSULTAR_POR_REGDIARIOMINCULTURA = 1;
	static final int CREAR = 2;
	static final int ACTUALIZAR = 3;
	static final int ELIMINAR = 4;
	static final int CONSULTA_GENERICA = 5;
	
	//Secuencia
	static final String SEQ_DETALLERS_REGISTROS_MINCULTURA = "DIL_DRDM_SEQ";

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDetRegDiaMincultura Llave primaria de DetRegDiaMincultura
	 */
	void inicializarConsultarPorPK(DDetRegDiaMinculturaPKTO pkDetRegDiaMincultura);

	/**
	 * Inicializa la consulta por RegDiarioMinCultura.
	 * @param pkRegDiarioMinCultura Llave primaria de RegDiarioMinCultura
	 */
	void inicializarConsultarPorRegDiarioMinCultura(DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura);

	/**
	 * Inicializa la creación de DetRegDiaMincultura.
	 * @param toDetRegDiaMincultura Objeto de Transporte de DetRegDiaMincultura
	 */
	void inicializarCrear(DDetRegDiaMinculturaTO toDetRegDiaMincultura);

	/**
	 * Inicializa la actualización de DetRegDiaMincultura.
	 * @param toDetRegDiaMincultura Objeto de Transporte de DetRegDiaMincultura
	 */
	void inicializarActualizar(DDetRegDiaMinculturaTO toDetRegDiaMincultura);

	/**
	 * Inicializa la eliminación de DetRegDiaMincultura.
	 * @param pkDetRegDiaMincultura Llave primaria de DetRegDiaMincultura
	 */
	void inicializarEliminar(DDetRegDiaMinculturaPKTO pkDetRegDiaMincultura);

	/**
	 * Inicializa la eliminación de DetRegDiaMincultura.
	 * @param attDetRegDiaMincultura Atributos de DetRegDiaMincultura
	 */
	void inicializarConsultaGenerica(DDetRegDiaMinculturaTO toDetRegDiaMincultura);

	/**
	 * Devuelve el objeto DetRegDiaMincultura que se haya consultado.
	 * @return Un objeto DDetRegDiaMinculturaTO
	 */
	DDetRegDiaMinculturaTO getDetRegDiaMincultura();

	/**
	 * Devuelve la colección de objetos DetRegDiaMincultura que se hayan consultado.
	 * @return Un Collection con objetos DDetRegDiaMinculturaTO
	 */
	Collection<DDetRegDiaMinculturaTO> getColeccionDetRegDiaMincultura();
}
