/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcciï¿½n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccTareaDilig130Vencida.java, 8, 2/7/08 6:14:58 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias;

//Paquetes requeridos
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;
import org.apache.log4j.Logger;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegTO;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion130;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de Acciï¿½n para el Manejo del Vencimiento de
 * Tarea de Diligenciamiento de Declaracion de Precios de Transferencia
 * Consolidada</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 8$
 * <pre>
 * $Log[10]:
 *  8    EntradaSalida 1.7         2/7/08 6:14:58 PM COT  ARMANDO PEREA MORA
 *       Mejoras seleccion helper expedientes
 *  7    EntradaSalida 1.6         2/7/08 5:59:21 PM COT  ARMANDO PEREA MORA
 *       Cambios en la seleccion del helper de expedientes
 *  6    EntradaSalida 1.5         2/7/08 5:27:06 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento de tareas y separacion de tarea
 *  5    EntradaSalida 1.4         1/22/08 2:57:50 PM COT ARMANDO PEREA MORA Se
 *        elimina llamado a setLogger por borrado en manejador de Expedientes
 *  4    EntradaSalida 1.3         1/21/08 9:25:25 AM COT ARMANDO PEREA MORA
 *       Cambio de nombre por uno mas descriptivo con respecto a sus funciones
 *  3    EntradaSalida 1.2         1/21/08 9:25:25 AM COT ARMANDO PEREA MORA 
 *  2    EntradaSalida 1.1         1/17/08 11:41:03 AM COTROLAND MAURICIO CRUZ
 *       VELANDIA Cambios varios en estructura del codigo... nada funcional..
 *       solo presentacion
 *  1    EntradaSalida 1.0         11/7/07 2:41:43 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */
public class DCmdAccTareaDilig130Vencida extends
  DComandoAccion {

  protected static Logger LOGGER = Logger.getLogger(
    DCmdAccTareaDilig130Vencida.class);
  private DTareaPersonaNegTO tareaPersonaTo = null;

  protected DTareaPersonaNegTO getTareaPersonaTo() {
    return tareaPersonaTo;
  }

  public void setTareaPersonaTo(DTareaPersonaNegTO tareaPersonaTo) {
    this.tareaPersonaTo = tareaPersonaTo;
  }

  /**
   * Ejecuta el comando de Acciï¿½n.
   * @throws DExcepcion Si ocurre algï¿½n error al realizar la
   * la creaciï¿½n de HechoSancionable
   */
  protected void ejecutarComando() {
    throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    DCmdAccTareaDilig130Vencida resultado =
      new DCmdAccTareaDilig130Vencida();
    asignar(resultado);
    return resultado;
  }

  /**
   * Indica si el comando es auditable.
   * @return true si el comando es auditable; false de lo contrario
   */
  public boolean isAuditable() {
    return true;
  }

  /**
   * Obtiene la descripciï¿½n del comando.
   * @return Un String con la descripciï¿½n del comando
   */
  public String getDescripcion() {
    return "Comando de Acciï¿½n para el Manejo del Vencimiento de " +
      "Tarea de Diligenciamiento de Declaracion de Precios de " +
      "Transferencia Consolidada";
  }

  /**
   * Inicializa la creaciï¿½n
   *
   * @param toMateriaProcesoIndicio DMateriaProcesoIndicioTO
   */
  public void inicializar(DTareaPersonaNegTO tareaPersonaTo) {
    setTareaPersonaTo(tareaPersonaTo);
  }

  /**
   * Método para validar los parámetros inicializados, invocado
   * previamente a la ejecución del comando.
   * @return true si los parámetros son válidos; false de lo contrario
   * @throws DValidarExcepcion Si los parámetros no son válidos
   */
  public boolean validar() throws DValidarExcepcion {
    if (getTareaPersonaTo() == null) {
      throw new DValidarExcepcion("Tarea Nula",
        "La tarea para venciada es nula");
    }
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como
   * parámetro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccTareaDilig130Vencida) {
      DCmdAccTareaDilig130Vencida copia =
        (DCmdAccTareaDilig130Vencida) comando;
      copia.setTareaPersonaTo(getTareaPersonaTo());
    }
  }
}
