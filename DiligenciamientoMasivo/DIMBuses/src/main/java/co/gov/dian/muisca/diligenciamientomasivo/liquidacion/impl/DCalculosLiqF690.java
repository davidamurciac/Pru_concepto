package co.gov.dian.muisca.diligenciamientomasivo.liquidacion.impl;

import java.util.Date;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.formateador.implgenerica.DFechaHora;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDCasillasFormularios;
import co.gov.dian.muisca.entradasalida.liquidacion.IDLiquidacion;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DCalculosLiquidacion;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DMetaLiquidacion;
import co.gov.dian.muisca.rut.constantes.IDTipoDeclaranteRUT;
import co.gov.dian.muisca.rut.general.to.DUsuarioAduanaTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsUsuarioAduana;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.formatos.IDTiposOrigenDeclaracion;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormato;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Rb2
 * @version 1.0
 */
public class DCalculosLiqF690 {
    public DCalculosLiqF690() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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


        //Asigna valor a la casilla 995 del 690

        docLiquidado.getGrupos().getGrupo(1).getOcurrencia(1).getValorCasilla(IDConstantesFormato.IDE_CASILLA_ORIGEN_DECLARACION)
                .setValor(new Long((long)IDTiposOrigenDeclaracion.INTERNO_PRIVADA));

        //** Ejecuta las operaciones definidas para este proceso en el XML. */

        long idePersona = metaLiquidacion.getIdPersonaRut();

        int casTipoUsuario = docLiquidado.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDCasillasFormularios.TIPO_USUARIO).getId();

        try {
            DCalculosLiquidacion.ejecutaDocumentoDeOperaciones(docLiquidacion,
                    docLiquidado, defLiquidacion, contextoSeg);

            DUsuarioAduanaTO usuarioAduanero =
                    cosultarUsuarioAduana(idePersona, contextoSeg);

            if(metaLiquidacion.getVersionFtoDocOut() == 7){
            		DCalculosLiquidacion.setValorCasilla(
                    usuarioAduanero.getPK().getIdeTipoUsuarioAdu(), casTipoUsuario,
                    IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);
            }
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
            new int[] {4, 24, 26, 30, 28, 29, 44, 997},
                docLiquidado);
    }

    /**
     *
     * @param docLiquidado IDDocumento
     */
    public static void calcularPagoTotal(IDDocumento docLiquidado) {


        IDOcurrencia ocurrencia = docLiquidado.getGrupos().getGrupo(1).getOcurrencia(1);

        Double salvaguardia =  DCalculosLiquidacion.getValorDecimalCasilla(
                        ocurrencia.getValCasillaNeg(IDCasillasFormularios.SALVAGUARDIA).getId(), IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);

        Double derecCompensatorios =  DCalculosLiquidacion.getValorDecimalCasilla(
                        ocurrencia.getValCasillaNeg(IDCasillasFormularios.DEREC_COMPENSATORIOS).getId(), IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);

        Double  derecAntidumping   =            DCalculosLiquidacion.getValorDecimalCasilla(
                        ocurrencia.getValCasillaNeg(IDCasillasFormularios.DEREC_ANIDUMPING).getId(), IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);

        Double rescate  =            DCalculosLiquidacion.getValorDecimalCasilla(
                        ocurrencia.getValCasillaNeg(IDCasillasFormularios.RESCATE).getId(), IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);

        Double valCas980 = new Double(

                DCalculosLiquidacion.getValorDecimalCasilla(
                        ocurrencia.getValCasillaNeg(IDCasillasFormularios.ARANCEL).getId(), IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue()
                +

                DCalculosLiquidacion.getValorDecimalCasilla(
                        ocurrencia.getValCasillaNeg(IDCasillasFormularios.IVA).getId(), IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue()
                +

                (salvaguardia != null ? salvaguardia.doubleValue():0)
                +

                (derecCompensatorios != null ? derecCompensatorios.doubleValue():0)

                +

                (derecAntidumping != null ? derecAntidumping.doubleValue():0)

                +

                DCalculosLiquidacion.getValorDecimalCasilla(
                        ocurrencia.getValCasillaNeg(IDCasillasFormularios.SANCION).getId(), IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue()
                +

                (rescate != null ? rescate.doubleValue():0)

                +

                DCalculosLiquidacion.getValorDecimalCasilla(
                        ocurrencia.getValCasillaNeg(IDCasillasFormularios.VALOR_PAGO_INTERES_MORA).getId(), IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado).
                doubleValue()
                );


        DCalculosLiquidacion.setValorCasilla(
                DCalculosLiquidacion.redondearAMiles(valCas980), IDCasillasFormularios.PAGO_TOTAL,
                IDLiquidacion.GRUPO_DOC_LIQUIDACION, docLiquidado);
    }

    private void jbInit() throws Exception {
    }

}
