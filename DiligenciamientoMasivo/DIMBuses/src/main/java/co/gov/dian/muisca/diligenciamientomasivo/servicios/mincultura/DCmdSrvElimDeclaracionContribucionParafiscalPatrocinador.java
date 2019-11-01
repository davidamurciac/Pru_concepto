/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de servicio utilizado para eliminar un objeto DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvElimDeclaracionContribucionParafiscalPatrocinador extends DComandoServicioInterno {
	private static final long serialVersionUID = 905424983L; 

	/** Llave primaria de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador;
	
	/** Llave primaria de DeclaracionContribucionParafiscal */
	protected DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal;
	
	public static final int ELIMINAR = 1;
	public static final int ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL = 2;
	
	/** Tipo de operaci�n de consulta */
	protected int tipoOperacion = -1;
	
	/**
	 * Inicializa la eliminaci�n de DeclaracionContribucionParafiscalPatrocinador.
	 * @param pkDeclaracionContribucionParafiscalPatrocinador Llave primaria de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializar(DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador) {
		tipoOperacion = ELIMINAR;
		this.pkDeclaracionContribucionParafiscalPatrocinador = pkDeclaracionContribucionParafiscalPatrocinador;
	}
	
	/**
	 * Inicializa la eliminaci�n por DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	public void inicializarEliminarPorDeclaracionContribucionParafiscal(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal) {
		tipoOperacion = ELIMINAR;
		this.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
	}

	
	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre alg�n error al realizar la
	 * la eliminaci�n de DeclaracionContribucionParafiscalPatrocinador
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvElimDeclaracionContribucionParafiscalPatrocinador();
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
		return "Permite eliminar un objeto DeclaracionContribucionParafiscalPatrocinador";
	}

	/**
	 * M�todo para validar los par�metros inicializados, invocado
	 * previamente a la ejecuci�n del comando.
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		if(tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador",pkDeclaracionContribucionParafiscalPatrocinador);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem());
		} else if(tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal",pkDeclaracionContribucionParafiscal);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		}
		validarParametros("Eliminar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvElimDeclaracionContribucionParafiscalPatrocinador) {
			DCmdSrvElimDeclaracionContribucionParafiscalPatrocinador copia = (DCmdSrvElimDeclaracionContribucionParafiscalPatrocinador) comando;
			copia.pkDeclaracionContribucionParafiscalPatrocinador = pkDeclaracionContribucionParafiscalPatrocinador;
			copia.tipoOperacion = tipoOperacion;
			copia.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
		}
	}
}
