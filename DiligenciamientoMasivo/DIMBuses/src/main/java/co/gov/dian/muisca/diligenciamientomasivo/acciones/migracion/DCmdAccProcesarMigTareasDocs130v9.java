/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccProcesarMigTareasDocs130v9.java, 4, 1/31/08 3:14:53 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion;

//Paquetes requeridos

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.gestionexpediente.acciones.migracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Interfaz para el procesamiento de la migracion al nuevo
 * esquema de Eventos y Tareas de documentos 130 version 9</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 4$
 * <pre>
 * $Log[10]:
 *  4    EntradaSalida 1.3         1/31/08 3:14:53 PM COT ARMANDO PEREA MORA
 *       Correciones estabilizacion para ejecución
 *  3    EntradaSalida 1.2         1/28/08 1:45:28 PM COT ARMANDO PEREA MORA
 *       Migracion solo 130
 *  2    EntradaSalida 1.1         1/28/08 1:45:00 PM COT ARMANDO PEREA MORA 
 *  1    EntradaSalida 1.0         1/25/08 8:14:50 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */

public class DCmdAccProcesarMigTareasDocs130v9 extends
  DCmdAccProcesarMigracion {
  public DCmdAccProcesarMigTareasDocs130v9() {
  }

  /**
   * asignarComando
   *
   * @param comando IDComando
   * @todo Implement this
   *   co.gov.dian.muisca.gestionexpediente.acciones.migracion.DCmdAccProcesarMigracion
   *   method
   */
  protected void asignarComando(IDComando comando) {
    if (comando instanceof DCmdAccProcesarMigTareasDocs130v9) {
        DCmdAccProcesarMigTareasDocs130v9 copia =
                (DCmdAccProcesarMigTareasDocs130v9) comando;
    }
  }

  /**
   *
   * @return Object
   * @todo Implement this
   *   co.gov.dian.muisca.arquitectura.interfaces.IDClonable method
   */
  public Object clonar() {
    DCmdAccProcesarMigTareasDocs130v9 resultado =
            new DCmdAccProcesarMigTareasDocs130v9();
    asignar(resultado);
    return resultado;
  }

  /**
   * Ejecuta la l�gica de negocio.
   *
   * @todo Implement this
   *   co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion
   *   method
   */
  protected void ejecutarComando() {
    //throw new UnsupportedOperationException();
  }

  /**
   * Retorna la descripci�n del comando
   *
   * @return String
   * @todo Implement this co.gov.dian.muisca.arquitectura.interfaces.IDComando
   *   method
   */
  public String getDescripcion() {
    return "Permite hacer la migracion del esquema de tareas por bandeja " +
      "al esquema de tareas por expediente de documentos de formato 130";
  }

  /**
   *
   * @return boolean
   * @todo Implement this
   *   co.gov.dian.muisca.arquitectura.interfaces.IDAuditable method
   */
  public boolean isAuditable() {
    return true;
  }

  /**
   * Previa a la ejecucion de un comando se deber�a ejecutar una validacion de
   * los par�metros inicializados
   *
   * @throws DValidarExcepcion
   * @return boolean
   * @todo Implement this co.gov.dian.muisca.arquitectura.interfaces.IDComando
   *   method
   */
  public boolean validar() throws DValidarExcepcion {
    return true;
  }

  /**
   * Inicializa
   *
   */
  public void inicializar() {
  }

}
