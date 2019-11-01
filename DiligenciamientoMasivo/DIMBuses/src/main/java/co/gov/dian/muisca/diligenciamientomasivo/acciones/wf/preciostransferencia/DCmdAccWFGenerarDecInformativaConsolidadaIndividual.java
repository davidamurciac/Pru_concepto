package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia;

import java.util.Collection;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.DMensajeWF;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.formato.DMapeoCasillaNegocioTO;
import co.gov.dian.muisca.gestionexpediente.comando.DComandoWF;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;

public class DCmdAccWFGenerarDecInformativaConsolidadaIndividual extends DComandoWF {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8549164964184489599L;

	protected static Logger LOGGER = Logger.getLogger(DCmdAccWFGenerarDecInformativaConsolidadaIndividual.class);
	
	protected  DDocumentoPKTO documentoCargaPK;
	protected  DAuditoriaRegistroPKTO auditoriaRegistro;
	protected  Integer anio;
	protected  Long nit;
	protected  Long idPersona;
	protected  IDDocumento documentoPT;
	protected  Collection<DMapeoCasillaNegocioTO> colMapeoCasillaNegocioPT;
	protected  Collection<DMapeoCasillaNegocioTO> colMapeoCasillaNegocioConsol;
	protected  Long montoTotalOperacionIngreso=0L;
	protected  Long montoTotalOperacionEgreso=0L;
	protected  Long aporteSociedadesExtranjeras=0L;
	protected  Long informacionAdicional=0L;
	protected  Long montoTotalPrincipalRec=0L;
	protected  Long montoTotalPrincipalPres=0L;
	protected  Boolean tieneErrorNoTransaccional = false;
	protected  Boolean esFraccionAnio = false;
	protected  Integer modoNegInicial;
	protected  Integer modoNegCorreccion;
	protected  IDFormato formato;
	protected  IDDocumento identDocumentoAnterior;
	protected  Boolean esCorreccion = false;
	protected  DSolicitudDeclaracionTO toSolicitudDeclaracion;
	protected DSolicitudIngresoTO solicitud;
	protected IDDocumento documentoCargadoSolic;
	//protected DAuditoriaRegistroTO toAudReg;
	protected Boolean cargaValida = true;
	protected Integer marca ;
	protected String mensaje;
	protected DRegistroRutTO registroRutTO;
	
	@Override
	protected void asignarWF(IDComando comando) {
		if(comando instanceof DCmdAccWFGenerarDecInformativaConsolidadaIndividual){
			DCmdAccWFGenerarDecInformativaConsolidadaIndividual copia = (DCmdAccWFGenerarDecInformativaConsolidadaIndividual)comando;
			copia.documentoCargaPK =documentoCargaPK;
			copia.auditoriaRegistro = auditoriaRegistro;
			copia.anio= anio;
			copia.nit = nit;
			copia.idPersona=idPersona;
			copia.documentoPT =documentoPT;
			copia.colMapeoCasillaNegocioPT = colMapeoCasillaNegocioPT;
			copia.colMapeoCasillaNegocioConsol = colMapeoCasillaNegocioConsol;
			copia.montoTotalOperacionIngreso = montoTotalOperacionIngreso;
			copia.montoTotalOperacionEgreso = montoTotalOperacionEgreso;
			copia.aporteSociedadesExtranjeras = aporteSociedadesExtranjeras;
			copia.informacionAdicional = informacionAdicional;
			copia.montoTotalPrincipalRec = montoTotalPrincipalRec;
			copia.montoTotalPrincipalPres = montoTotalPrincipalPres;
			copia.tieneErrorNoTransaccional = tieneErrorNoTransaccional;
			copia.esFraccionAnio = esFraccionAnio;
			copia.modoNegInicial = modoNegInicial;
			copia.modoNegCorreccion = modoNegCorreccion;
			copia.formato = formato;
			copia.identDocumentoAnterior = identDocumentoAnterior;
			copia.esCorreccion = esCorreccion;
			copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
			copia.cargaValida = cargaValida;
			copia.marca =marca;
			copia.mensaje=mensaje;
			copia.registroRutTO = registroRutTO;
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


	protected void cargarParametros() {
		
	}


	protected void ejecutarComandoWF() {
		
	}

	public Object clonar() {
		return new DCmdAccWFGenerarDecInformativaConsolidadaIndividual();
	}

	public boolean isAuditable() {
		return false;
	}

	public String getDescripcion() {
		return "comando de WF para registrar y generar documentos de Precios de Transferencia que llegan por carga masiva";
	}
}


