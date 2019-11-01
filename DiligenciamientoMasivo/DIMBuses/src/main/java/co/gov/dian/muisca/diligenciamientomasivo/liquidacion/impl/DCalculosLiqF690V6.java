package co.gov.dian.muisca.diligenciamientomasivo.liquidacion.impl;

import java.util.Date;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.formateador.implgenerica.DFechaHora;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.diligenciamientomasivo.liquidacion.IDCasillasFormulario690;
import co.gov.dian.muisca.entradasalida.liquidacion.IDLiquidacion;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DCalculosLiquidacion;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DMetaLiquidacion;
import co.gov.dian.muisca.rut.constantes.IDTipoDeclaranteRUT;
import co.gov.dian.muisca.rut.general.to.DUsuarioAduanaTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsUsuarioAduana;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Rb2
 * @version 1.0
 */
public class DCalculosLiqF690V6 {

    /**
     * Copia las Casillas que vienen en la Definición para la liquidación.
     * @param docLiquidacion IDDocumento
     * @param docLiquidado IDDocumento
     * @param metaLiquidacion DMetaLiquidacion
     * @param contextoSeg DContextoSeguridad
     * @throws DEntradaSalidaExcepcion
     */
    public static void copiaValoresDefinicion(IDDocumento docLiquidacion,
                                              IDDocumento docLiquidado,
                                              DMetaLiquidacion metaLiquidacion,
                                              DContextoSeguridad contextoSeg)
            throws DEntradaSalidaExcepcion {

        DDefinicionLiqF690V6 defLiquidacion =
                (DDefinicionLiqF690V6) metaLiquidacion.getDefinicionLiquidacion();
        DCalculosLiquidacion.copiaValsContextoADocLiquidado(docLiquidado,
                defLiquidacion);

        //** Ejecuta las operaciones definidas para este proceso en el XML. */

        long idePersona = metaLiquidacion.getIdPersonaRut();

        try {
            DCalculosLiquidacion.ejecutaDocumentoDeOperaciones(docLiquidacion,
                    docLiquidado, defLiquidacion, contextoSeg);

            /**
             * Para el formulario 520 cuando se trata de Personas Naturales debe concatenar
             * las Casillas nombres y apellidos, y ubicarlos en la Casilla razón social del
             * recibo de pago.
             */
            if (docLiquidacion.getIdFormato() == 520) {
                String tipoDeclarante = defLiquidacion.getString(
                        IDCasillasFormulario690.TIPO_DECLARANTE);

                if (tipoDeclarante.equals(String.valueOf(IDTipoDeclaranteRUT.
                        NATURAL))) {
                    String priApell = null;
                    String segApell = null;
                    String priNom = null;
                    String segNom = null;

                    priApell = docLiquidacion.getGrupos().getGrupo(1).
                               getOcurrencia(
                                       1).
                               getValorCasilla(26).getValorCadena() == null ?
                               "" :
                               docLiquidacion.getGrupos().getGrupo(1).
                               getOcurrencia(
                                       1).
                               getValorCasilla(26).getValorCadena();
                    segApell = docLiquidacion.getGrupos().getGrupo(1).
                               getOcurrencia(
                                       1).
                               getValorCasilla(27).getValorCadena() == null ?
                               "" :
                               docLiquidacion.getGrupos().getGrupo(1).
                               getOcurrencia(
                                       1).
                               getValorCasilla(27).getValorCadena();
                    priNom = docLiquidacion.getGrupos().getGrupo(1).
                             getOcurrencia(1).
                             getValorCasilla(28).getValorCadena() == null ? "" :
                             docLiquidacion.getGrupos().getGrupo(1).
                             getOcurrencia(1).
                             getValorCasilla(28).getValorCadena();
                    segNom = docLiquidacion.getGrupos().getGrupo(1).
                             getOcurrencia(1).
                             getValorCasilla(29).getValorCadena() == null ? "" :
                             docLiquidacion.getGrupos().getGrupo(1).
                             getOcurrencia(1).
                             getValorCasilla(29).getValorCadena(); ;

                    DCalculosLiquidacion.setValorCasilla(
                            priApell + " " + segApell + " " + priNom + " " +
                            segNom,
                            26,
                            IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);
                }
            }

            DUsuarioAduanaTO usuarioAduanero =
                    cosultarUsuarioAduana(idePersona, contextoSeg);
            DCalculosLiquidacion.setValorCasilla(
                    usuarioAduanero.getPK().getIdeTipoUsuarioAdu(), 29,
                    IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);
        } catch (DExcepcion ex) {
            throw new DEntradaSalidaExcepcion(ex);
        }
    }

