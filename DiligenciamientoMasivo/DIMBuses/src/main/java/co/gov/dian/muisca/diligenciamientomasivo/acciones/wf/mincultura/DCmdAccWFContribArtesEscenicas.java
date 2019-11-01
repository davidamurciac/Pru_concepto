package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.mincultura;

import java.util.Collection;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.DMensajeWF;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.anexorenta.DCmdAccWFAnexoDeclaracionRenta;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDeclaracionContribucionParafiscalTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.formato.DMapeoCasillaNegocioTO;
import co.gov.dian.muisca.gestionexpediente.comando.DComandoWF;

public class DCmdAccWFContribArtesEscenicas extends DComandoWF {
	
	protected static Logger LOGGER = Logger.getLogger(DCmdAccWFContribArtesEscenicas.class);
	protected transient DDocumentoPKTO documentoCargaPK;
	protected transient Boolean tieneErrorNoTransaccional = false;
	protected transient DAuditoriaRegistroPKTO auditoriaRegistro;
	protected transient Integer anio;
	protected transient Integer periodo;
	protected transient Long nit;
	protected transient Integer tipoDecla;
	protected transient Long idPersona;
	protected transient IDDocumento documentoRetencion;
	protected transient DSolicitudDeclaracionTO toSolicitudDeclaracion;
	
	protected transient Collection<DMapeoCasillaNegocioTO> colMapeoCasillaNegocioRetencion;
	protected transient Collection<DMapeoCasillaNegocioTO> colMapeoCasillaNegocioAnexoRte;
	protected transient Boolean correccion = false;
	protected transient Boolean esObligado=false;
	protected transient Long docCorreccion480;
	
	protected transient DDeclaracionContribucionParafiscalTO declaracionContribParafTO;

	@Override
	protected void asignarWF(IDComando comando) {
		if(comando instanceof DCmdAccWFContribArtesEscenicas){
			DCmdAccWFContribArtesEscenicas copia = (DCmdAccWFContribArtesEscenicas)comando;
			copia.documentoCargaPK = documentoCargaPK;
			copia.tieneErrorNoTransaccional = tieneErrorNoTransaccional;
			copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
			copia.colMapeoCasillaNegocioRetencion = colMapeoCasillaNegocioRetencion;
			copia.colMapeoCasillaNegocioAnexoRte = colMapeoCasillaNegocioAnexoRte;
			copia.anio = anio;
			copia.nit = nit;
			copia.idPersona = idPersona;
			copia.documentoRetencion = documentoRetencion;
			copia.correccion = correccion;
			copia.docCorreccion480 = docCorreccion480;			
			copia.esObligado = esObligado;
			copia.auditoriaRegistro = auditoriaRegistro;
			copia.tipoDecla = tipoDecla;
			copia.declaracionContribParafTO = declaracionContribParafTO;
			copia.periodo = periodo;
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
		return new DCmdAccWFContribArtesEscenicas();
	}

	@Override
	public boolean isAuditable() {
		return false;
	}

	@Override
	public String getDescripcion() {
		return "comando de WF para registrar y generar documentos de formato 480 que llegan por carga masiva";
	}

}
