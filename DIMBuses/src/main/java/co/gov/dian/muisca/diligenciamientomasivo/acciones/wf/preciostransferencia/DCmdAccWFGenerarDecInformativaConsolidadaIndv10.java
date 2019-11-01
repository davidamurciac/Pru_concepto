package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia;

import java.util.Collection;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.DMensajeWF;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.gestionexpediente.comando.DComandoWF;
import co.gov.dian.muisca.rut.general.to.DPersonaRutTO;

public class DCmdAccWFGenerarDecInformativaConsolidadaIndv10 extends DComandoWF {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -142790926934826168L;

	/*Atributos*/
	protected static Logger logger = Logger.getLogger(DCmdAccWFGenerarDecInformativaConsolidadaIndv10.class);

	protected transient Collection documentosCargados;
	protected transient IDDocumento documento;
	protected transient DIdentificadorDoc identificadorDoc;
	protected transient DDocumentoPKTO documentoCargaPK;
	protected transient DPersonaRutTO personaRut;
	protected transient DSolicitudIngresoTO solicitud;
	protected boolean procesarValido;
	protected String tipoError = "";

	protected long idDocumentoCorreccion = 0; //cas25
	protected int numRepDocumentoCorreccion = 1;
	protected transient Long nit = null;
	protected transient Long dv = null;
	protected transient Long idPersona = new Long(0);
	protected transient Long admin = null;
	protected transient long num_item = 0;

	//---------------------VARIABLES ACUMULADORAS--------------------------//
	protected int annoDeclaracion = 0; //cas1
	protected long montoTotalOperacionIngreso = 0; //cas28
	protected long montoTotalOperacionEgreso = 0;  //cas29
	protected long montoMovimientoActivoDebito = 0;
	protected long aporteSociedadesExtranjeras = 0;//cas30
	protected long montoMovimientoActivoCredito = 0;
	protected long informacionAdicional = 0;    //cas31
	protected long montoTotalPrincipalRecibido = 0; 
	protected long montoSaldoActivoFinal = 0;    //cas32
	protected long montoMovimientoPasivoDebito = 0;
	protected long montoTotalPrincipalPrestado = 0;//cas33
	protected long montoMovimientoPasivoCredito = 0; //cas34
	protected long montoSaldoPasivoFinal = 0; //cas35
	protected long montoOperacion = 0; //cas44
	protected long idSolilcitudIngreso1125 = 0; //cas 46;


	public static final String DILIGENCIAR_DOCUMENTO = "Terminar el diligenciamiento de la Declaraci�n " +
	"Informativa individual - (Hoja principal) n�mero ";

	protected boolean inicializarOnEvento;

	protected long valorSancionAnterior = 0;
	protected double valorMargenPositivo = 0;
	protected double valorMargenNegativo = 0;



	public DCmdAccWFGenerarDecInformativaConsolidadaIndv10() {
	}

