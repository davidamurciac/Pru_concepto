/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para consultar un objeto FormalizaBorradorDef.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsFormalizaBorradorDef extends DComandoServicioInterno {
	private static final long serialVersionUID = 892675753L; 

	/** Objeto de transporte de FormalizaBorradorDef */
	protected DFormalizaBorradorDefTO toFormalizaBorradorDef;
	/** Llave primaria de FormalizaBorradorDef */
	protected DFormalizaBorradorDefPKTO pkFormalizaBorradorDef;
	/** Atributos de FormalizaBorradorDef */
	protected DFormalizaBorradorDefAttTO attFormalizaBorradorDef;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkFormalizaBorradorDef Llave primaria de FormalizaBorradorDef
	 */
	public void inicializar(DFormalizaBorradorDefPKTO pkFormalizaBorradorDef) {
		toFormalizaBorradorDef = null;
		attFormalizaBorradorDef = null;
		this.pkFormalizaBorradorDef = pkFormalizaBorradorDef;
	}

	/**
	 * Devuelve el objeto FormalizaBorradorDef que se haya consultado.
	 * @return Un objeto DFormalizaBorradorDefTO
	 */
	public DFormalizaBorradorDefTO getFormalizaBorradorDef() {
		return toFormalizaBorradorDef;
	}

	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre alg�n error al realizar la
	 * la consulta de FormalizaBorradorDef
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvConsFormalizaBorradorDef();
	}

	/**
	 * Indica si el comando es auditable.
	 * @return true si el comando es auditable; false de lo contrario
	 */
	public boolean isAuditable() {
		return true;
	}

	/**
	 * Obtiene la descripci�n del comando.
	 * @return Un String con la descripci�n del comando
	 */
	public String getDescripcion() {
		return "Permite consultar un objeto FormalizaBorradorDef";
	}

	/**
	 * M�todo para validar los par�metros inicializados, invocado
	 * previamente a la ejecuci�n del comando.
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkFormalizaBorradorDef",pkFormalizaBorradorDef);
		parametros.put(this.getClass().getName()+":validar:pkFormalizaBorradorDef.getIdeProceso()",pkFormalizaBorradorDef.getIdeProceso());
		validarParametros("Consulta",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvConsFormalizaBorradorDef) {
			DCmdSrvConsFormalizaBorradorDef copia = (DCmdSrvConsFormalizaBorradorDef) comando;
			copia.toFormalizaBorradorDef = toFormalizaBorradorDef;
			copia.pkFormalizaBorradorDef = pkFormalizaBorradorDef;
			copia.attFormalizaBorradorDef = attFormalizaBorradorDef;
		}
	}
}
