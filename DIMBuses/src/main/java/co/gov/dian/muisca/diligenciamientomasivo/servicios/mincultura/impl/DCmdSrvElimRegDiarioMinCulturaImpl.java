/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de servicio utilizado para eliminar un objeto RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvElimRegDiarioMinCulturaImpl extends DCmdSrvElimRegDiarioMinCultura {

	private static final long serialVersionUID = -460818049L; 


	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * consulta de RegDiarioMinCultura o si el objeto no existe
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAORegDiarioMinCultura dao = fabrica.getDaoRegDiarioMinCultura();

			// Eliminar
			dao.inicializarEliminar(pkRegDiarioMinCultura);
			admin.eliminar(dao);
		}
		finally {
			admin.cerrarSesion();
		}
	}
}