	/**
	 * asignarWF
	 *
	 * @param comando IDComando
	 * @todo Implement this
	 *   co.gov.dian.muisca.gestionexpediente.comando.DComandoWF method
	 */
	protected void asignarWF(IDComando comando) {
		if(comando instanceof DCmdAccWFGenerarDecInformativaConsolidadaIndv10){
			DCmdAccWFGenerarDecInformativaConsolidadaIndv10 copia = (DCmdAccWFGenerarDecInformativaConsolidadaIndv10)comando;
			copia.documentosCargados = documentosCargados;
			copia.documento = documento;
			copia.identificadorDoc = identificadorDoc;
			copia.documentoCargaPK = documentoCargaPK;
			copia.personaRut = personaRut;
			copia.solicitud = solicitud;
			copia.nit = nit;
			copia.dv = dv;
			copia.admin = admin;
			copia.idPersona = idPersona;
			copia.num_item = num_item;
			copia.annoDeclaracion = annoDeclaracion;
			copia.idDocumentoCorreccion = idDocumentoCorreccion; //cas25
			copia.montoTotalOperacionIngreso = montoTotalOperacionIngreso; //cas28
			copia.montoTotalOperacionEgreso = montoTotalOperacionEgreso;  //cas29
			copia.montoMovimientoActivoDebito = montoMovimientoActivoDebito; 
			copia.aporteSociedadesExtranjeras = aporteSociedadesExtranjeras;//cas30
			copia.montoMovimientoActivoCredito = montoMovimientoActivoCredito; 
			copia.informacionAdicional = informacionAdicional;//cas31
			copia.montoSaldoActivoFinal = montoSaldoActivoFinal;
			copia.montoTotalPrincipalRecibido= montoTotalPrincipalRecibido;//cas32
			copia.montoMovimientoPasivoDebito = montoMovimientoPasivoDebito; 
			copia.montoTotalPrincipalPrestado= montoTotalPrincipalPrestado;//cas33
			copia.montoMovimientoPasivoDebito = montoMovimientoPasivoDebito; 
			copia.montoMovimientoPasivoCredito = montoMovimientoPasivoCredito; //cas34
			copia.montoSaldoPasivoFinal = montoSaldoPasivoFinal; //cas35
			copia.montoOperacion= montoOperacion; //cas44
			copia.idSolilcitudIngreso1125 = idSolilcitudIngreso1125;
			copia.procesarValido = procesarValido;
			copia.tipoError = tipoError;
			copia.numRepDocumentoCorreccion = numRepDocumentoCorreccion;
			copia.valorSancionAnterior = valorSancionAnterior;
			copia.valorMargenPositivo = valorMargenPositivo;
			copia.valorMargenNegativo = valorMargenNegativo;
		}
	}

	/**
	 * Carga los parametros del comando de workflow, es decir, los parametros
	 * de entrada y salida.
	 *
	 * @todo Implement this
	 *   co.gov.dian.muisca.gestionexpediente.comando.DComandoWF method
	 */
	protected void cargarParametros() {
	}

	/**
	 *
	 * @return Object
	 * @todo Implement this
	 *   co.gov.dian.muisca.arquitectura.interfaces.IDClonable method
	 */
	public Object clonar() {
		return new DCmdAccWFGenerarDecInformativaConsolidadaIndv10();
	}

	/**
	 * Ejecuta la logica de negocio.
	 *
	 * @todo Implement this
	 *   co.gov.dian.muisca.gestionexpediente.comando.DComandoWF method
	 */
	protected void ejecutarComandoWF() {
	}

	/**
	 *
	 * @return String
	 * @todo Implement this
	 *   co.gov.dian.muisca.arquitectura.interfaces.IDDescribible method
	 */
	public String getDescripcion() {
		return "Comando WF que genera una declaraci�n 120 Version 10";
	}

	/**
	 * Devuelve la salida activa en ejecucion.
	 *
	 * @return Un String con la salida
	 * @todo Implement this
	 *   co.gov.dian.muisca.gestionexpediente.comando.IDComandoWF method
	 */
	public String getSalidaEjecucionActiva() {
		return "";
	}

	/**
	 * Devuelve la lista de todas las salidas que puede tener un comando.
	 *
	 * @return Un Collection con objetos String que representan las salidas
	 * @todo Implement this
	 *   co.gov.dian.muisca.gestionexpediente.comando.IDComandoWF method
	 */
	public Collection getSalidasEjecucion() {
		return null;
	}

	/**
	 * Invocado por el motor para reanudar la ejecuci�n de una actividad
	 * as�ncrona.
	 *
	 * @param unMensaje Mensaje de WF
	 * @todo Implement this
	 *   co.gov.dian.muisca.gestionexpediente.comando.IDComandoWF method
	 */
	public void inicializarOnEvento(DMensajeWF unMensaje) {
	}

	/**
	 *
	 * @return boolean
	 * @todo Implement this
	 *   co.gov.dian.muisca.arquitectura.interfaces.IDAuditable method
	 */
	public boolean isAuditable() {
		return false;
	}

	/**
	 *
	 * @throws DValidarExcepcion
	 * @return boolean
	 * @todo Implement this
	 *   co.gov.dian.muisca.arquitectura.interfaces.IDComando method
	 */
	public boolean validar() throws DValidarExcepcion {
		return true;
	}

	public void reanudar() throws DExcepcion {
		inicializarOnEvento = true;
	}

	public long getMontoOperacion() {
		return montoOperacion;
	}
}