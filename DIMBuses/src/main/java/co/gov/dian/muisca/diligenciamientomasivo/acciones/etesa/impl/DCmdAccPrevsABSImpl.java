package co.gov.dian.muisca.diligenciamientomasivo.acciones.etesa.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.etesa.DCmdAccPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.etesa.IDConstantesPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.etesa.DCmdSrvPrevsABS;


/**
 * Implementación de la lógica de las operaciones del comando de acción DCmdAccPrevsABS
 * 
 * @author nfontechar
 *
 */
public class DCmdAccPrevsABSImpl extends DCmdAccPrevsABS {

	
	
	
    protected void ejecutarComando(){
		
		try{
			switch(getTipoOperacion()){
			   case IDConstantesPrevsABS.CREAR:
			   case IDConstantesPrevsABS.ACTUALIZAR:
				   crearActualizar();
				   break;
			   case IDConstantesPrevsABS.ELIMINAR:
				   eliminar();
				   break;
			   case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
			   case IDConstantesPrevsABS.CONSULTAR_POR_PK:
			   case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
				   consultar();
				   break;
			}/*fin de switch*/
			isOk=true;
		}/*fin de try*/		
		catch(Exception exc){
			mensajeError=exc.getMessage();
			mensajeErrorDetallado=exc.getLocalizedMessage();
			isOk=false;
		}/*fin de catch*/
		
	}
	
	
	
	/*
	 * MÉTODOS PRIVADOS
	 */
	
	private void crearActualizar() throws DExcepcion{
		DCmdSrvPrevsABS srv=(DCmdSrvPrevsABS) getServicio("diligenciamientomasivo.etesa.DCmdSrvPrevsABS");
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CREAR:
			   srv.inicializarCrear(elTO);
			   break;
		   case IDConstantesPrevsABS.ACTUALIZAR:
			   srv.inicializarActualizar(elTO);
			   break;		   
		}/*fin de switch*/
		srv.ejecutar();
	}/*fin de crearActualizar*/
	
	private void consultar() throws DExcepcion{
		DCmdSrvPrevsABS srv=(DCmdSrvPrevsABS) getServicio("diligenciamientomasivo.etesa.DCmdSrvPrevsABS");
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
			   srv.inicializarConsultarPorNIT(laPK);
			   break;
		   case IDConstantesPrevsABS.CONSULTAR_POR_PK:
			   srv.inicializarConsultarPorPK(laPK);
			   break;		
		   case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
			   srv.inicializarConsultarPorSolicitudYExpediente(laPK);
			   break;
		}/*fin de switch*/
		srv.ejecutar();
		losTOs=srv.getLosTOs();
	}/*fin de consultar*/
	
	private void eliminar() throws DExcepcion{
		DCmdSrvPrevsABS srv=(DCmdSrvPrevsABS) getServicio("diligenciamientomasivo.etesa.DCmdSrvPrevsABS");
		srv.inicializarEliminar(laPK);
		srv.ejecutar();
	}/*fin de eliminar*/
	
	
}/*fin de class*/
