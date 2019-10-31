package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.anexorenta;

import java.util.Collection;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.DMensajeWF;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.formato.DMapeoCasillaNegocioTO;
import co.gov.dian.muisca.gestionexpediente.comando.DComandoWF;

public class DCmdAccWFAnexoDeclaracionRenta extends DComandoWF {

	protected transient DDocumentoPKTO documentoCargaPK;
	protected static Logger LOGGER = Logger.getLogger(DCmdAccWFAnexoDeclaracionRenta.class);
	protected transient Boolean tieneErrorNoTransaccional = false;
	protected transient DSolicitudDeclaracionTO toSolicitudDeclaracion;
	protected transient Collection<DMapeoCasillaNegocioTO> colMapeoCasillaNegocioRenta;
	protected transient Collection<DMapeoCasillaNegocioTO> colMapeoCasillaNegocioAnexo;
	protected transient Integer anio;
	protected transient Long nit;
	protected transient Long idPersona;
	protected transient IDDocumento documentoRenta;
	protected transient Boolean generar110 = true ;
	protected transient Boolean correccion110 = false;
	protected transient Long declaRentaAsoc;
	protected transient Long docCorreccion110;
	protected transient Boolean esGranContribuyente=false;
	protected transient Boolean esFraccionAnio=false;
	protected transient Boolean esFraccionAnioValida = true;
	protected transient int modoNegInicial;
	protected transient int modoNegCorreccion;
	protected transient Boolean esObligado=false;
	protected transient DAuditoriaRegistroPKTO auditoriaRegistro;

	protected void asignarWF(IDComando comando) {
		if(comando instanceof DCmdAccWFAnexoDeclaracionRenta){
			DCmdAccWFAnexoDeclaracionRenta copia = (DCmdAccWFAnexoDeclaracionRenta)comando;
			copia.documentoCargaPK = documentoCargaPK;
			copia.tieneErrorNoTransaccional = tieneErrorNoTransaccional;
			copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
			copia.colMapeoCasillaNegocioRenta = colMapeoCasillaNegocioRenta;
			copia.colMapeoCasillaNegocioAnexo = colMapeoCasillaNegocioAnexo;
			copia.anio = anio;
			copia.nit = nit;
			copia.idPersona = idPersona;
			copia.documentoRenta = documentoRenta;
			copia.generar110 = generar110;
			copia.correccion110 = correccion110;
			copia.declaRentaAsoc = declaRentaAsoc;
			copia.docCorreccion110 = docCorreccion110;
			copia.esGranContribuyente =esGranContribuyente;
			copia.esFraccionAnio = esFraccionAnio;
			copia.modoNegInicial = modoNegInicial;
			copia.modoNegCorreccion = modoNegCorreccion;
			copia.esObligado = esObligado;
			copia.auditoriaRegistro = auditoriaRegistro;
		}		 
	}

	protected void cargarParametros() {
	
	}

	protected void ejecutarComandoWF() {
			
	}

	public Object clonar() {
		return new DCmdAccWFAnexoDeclaracionRenta();
	}

	public String getDescripcion() {
		return "comando de WF para registrar y generar documentos de renta que llegan por carga masiva";
	}

	public boolean isAuditable() {
		return false;
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

}
