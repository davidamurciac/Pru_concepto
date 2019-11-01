package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.mincultura;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.DMensajeWF;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRetContrArtEscenicsTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRetenContribArtEscenicsTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.formato.DMapeoCasillaNegocioTO;
import co.gov.dian.muisca.gestionexpediente.comando.DComandoWF;

public class DCmdAccWFRteContibArtesEscenicas extends DComandoWF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6829492723293846841L;
	protected static Logger LOGGER = Logger
			.getLogger(DCmdAccWFRteContibArtesEscenicas.class);
	protected transient DDocumentoPKTO documentoCargaPK;
	protected transient Boolean tieneErrorNoTransaccional = false;
	protected transient DAuditoriaRegistroPKTO auditoriaRegistro;
	protected transient Integer anio;
	protected transient Long nit;
	protected transient Integer periodo;
	protected transient Integer tipoDecla;
	protected transient Long idPersona;
	protected transient IDDocumento documentoRetencion;
	protected transient DRetenContribArtEscenicsTO toRetenContribArtEscenics;
	protected transient DSolicitudDeclaracionTO toSolicitudDeclaracion;

	protected transient Collection<DMapeoCasillaNegocioTO> colMapeoCasillaNegocioRetencion;
	protected transient Collection<DMapeoCasillaNegocioTO> colMapeoCasillaNegocioAnexoRte;
	protected transient List<DDetRetContrArtEscenicsTO> lstDetRetContrArtEscenics;
	protected transient Boolean correccion = false;
	protected transient Boolean esObligado = false;
	protected transient Long docCorreccion470;
	protected final Integer IDE_FORMATO_RETEN_CONTRIB_ESPECTAC_PUBLICOS_ARTES_ESCENICAS = 2184;
	protected final Integer NUM_VERSION_FORMATO_RETEN_CONTRIB_ESPECTAC_PUBLICOS_ARTES_ESCENICAS = 2;

	@Override
	protected void asignarWF(IDComando comando) {
		if (comando instanceof DCmdAccWFRteContibArtesEscenicas) {
			final DCmdAccWFRteContibArtesEscenicas copia = (DCmdAccWFRteContibArtesEscenicas) comando;
			copia.documentoCargaPK = documentoCargaPK;
			copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
			copia.tieneErrorNoTransaccional = tieneErrorNoTransaccional;
			copia.toRetenContribArtEscenics = toRetenContribArtEscenics;
			copia.colMapeoCasillaNegocioRetencion = colMapeoCasillaNegocioRetencion;
			copia.colMapeoCasillaNegocioAnexoRte = colMapeoCasillaNegocioAnexoRte;
			copia.anio = anio;
			copia.nit = nit;
			copia.idPersona = idPersona;
			copia.documentoRetencion = documentoRetencion;
			copia.correccion = correccion;
			copia.docCorreccion470 = docCorreccion470;
			copia.esObligado = esObligado;
			copia.auditoriaRegistro = auditoriaRegistro;
			copia.tipoDecla = tipoDecla;
			copia.lstDetRetContrArtEscenics = lstDetRetContrArtEscenics;
			copia.periodo = periodo;
		}
	}

	@Override
	protected void cargarParametros() {

	}

	@Override
	public Object clonar() {
		return new DCmdAccWFRteContibArtesEscenicas();
	}

	@Override
	protected void ejecutarComandoWF() {

	}

	@Override
	public String getDescripcion() {
		return "comando de WF para registrar y generar documentos de formato 470 que llegan por carga masiva";
	}

	public String getSalidaEjecucionActiva() {
		return "";
	}

	public Collection getSalidasEjecucion() {
		return null;
	}

	public void inicializarOnEvento(DMensajeWF arg0) {

	}

	@Override
	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		return true;
	}

}
