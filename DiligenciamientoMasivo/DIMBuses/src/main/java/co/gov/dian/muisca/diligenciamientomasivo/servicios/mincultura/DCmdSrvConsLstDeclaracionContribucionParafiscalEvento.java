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
 * <p>Descripcion: Comando de servicio utilizado para consultar objetos DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsLstDeclaracionContribucionParafiscalEvento extends DComandoServicioInterno {
	private static final long serialVersionUID = 261918841L; 

	public static final int CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL = 0;

	public static final int CONSULTA_GENERICA = 1;

	/** Llave primaria de DeclaracionContribucionParafiscal */
	protected DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal;

	protected DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento;

	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	/** Colección de objetos DDeclaracionContribucionParafiscalEventoTO */
	protected Collection<DDeclaracionContribucionParafiscalEventoTO> objetosDeclaracionContribucionParafiscalEvento;

	/**
	 * Inicializa la consulta por DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	public void inicializarConsultarPorDeclaracionContribucionParafiscal(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal) {
		tipoOperacion = CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL;
		this.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
		objetosDeclaracionContribucionParafiscalEvento = null;
	}

	/**
	 * Inicializa la consulta genérica de DeclaracionContribucionParafiscalEvento.
	 * @param attDeclaracionContribucionParafiscalEvento Atributos de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toDeclaracionContribucionParafiscalEvento = toDeclaracionContribucionParafiscalEvento;
	}

	/**
	 * Devuelve la colección de objetos DeclaracionContribucionParafiscalEvento que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalEventoTO
	 */
	public Collection<DDeclaracionContribucionParafiscalEventoTO> getColeccionDeclaracionContribucionParafiscalEvento() {
		return objetosDeclaracionContribucionParafiscalEvento;
	}

	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la consulta de DeclaracionContribucionParafiscalEvento
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvConsLstDeclaracionContribucionParafiscalEvento();
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
		return "Permite consultar objetos DeclaracionContribucionParafiscalEvento";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los paretros son váidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son váidos
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
				parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalEvento",toDeclaracionContribucionParafiscalEvento);
				break;

			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DeclaracionContribucionParafiscalEvento"), getMensajeOperInvalida());
		}
		validarParametros("Listar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvConsLstDeclaracionContribucionParafiscalEvento) {
			DCmdSrvConsLstDeclaracionContribucionParafiscalEvento copia = (DCmdSrvConsLstDeclaracionContribucionParafiscalEvento) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
			copia.objetosDeclaracionContribucionParafiscalEvento = objetosDeclaracionContribucionParafiscalEvento;
			copia.toDeclaracionContribucionParafiscalEvento = toDeclaracionContribucionParafiscalEvento;
		}
	}
}
