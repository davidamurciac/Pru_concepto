/**
 * 
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.constantes;

/**
 * Interfase que representa las constantes de estados para finalizar un circuito posterior de acuerdo a determinadas caracter�sticas.
 *
 * @author nfontechar 
 */
public interface IDConstantesEstadosProcesoFlujoPosterior {

	/**
	 * Constante de tipo <code>int</code> que indica que se trat� de generar una declaraci�n de fracci�n de a�o para un a�o gravable que no aplica. 
	 */
	public static final int MARCA_ERROR_FRACCION_ANIO=52;
	
	/**
	 * Constante de tipo <code>int</code> que indica que se encontr� una declaraci�n de renta en proceso de firmas.
	 */	
	public static final int MARCA_ERROR_EN_FIRMAS=53;
	
	/**
	 * Constante de tipo <code>int</code> que indica que el n�mero de declaraci�n de renta registrado para corregir, no corresponde con la �ltima declaraci�n v�lida para realizar correcci�n.
	 */
	public static final int MARCA_ERROR_NUM_DEC_RENTA_NO_ES_ULTIMA_VALIDA=55;
	
	/**
	 * Constante de tipo <code>int</code> que indica que ya existe una declaraci�n inicial presentada.
	 */
	public static final int MARCA_ERROR_EXISTE_INICIAL=56;
	
	/**
	 * Constante de tipo <code>int</code> que indica que no se encontr� una declaraci�n inicial presentada para corregir.
	 */
	public static final int MARCA_ERROR_NO_EXISTE_INICIAL_PARA_CORREGIR=57;
	
	/**
	 * Constante para el codigo de error que se debe utilizar al asociar una marca
	 */
	public static final int COD_ERROR_CON_MARCA = 100;
	
}/*fin de interface*/
