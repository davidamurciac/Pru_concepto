package co.gov.dian.muisca.diligenciamientomasivo.liquidacion.delegados;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormato;
import co.gov.dian.muisca.diligenciamientomasivo.liquidacion.IDCasillasFormulario690;
import co.gov.dian.muisca.entradasalida.liquidacion.IDDefinicionLiquidacion;
import co.gov.dian.muisca.entradasalida.liquidacion.IDLiquidacion;
import co.gov.dian.muisca.diligenciamientomasivo.liquidacion.impl.DDefinicionLiqF690V6;
import co.gov.dian.muisca.diligenciamiento.liquidacion.delegados.DDelegadoPreparar;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DMetaLiquidacion;
import co.gov.dian.muisca.rut.general.to.DPersonaRutTO;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsPersonaRut;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author dmahechav
 * @version 1.0
 */
public class DDelegadoPreparar570v9 extends DDelegadoPreparar {

	public static String anno_presentacion;
	

	public DDelegadoPreparar570v9() {

    }

    /**
     *
     * @return IDDocumento
     * @throws DExcepcion
     */
    public IDDocumento cargarLiquidacion() throws DExcepcion {

    	SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy");
    	anno_presentacion = formatoFecha.format(new Date());

    	docLiquidacion = (IDDocumento) parametrosLiq.get(PARAMETRO_DOCUMENTO);
        DCmdSrvConsPersonaRut consPersona = (DCmdSrvConsPersonaRut)
        		getServicio("DCmdSrvConsPersonaRut"); 

        obtenerVersionVigente(690);

        /** Selecciona el ID Persona RUT apropiado para la liquidación. */

        
        
        int tipoDocImportador = ((Number) DDocumentoUtil.getValorCasillaSimp(
                IDConstantesFormato.IDE_CASILLA_TIPO_DOCUMENTO, IDLiquidacion.GRUPO_DOC_LIQUIDACION,
                IDLiquidacion.OCURRENCIA_DOC_LIQUIDACION, docLiquidacion)).intValue();

        String numDocImportador = ((Number) DDocumentoUtil.getValorCasillaSimp(
                IDConstantesFormato.IDE_CASILLA_NUM_IDENTIFICACION, IDLiquidacion.GRUPO_DOC_LIQUIDACION,
                IDLiquidacion.OCURRENCIA_DOC_LIQUIDACION, docLiquidacion)).toString();
        
        
        consPersona.inicializarConsultarPorIdentificacion(tipoDocImportador, numDocImportador, true); 
        consPersona.ejecutar();         
        DPersonaRutTO personaRut = consPersona.getPersonaRut();

        idPersonaRut = personaRut.getPK().getIdeMascaraRut();;

        DMetaLiquidacion metaLiquidacion =
            (DMetaLiquidacion) parametrosLiq.get(PARAMETRO_METALIQUIDACION);

        metaLiquidacion.setDefinicionLiquidacion((IDDefinicionLiquidacion)
                parametrosLiq.get(PARAMETRO_DEFINICION_LIQUIDACION));
        metaLiquidacion.setDescripcion("Parámetros para la Liquidación del Formulario 570 versión 9");
        metaLiquidacion.setIdPersonaRut(idPersonaRut);
        metaLiquidacion.setIdFormatoDocIn(570);
        metaLiquidacion.setVersionFtoDocIn(9);
        metaLiquidacion.setIdFormatoDocOut(690);
        metaLiquidacion.setVersionFtoDocOut(version);
        metaLiquidacion.setIdLiquidacion(-1); /**@todo Está por definir su uso. */
        metaLiquidacion.setVersionLiq(0); /**@todo Está por definir su uso. */

        String docXmlOperaciones = 	"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+
							        " <doc xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"\\..\\documentos-simplificado.xsd\" cf=\"10401\" vf=\"1\" id=\"\">"+
							        " 	<gr id=\"1\">"+
							        " 		<oc id=\"1\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;32\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;24\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"2\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;4\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;32\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"3\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;107\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;33\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"4\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;108\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;34\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"5\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;78\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;44\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"6\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;81\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;45\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"7\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;84\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;46\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"8\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;87\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;47\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"9\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;90\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;48\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"10\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;97\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;49\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		<oc id=\"11\">"+
							        " 			<cs id=\"1\" v=\"1\"/>"+
							        " 			<cc id=\"5\">"+
							        " 				<it id=\"1\" v=\"570;9;1;99\"/>"+
							        " 			</cc>"+
							        " 			<cc id=\"6\">"+
							        " 				<it id=\"1\" v=\"690;8;1;51\"/>"+
							        " 			</cc>"+
							        " 		</oc>"+
							        " 		</gr>"+
							        " </doc>";



        metaLiquidacion.getDefinicionLiquidacion().setXmlOperaciones(docXmlOperaciones);
        DDefinicionLiqF690V6 defLiquidacion =
            (DDefinicionLiqF690V6) parametrosLiq.get(PARAMETRO_DEFINICION_LIQUIDACION);
        defLiquidacion.setValorCasilla(IDCasillasFormulario690.ANNO, anno_presentacion);
        // Es un formulario aperiódico.
    	defLiquidacion.setValorCasilla(IDCasillasFormulario690.PERIODO, new Integer(1));

        return docLiquidacion;
    }


}