    /**
     *
     * @param idePersona long
     * @param contextoSeg DComandoServicio
     * @return DUsuarioAduanaTO
     * @throws DEntradaSalidaExcepcion
     * @author vclavijol
     */

    private static DUsuarioAduanaTO cosultarUsuarioAduana(long idePersona,
            DContextoSeguridad contextoSeg) throws DEntradaSalidaExcepcion {

        DFechaHora fechaHora = new DFechaHora(new Date());

        try {
            DCmdSrvConsUsuarioAduana srvConsUsuarioAduana = (
                    DCmdSrvConsUsuarioAduana)
                    DCalculosLiquidacion.getServicioDelegado(
                            "rut.DCmdSrvConsUsuarioAduana", contextoSeg);
            srvConsUsuarioAduana.getContexto().setContextoSeguridad(contextoSeg);
            srvConsUsuarioAduana.inicializarPorConsultarIdePersonaRUT(
                    idePersona, (int) fechaHora.getNumericDate());
            srvConsUsuarioAduana.ejecutar();
            DUsuarioAduanaTO usuarioAduanero = srvConsUsuarioAduana.
                                               getUsuarioAduana();

            return usuarioAduanero;
        } catch (Exception ex) {
            throw new DEntradaSalidaExcepcion(ex);
        }
    }


    /**
     * Elimina de manera específica los ceros del Documento y coloca vacíos.
     * @param docLiquidado IDDocumento
     */
    public static void eliminarCeros(IDDocumento docLiquidado) {
        DCalculosLiquidacion.eliminarCeros(
                new int[] {4, 24, 25, 30, 31, 35, 33, 34, 45, 997},
                docLiquidado);
    }


    /**
     * Asigna los valores calculados al Documento liquidado.
     * @param docLiquidado IDOcurrencia
     * @param pagoSancion double
     * @param pagoOtros double
     */
    public static void asignarValoresDocLiquidado(IDDocumento docLiquidado,
            double pagoSancion, double pagoOtros) {
        IDOcurrencia ocurrenciaDoc690 = docLiquidado.getGrupos().getGrupo(
                IDLiquidacion.GRUPO_DOC_LIQUIDACION).getOcurrencia(IDLiquidacion.OCURRENCIA_DOC_LIQUIDACION);

        ocurrenciaDoc690.getValorCasilla(50)
                .setValor(new Long((long)pagoOtros));

        ocurrenciaDoc690.getValorCasilla(51)
                .setValor(new Long((long)pagoSancion));
     }


    /**
     *
     * @param docLiquidado IDDocumento
     */
    public static void calcularPagoTotal(IDDocumento docLiquidado) {
        Double valCas980 = new Double(
                DCalculosLiquidacion.getValorDecimalCasilla(
                        48, IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue() +
                DCalculosLiquidacion.getValorDecimalCasilla(
                        49, IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue() +
                DCalculosLiquidacion.getValorDecimalCasilla(
                        50, IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue() +
                DCalculosLiquidacion.getValorDecimalCasilla(
                        51, IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue() +
                DCalculosLiquidacion.getValorDecimalCasilla(
                        52, IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue());

        DCalculosLiquidacion.setValorCasilla(
                DCalculosLiquidacion.redondearAMiles(valCas980), 980,
                IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);
    }

}
