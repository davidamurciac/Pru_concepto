/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.mincultura;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acción utilizado para eliminar un objeto DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccElimDeclaracionContribucionParafiscalEvento extends DComandoAccion {
	private static final long serialVersionUID = 848324128L; 

	/** Llave primaria de DeclaracionContribucionParafiscalEvento */
	protected DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento;

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscalEvento.
	 * @param pkDeclaracionContribucionParafiscalEvento Llave primaria de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializar(DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento) {
		isOk = false;
		this.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
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
		return new DCmdAccElimDeclaracionContribucionParafiscalEvento();
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
		return "Permite eliminar un objeto DeclaracionContribucionParafiscalEvento";
	}

	/**
	 * Méodo para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parímetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parímetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento",pkDeclaracionContribucionParafiscalEvento);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeItem()",pkDeclaracionContribucionParafiscalEvento.getIdeItem());
		validarParametros("Eliminar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccElimDeclaracionContribucionParafiscalEvento) {
			DCmdAccElimDeclaracionContribucionParafiscalEvento copia = (DCmdAccElimDeclaracionContribucionParafiscalEvento) comando;
			copia.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
		}
	}
}
