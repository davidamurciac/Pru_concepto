package co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion;

import java.util.HashMap;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;

public class DCmdSrvFormalizarBorradorDefinitivo extends DComandoServicioInterno {

	/**
	 *
	 */
	private static final long serialVersionUID = -4741412803956994432L;

	protected Integer ANIO_GRAVABLE_DESDE = 2006;
	protected Integer ANIO_GRAVABLE_HASTA = 2011;
	protected Integer ANIO_GRAVABLE_HASTA_RENTA = 2015;
	protected Integer IDE_FORMATO_RENTA = 210;
	protected Long FECHA_HASTA = 20120110L;
	protected Long FECHA_HASTA_RENTA = 20161229L;
	protected Long fechaTransaccion;

	protected String error;
	protected DFormalizaBorradorDefTO docFormalizar;

	/**
	 * Para copiar el contenido del comando actual al comando enviado como
	 * parámetro.
	 *
	 * @param comando
	 *            Comando sobre el cual copiar
	 */
	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvFormalizarBorradorDefinitivo) {
			final DCmdSrvFormalizarBorradorDefinitivo copia = (DCmdSrvFormalizarBorradorDefinitivo) comando;
			copia.error = error;
			copia.docFormalizar = docFormalizar;
			copia.fechaTransaccion = fechaTransaccion;
		}
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 *
	 * @return Un Object con la copia del comando
	 */
	@Override
	public Object clonar() {
		return new DCmdSrvFormalizarBorradorDefinitivo();
	}

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion
	 *             Si ocurre algún error al realizar la la eliminación de
	 *             FormalizaBorradorDef
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getDescripcion() {
		return "Comando de acción que se encarga de la formalización de los borradores definitivos escritos en la tabla";
	}

	/**
	 * Inicializa la eliminación de FormalizaBorradorDef.
	 *
	 * @param pkFormalizaBorradorDef
	 *            Llave primaria de FormalizaBorradorDef
	 */
	public void inicializar(DFormalizaBorradorDefTO to) {
		docFormalizar = to;
	}

	/**
	 * Indica si el comando es auditable.
	 *
	 * @return true si el comando es auditable; false de lo contrario
	 */
	@Override
	public boolean isAuditable() {
		return true;
	}

	/**
	 * Método para validar los parámetros inicializados, invocado previamente a
	 * la ejecución del comando.
	 *
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion
	 *             Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		final Map<String, Object> parametros = new HashMap<String, Object>();
		validarParametros("Consultar", parametros);
		return true;
	}

}
