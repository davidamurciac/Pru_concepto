package co.gov.dian.muisca.diligenciamientomasivo.liquidacion.delegados;



import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.diligenciamiento.liquidacion.IDCasillasFormulario490v7;
import co.gov.dian.muisca.entradasalida.liquidacion.IDDefinicionLiquidacion;
import co.gov.dian.muisca.diligenciamiento.liquidacion.delegados.DDelegadoPreparar;
import co.gov.dian.muisca.diligenciamiento.liquidacion.impl.DDefinicionLiqF490V6;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DMetaLiquidacion;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author dmahechav
 * @version 1.0
 */
public class DDelegadoPreparar120v1 extends DDelegadoPreparar {

    /**
     *
     * @return IDDocumento
     * @throws DExcepcion
     */
    public IDDocumento cargarLiquidacion() throws DExcepcion {

        docLiquidacion = (IDDocumento) parametrosLiq.get(PARAMETRO_DOCUMENTO);

        DMetaLiquidacion metaLiquidacion =
            (DMetaLiquidacion) parametrosLiq.get(PARAMETRO_METALIQUIDACION);

        obtenerVersionVigente(490);
        metaLiquidacion.setDefinicionLiquidacion((IDDefinicionLiquidacion)
                parametrosLiq.get(PARAMETRO_DEFINICION_LIQUIDACION));
        metaLiquidacion.setDescripcion("Par�metros para la Liquidaci�n del Formulario 120 versi�n 1");
        metaLiquidacion.setIdPersonaRut(idPersonaRut);
        metaLiquidacion.setIdFormatoDocIn(120);
        metaLiquidacion.setVersionFtoDocIn(1);
        metaLiquidacion.setIdFormatoDocOut(490);
        metaLiquidacion.setVersionFtoDocOut(version);
        metaLiquidacion.setIdLiquidacion(-1); /**@todo Est� por definir su uso. */
        metaLiquidacion.setVersionLiq(0); /**@todo Est� por definir su uso. */

        DDefinicionLiqF490V6 defLiquidacion =
                (DDefinicionLiqF490V6) parametrosLiq.get(PARAMETRO_DEFINICION_LIQUIDACION);
        defLiquidacion.setValorCasilla(IDCasillasFormulario490v7.ANNO, docLiquidacion);
        // Es un formulario aperi�dico.
        defLiquidacion.setValorCasilla(IDCasillasFormulario490v7.PERIODO, new Integer(1));

        return docLiquidacion;
    }

}
