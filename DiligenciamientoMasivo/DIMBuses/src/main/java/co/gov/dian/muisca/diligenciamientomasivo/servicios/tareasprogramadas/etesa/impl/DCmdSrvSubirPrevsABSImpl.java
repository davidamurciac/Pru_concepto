package co.gov.dian.muisca.diligenciamientomasivo.servicios.tareasprogramadas.etesa.impl;

import java.util.Calendar;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.bandejasalida.general.to.DBandejaSalidaTO;
import co.gov.dian.muisca.bandejasalida.general.to.DSolicitudSalidaTO;
import co.gov.dian.muisca.bandejasalida.servicios.DCmdSrvConsLstBandejaSalida;
import co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.etesa.IDConstantesPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.etesa.DCmdSrvPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.tareasprogramadas.etesa.DCmdSrvSubirPrevsABS;
import co.gov.dian.muisca.entradasalida.servicios.bandejasalida.DCmdSrvEnviarArchivosABandejaSalida;



/**
 * Implementación de la lógica de las operaciones del comando de servicio DCmdSrvSubirPrevsABSImpl
 * 
 * @author nfontechar
 *
 */
public class DCmdSrvSubirPrevsABSImpl extends DCmdSrvSubirPrevsABS {
	
	private static Logger log=Logger.getLogger(DCmdSrvSubirPrevsABSImpl.class);

	

	protected void ejecutarComando() {
		try{
			switch(getTipoOperacion()){
			   case SUBIR_PREV:
				   subirPrevsABandejaDeSalida();
				   break;
			}/*fin de switch*/			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	
	private void subirPrevsABandejaDeSalida() throws Exception {
		DCmdSrvEnviarArchivosABandejaSalida srvEnviarArchivosABandejaSalida = (DCmdSrvEnviarArchivosABandejaSalida) getServicio("entradasalida.bandejasalida.DCmdSrvEnviarArchivosABandejaSalida");		
        srvEnviarArchivosABandejaSalida.inicializar(elPrev.getContenidoArchivo(),elPrev.getNombreArchivo(),IDConstantesPrevsABS.NUM_ARCHIVOS,elPrev.getExtensionArchivo(),elPrev.getPk());
        srvEnviarArchivosABandejaSalida.ejecutar();
        DSolicitudSalidaTO salida=srvEnviarArchivosABandejaSalida.getSolicitudSalidaTO();
        Long exp=srvEnviarArchivosABandejaSalida.getIdeExpediente();
        Long sol=salida.getPK().getIdeSolicitud();
        DCmdSrvConsLstBandejaSalida consBS=(DCmdSrvConsLstBandejaSalida) getServicio("bandejasalida.DCmdSrvConsLstBandejaSalida");
        consBS.inicializar(sol);
        consBS.ejecutar();
        Collection col=consBS.getColeccionBandejaSalida();
        Integer ideRecurso=null;
        if(col!=null){
        	Iterator iter=col.iterator();
        	while(iter.hasNext()){
        		DBandejaSalidaTO aux=(DBandejaSalidaTO) iter.next();
        		ideRecurso=aux.getPK().getIdeRecurso();
        		break;
        	}/*fin de while*/
        }/*fin de if*/
        DPrevalidadorABSPKTO miPK=new DPrevalidadorABSPKTO();
        miPK.setIdeExpediente(exp);
        miPK.setIdeSolicitud(sol);
        miPK.setNumNIT(new Long(elPrev.getNumNIT()));
        DPrevalidadorABSAttTO miAtt=new DPrevalidadorABSAttTO();
        
        miAtt.setFecGeneracion(obtenerFecGeneracion());
        miAtt.setIdeRecurso(ideRecurso);
        miAtt.setIdeUsuarioCambio(getContexto().getContextoSeguridad().getIdeUsuario());
        miAtt.setTipoArchivo(IDConstantesPrevsABS.TIPO_ARCHIVO);
        DPrevalidadorABSTO miTO=new DPrevalidadorABSTO(miPK,miAtt);
        DCmdSrvPrevsABS misrv=(DCmdSrvPrevsABS) getServicio("diligenciamientomasivo.etesa.DCmdSrvPrevsABS");
        misrv.inicializarCrear(miTO);
        misrv.ejecutar();
	}/*fin de subirPrevsABandejaDeSalida*/
	
	
	private Date obtenerFecGeneracion(){
		Calendar cal=Calendar.getInstance();
		Date fecha=new Date(cal.getTimeInMillis());
		return fecha;
	}
	
	

}/*fin de class*/
