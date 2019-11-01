package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia;

import co.gov.dian.muisca.arquitectura.interfaces.*;
import java.util.*;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.*;
import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.gestionexpediente.comando.*;
import org.apache.log4j.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DCmdAccWFVeriFormatoDeclConsolidada extends DComandoWF{

    protected static Logger LOGGER = Logger.getLogger(
            DCmdAccWFVeriFormatoDeclConsolidada.class);

    protected String salida;
    protected static final String[] SALIDAS = {"SI", "NO"};

    public static final int NUM_FORMATO_TRAFICO_DEC_CONSOLIDADA = 1126;

    public DCmdAccWFVeriFormatoDeclConsolidada() {
    }

    protected void asignarWF(IDComando comando) {
        if (comando instanceof DCmdAccWFVeriFormatoDeclConsolidada){
                DCmdAccWFVeriFormatoDeclConsolidada copia = (DCmdAccWFVeriFormatoDeclConsolidada) comando;
                copia.salida = salida;
        }
    }

    /**
     * cargarParametros
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
        return new DCmdAccWFVeriFormatoDeclConsolidada();
    }

    /**
     * ejecutarComandoWF
     *
     * @todo Implement this
     *   co.gov.dian.muisca.gestionexpediente.comando.DComandoWF method
     */
    protected void ejecutarComandoWF() {
        throw new UnsupportedOperationException();

    }

    /**
     *
     * @return String
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDDescribible method
     */
    public String getDescripcion() {
        return "Comando de WorkFlow para la validacion de la casilla 40 de un formato 10006 para determinar si es es igual a 1126";
    }

    /**
     * getSalidaEjecucionActiva
     *
     * @return String
     * @todo Implement this
     *   co.gov.dian.muisca.gestionexpediente.comando.IDComandoWF method
     */
    public String getSalidaEjecucionActiva() {
        return salida;
    }

    /**
     * getSalidasEjecucion
     *
     * @return Collection
     * @todo Implement this
     *   co.gov.dian.muisca.gestionexpediente.comando.IDComandoWF method
     */
    public Collection getSalidasEjecucion() {
        return Arrays.asList(SALIDAS);
    }

    /**
     * inicializarOnEvento
     *
     * @param dMensajeWF DMensajeWF
     * @todo Implement this
     *   co.gov.dian.muisca.gestionexpediente.comando.IDComandoWF method
     */
    public void inicializarOnEvento(DMensajeWF dMensajeWF) {
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

}
