/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccTareaDilig120Vencida.java, 5, 2/3/08 1:03:11 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias;

//Paquetes requeridos
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.
  DComandoAccion;
import org.apache.log4j.Logger;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegTO;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de Acci�n para el Manejo del Vencimiento de
 * Tarea de Diligenciamiento de Declaracion de Precios de Transferencia
 * Individual</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 5$
 * <pre>
 * $Log[10]:
 *  5    EntradaSalida 1.4         2/3/08 1:03:11 PM COT  ARMANDO PEREA MORA
 *       Cambios para seleccionar la version correcta de manejador de
 *       expedientes e tareas y eventos de Presentacion formato 120
 *  4    EntradaSalida 1.3         1/22/08 2:57:50 PM COT ARMANDO PEREA MORA Se
 *        elimina llamado a setLogger por borrado en manejador de Expedientes
 *  3    EntradaSalida 1.2         1/21/08 9:25:25 AM COT ARMANDO PEREA MORA
 *       Cambio de nombre por uno mas descriptivo con respecto a sus funciones
 *  2    EntradaSalida 1.1         1/21/08 9:25:25 AM COT ARMANDO PEREA MORA 
 *  1    EntradaSalida 1.0         11/7/07 2:41:28 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */
public class DCmdAccTareaDilig120Vencida extends DComandoAccion {

  protected static Logger LOGGER = Logger.getLogger(
    DCmdAccTareaDilig120Vencida.class);
  private DTareaPersonaNegTO tareaPersonaTo = null;

  protected DTareaPersonaNegTO getTareaPersonaTo() {
    return tareaPersonaTo;
  }

  public void setTareaPersonaTo(DTareaPersonaNegTO tareaPersonaTo) {
    this.tareaPersonaTo = tareaPersonaTo;
  }

  /**
   * Ejecuta el comando de Acci�n.
   * @throws DExcepcion Si ocurre alg�n error al realizar la
   * la creaci�n de HechoSancionable
   */
  protected void ejecutarComando() {
    throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    DCmdAccTareaDilig120Vencida resultado =
      new DCmdAccTareaDilig120Vencida();
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
   * Obtiene la descripci�n del comando.
   * @return Un String con la descripci�n del comando
   */
  public String getDescripcion() {
    return "Comando de Acci�n para el Manejo del Vencimiento de " +
      "Tarea de Diligenciamiento de Declaracion de Precios de " +
      "Transferencia Individual";
  }

  /**
   * Inicializa la creaci�n de Materia Proceso Indicio
   *
   * @param toMateriaProcesoIndicio DMateriaProcesoIndicioTO
   */
  public void inicializar(DTareaPersonaNegTO tareaPersonaTo) {
    setTareaPersonaTo(tareaPersonaTo);
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�n del comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
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
   * par�metro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccTareaDilig120Vencida) {
      DCmdAccTareaDilig120Vencida copia =
        (DCmdAccTareaDilig120Vencida) comando;
      copia.setTareaPersonaTo(getTareaPersonaTo());
    }
  }


}
