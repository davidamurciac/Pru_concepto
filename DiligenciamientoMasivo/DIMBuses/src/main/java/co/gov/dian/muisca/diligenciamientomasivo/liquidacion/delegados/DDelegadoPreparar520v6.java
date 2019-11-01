package co.gov.dian.muisca.diligenciamientomasivo.liquidacion.delegados;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.liquidacion.IDDefinicionLiquidacion;
import co.gov.dian.muisca.entradasalida.liquidacion.IDLiquidacion;
import co.gov.dian.muisca.diligenciamientomasivo.liquidacion.impl.DDefinicionLiqF690V6;
import co.gov.dian.muisca.diligenciamiento.liquidacion.delegados.DDelegadoPreparar;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DMetaLiquidacion;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;
import co.gov.dian.muisca.diligenciamientomasivo.liquidacion.IDCasillasFormulario690;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormato;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author dmahechav
 * @version 1.0
 */
public class DDelegadoPreparar520v6 extends DDelegadoPreparar {

	public static String anno_presentacion;
    /**
     *
     * @return IDDocumento
     * @throws DExcepcion
     */
    public IDDocumento cargarLiquidacion() throws DExcepcion {

    	SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy");
    	anno_presentacion = formatoFecha.format(new Date());

    	docLiquidacion = (IDDocumento) parametrosLiq.get(PARAMETRO_DOCUMENTO);
        DCmdSrvConsRegistroRut srvConsRegistroRut = (DCmdSrvConsRegistroRut)
                getServicio("rut.DCmdSrvConsRegistroRut");

        obtenerVersionVigente(690);

        /** Selecciona el ID Persona RUT apropiado para la liquidación. */

        long numNIT = ((Number) DDocumentoUtil.getValorCasillaSimp(
                IDConstantesFormato.IDE_CASILLA_NIT, IDLiquidacion.GRUPO_DOC_LIQUIDACION,
                IDLiquidacion.OCURRENCIA_DOC_LIQUIDACION, docLiquidacion)).longValue();

        srvConsRegistroRut.inicializarConsultarPorNIT(numNIT);
        srvConsRegistroRut.ejecutar();
        DRegistroRutTO registroRut = srvConsRegistroRut.getRegistroRut();

        idPersonaRut = registroRut.getPK().getIdePersonaRut().longValue();

        DMetaLiquidacion metaLiquidacion =
            (DMetaLiquidacion) parametrosLiq.get(PARAMETRO_METALIQUIDACION);

        metaLiquidacion.setDefinicionLiquidacion((IDDefinicionLiquidacion)
                parametrosLiq.get(PARAMETRO_DEFINICION_LIQUIDACION));
        metaLiquidacion.setDescripcion("Parámetros para la Liquidación del Formulario 520 versión 6");
        metaLiquidacion.setIdPersonaRut(idPersonaRut);
        metaLiquidacion.setIdFormatoDocIn(520);
        metaLiquidacion.setVersionFtoDocIn(6);
        metaLiquidacion.setIdFormatoDocOut(690);
        metaLiquidacion.setVersionFtoDocOut(version);
        metaLiquidacion.setIdLiquidacion(-1); /**@todo Está por definir su uso. */
        metaLiquidacion.setVersionLiq(0); /**@todo Está por definir su uso. */

        String docXmlOperaciones = "";

        if(metaLiquidacion.getVersionFtoDocOut() == 7){
        	docXmlOperaciones = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n\r\n\r<doc xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"\\..\\documentos-simplificado.xsd\" cf=\"10401\" vf=\"1\" id=\"\">\n\r\n\r\t<gr id=\"1\">\n\r\n\r\t\t<oc id=\"1\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;41\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;31\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"2\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;4\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;32\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"3\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;54\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;34\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"4\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;55\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;35\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"5\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;44\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;45\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"6\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;45\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;46\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"7\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;46\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;49\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"8\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;47\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;50\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t</gr>\n\r\n\r</doc>\n\r";
        }else{
        	docXmlOperaciones = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n\r\n\r<doc xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"\\..\\documentos-simplificado.xsd\" cf=\"10401\" vf=\"1\" id=\"\">\n\r\n\r\t\t<gr id=\"1\">\n\r\n\r\t\t<oc id=\"1\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;41\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;31\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"2\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;4\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;32\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"3\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;54\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;33\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"4\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;55\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;34\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"5\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;44\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;44\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"6\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;45\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;45\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"7\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;46\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;48\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"8\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;47\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;49\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t<oc id=\"9\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;32\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;25\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"10\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"520;6;1;31\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;24\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t</gr>\n\r\n\r</doc>\n\r";
        }
        metaLiquidacion.getDefinicionLiquidacion().setXmlOperaciones(docXmlOperaciones);

        DDefinicionLiqF690V6 defLiquidacion =
            (DDefinicionLiqF690V6) parametrosLiq.get(PARAMETRO_DEFINICION_LIQUIDACION);
        defLiquidacion.setValorCasilla(IDCasillasFormulario690.ANNO, anno_presentacion);
        // Es un formulario aperiódico.
        defLiquidacion.setValorCasilla(IDCasillasFormulario690.PERIODO, new Integer(1));


        return docLiquidacion;
    }
}
