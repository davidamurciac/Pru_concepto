/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para actualizar un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvActSolicitudDeclaracionImpl extends DCmdSrvActSolicitudDeclaracion {


  /**
   * Ejecuta el comando de servicio.
   *
   * @throws DExcepcion Si ocurre algn error al realizar la
   * actualizaciï¿½ de SolicitudDeclaracion
   */
  protected void ejecutarComando() throws DExcepcion {
    IDAdminPersistencia admin = getAdministradorPersistencia();
    try {
      // Iniciar los DAO's
      IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
      IDDAOSolicitudDeclaracion dao = fabrica.getDaoSolicitudDeclaracion();

      // Actualizar
      dao.inicializarActualizar(toSolicitudDeclaracion);
      admin.guardar(dao);
    }
    finally {
      admin.cerrarSesion();
    }
  }

}
