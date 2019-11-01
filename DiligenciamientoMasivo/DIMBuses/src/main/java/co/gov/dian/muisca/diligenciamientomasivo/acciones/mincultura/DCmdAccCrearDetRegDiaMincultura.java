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
 * <p>Descripcion: Comando de acción utilizado para crear un objeto DetRegDiaMincultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccCrearDetRegDiaMincultura extends DComandoAccion {
	private static final long serialVersionUID = -770725248L; 

	/** Objeto de transporte de DetRegDiaMincultura */
	protected DDetRegDiaMinculturaTO toDetRegDiaMincultura;
	/** Llave primaria de DetRegDiaMincultura */
	protected DDetRegDiaMinculturaPKTO pkDetRegDiaMincultura;
	/** Atributos de DetRegDiaMincultura */
	protected DDetRegDiaMinculturaAttTO attDetRegDiaMincultura;

	/**
	 * Inicializa la creación de DetRegDiaMincultura.
	 * @param toDetRegDiaMincultura Objeto de Transporte de DetRegDiaMincultura
	 */
	public void inicializar(DDetRegDiaMinculturaTO toDetRegDiaMincultura) {
		isOk = false;
		this.toDetRegDiaMincultura = toDetRegDiaMincultura;
		if (toDetRegDiaMincultura != null) {
			pkDetRegDiaMincultura = this.toDetRegDiaMincultura.getPK();
			attDetRegDiaMincultura = this.toDetRegDiaMincultura.getAtt();
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
		return new DCmdAccCrearDetRegDiaMincultura();
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
		return "Permite crear un objeto DetRegDiaMincultura";
	}

	/**
	 * Mátodo para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parametros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:toDetRegDiaMincultura",toDetRegDiaMincultura);
		parametros.put(this.getClass().getName()+":validar:attDetRegDiaMincultura",attDetRegDiaMincultura);
		parametros.put(this.getClass().getName()+":validar:pkDetRegDiaMincultura",pkDetRegDiaMincultura);
		parametros.put(this.getClass().getName()+":validar:pkDetRegDiaMincultura.getIdeDetalleDiario()",pkDetRegDiaMincultura.getIdeDetalleDiario());
		parametros.put(this.getClass().getName()+":validar:attDetRegDiaMincultura.getIdeRegistroDiario()",attDetRegDiaMincultura.getIdeRegistroDiario());
		parametros.put(this.getClass().getName()+":validar:attDetRegDiaMincultura.getFecRegistro()",attDetRegDiaMincultura.getFecRegistro());
		parametros.put(this.getClass().getName()+":validar:attDetRegDiaMincultura.getFecCambio()",attDetRegDiaMincultura.getFecCambio());
		validarParametros("Crear",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccCrearDetRegDiaMincultura) {
			DCmdAccCrearDetRegDiaMincultura copia = (DCmdAccCrearDetRegDiaMincultura) comando;
			copia.toDetRegDiaMincultura = toDetRegDiaMincultura;
			copia.pkDetRegDiaMincultura = pkDetRegDiaMincultura;
			copia.attDetRegDiaMincultura = attDetRegDiaMincultura;
		}
	}
}
