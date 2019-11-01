/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acción utilizado para actualizar un objeto FormalizaBorradorDef.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccActFormalizaBorradorDef extends DComandoAccion {
	private static final long serialVersionUID = -650196968L; 

	/** Objeto de transporte de FormalizaBorradorDef */
	protected DFormalizaBorradorDefTO toFormalizaBorradorDef;
	/** Llave primaria de FormalizaBorradorDef */
	protected DFormalizaBorradorDefPKTO pkFormalizaBorradorDef;
	/** Atributos de FormalizaBorradorDef */
	protected DFormalizaBorradorDefAttTO attFormalizaBorradorDef;

	/**
	 * Inicializa la actualización de FormalizaBorradorDef.
	 * @param toFormalizaBorradorDef Objeto de Transporte de FormalizaBorradorDef
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
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdAccActFormalizaBorradorDef();
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
		return "Permite actualizar un objeto FormalizaBorradorDef";
	}

	/**
	 * Méodo para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:toFormalizaBorradorDef",toFormalizaBorradorDef);
		parametros.put(this.getClass().getName()+":validar:pkFormalizaBorradorDef",pkFormalizaBorradorDef);
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef",attFormalizaBorradorDef);
		parametros.put(this.getClass().getName()+":validar:pkFormalizaBorradorDef.getIdeProceso()",pkFormalizaBorradorDef.getIdeProceso());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getIdeDocumento()",attFormalizaBorradorDef.getIdeDocumento());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getNumRepeticion()",attFormalizaBorradorDef.getNumRepeticion());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getIdeFormato()",attFormalizaBorradorDef.getIdeFormato());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getNumVersionFormato()",attFormalizaBorradorDef.getNumVersionFormato());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getIdeDocumentoRecibo()",attFormalizaBorradorDef.getIdeDocumentoRecibo());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getNumRepeticionRecibo()",attFormalizaBorradorDef.getNumRepeticionRecibo());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getIdeFormatoRecibo()",attFormalizaBorradorDef.getIdeFormatoRecibo());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getNumVersionFormatoRecibo()",attFormalizaBorradorDef.getNumVersionFormatoRecibo());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getIdePersonaRut()",attFormalizaBorradorDef.getIdePersonaRut());
		parametros.put(this.getClass().getName()+":validar:attFormalizaBorradorDef.getIndEstado()",attFormalizaBorradorDef.getIndEstado());
		validarParametros("Actualizar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccActFormalizaBorradorDef) {
			DCmdAccActFormalizaBorradorDef copia = (DCmdAccActFormalizaBorradorDef) comando;
			copia.toFormalizaBorradorDef = toFormalizaBorradorDef;
			copia.pkFormalizaBorradorDef = pkFormalizaBorradorDef;
			copia.attFormalizaBorradorDef = attFormalizaBorradorDef;
		}
	}
}
