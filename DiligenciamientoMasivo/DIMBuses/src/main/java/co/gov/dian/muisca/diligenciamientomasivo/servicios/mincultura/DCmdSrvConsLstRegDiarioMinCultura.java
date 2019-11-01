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
 * <p>Descripcion: Comando de servicio utilizado para consultar objetos RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsLstRegDiarioMinCultura extends DComandoServicioInterno {
	private static final long serialVersionUID = -1833633694L; 


	public static final int CONSULTA_GENERICA = 0;


	protected DRegDiarioMinCulturaTO toRegDiarioMinCultura;

	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	/** Colección de objetos DRegDiarioMinCulturaTO */
	protected Collection<DRegDiarioMinCulturaTO> objetosRegDiarioMinCultura;


	/**
	 * Inicializa la consulta genérica de RegDiarioMinCultura.
	 * @param attRegDiarioMinCultura Atributos de RegDiarioMinCultura
	 */
	public void inicializarConsultaGenerica(DRegDiarioMinCulturaTO toRegDiarioMinCultura) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toRegDiarioMinCultura = toRegDiarioMinCultura;
	}

	/**
	 * Devuelve la colección de objetos RegDiarioMinCultura que se hayan consultado.
	 * @return Un Collection con objetos DRegDiarioMinCulturaTO
	 */
	public Collection<DRegDiarioMinCulturaTO> getColeccionRegDiarioMinCultura() {
		return objetosRegDiarioMinCultura;
	}

	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la consulta de RegDiarioMinCultura
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvConsLstRegDiarioMinCultura();
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
		return "Permite consultar objetos RegDiarioMinCultura";
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

			case CONSULTA_GENERICA:
				parametros.put(this.getClass().getName()+":validar:toRegDiarioMinCultura",toRegDiarioMinCultura);
				break;

			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos RegDiarioMinCultura"), getMensajeOperInvalida());
		}
		validarParametros("Listar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvConsLstRegDiarioMinCultura) {
			DCmdSrvConsLstRegDiarioMinCultura copia = (DCmdSrvConsLstRegDiarioMinCultura) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.objetosRegDiarioMinCultura = objetosRegDiarioMinCultura;
			copia.toRegDiarioMinCultura = toRegDiarioMinCultura;
		}
	}
}
