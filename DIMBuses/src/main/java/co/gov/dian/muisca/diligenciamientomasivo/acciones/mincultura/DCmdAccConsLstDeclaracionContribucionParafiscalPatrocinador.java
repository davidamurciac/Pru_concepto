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
 * <p>Descripcion: Comando de acción utilizado para consultar objetos DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstDeclaracionContribucionParafiscalPatrocinador extends DComandoAccion {
	private static final long serialVersionUID = -589102928L; 
	
	public static final int CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL = 0;

	protected static final int CONSULTA_GENERICA = 1;

	/** Llave primaria de DeclaracionContribucionParafiscal */
	protected DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal;

	protected DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador;


	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	/** Colección de objetos DDeclaracionContribucionParafiscalPatrocinadorTO */
	protected Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> objetosDeclaracionContribucionParafiscalPatrocinador;

	/**
	 * Inicializa la consulta por DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	public void inicializarConsultarPorDeclaracionContribucionParafiscal(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal) {
		isOk = false;
		tipoOperacion = CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL;
		this.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
		objetosDeclaracionContribucionParafiscalPatrocinador = null;
	}


	/**
	 * Inicializa la consulta genérica de DeclaracionContribucionParafiscalPatrocinador.
	 * @param attDeclaracionContribucionParafiscalPatrocinador Atributos de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;

	}

	/**
	 * Devuelve la colección de objetos DeclaracionContribucionParafiscalPatrocinador que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalPatrocinadorTO
	 */
	public Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> getColeccionDeclaracionContribucionParafiscalPatrocinador() {
		return objetosDeclaracionContribucionParafiscalPatrocinador;
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
		return new DCmdAccConsLstDeclaracionContribucionParafiscalPatrocinador();
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
		return "Permite consultar objetos DeclaracionContribucionParafiscalPatrocinador";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		switch (tipoOperacion) {
			case CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL:
				parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal",pkDeclaracionContribucionParafiscal);
				parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
				parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
				break;

			case CONSULTA_GENERICA:
				parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalPatrocinador",toDeclaracionContribucionParafiscalPatrocinador);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DeclaracionContribucionParafiscalPatrocinador"), getMensajeOperInvalida());
		}
		validarParametros("Listar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsLstDeclaracionContribucionParafiscalPatrocinador) {
			DCmdAccConsLstDeclaracionContribucionParafiscalPatrocinador copia = (DCmdAccConsLstDeclaracionContribucionParafiscalPatrocinador) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
			copia.objetosDeclaracionContribucionParafiscalPatrocinador = objetosDeclaracionContribucionParafiscalPatrocinador;
			copia.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
		}
	}
}
