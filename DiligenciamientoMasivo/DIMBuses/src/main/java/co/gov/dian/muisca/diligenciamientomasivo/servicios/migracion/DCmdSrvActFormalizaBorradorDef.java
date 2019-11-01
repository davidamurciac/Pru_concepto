/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion;

import java.util.HashMap;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para actualizar un objeto
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
public class DCmdSrvActFormalizaBorradorDef extends DComandoServicioInterno {
	private static final long serialVersionUID = 1362202460L;

	protected static final int ACTUALIZAR = 0;
	protected static final int ACTUALIZAR_ESTADO = 1;
	/** Objeto de transporte de FormalizaBorradorDef */
	protected DFormalizaBorradorDefTO toFormalizaBorradorDef;

	/** Llave primaria de FormalizaBorradorDef */
	protected DFormalizaBorradorDefPKTO pkFormalizaBorradorDef;
	/** Atributos de FormalizaBorradorDef */
	protected DFormalizaBorradorDefAttTO attFormalizaBorradorDef;

	protected Integer indEstado;

	protected String error;
	/** Tipo de operaci�n de consulta */
	protected int tipoOperacion = -1;

	/**
	 * Para copiar el contenido del comando actual al comando enviado como
	 * par�metro.
	 *
	 * @param comando
	 *            Comando sobre el cual copiar
	 */
	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvActFormalizaBorradorDef) {
			final DCmdSrvActFormalizaBorradorDef copia = (DCmdSrvActFormalizaBorradorDef) comando;
			copia.toFormalizaBorradorDef = toFormalizaBorradorDef;
			copia.pkFormalizaBorradorDef = pkFormalizaBorradorDef;
			copia.attFormalizaBorradorDef = attFormalizaBorradorDef;
			copia.tipoOperacion = tipoOperacion;
		}
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 *
	 * @return Un Object con la copia del comando
	 */
	@Override
	public Object clonar() {
		return new DCmdSrvActFormalizaBorradorDef();
	}

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion
	 *             Si ocurre alg�n error al realizar la la actualizaci�n de
	 *             FormalizaBorradorDef
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene la descripci�n del comando.
	 *
	 * @return Un String con la descripci�n del comando
	 */
	@Override
	public String getDescripcion() {
		return "Permite actualizar un objeto FormalizaBorradorDef";
	}

	/**
	 * Inicializa la actualizaci�n de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	public void inicializar(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		tipoOperacion = ACTUALIZAR;
		this.toFormalizaBorradorDef = toFormalizaBorradorDef;
		if (toFormalizaBorradorDef != null) {
			pkFormalizaBorradorDef = this.toFormalizaBorradorDef.getPK();
			attFormalizaBorradorDef = this.toFormalizaBorradorDef.getAtt();
		}
	}

	/**
	 * Inicializa la actualizaci�n del estado de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	public void inicializarActualizarEstado(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		tipoOperacion = ACTUALIZAR_ESTADO;
		this.toFormalizaBorradorDef = toFormalizaBorradorDef;
		if (toFormalizaBorradorDef != null) {
			pkFormalizaBorradorDef = this.toFormalizaBorradorDef.getPK();
			attFormalizaBorradorDef = this.toFormalizaBorradorDef.getAtt();
		}
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
	 * M�todo para validar los par�metros inicializados, invocado previamente a
	 * la ejecuci�n del comando.
	 *
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion
	 *             Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		final Map<String, Object> parametros = new HashMap<String, Object>();

		switch (tipoOperacion) {

		case ACTUALIZAR:
			parametros.put(this.getClass().getName() + ":validar:toFormalizaBorradorDef", toFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:pkFormalizaBorradorDef", pkFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef", attFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:pkFormalizaBorradorDef.getIdeProceso()",
					pkFormalizaBorradorDef.getIdeProceso());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdeDocumento()",
					attFormalizaBorradorDef.getIdeDocumento());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getNumRepeticion()",
					attFormalizaBorradorDef.getNumRepeticion());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdeFormato()",
					attFormalizaBorradorDef.getIdeFormato());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getNumVersionFormato()",
					attFormalizaBorradorDef.getNumVersionFormato());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdeDocumentoRecibo()",
					attFormalizaBorradorDef.getIdeDocumentoRecibo());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getNumRepeticionRecibo()",
					attFormalizaBorradorDef.getNumRepeticionRecibo());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdeFormatoRecibo()",
					attFormalizaBorradorDef.getIdeFormatoRecibo());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getNumVersionFormatoRecibo()",
					attFormalizaBorradorDef.getNumVersionFormatoRecibo());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdePersonaRut()",
					attFormalizaBorradorDef.getIdePersonaRut());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIndEstado()",
					attFormalizaBorradorDef.getIndEstado());
			break;

		case ACTUALIZAR_ESTADO:
			parametros.put(this.getClass().getName() + ":validar:toFormalizaBorradorDef", toFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:pkFormalizaBorradorDef", pkFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef", attFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:pkFormalizaBorradorDef.getIdeProceso()",
					pkFormalizaBorradorDef.getIdeProceso());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIndEstado()",
					attFormalizaBorradorDef.getIndEstado());

			break;

		default:
			throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos FormalizaBorradorDef"),
					getMensajeOperInvalida());
		}

		validarParametros("Actualizar", parametros);
		return true;
	}
}
