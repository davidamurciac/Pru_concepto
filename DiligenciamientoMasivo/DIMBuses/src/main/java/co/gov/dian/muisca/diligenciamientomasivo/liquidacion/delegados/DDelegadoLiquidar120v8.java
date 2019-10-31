package co.gov.dian.muisca.diligenciamientomasivo.liquidacion.delegados;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDCasillasEncabezadoFormularios;
import co.gov.dian.muisca.diligenciamiento.liquidacion.delegados.DDelegadoLiquidar;
import co.gov.dian.muisca.diligenciamiento.liquidacion.impl.DCalculosLiqF490;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DCalculosLiquidacion;
import co.gov.dian.muisca.entradasalida.liquidacion.IDLiquidacion;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.entradasalida.formatos.IDCasillasFormularios;


/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author dmahechav
 * @version 1.0
 */

public class DDelegadoLiquidar120v8 extends DDelegadoLiquidar{

   /**
     *
     * @return String
     */
    public String getDescripcion() {
        return "Delegado que implementa la Liquidación para el Formulario 120 versión 8.";
    }

  public IDDocumento liquidar(IDDocumento docLiquidacion) throws DExcepcion{
      DContextoSeguridad contextoSeg = super.getContextoSeguridad();
      /**
       * Construye el Documento para el resultado de la liquidación y llena
       * las Casillas de cabecera y Pie.
       */

      this.docLiquidacion = docLiquidacion;
      docLiquidado = DCalculosLiquidacion.construirDocumentoLiquidado(
              metaLiquidacion.getIdFormatoDocOut(),
              metaLiquidacion.getVersionFtoDocOut(),
              metaLiquidacion.getIdPersonaRut(), contextoSeg);

      DCalculosLiqF490.copiaValoresDefinicion(docLiquidacion, docLiquidado,
                                              metaLiquidacion, contextoSeg);
      efectuarLiquidacion();
      DCalculosLiqF490.calcularPagoTotal(docLiquidado);
      DCalculosLiqF490.eliminarCeros(docLiquidado);
      return docLiquidado;

}

/**
*
* @throws DEntradaSalidaExcepcion
* @throws DExcepcion
*/
private void efectuarLiquidacion() throws DEntradaSalidaExcepcion, DExcepcion {

// Obtiene información de la declaración.

    IDOcurrencia ocurrenciaDoc120 = docLiquidacion.getGrupos().getGrupo(
            GRUPO_DOC_LIQUIDACION).getOcurrencia(IDLiquidacion.
                                                 OCURRENCIA_DOC_LIQUIDACION);
    long nit = ocurrenciaDoc120.getValorCasilla(IDCasillasEncabezadoFormularios.
                                                NIT).getLong();


    int valCasSancion = ocurrenciaDoc120.getValCasillaNeg(IDCasillasFormularios.SANCION).getId();
    double declaracionPagoSancion = ocurrenciaDoc120.getValorCasilla(
            valCasSancion).getDouble();

    /*String frStr = ocurrenciaDoc120.getValorCasilla(
        IDCasillasFormulario120.CASILLA_FRACCION_ANNO).getValorCadena();
     frStr = frStr!=null?frStr:"false";
     boolean isFraccion = Boolean.getBoolean(frStr);*/

    double declaracionPagoImpuesto = 0;
    double pagoInteresMora = 0;
// Ejecuta el Servicio para liquidación de cuotas.

    /*if(!isFraccion){
        DCmdSrvLiquidarCuota srvLiquidarCuota = (DCmdSrvLiquidarCuota)
                               getServicio(
                "entradasalida.liquidacion.DCmdSrvLiquidarCuota");
        srvLiquidarCuota.inicializar(metaLiquidacion, nit,
                                     declaracionPagoSancion,
                                     declaracionPagoImpuesto);
        srvLiquidarCuota.ejecutar();
        DCalculosLiqF490.asignarValoresDocLiquidado(
                docLiquidado, srvLiquidarCuota.getPagoSancion(),
                srvLiquidarCuota.getPagoInteresMora(),
                srvLiquidarCuota.getPagoImpuesto());
     }else{*/
    DCalculosLiqF490.asignarValoresDocLiquidado(
            docLiquidado, declaracionPagoSancion, pagoInteresMora,
            declaracionPagoImpuesto);

//}
}
}
