/**
 * 
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.constantes;

/**
 * Interfase que representa las constantes de estados para finalizar un circuito posterior de acuerdo a determinadas características.
 *
 * @author nfontechar 
 */
public interface IDConstantesEstadosProcesoFlujoPosterior {

	/**
	 * Constante de tipo <code>int</code> que indica que se trató de generar una declaración de fracción de año para un año gravable que no aplica. 
	 */
	public static final int MARCA_ERROR_FRACCION_ANIO=52;
	
	/**
	 * Constante de tipo <code>int</code> que indica que se encontró una declaración de renta en proceso de firmas.
	 */	
	public static final int MARCA_ERROR_EN_FIRMAS=53;
	
	/**
	 * Constante de tipo <code>int</code> que indica que el número de declaración de renta registrado para corregir, no corresponde con la última declaración válida para realizar corrección.
	 */
	public static final int MARCA_ERROR_NUM_DEC_RENTA_NO_ES_ULTIMA_VALIDA=55;
	
	/**
	 * Constante de tipo <code>int</code> que indica que ya existe una declaración inicial presentada.
	 */
	public static final int MARCA_ERROR_EXISTE_INICIAL=56;
	
	/**
	 * Constante de tipo <code>int</code> que indica que no se encontró una declaración inicial presentada para corregir.
	 */
	public static final int MARCA_ERROR_NO_EXISTE_INICIAL_PARA_CORREGIR=57;
	
	/**
	 * Constante para el codigo de error que se debe utilizar al asociar una marca
	 */
	public static final int COD_ERROR_CON_MARCA = 100;
	
}/*fin de interface*/
