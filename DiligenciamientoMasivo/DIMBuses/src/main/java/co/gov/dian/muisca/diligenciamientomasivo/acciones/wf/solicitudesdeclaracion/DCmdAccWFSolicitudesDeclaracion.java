package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.solicitudesdeclaracion;

import java.util.Collection;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.DMensajeWF;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.gestionexpediente.comando.DComandoWF;

public class DCmdAccWFSolicitudesDeclaracion extends DComandoWF {
	protected transient DDocumentoPKTO documentoCargaPK;
	protected static Logger LOGGER = Logger.getLogger(DCmdAccWFSolicitudesDeclaracion.class);
	protected transient Boolean tieneErrorNoTransaccional = false;
	protected transient DSolicitudDeclaracionTO toSolicitudDeclaracion;
	 protected void asignarWF(IDComando comando) {
		 if(comando instanceof DCmdAccWFSolicitudesDeclaracion){
			 DCmdAccWFSolicitudesDeclaracion copia = (DCmdAccWFSolicitudesDeclaracion)comando;
			 copia.documentoCargaPK = documentoCargaPK;
			 copia.tieneErrorNoTransaccional = tieneErrorNoTransaccional;
			 copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
	}		 
}


	public String getSalidaEjecucionActiva() {
		return "";
	}


	public Collection getSalidasEjecucion() {
		return null;
	}


	public void inicializarOnEvento(DMensajeWF arg0) {
	}


	public boolean validar() throws DValidarExcepcion {
		return true;
	}


	@Override
	protected void cargarParametros() {
	}


	@Override
	protected void ejecutarComandoWF() {
	}


	@Override
	public Object clonar() {
		return new DCmdAccWFSolicitudesDeclaracion();
	}


	@Override
	public String getDescripcion() {
		return "comando de WF para registrar documentos que llegan por carga masiva";
	}


	@Override
	public boolean isAuditable() {
		return false;
	}
}