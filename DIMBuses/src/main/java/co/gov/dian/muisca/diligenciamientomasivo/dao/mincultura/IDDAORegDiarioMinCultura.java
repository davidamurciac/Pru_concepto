/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura;

import java.math.BigDecimal;
import java.util.*;

import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAORegDiarioMinCultura extends IDDAO {
	static final int CONSULTAR_POR_PK = 0;
	static final int CREAR = 1;	
	static final int ACTUALIZAR = 2;
	static final int ELIMINAR = 3;
	static final int CONSULTA_GENERICA = 4;
	static final int CREAR_CON_DETALLE = 5;
	
	//Secuencia
	static final String SEQ_REGISTROS_MINCULTURA = "DIL_REDM_SEQ";

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkRegDiarioMinCultura Llave primaria de RegDiarioMinCultura
	 */
	void inicializarConsultarPorPK(DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura);

	/**
	 * Inicializa la creación de RegDiarioMinCultura.
	 * @param toRegDiarioMinCultura Objeto de Transporte de RegDiarioMinCultura
	 */
	void inicializarCrear(DRegDiarioMinCulturaTO toRegDiarioMinCultura);


	/**
	 * Inicializa la actualización de RegDiarioMinCultura.
	 * @param toRegDiarioMinCultura Objeto de Transporte de RegDiarioMinCultura
	 */
	void inicializarActualizar(DRegDiarioMinCulturaTO toRegDiarioMinCultura);

	/**
	 * Inicializa la eliminación de RegDiarioMinCultura.
	 * @param pkRegDiarioMinCultura Llave primaria de RegDiarioMinCultura
	 */
	void inicializarEliminar(DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura);

	/**
	 * Inicializa la eliminación de RegDiarioMinCultura.
	 * @param attRegDiarioMinCultura Atributos de RegDiarioMinCultura
	 */
	void inicializarConsultaGenerica(DRegDiarioMinCulturaTO toRegDiarioMinCultura);

	/**
	 * Devuelve el objeto RegDiarioMinCultura que se haya consultado.
	 * @return Un objeto DRegDiarioMinCulturaTO
	 */
	DRegDiarioMinCulturaTO getRegDiarioMinCultura();

	/**
	 * Devuelve la colección de objetos RegDiarioMinCultura que se hayan consultado.
	 * @return Un Collection con objetos DRegDiarioMinCulturaTO
	 */
	Collection<DRegDiarioMinCulturaTO> getColeccionRegDiarioMinCultura();
	
	/**
	 * Retorna el número de secuencia de registro creado 
	 * @return Integer con el numero de secuencia para la tabla
	 */
	BigDecimal getNumSecuencia(); 
}
