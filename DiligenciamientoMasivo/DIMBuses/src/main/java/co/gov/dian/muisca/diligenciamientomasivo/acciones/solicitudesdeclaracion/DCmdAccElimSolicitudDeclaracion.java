/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.solicitudesdeclaracion;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acciï¿½ utilizado para eliminar un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccElimSolicitudDeclaracion extends DComandoAccion {
  /** Llave primaria de SolicitudDeclaracion */
  protected DSolicitudDeclaracionPKTO pkSolicitudDeclaracion;

  /**
   * Inicializa la eliminaciï¿½ de SolicitudDeclaracion.
   * @param pkSolicitudDeclaracion Llave primaria de SolicitudDeclaracion
   */
  public void inicializar(DSolicitudDeclaracionPKTO pkSolicitudDeclaracion) {
    isOk = false;
    this.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
  }

  /**
   * Ejecuta el comando de acción.
   */
  protected void ejecutarComando() {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdAccElimSolicitudDeclaracion();
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
   * @return Un String con la descripciï¿½ del comando
   */
  public String getDescripcion() {
    return "Permite eliminar un objeto SolicitudDeclaracion";
  }

  /**
   * Méodo para validar los parámetros inicializados, invocado
   * previamente a la ejecución del comando.
   * @return true si los parímetros son válidos; false de lo contrario
   * @throws DValidarExcepcion Si los parímetros no son válidos
   */
  public boolean validar() throws DValidarExcepcion {
    Map parametros=new HashMap();
    parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion",pkSolicitudDeclaracion);
	validarParametros("Eliminar",parametros);
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccElimSolicitudDeclaracion) {
      DCmdAccElimSolicitudDeclaracion copia = (DCmdAccElimSolicitudDeclaracion) comando;
      copia.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
    }
  }
}