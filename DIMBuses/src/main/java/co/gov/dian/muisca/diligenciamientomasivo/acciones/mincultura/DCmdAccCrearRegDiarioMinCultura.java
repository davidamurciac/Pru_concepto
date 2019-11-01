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
 * <p>Descripcion: Comando de acción utilizado para crear un objeto RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccCrearRegDiarioMinCultura extends DComandoAccion {
	private static final long serialVersionUID = 575083445L; 

	/** Objeto de transporte de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaTO toRegDiarioMinCultura;
	/** Llave primaria de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura;
	/** Atributos de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaAttTO attRegDiarioMinCultura;

	/**
	 * Inicializa la creación de RegDiarioMinCultura.
	 * @param toRegDiarioMinCultura Objeto de Transporte de RegDiarioMinCultura
	 */
	public void inicializar(DRegDiarioMinCulturaTO toRegDiarioMinCultura) {
		isOk = false;
		this.toRegDiarioMinCultura = toRegDiarioMinCultura;
		if (toRegDiarioMinCultura != null) {
			pkRegDiarioMinCultura = this.toRegDiarioMinCultura.getPK();
			attRegDiarioMinCultura = this.toRegDiarioMinCultura.getAtt();
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
		return new DCmdAccCrearRegDiarioMinCultura();
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
		return "Permite crear un objeto RegDiarioMinCultura";
	}

	/**
	 * Mátodo para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parametros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:toRegDiarioMinCultura",toRegDiarioMinCultura);
		parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura",attRegDiarioMinCultura);
		parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura",pkRegDiarioMinCultura);
		parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura.getIdeRegistroDiario()",pkRegDiarioMinCultura.getIdeRegistroDiario());
		parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getFecRegistro()",attRegDiarioMinCultura.getFecRegistro());
		parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getCodEstado()",attRegDiarioMinCultura.getCodEstado());
		parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getDirServidorProceso()",attRegDiarioMinCultura.getDirServidorProceso());
		parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getValDescripcionProceso()",attRegDiarioMinCultura.getValDescripcionProceso());
		parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getFecCambio()",attRegDiarioMinCultura.getFecCambio());
		validarParametros("Crear",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccCrearRegDiarioMinCultura) {
			DCmdAccCrearRegDiarioMinCultura copia = (DCmdAccCrearRegDiarioMinCultura) comando;
			copia.toRegDiarioMinCultura = toRegDiarioMinCultura;
			copia.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
			copia.attRegDiarioMinCultura = attRegDiarioMinCultura;
		}
	}
}
