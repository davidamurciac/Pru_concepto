package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.juegosdesuerteyazar;

import java.util.Collection;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.DMensajeWF;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.DJuegoSuerteAzarAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.DJuegoSuerteAzarPKTO;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.gestionexpediente.comando.DComandoWF;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DExpedienteTO;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
/**
 * Descripcion: Comando de WorkFlow para la generacion de documento 
 * Declaración derechos de explotación de Administración de juegos de
 * Suerte y Azar ETESA (320)</p>
 * 
 * @author DESPITIAC
 *
 */
public class DCmdAccWFDeclaracionJuegosSuerteAzar extends DComandoWF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 452217710453406761L;

	protected static Logger LOGGER = Logger.getLogger(
			DCmdAccWFDeclaracionJuegosSuerteAzar.class);
	
	protected transient DDocumentoPKTO documentoCargaPK;
	protected transient DJuegoSuerteAzarPKTO juegosPK;
	protected transient DJuegoSuerteAzarAttTO juegosAtt;
	protected transient Collection documentosCargados;
	protected transient IDDocumento documento;
	protected transient Boolean tieneErrorNoTransaccional = false;
    protected transient Long nit = null;
    protected transient Long dv = null;
    protected transient DSolicitudIngresoTO solicitud;
    protected transient long num_item = 0;
    protected transient long num_item_ant = 0;
    protected transient DRegistroRutTO personaRut;
    protected transient Long idPersona = new Long(0);
    protected transient DIdentificadorDoc identificadorDoc;
	protected transient long totalBaseLiquidacionMod1; 
	protected transient long totalBaseLiquidacionMod2; 
	protected transient long totalBaseLiquidacionMod3; 
	protected transient long totalBaseLiquidacionMod4; 
	protected transient long totalBaseLiquidacionMod5; 
	protected transient long totalDerExplotacionPorItem1;
	protected transient long totalDerExplotacionPorItem2;
	protected transient long totalDerExplotacionPorItem3;
	protected transient long totalDerExplotacionPorItem4;
	protected transient long totalDerExplotacionPorItem5;
	protected transient double totalBaseLiqJuegos;
	protected transient double totalDerExplotacion;
	protected transient double gastosAdmon;
	protected transient double totalPagarPeriodo;
    protected DExpedienteTO expediente;
    protected Integer anio;
    protected Integer periodo;
    
	
	
	 protected void asignarWF(IDComando comando) {
		 if(comando instanceof DCmdAccWFDeclaracionJuegosSuerteAzar){
			 DCmdAccWFDeclaracionJuegosSuerteAzar copia = (DCmdAccWFDeclaracionJuegosSuerteAzar)comando;
			 copia.documentoCargaPK = documentoCargaPK;
			 copia.juegosPK = juegosPK;
			 copia.juegosAtt = juegosAtt;
			 copia.documentosCargados = documentosCargados;
			 copia.documento = documento;
			 copia.nit = nit;
			 copia.dv = dv;
			 copia.anio =  anio;
			 copia.periodo = periodo;
			 copia.solicitud = solicitud;
			 copia.num_item = num_item;
			 copia.num_item_ant = num_item_ant;
			 copia.personaRut = personaRut;
			 copia.idPersona = idPersona;
			 copia.identificadorDoc = identificadorDoc;
			 copia.totalBaseLiquidacionMod1 = totalBaseLiquidacionMod1; 
			 copia.totalBaseLiquidacionMod2 = totalBaseLiquidacionMod2; 
			 copia.totalBaseLiquidacionMod3 = totalBaseLiquidacionMod3; 
			 copia.totalBaseLiquidacionMod4 = totalBaseLiquidacionMod4; 
			 copia.totalBaseLiquidacionMod5 = totalBaseLiquidacionMod5;
			 copia.totalDerExplotacionPorItem1 = totalDerExplotacionPorItem1;
			 copia.totalDerExplotacionPorItem2 = totalDerExplotacionPorItem2;
			 copia.totalDerExplotacionPorItem3 = totalDerExplotacionPorItem3;
			 copia.totalDerExplotacionPorItem4 = totalDerExplotacionPorItem4;
			 copia.totalDerExplotacionPorItem5 = totalDerExplotacionPorItem5;
			 copia.totalBaseLiqJuegos = totalBaseLiqJuegos;
			 copia.totalDerExplotacion = totalDerExplotacion;
			 copia.gastosAdmon = gastosAdmon;
			 copia.totalPagarPeriodo = totalPagarPeriodo;
			 copia.tieneErrorNoTransaccional = tieneErrorNoTransaccional;
		 }
	 }

	@Override
	protected void cargarParametros() {
	}

	@Override
	protected void ejecutarComandoWF() {
		
	}

	@Override
	public Object clonar() {
		return new DCmdAccWFDeclaracionJuegosSuerteAzar();
	}

	@Override
	public String getDescripcion() {
		return "comando de WF para la generacion de un documento 320";
	}

	@Override
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
