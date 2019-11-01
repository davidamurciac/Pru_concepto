/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion;

import java.util.HashMap;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de acción utilizado para crear un objeto
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
public class DCmdAccCrearFormalizaBorradorDef extends DComandoAccion {
	private static final long serialVersionUID = -374396389L;

	/** Objeto de transporte de FormalizaBorradorDef */
	protected DFormalizaBorradorDefTO toFormalizaBorradorDef;
	/** Llave primaria de FormalizaBorradorDef */
	protected DFormalizaBorradorDefPKTO pkFormalizaBorradorDef;
	/** Atributos de FormalizaBorradorDef */
	protected DFormalizaBorradorDefAttTO attFormalizaBorradorDef;

	/**
	 * Para copiar el contenido del comando actual al comando enviado como
	 * parï¿½etro.
	 *
	 * @param comando
	 *            Comando sobre el cual copiar
	 */
	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccCrearFormalizaBorradorDef) {
			final DCmdAccCrearFormalizaBorradorDef copia = (DCmdAccCrearFormalizaBorradorDef) comando;
			copia.toFormalizaBorradorDef = toFormalizaBorradorDef;
			copia.pkFormalizaBorradorDef = pkFormalizaBorradorDef;
			copia.attFormalizaBorradorDef = attFormalizaBorradorDef;
		}
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 *
	 * @return Un Object con la copia del comando
	 */
	@Override
	public Object clonar() {
		return new DCmdAccCrearFormalizaBorradorDef();
	}

	/**
	 * Ejecuta el comando de acción.
	 */
	@Override
	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene la descripción del comando.
	 *
	 * @return Un String con la descripción del comando
	 */
	@Override
	public String getDescripcion() {
		return "Permite crear un objeto FormalizaBorradorDef";
	}

	public DFormalizaBorradorDefPKTO getPkFormalizaBorradorDef() {
		return pkFormalizaBorradorDef;
	}

	/**
	 * Inicializa la creación de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	public void inicializar(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		isOk = false;
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
	 * Mátodo para validar los parámetros inicializados, invocado previamente a
	 * la ejecución del comando.
	 *
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion
	 *             Si los parametros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		final Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(this.getClass().getName() + ":validar:toFormalizaBorradorDef", toFormalizaBorradorDef);
		parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef", attFormalizaBorradorDef);
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
		validarParametros("Crear", parametros);
		return true;
	}

}
