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

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author dmahechav
 * @version 1.0
 */
public class DDelegadoPreparar540v7 extends DDelegadoPreparar {

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

        obtenerVersionVigente(690);

        DMetaLiquidacion metaLiquidacion =
            (DMetaLiquidacion) parametrosLiq.get(PARAMETRO_METALIQUIDACION);

        metaLiquidacion.setDefinicionLiquidacion((IDDefinicionLiquidacion)
                parametrosLiq.get(PARAMETRO_DEFINICION_LIQUIDACION));
        metaLiquidacion.setDescripcion("Par�metros para la Liquidaci�n del Formulario 540 versi�n 7");
        metaLiquidacion.setIdPersonaRut(idPersonaRut);
        metaLiquidacion.setIdFormatoDocIn(540);
        metaLiquidacion.setVersionFtoDocIn(7);
        metaLiquidacion.setIdFormatoDocOut(690);
        metaLiquidacion.setVersionFtoDocOut(version);
        metaLiquidacion.setIdLiquidacion(-1); /**@todo Est� por definir su uso. */
        metaLiquidacion.setVersionLiq(0); /**@todo Est� por definir su uso. */

        String docXmlOperaciones = "";

        if (metaLiquidacion.getVersionFtoDocOut()==7){
        	docXmlOperaciones = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n\r\n\r<doc xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"\\..\\documentos-simplificado.xsd\" cf=\"\" vf=\"\" id=\"\">\n\r\n\r\t<gr id=\"1\">\n\r\n\r\t\t<oc id=\"1\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;4\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;32\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"2\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;26\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;31\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"3\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;27\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;40\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"4\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;28\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;41\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"5\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;29\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;45\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"6\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;31\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;46\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"7\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;33\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;47\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"8\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;35\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;48\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"9\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;37\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;49\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"10\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;39\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;50\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"11\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;41\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;51\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"12\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;43\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;7;1;52\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t</gr>\n\r\n\r</doc>\n\r";
        }
        else {
        	docXmlOperaciones = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n\r\n\r<doc xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"\\..\\documentos-simplificado.xsd\" cf=\"\" vf=\"\" id=\"\">\n\r\n\r\t<gr id=\"1\">\n\r\n\r\t\t<oc id=\"1\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;4\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;32\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"2\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;26\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;31\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"3\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;27\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;39\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"4\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;28\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;40\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"5\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;29\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;44\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"6\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;31\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;45\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"7\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;33\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;46\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"8\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;35\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;47\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"9\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;37\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;48\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"10\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;39\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;49\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"11\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;41\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;51\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"12\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;43\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;52\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t<oc id=\"13\">\n\r\n\r\t\t\t<cs id=\"1\" v=\"1\"/>\n\r\n\r\t\t\t<cc id=\"5\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"540;7;1;25\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t\t<cc id=\"6\">\n\r\n\r\t\t\t\t<it id=\"1\" v=\"690;8;1;25\"/>\n\r\n\r\t\t\t</cc>\n\r\n\r\t\t</oc>\n\r\n\r\t\t</gr>\n\r\n\r</doc>\n\r";
        }
        metaLiquidacion.getDefinicionLiquidacion().setXmlOperaciones(docXmlOperaciones);

        DDefinicionLiqF690V6 defLiquidacion =
                (DDefinicionLiqF690V6) parametrosLiq.get(PARAMETRO_DEFINICION_LIQUIDACION);
        defLiquidacion.setValorCasilla(IDCasillasFormulario690.ANNO, anno_presentacion);
        // Es un formulario aperi�dico.
        defLiquidacion.setValorCasilla(IDCasillasFormulario690.PERIODO, new Integer(1));

        return docLiquidacion;
    }

}
