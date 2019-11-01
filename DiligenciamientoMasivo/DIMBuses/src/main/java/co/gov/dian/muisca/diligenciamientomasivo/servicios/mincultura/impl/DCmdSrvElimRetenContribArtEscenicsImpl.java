/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.impl;


import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvElimRetenContribArtEscenics;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para eliminar un objeto RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvElimRetenContribArtEscenicsImpl extends DCmdSrvElimRetenContribArtEscenics {

	private static final long serialVersionUID = 294299366L; 


	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * consulta de RetenContribArtEscenics o si el objeto no existe
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAORetenContribArtEscenics dao = fabrica.getDaoRetenContribArtEscenics();

			// Eliminar
			dao.inicializarEliminar(pkRetenContribArtEscenics);
			admin.eliminar(dao);
		}
		finally {
			admin.cerrarSesion();
		}
	}
}
