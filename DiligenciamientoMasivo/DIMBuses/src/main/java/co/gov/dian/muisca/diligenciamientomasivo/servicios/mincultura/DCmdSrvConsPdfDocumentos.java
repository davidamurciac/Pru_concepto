/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura;

import java.util.HashMap;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para consultar un objeto DCmdSrvConsPdfDocumentos.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsPdfDocumentos extends DComandoServicioInterno {
	private static final long serialVersionUID = -525516161L; 

	/** Objeto documento */
	protected IDDocumento documento;
	/** objeto identificador documento */
	protected DIdentificadorDoc identificadorDoc;	
	/** Arreglo de bytes para guardar el pdf*/
	protected byte[] pdfBytes;	
	/**Contiene la traza de error para facilitar el seguimiento del error**/
	protected String trazaError;
	
	/**Contiene el id del documento a consultar**/
	protected Long idDocumento;
	/**Contiene el número de repetición del documento a consultar**/
	protected Integer numRepeticion;
	/**Contiene el id del formato a consultar**/
	protected Integer ideFormato;
	/**Contiene el número de repositorio del documento a consultar**/
	protected int ideRepositorio;

	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	
	
	public static final int CONSULTAR_IDENTIFICADORES = 0;
	public static final int CONSULTAR_NUM_DOCUMENTO = 1;

	/**
	 * 
	 * @param documento
	 * @param identificadorDoc
	 */
	public void inicializar(IDDocumento documento, DIdentificadorDoc identificadorDoc) {		
		this.documento = documento;
		this.identificadorDoc = identificadorDoc;
		this.tipoOperacion = CONSULTAR_IDENTIFICADORES;
	}
	
	
	/**
	 * 
	 * @param idDocumento
	 * @param numRepeticion
	 * @param ideFormato
	 * @param ideRepositorio
	 */
	public void inicializar(Long idDocumento, Integer numRepeticion, Integer ideFormato, int ideRepositorio){
		this.idDocumento = idDocumento;
		this.numRepeticion = numRepeticion;
		this.ideFormato = ideFormato;
		this.ideRepositorio = ideRepositorio;
		this.tipoOperacion = CONSULTAR_NUM_DOCUMENTO;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] getPdfBytes() {
		return pdfBytes;
	}

	/**
	 * 
	 * @param pdfBytes
	 */
	public void setPdfBytes(byte[] pdfBytes) {
		this.pdfBytes = pdfBytes;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTrazaError() {
		return trazaError;
	}

	/**
	 * 
	 * @param trazaError
	 */
	public void setTrazaError(String trazaError) {
		this.trazaError = trazaError;
	}
	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la consulta de DCmdSrvConsPdfDocumentos
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvConsPdfDocumentos();
	}

	/**
	 * Indica si el comando es auditable.
	 * @return true si el comando es auditable; false de lo contrario
	 */
	public boolean isAuditable() {
		return true;
	}

	/**
	 * Obtiene la descripción del comando.
	 * @return Un String con la descripción del comando
	 */
	public String getDescripcion() {
		return "Permite consultar un objeto DCmdSrvConsPdfDocumentos";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:IDDocumento", documento);
		parametros.put(this.getClass().getName()+":validar:DIdentificadorDoc", identificadorDoc);
		validarParametros("Consulta",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvConsPdfDocumentos) {
			DCmdSrvConsPdfDocumentos copia = (DCmdSrvConsPdfDocumentos) comando;
			copia.documento = documento;
			copia.identificadorDoc = identificadorDoc;
			copia.pdfBytes = pdfBytes;
			copia.trazaError = trazaError;
			copia.idDocumento = idDocumento;
			copia.numRepeticion = numRepeticion;
			copia.ideFormato = ideFormato;
			copia.ideRepositorio = ideRepositorio;
			copia.tipoOperacion = tipoOperacion;
		}
	}
}
