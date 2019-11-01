/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.impl;


import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.*;

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
public class DCmdSrvConsLstDeclaracionContribucionParafiscalEventoImpl extends DCmdSrvConsLstDeclaracionContribucionParafiscalEvento {

	private static final long serialVersionUID = -935067487L; 

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * consulta de objetos DeclaracionContribucionParafiscalEvento
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAODeclaracionContribucionParafiscalEvento dao = fabrica.getDaoDeclaracionContribucionParafiscalEvento();

			switch (tipoOperacion) {
				case CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL:
					dao.inicializarConsultarPorDeclaracionContribucionParafiscal(pkDeclaracionContribucionParafiscal);
					break;

				case CONSULTA_GENERICA:
					dao.inicializarConsultaGenerica(toDeclaracionContribucionParafiscalEvento);
					break;

				default:
					throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DeclaracionContribucionParafiscalEvento"), getMensajeOperInvalida());
			}

			// Consultar
			admin.buscar(dao);
			objetosDeclaracionContribucionParafiscalEvento = dao.getColeccionDeclaracionContribucionParafiscalEvento();
		}
		finally {
			admin.cerrarSesion();
		}
	}
}
