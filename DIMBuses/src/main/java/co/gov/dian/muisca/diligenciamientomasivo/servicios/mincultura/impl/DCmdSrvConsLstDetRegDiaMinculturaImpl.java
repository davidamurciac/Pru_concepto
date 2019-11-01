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
public class DCmdSrvConsLstDetRegDiaMinculturaImpl extends DCmdSrvConsLstDetRegDiaMincultura {

	private static final long serialVersionUID = 1469096789L; 

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * consulta de objetos DetRegDiaMincultura
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAODetRegDiaMincultura dao = fabrica.getDaoDetRegDiaMincultura();

			switch (tipoOperacion) {
				case CONSULTAR_POR_REGDIARIOMINCULTURA:
					dao.inicializarConsultarPorRegDiarioMinCultura(pkRegDiarioMinCultura);
					break;

				case CONSULTA_GENERICA:
					dao.inicializarConsultaGenerica(toDetRegDiaMincultura);
					break;

				default:
					throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DetRegDiaMincultura"), getMensajeOperInvalida());
			}

			// Consultar
			admin.buscar(dao);
			objetosDetRegDiaMincultura = dao.getColeccionDetRegDiaMincultura();
		}
		finally {
			admin.cerrarSesion();
		}
	}
}
