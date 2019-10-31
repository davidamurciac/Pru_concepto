package co.gov.dian.muisca.diligenciamientomasivo.servicios.etesa.impl;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.etesa.IDConstantesPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.etesa.IDDAOPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.etesa.DCmdSrvPrevsABS;



/**
 * Implementación de la lógica de las operaciones del comando de servicio DCmdSrvPrevsABS
 * 
 * @author nfontechar
 *
 */
public class DCmdSrvPrevsABSImpl extends DCmdSrvPrevsABS {

	
	private static Logger log=Logger.getLogger(DCmdSrvPrevsABSImpl.class);
	
	protected void ejecutarComando() throws DExcepcion {
		
		IDAdminPersistencia admin=getAdministradorPersistencia();
		try{
			switch(getTipoOperacion()){
			   case IDConstantesPrevsABS.CREAR:
			   case IDConstantesPrevsABS.ACTUALIZAR:
				   crearActualizar(admin);
				   break;
			   case IDConstantesPrevsABS.ELIMINAR:
				   eliminar(admin);
				   break;
			   case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
			   case IDConstantesPrevsABS.CONSULTAR_POR_PK:
			   case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
				   consultar(admin);
				   break;
			}/*fin de switch*/
		}/*fin de try*/
		catch(DExcepcion exc){
			log.error("Se progujo un error registrando prevalidador en la Bandeja de Salida: "+exc.getMensajeDetallado());
			exc.printStackTrace();
		}/*fin de catch*/
		finally{
			admin.cerrarSesion();
		}/*fin de finally*/
	}
	
	
	
	/*
	 * MÉTODOS PRIVADOS
	 */
	
	private void crearActualizar(IDAdminPersistencia admin) throws DExcepcion{
		IDDAOFactoryDiligenciamientoMasivo fabrica=new DDAOFactoryDiligenciamientoMasivo();
		IDDAOPrevsABS dao=fabrica.getDaoPrevsABS();
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CREAR:
			   dao.inicializarCrear(elTO);
			   break;
		   case IDConstantesPrevsABS.ACTUALIZAR:
			   dao.inicializarActualizar(elTO);
			   break;		   
		}/*fin de switch*/
		admin.guardar(dao);
	}/*fin de crearActualizar*/
	
	private void consultar(IDAdminPersistencia admin) throws DExcepcion{
		IDDAOFactoryDiligenciamientoMasivo fabrica=new DDAOFactoryDiligenciamientoMasivo();
		IDDAOPrevsABS dao=fabrica.getDaoPrevsABS();
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
			   dao.inicializarConsultarPorNIT(laPK);
			   break;
		   case IDConstantesPrevsABS.CONSULTAR_POR_PK:
			   dao.inicializarConsultarPorPK(laPK);
			   break;		
		   case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
			   dao.inicializarConsultarPorSolicitudYExpediente(laPK);
			   break;
		}/*fin de switch*/
		admin.buscar(dao);
		losTOs=dao.getLosTOs();
	}/*fin de consultar*/
	
	private void eliminar(IDAdminPersistencia admin) throws DExcepcion{
		IDDAOFactoryDiligenciamientoMasivo fabrica=new DDAOFactoryDiligenciamientoMasivo();
		IDDAOPrevsABS dao=fabrica.getDaoPrevsABS();
		dao.inicializarEliminar(laPK);
		admin.eliminar(dao);
	}/*fin de eliminar*/
	

}/*fin de class*/
