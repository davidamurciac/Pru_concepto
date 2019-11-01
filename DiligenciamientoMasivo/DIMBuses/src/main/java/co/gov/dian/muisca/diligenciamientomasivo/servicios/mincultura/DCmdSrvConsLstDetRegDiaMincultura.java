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
 * <p>Descripcion: Comando de servicio utilizado para consultar objetos DetRegDiaMincultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsLstDetRegDiaMincultura extends DComandoServicioInterno {
	private static final long serialVersionUID = 1115524909L; 

	public static final int CONSULTAR_POR_REGDIARIOMINCULTURA = 0;

	public static final int CONSULTA_GENERICA = 1;

	/** Llave primaria de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura;

	protected DDetRegDiaMinculturaTO toDetRegDiaMincultura;

	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	/** Colección de objetos DDetRegDiaMinculturaTO */
	protected Collection<DDetRegDiaMinculturaTO> objetosDetRegDiaMincultura;

	/**
	 * Inicializa la consulta por RegDiarioMinCultura.
	 * @param pkRegDiarioMinCultura Llave primaria de RegDiarioMinCultura
	 */
	public void inicializarConsultarPorRegDiarioMinCultura(DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura) {
		tipoOperacion = CONSULTAR_POR_REGDIARIOMINCULTURA;
		this.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
		objetosDetRegDiaMincultura = null;
	}

	/**
	 * Inicializa la consulta genérica de DetRegDiaMincultura.
	 * @param attDetRegDiaMincultura Atributos de DetRegDiaMincultura
	 */
	public void inicializarConsultaGenerica(DDetRegDiaMinculturaTO toDetRegDiaMincultura) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toDetRegDiaMincultura = toDetRegDiaMincultura;
	}

	/**
	 * Devuelve la colección de objetos DetRegDiaMincultura que se hayan consultado.
	 * @return Un Collection con objetos DDetRegDiaMinculturaTO
	 */
	public Collection<DDetRegDiaMinculturaTO> getColeccionDetRegDiaMincultura() {
		return objetosDetRegDiaMincultura;
	}

	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la consulta de DetRegDiaMincultura
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvConsLstDetRegDiaMincultura();
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
		return "Permite consultar objetos DetRegDiaMincultura";
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
			case CONSULTAR_POR_REGDIARIOMINCULTURA:
				parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura",pkRegDiarioMinCultura);
				parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura.getIdeRegistroDiario()",pkRegDiarioMinCultura.getIdeRegistroDiario());
				break;

			case CONSULTA_GENERICA:
				parametros.put(this.getClass().getName()+":validar:toDetRegDiaMincultura",toDetRegDiaMincultura);
				break;

			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DetRegDiaMincultura"), getMensajeOperInvalida());
		}
		validarParametros("Listar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvConsLstDetRegDiaMincultura) {
			DCmdSrvConsLstDetRegDiaMincultura copia = (DCmdSrvConsLstDetRegDiaMincultura) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
			copia.objetosDetRegDiaMincultura = objetosDetRegDiaMincultura;
			copia.toDetRegDiaMincultura = toDetRegDiaMincultura;
		}
	}
}
