package co.gov.dian.muisca.diligenciamientomasivo.general.enums;

public enum DEnumEstadosPrMigracion {
	ERROR_PROCESANDO(1, "Error procesando"), POR_PROCESAR(2, "Por procesar"), PROCESANDO(3, "Procesando"), TERMINADO(4,
			"Terminado");

	/**
	 * Almacena el código del tipo de estado
	 */
	private Integer codTipoEstado;

	/**
	 * Almacena el nombre del estado
	 */
	private String nomEstado;

	/**
	 * Constructor de la Enumeración
	 *
	 * @param codTipoEstado
	 *            código del tipo de estado
	 */
	private DEnumEstadosPrMigracion(Integer codTipoEstado, String nomEstado) {
		this.codTipoEstado = codTipoEstado;
		this.nomEstado = nomEstado;
	}

	public Integer getCodTipoEstado() {
		return codTipoEstado;
	}

	/**
	 * Retorna el codTipoEstado
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return nomEstado;
	}

}
