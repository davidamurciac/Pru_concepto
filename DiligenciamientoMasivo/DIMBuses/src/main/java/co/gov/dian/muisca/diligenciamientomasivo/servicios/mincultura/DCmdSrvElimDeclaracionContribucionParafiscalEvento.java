/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para eliminar un objeto DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvElimDeclaracionContribucionParafiscalEvento extends DComandoServicioInterno {
	private static final long serialVersionUID = 1261871166L; 

	/** Llave primaria de DeclaracionContribucionParafiscalEvento */
	protected DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento;
	
	/** Llave primaria de DeclaracionContribucionParafiscal */
	protected DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal;
	
	public static final int ELIMINAR = 1;
	public static final int ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL = 2;
	
	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	
	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscalEvento.
	 * @param pkDeclaracionContribucionParafiscalEvento Llave primaria de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializar(DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento) {
		tipoOperacion = ELIMINAR;
		this.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
	}
	/**
	 * Inicializa la eliminación por DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	public void inicializarEliminarPorDeclaracionContribucionParafiscal(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal) {
		tipoOperacion = ELIMINAR;
		this.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
	}

	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la eliminación de DeclaracionContribucionParafiscalEvento
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvElimDeclaracionContribucionParafiscalEvento();
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
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		if(tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento",pkDeclaracionContribucionParafiscalEvento);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeItem()",pkDeclaracionContribucionParafiscalEvento.getIdeItem());
		} else if(tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal",pkDeclaracionContribucionParafiscal);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		}
		validarParametros("Eliminar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvElimDeclaracionContribucionParafiscalEvento) {
			DCmdSrvElimDeclaracionContribucionParafiscalEvento copia = (DCmdSrvElimDeclaracionContribucionParafiscalEvento) comando;
			copia.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
			copia.tipoOperacion = tipoOperacion;
			copia.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
		}
	}
}
