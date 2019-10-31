/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para consultar objetos
 * FormalizaBorradorDef.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: DIAN
 * </p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 *
 *          <pre>
 * $Log[10]:$
 *          </pre>
 */
public class DCmdSrvConsLstFormalizaBorradorDef extends DComandoServicioInterno {
	private static final long serialVersionUID = 195267622L;

	public static final int CONSULTA_GENERICA = 0;
	public static final int CONSULTA_TODOS = 1;

	protected DFormalizaBorradorDefTO toFormalizaBorradorDef;

	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	/** Colección de objetos DFormalizaBorradorDefTO */
	protected Collection<DFormalizaBorradorDefTO> objetosFormalizaBorradorDef;

	/**
	 * Para copiar el contenido del comando actual al comando enviado como
	 * parámetro.
	 *
	 * @param comando
	 *            Comando sobre el cual copiar
	 */
	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvConsLstFormalizaBorradorDef) {
			final DCmdSrvConsLstFormalizaBorradorDef copia = (DCmdSrvConsLstFormalizaBorradorDef) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.objetosFormalizaBorradorDef = objetosFormalizaBorradorDef;
			copia.toFormalizaBorradorDef = toFormalizaBorradorDef;
		}
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 *
	 * @return Un Object con la copia del comando
	 */
	@Override
	public Object clonar() {
		return new DCmdSrvConsLstFormalizaBorradorDef();
	}

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion
	 *             Si ocurre algún error al realizar la la consulta de
	 *             FormalizaBorradorDef
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Devuelve la colección de objetos FormalizaBorradorDef que se hayan
	 * consultado.
	 *
	 * @return Un Collection con objetos DFormalizaBorradorDefTO
	 */
	public Collection<DFormalizaBorradorDefTO> getColeccionFormalizaBorradorDef() {
		return objetosFormalizaBorradorDef;
	}

	/**
	 * Obtiene la descripción del comando.
	 *
	 * @return Un String con la descripción del comando
	 */
	@Override
	public String getDescripcion() {
		return "Permite consultar objetos FormalizaBorradorDef";
	}

	/**
	 * Inicializa la consulta genérica de FormalizaBorradorDef.
	 *
	 * @param attFormalizaBorradorDef
	 *            Atributos de FormalizaBorradorDef
	 */
	public void inicializarConsultaGenerica(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toFormalizaBorradorDef = toFormalizaBorradorDef;
	}

	/**
	 * Inicializa la consulta Todos.
	 *
	 */
	public void inicializarConsultaTodos() {
		tipoOperacion = CONSULTA_TODOS;
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
	 * @return true si los paretros son váidos; false de lo contrario
	 * @throws DValidarExcepcion
	 *             Si los parámetros no son váidos
	 */
	public boolean validar() throws DValidarExcepcion {
		final Map<String, Object> parametros = new HashMap<String, Object>();
		switch (tipoOperacion) {

		case CONSULTA_GENERICA:
			parametros.put(this.getClass().getName() + ":validar:toFormalizaBorradorDef", toFormalizaBorradorDef);
			break;
		case CONSULTA_TODOS:
			break;

		default:
			throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos FormalizaBorradorDef"),
					getMensajeOperInvalida());
		}
		validarParametros("Listar", parametros);
		return true;
	}
}
