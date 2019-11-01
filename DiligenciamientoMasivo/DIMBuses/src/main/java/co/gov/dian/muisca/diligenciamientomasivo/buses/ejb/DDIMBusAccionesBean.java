/**
 *  Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DEYSBusAccionesBean.java, 2, 30/11/2005 08:41:03 p.m., EBERRIOC,AFRADEB$
 */

package co.gov.dian.muisca.diligenciamientomasivo.buses.ejb;

import javax.ejb.CreateException;

import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.buses.DBusAccionesBase;
/**
 * Titulo: Proyecto muisca<br/>
 * Descripcion: Bus de acciones de Entrada Salida.
 * Este bus es referenciado por el bus de acciones
 * General de Arquitectura <br/>
 * Copyright: Copyright (c)  2004 DIAN<br/>
 * Company: DIAN<br/>
 *
 * @author Pedro Julio Rozo Castañeda
 * @version $Revision: 2$
 * <pre>
 * $Log[20]:
 *  2    EntradaSalida1.1         30/11/2005 08:41:03 p.m.EBERRIOC,AFRADEB
 *  1    EntradaSalida1.0         03/10/2005 10:34:26 a.m.StarTeam Server
 *       Administrator
 * $
 * </pre>
 */


public class DDIMBusAccionesBean extends DBusAccionesBase{
    public void ejbCreate() throws CreateException {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }
}
