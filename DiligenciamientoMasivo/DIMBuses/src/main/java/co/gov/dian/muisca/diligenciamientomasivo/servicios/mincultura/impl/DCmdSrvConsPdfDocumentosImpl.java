/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.impl;


import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.*;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.delegados.DDelegadoAccGenerarPDF;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para consultar un pdf de documento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsPdfDocumentosImpl extends DCmdSrvConsPdfDocumentos {

	private static final long serialVersionUID = 1078386533L; 

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * consulta del pdf de algún documento
	 */
	protected void ejecutarComando() throws DExcepcion {
		
		try {
			//Instancia el delegado para consultar el pdf
			DDelegadoAccGenerarPDF delegadoPdf = new DDelegadoAccGenerarPDF(this, documento.getFormato().getIdRepositorio());			
			switch (tipoOperacion) {
			case CONSULTAR_IDENTIFICADORES:
				//consulta el pdf del documento				 		
				pdfBytes = delegadoPdf.generarPdf(documento, identificadorDoc, "", true);
				break;
			case CONSULTAR_NUM_DOCUMENTO:				
				//consulta el pdf del documento			
				pdfBytes = delegadoPdf.generarPdf(idDocumento, numRepeticion, "", ideFormato);
				break;
			default:
				throw new DValidarExcepcion(getMensajeGeneral("la creación",
						"de documentos pdf"),
						getMensajeOperInvalida());
			}
		}

		catch (Exception e) {
			trazaError = e.getMessage();
			e.printStackTrace();			
		}

		
	}
}
