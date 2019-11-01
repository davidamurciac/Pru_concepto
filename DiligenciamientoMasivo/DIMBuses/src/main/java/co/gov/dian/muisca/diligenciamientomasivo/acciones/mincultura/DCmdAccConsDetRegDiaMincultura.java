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
 * <p>Descripcion: Comando de acción utilizado para consultar un objeto DetRegDiaMincultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsDetRegDiaMincultura extends DComandoAccion {
	private static final long serialVersionUID = -798536338L; 

	/** Objeto de transporte de DetRegDiaMincultura */
	protected DDetRegDiaMinculturaTO toDetRegDiaMincultura;
	/** Llave primaria de DetRegDiaMincultura */
	protected DDetRegDiaMinculturaPKTO pkDetRegDiaMincultura;
	/** Atributos de DetRegDiaMincultura */
	protected DDetRegDiaMinculturaAttTO attDetRegDiaMincultura;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDetRegDiaMincultura Llave primaria de DetRegDiaMincultura
	 */
	public void inicializar(DDetRegDiaMinculturaPKTO pkDetRegDiaMincultura) {
		isOk = false;
		toDetRegDiaMincultura = null;
		attDetRegDiaMincultura = null;
		this.pkDetRegDiaMincultura = pkDetRegDiaMincultura;
	}

	/**
	 * Devuelve el objeto DetRegDiaMincultura que se haya consultado.
	 * @return Un objeto DDetRegDiaMinculturaTO
	 */
	public DDetRegDiaMinculturaTO getDetRegDiaMincultura() {
		return toDetRegDiaMincultura;
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
		return new DCmdAccConsDetRegDiaMincultura();
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
		return "Permite consultar un objeto DetRegDiaMincultura";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkDetRegDiaMincultura",pkDetRegDiaMincultura);
		parametros.put(this.getClass().getName()+":validar:pkDetRegDiaMincultura.getIdeDetalleDiario()",pkDetRegDiaMincultura.getIdeDetalleDiario());
		validarParametros("Consulta",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsDetRegDiaMincultura) {
			DCmdAccConsDetRegDiaMincultura copia = (DCmdAccConsDetRegDiaMincultura) comando;
			copia.toDetRegDiaMincultura = toDetRegDiaMincultura;
			copia.pkDetRegDiaMincultura = pkDetRegDiaMincultura;
			copia.attDetRegDiaMincultura = attDetRegDiaMincultura;
		}
	}
}
