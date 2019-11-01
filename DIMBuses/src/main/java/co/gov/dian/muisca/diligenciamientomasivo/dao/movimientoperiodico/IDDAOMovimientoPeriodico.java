package co.gov.dian.muisca.diligenciamientomasivo.dao.movimientoperiodico;

import java.util.List;

import co.gov.dian.muisca.arquitectura.interfaces.IDDAO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DMarcaDocumentoTO;




/****
 * Interface de opciones que es implementada por la clase DDAOMovimientoPeriodico
 * 
 * @author nfontechar
 *
 */
public interface IDDAOMovimientoPeriodico extends IDDAO {
	
	
	/*
	 * CONSTANTES DE OPERACIÓN
	 */
	
	/***
	 * Constante de operación CONSULTA DE SOLICTUDES NO MOVIDAS
	 */
	public static final int CONSULTAR_SOLICITUDES_NO_MOVIDAS=1;
	
	
	
	/***
	 * Constante de operación CONSULTA DE LOG DE MARCAS
	 */
	public static final int CONSULTAR_LOG_MARCAS_DOCS_SOLICITUD=2;
	
	
	/***
	 * Constante de operación CONSULTA DE SOLICTUDES NO MOVIDAS
	 */
	public static final int CONSULTAR_REGISTROS_PENDIENTES=3;
	
	
	/***
	 * Constante de operación CONSULTA DE SOLICTUDES NO MOVIDAS
	 */
	public static final int CONSULTAR_MARCAS_PENDIENTES=4;
	
	
	
	
	/****
	 * Inicializa el DAO para consulta de las solicitudes que figuran en el sistema como no movidas
	 */
	void inicializarConsultarSolicitudesNoMovidas(DSolicitudIngresoNoMovidaTO to);
	
	
	
	
	/*****
	 * Inicializa el DAO para consulta del log de marcas de los documentos contenidos en las solicitudes no movidas
	 */
	void inicializarConsultarLogMarcasDocSolictud(DSolicitudIngresoNoMovidaTO to);
		
	
	
	/****
	 * Inicializa el DAO para consulta del número de registros pendientes por mover
	 */
	public void inicializarConsultarRegistrosPorMover();
	
	
	/****
	 * Inicializa el DAO para consulta del número de marcas pendientes por borrar
	 */
	public void inicializarConsultarMarcasPorQuitar();
	
	
	
	
	/***
	 * Devuelve el valor del atributo solicitudesNoMovidas.
	 * 
	 * @return solicitudesNoMovidas
	 */
	List<DSolicitudIngresoNoMovidaTO> getSolicitudesNoMovidas();
	
	
	
	
	/****
	 * Devuelve el valor del atributo marcasDoc.
	 * 
	 * @return marcasDoc
	 */
	List<DMarcaDocumentoTO> getMarcasDoc();
	
	
	/**
	 * Devuelve el valor del atributo to
	 * 	
	 * @return to
	 */
	public DSolicitudIngresoNoMovidaTO getTo();
	
	

}/*fin de interface*/
