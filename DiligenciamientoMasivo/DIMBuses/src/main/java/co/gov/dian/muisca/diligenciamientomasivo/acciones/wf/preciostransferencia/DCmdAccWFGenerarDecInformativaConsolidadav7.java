package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.gestionexpediente.comando.*;
import org.apache.log4j.*;
import co.gov.dian.muisca.entradasalida.documento.*;
import co.gov.dian.muisca.entradasalida.general.to.documento.*;
import co.gov.dian.muisca.rut.general.to.*;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.*;

/**
 * <p>Title: DCmdAccWFGenerarDecInformativaConsolidadav7</p>
 * <p>Description: Genera el esqueleto de la declaracion informativa consolidada
 * de precios de transferencia</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * * <p>Company:DIAN </p>
 * @author AFRADEB
 * @version 1.0
 */
public class DCmdAccWFGenerarDecInformativaConsolidadav7 extends DComandoWF {

    /*Atributos*/
    protected static Logger logger = Logger.getLogger(
            DCmdAccWFGenerarDecInformativaConsolidadav7.class);

    protected transient Collection documentosCargados;
    protected transient IDDocumento documento120;
    protected transient IDDocumento documento130;
    protected transient DIdentificadorDoc identificadorDoc;
    protected transient DDocumentoPKTO documentoCargaPK;
    protected transient DPersonaRutTO personaRut;
    protected transient DSolicitudIngresoTO solicitud;

    protected transient Long nit = null;
    protected transient Long dv = null;
    protected transient Long idPersona = new Long(0);
    protected transient long num_item = 0;
    protected transient long num_item_ant = 0;
    protected boolean procesarValido;
    protected String tipoError = "";
    protected int annoSolicitud = 0; //cas1
    protected long idDocumentoCorreccion = 0; //cas28
    protected int repeticionDocCorreccion = 1;

    protected transient long totalOperacionesIngreso = 0;
    protected transient long totalOperacionesEgreso  = 0;
    protected transient long totalActivoMovimientoDebito  = 0;
    protected transient long totalActivoMovimientoCredito = 0;
    protected transient long totalActivoSaldo = 0;
    protected transient long totalPasivoMovimientoDebito = 0;
    protected transient long totalPasivoMovimientoCredito = 0;
    protected transient long totalPasivoSaldo = 0;
/*prueba para datos del obligado
    protected int presentoDecIndividual = 2;
    protected long declaIndivConsolida = 0;
    protected transient long totalOperacionesIngresoObligado = 0;
    protected transient long totalOperacionesEgresoObligado  = 0;
    protected transient long totalActivoMovimientoDebitoObligado  = 0;
    protected transient long totalActivoMovimientoCreditoObligado = 0;
    protected transient long totalActivoSaldoObligado = 0;
    protected transient long totalPasivoMovimientoDebitoObligado = 0;
    protected transient long totalPasivoMovimientoCreditoObligado = 0;
    protected transient long totalPasivoSaldoObligado = 0;
hasta aqu�*/
    protected transient DSolicitudIngresoTO solicitudIngresoTO;

    public static final String DILIGENCIAR_DOCUMENTO = "Terminar el diligenciamiento de la Declaraci�n Informativa Consolidada - (Hoja principal) n�mero ";
    public static final String DILIGENCIAR_DOCUMENTO_2 = " generada por la solicitud ";
    protected boolean inicializarOnEvento;
    protected long valorSancionAnterior = 0;


    public DCmdAccWFGenerarDecInformativaConsolidadav7() {
    }

    /**
     * asignarWF
     *
     * @param comando IDComando
     * @todo Implement this
     *   co.gov.dian.muisca.gestionexpediente.comando.DComandoWF method
     */
    protected void asignarWF(IDComando comando) {
        if(comando instanceof DCmdAccWFGenerarDecInformativaConsolidadav7){
            DCmdAccWFGenerarDecInformativaConsolidadav7 copia = (DCmdAccWFGenerarDecInformativaConsolidadav7)comando;
            copia.documentosCargados = documentosCargados;
            copia.documento120 = documento120;
            copia.documento130 = documento130;
            copia.identificadorDoc = identificadorDoc;
            copia.documentoCargaPK = documentoCargaPK;
            copia.idDocumentoCorreccion = idDocumentoCorreccion;
            copia.repeticionDocCorreccion = repeticionDocCorreccion;
            copia.personaRut = personaRut;
            copia.solicitud = solicitud;
            copia.totalOperacionesIngreso = totalOperacionesIngreso;
            copia.totalOperacionesEgreso  = totalOperacionesEgreso;
            copia.totalActivoMovimientoDebito  = totalActivoMovimientoDebito;
            copia.totalActivoMovimientoCredito = totalActivoMovimientoCredito;
            copia.totalActivoSaldo = totalActivoSaldo;
            copia.totalPasivoMovimientoDebito = totalPasivoMovimientoDebito;
            copia.totalPasivoMovimientoCredito = totalPasivoMovimientoCredito;
            copia.totalPasivoSaldo = totalPasivoSaldo;
            copia.annoSolicitud = annoSolicitud;
            copia.nit = nit;
            copia.dv = dv;
            copia.num_item = num_item;
            copia.solicitudIngresoTO = solicitudIngresoTO;
            copia.procesarValido = procesarValido;
            copia.tipoError = tipoError;
            copia.valorSancionAnterior = valorSancionAnterior;
        }
    }

    /**
     * Carga los par�metros del comando de workflow, es decir, los par�metros
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
        return new DCmdAccWFGenerarDecInformativaConsolidadav7();
    }

    /**
     * Ejecuta la l�gica de negocio.
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
        return "Comando WF que genera una declaraci�n 130";
    }

    /**
     * Devuelve la salida activa en ejecuci�n.
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

}
