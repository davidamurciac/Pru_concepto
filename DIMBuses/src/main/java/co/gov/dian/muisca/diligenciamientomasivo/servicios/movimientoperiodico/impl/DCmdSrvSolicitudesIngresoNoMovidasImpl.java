package co.gov.dian.muisca.diligenciamientomasivo.servicios.movimientoperiodico.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.cargamasiva.servicios.DCmdSrvMoverDocsAuditoriaRegistros;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.movimientoperiodico.IDDAOMovimientoPeriodico;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.movimientoperiodico.DCmdSrvSolicitudesIngresoNoMovidas;




/****
 * Implementación del servicio que permite mover a los repositorios definitivos, solicitudes de ingreso que presentaron errores de homologación, pero que fueron 
 * tenidas en cuenta como válidas 
 * 
 * @author nfontechar
 *
 */
public class DCmdSrvSolicitudesIngresoNoMovidasImpl extends DCmdSrvSolicitudesIngresoNoMovidas {
	
	/*
	 * CONSTANTES
	 */
	private final static Logger logger = Logger.getLogger(DCmdSrvSolicitudesIngresoNoMovidasImpl.class);
	
	
	
	
	/*
	 * MÉTODO BASE
	 */
	public void ejecutarComando()throws DExcepcion{
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try{
			switch(getTipoOperacion()){
			   case CONSULTAR_SOLICITUDES_NO_MOVIDAS:
				   consultarSolicitudesNoMovidas(admin);
				   break;			   
			   case CONSULTAR_LOG_MARCAS_DOCS_SOLICITUD:
				   consultarLogMarcasDocSolicitud(admin);
				   break;
			   case CONSULTAR_REGISTROS_PENDIENTES:
				   consultarRegistrosPendientes(admin);
				   break;
			}/*fin de switch*/
		}
		catch(DExcepcion exc){
			logger.error("\n\n*******************\n*\n*  E  R  R  O  R  *\n*\n*******************\n\nDCmdSrvSolicitudesIngresoNoMovidasImpl.ejecutarComando(): Se ha producido un error ejecutando el comando.\nDetalles: "+exc.getMensajeDetallado());
			logger.error("\n\n\nLa traza de la excepción es:\n");
			exc.printStackTrace();
			throw exc;
		}
		finally {
            admin.cerrarSesion();
        }
	}/*fin de ejecutarComando*/
	
	
	
	
	/*
	 * M{ETODOS PRIVADOS
	 */
	
	
	/**
	 * Método que consulta y obtiene el log de marcas de las solicitudes que no han podido ser movidas 
	 * 
	 * @param admin Objeto de tipo IDAdminPersistencia que ejecutará las consultas del DAO correspondiente.
	 * @throws DExcepcion Si la consulta de log de marcas falla por alguna razón.
	 */
	private void consultarLogMarcasDocSolicitud(IDAdminPersistencia admin) throws DExcepcion{
		IDDAOFactoryDiligenciamientoMasivo fabrica=new DDAOFactoryDiligenciamientoMasivo();
		IDDAOMovimientoPeriodico dao=fabrica.getDaoMovimientoPeriodico();
		dao.inicializarConsultarLogMarcasDocSolictud(to);
		admin.buscar(dao);
		marcasLogDoc=dao.getMarcasDoc();
	}/*fin de consultarLogMarcasDocSolicitud*/
	
	
	
	/****
	 * Método que consulta y obtiene las solicitudes de ingreso que aún no han podido ser movidas
	 * 
	 * @param admin Objeto de tipo IDAdminPersistencia que ejecutará las consultas del DAO correspondiente.
	 * @throws DExcepcion Si la consulta de solicitudes no movidas falla por alguna razón.
	 */
	private void consultarSolicitudesNoMovidas(IDAdminPersistencia admin) throws DExcepcion{
		IDDAOFactoryDiligenciamientoMasivo fabrica=new DDAOFactoryDiligenciamientoMasivo();
		IDDAOMovimientoPeriodico dao=fabrica.getDaoMovimientoPeriodico();
		dao.inicializarConsultarSolicitudesNoMovidas(to);
		admin.buscar(dao);
		solicitudesNoMovidas=dao.getSolicitudesNoMovidas();
	}/*fin de consultarSolicitudesNoMovidas*/
	
	
	
	/****
	 * Método que consulta y obtiene el número de registros pendientes relacionados con las solicitudes de ingreso que aún no han podido ser movidas
	 * 
	 * @param admin Objeto de tipo IDAdminPersistencia que ejecutará las consultas del DAO correspondiente.
	 * @throws DExcepcion Si la consulta de solicitudes no movidas falla por alguna razón.
	 */
	private void consultarRegistrosPendientes(IDAdminPersistencia admin) throws DExcepcion{
		IDDAOFactoryDiligenciamientoMasivo fabrica=new DDAOFactoryDiligenciamientoMasivo();
		IDDAOMovimientoPeriodico dao=fabrica.getDaoMovimientoPeriodico();
		String tipoConsulta=to.getConteo();
		if(tipoConsulta!=null){
			if(tipoConsulta.equals("M")){
				dao.inicializarConsultarMarcasPorQuitar();
			}/*fin de if*/
			if(tipoConsulta.equals("S")){
				dao.inicializarConsultarRegistrosPorMover();
			}/*fin de if*/
			admin.buscar(dao);
			to=dao.getTo();
		}/*fin de if*/
		
	}/*fin de consultarSolicitudesNoMovidas*/	
	
	 

}/*fin de class*/
