/**
 * 
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.helpers;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicio;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoAttTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvActSolicitudIngreso;
import co.gov.dian.muisca.diligenciamientomasivo.buses.DBusServiciosDelegateDIMBTxNueva;

/**
 * Clase que representa el helper de Diligenciamiento Masivo, para manejo de estados de auditoría en el procesamiento de una solicitud.
 *
 * @author nfontechar 
 */
public class DProcesamientoFlujoPosteriorDMHelper {
	
	/**
	 * Atributo de tipo <code>DContextoSeguridad</code> que maneja los datos del autenticado en la sesión.
	 */
	private DContextoSeguridad contexto=null;
	
	
	/**
	 * Construye un objeto de tipo <code>DProcesamientoFlujoPosteriorDMHelper.java</code> con valores recibidos por parámetro.
	 * 
	 * @param contexto Dato de tipo <code>DContextoSeguridad</code> con los datos del contexto.
	 */
	public DProcesamientoFlujoPosteriorDMHelper(DContextoSeguridad contexto) {
		this.contexto = contexto;
	}
	
	
	

	/**
	 * Obtiene un comando de servicio para una transacción nueva.
	 * 
	 * @param nombreServicio Dato de tipo <code>String</code> con el nombre del comando a cargar.
	 * @return Dato de tipo <code>DComandoServicio</code> con el comando solicitado.
	 * @throws DExcepcion Si el proceso falla por algún motivo.
	 */
	protected DComandoServicio getServicioTrxNueva(String nombreServicio) throws DExcepcion{
		try{
			DBusServiciosDelegateDIMBTxNueva busServicios = new DBusServiciosDelegateDIMBTxNueva();
			busServicios.setContextoSeguridad(contexto);
			busServicios.setTransaccional(true);
			return (DComandoServicio) busServicios.getComando(nombreServicio);
		}catch( Exception e ){
			throw new DExcepcion(e);
		}
	}
	
	/**
	 * Actualiza la solicitud con un estado determinado.
	 * 
	 * @param ideSolicitud Dato de tipo <code>Long</code> con el número de la solicitud a actualizar.
	 * @param estado Dato de tipo <code>String</code> con el estado a asignar a la solicitud.
	 * @throws DExcepcion Si el proceso falla por algún motivo.
	 */
	protected void actualizarSolicitud(Long ideSolicitud,String estado) throws DExcepcion{
		DSolicitudIngresoTO solicitud = new DSolicitudIngresoTO(new DSolicitudIngresoPKTO(ideSolicitud), new DSolicitudIngresoAttTO());
		solicitud.getSolicitudAtt().setCodEstado(estado);
		DCmdSrvActSolicitudIngreso actSolicitudIngreso = (DCmdSrvActSolicitudIngreso) getServicioTrxNueva("cargamasiva.procesamiento.DCmdSrvActSolicitudIngreso");		
		actSolicitudIngreso.inicializarActualizarEstado(solicitud);
		actSolicitudIngreso.ejecutar();
	}
	
		
	/**
	 * Finaliza el procesamiento de una solicitud asignando un estado determinado.
	 * 
	 * @param ideSolicitud Dato de tipo <code>Long</code> con el número de la solicitud en proceso.
	 * @param estadoProceso Dato de tipo <code>Integer</code> con el estado a asignar.
	 * @throws DExcepcion Si el proceso falla por algún motivo.
	 */
	public void finalizarProcesoFlujoPosterior(Long ideSolicitud, Integer estadoProceso) throws DExcepcion{
		actualizarSolicitud(ideSolicitud,estadoProceso.toString());
	}

	
	
}/*fin de class*/
