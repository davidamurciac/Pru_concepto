/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.pagocaja.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.IDDAOSolicitudDocConciliaPago;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.pagocaja.DCmdSrvActSolicitudDocConciliaPago;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para actualizar un objeto SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvActSolicitudDocConciliaPagoImpl extends DCmdSrvActSolicitudDocConciliaPago {


  /**
   * Ejecuta el comando de servicio.
   *
   * @throws DExcepcion Si ocurre algn error al realizar la
   * actualizaci� de SolicitudDocConciliaPago
   */
  protected void ejecutarComando() throws DExcepcion {
    IDAdminPersistencia admin = getAdministradorPersistencia();
    try {
      // Iniciar los DAO's
      IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
      IDDAOSolicitudDocConciliaPago dao = fabrica.getDaoSolicitudDocConciliaPago();
      
      switch (tipoOperacion) {
  	case ACTUALIZAR:
  		 dao.inicializarActualizar(toSolicitudDocConciliaPago);
  		break;
  	
  	 case MERGE:
  		 dao.inicializarMerge(toSolicitudDocConciliaPago);
  		break;
  	default:
  		break;
  	}
      // Actualizar
      admin.guardar(dao);
    }
    finally {
      admin.cerrarSesion();
    }
  }

}
