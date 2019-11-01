package co.gov.dian.muisca.diligenciamientomasivo.liquidacion.delegados;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDContexto;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDCasillasEncabezadoFormularios;
import co.gov.dian.muisca.diligenciamiento.liquidacion.delegados.DDelegadoLiquidar;
import co.gov.dian.muisca.diligenciamiento.liquidacion.delegados.DDelegadoPreparar;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DCalculosLiquidacion;
import co.gov.dian.muisca.diligenciamiento.servicios.liquidacion.DCmdSrvLiquidarCuota;
import co.gov.dian.muisca.entradasalida.liquidacion.IDLiquidacion;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.diligenciamientomasivo.liquidacion.impl.DCalculosLiqF690;
import org.apache.log4j.Logger;


/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author dmahechav
 * @version 1.0
 */

public class DDelegadoLiquidar520v7 extends DDelegadoLiquidar{

    public static Logger log = Logger.getLogger(DDelegadoLiquidar520v7.class);

    /**
     *
     * @return String
     */
    public String getDescripcion() {
        return "Delegado que implementa la Liquidación para el Formulario 520 versión 7.";
    }

  public IDDocumento liquidar(IDDocumento docLiquidacion) throws DExcepcion{

        DContextoSeguridad contextoSeg = super.getContextoSeguridad();
        /**
         * Construye el Documento para el resultado de la liquidación y llena
         * las Casillas de cabecera y Pie.
         */

        this.docLiquidacion = docLiquidacion;
        docLiquidado = DCalculosLiquidacion.construirDocumentoLiquidado(
                metaLiquidacion.getIdFormatoDocOut(), metaLiquidacion.getVersionFtoDocOut(),
                metaLiquidacion.getIdPersonaRut(), contextoSeg);

        DCalculosLiqF690.copiaValoresDefinicion(docLiquidacion, docLiquidado,
                                                  metaLiquidacion, contextoSeg);
        if (metaLiquidacion.getVersionFtoDocOut()==7){
        	efectuarLiquidacion();
        }
        DCalculosLiqF690.calcularPagoTotal(docLiquidado);
        DCalculosLiqF690.eliminarCeros(docLiquidado);

        return docLiquidado;
    }

    /**
     *
     * @throws DExcepcion
     */
    private void efectuarLiquidacion() throws DExcepcion {

        //** Operaciones específicas para la liquidación. */


        docLiquidado.getGrupos().getGrupo(IDLiquidacion.GRUPO_DOC_LIQUIDACION).
                getOcurrencia(IDLiquidacion.OCURRENCIA_DOC_LIQUIDACION).getValorCasilla(33).//38 Para el 690v6
                setValor(DCalculosLiquidacion.getUltimoDigito(docLiquidacion.
                getGrupos().getGrupo(IDLiquidacion.GRUPO_DOC_LIQUIDACION).
                getOcurrencia(IDLiquidacion.OCURRENCIA_DOC_LIQUIDACION).getValorCasilla(4)));

        /**
         * únicamente si el recibo de pago es el 690v6
         */
        //IDOcurrencia declaracion = docLiquidacion.getGrupos().getGrupo(1).getOcurrencia(1);

        /**
         * Se calcula la casilla otros sumando salvaguardia, derechos compensatorios y derechos antidumping
         */
        /*Double valCasOtros = new Double(
            declaracion.getValorCasilla(46).getDouble() +
            declaracion.getValorCasilla(47).getDouble() +
            declaracion.getValorCasilla(48).getDouble()
        );*/

         /**
         * Se calcula la casilla otros sumando sanciones y rescate
         */
        /*Double valCasSanciones = new Double(
            declaracion.getValorCasilla(49).getDouble() +
            declaracion.getValorCasilla(50).getDouble()
        );
       DCalculosLiqF690V6.asignarValoresDocLiquidado(docLiquidado,valCasSanciones.doubleValue(),valCasOtros.doubleValue());*/
    }

}
