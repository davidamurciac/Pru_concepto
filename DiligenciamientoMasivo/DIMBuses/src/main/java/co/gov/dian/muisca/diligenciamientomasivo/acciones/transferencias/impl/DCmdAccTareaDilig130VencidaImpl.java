/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcciï¿½n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccTareaDilig130VencidaImpl.java, 10, 2/3/09 8:48:38 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias.impl;

//Paquetes referenciados
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaAttTO;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvConsLstDocGenExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvActDocGenExogena;
import java.util.Collection;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaTO;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegTO;
import java.util.Iterator;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCambiarModoNegocioDoc;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias.DCmdAccTareaDilig130Vencida;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion130;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion130v6;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion130v7;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de Acciï¿½n para el Manejo del Vencimiento de Tarea de Diligenciamiento 
 * de Declaracion de Precios de Transferencia Consolidada</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 10$
 * <pre>
 * $Log[10]:
 *  10   V1.3       1.7.1.1     2/3/09 8:48:38 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  9    V1.3       1.7.1.0     2/3/09 7:23:36 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  8    EntradaSalida 1.7         2/7/08 6:14:58 PM COT  ARMANDO PEREA MORA
 *       Mejoras seleccion helper expedientes
 *  7    EntradaSalida 1.6         1/21/08 9:25:25 AM COT ARMANDO PEREA MORA
 *       Cambio de nombre por uno mas descriptivo con respecto a sus funciones
 *  6    EntradaSalida 1.5         1/21/08 9:25:25 AM COT ARMANDO PEREA MORA 
 *  5    EntradaSalida 1.4         1/17/08 2:46:08 PM COT ARMANDO PEREA MORA Se
 *        elimina constante inoperante VERSION_FORMATO
 *  4    EntradaSalida 1.3         12/13/07 12:03:15 PM COTRAUL BELTRÁN BELTRÁN
 *         Modificaciones para SegmentaciÃ³n.
 *  3    EntradaSalida 1.2         11/8/07 5:25:50 PM COT ARMANDO PEREA MORA Se
 *        cambio la clase DCmdSrvActEstadosIndicadoresDoc por
 *       DCmdSrvCambiarModoNegocioDoc para la anulaciÃ³n del documento
 *  2    EntradaSalida 1.1         11/8/07 3:14:29 PM COT ARMANDO PEREA MORA Se
 *        utilizo el metodo inicializarActModoNegocioDocEs de la clase
 *       CmdSrvActEstadosIndicadoresDoc para anular el documento de
 *       Declaración Borrador
 *  1    EntradaSalida 1.0         11/7/07 2:41:43 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */
public class DCmdAccTareaDilig130VencidaImpl extends
  DCmdAccTareaDilig130Vencida {

  private DHelperExpedienteGeneracion130 manejadorExpedientes = null;
  private Integer versionFormato130 = null;

  /**
   * Ejecuta el comando de Acciï¿½n.
   *
   * @throws DExcepcion Si ocurre algï¿½n error al realizar la
   * creaciï¿½n de Relaciï¿½n Materia Proceso Indicio
   */
  protected void ejecutarComando() {
    try {
      versionFormato130 = null;
      anularDocumentoDeclaracion(getTareaPersonaTo());
      if (versionFormato130 == null) {
        throw new DExcepcion("Error", "No fue posible encontrar la " +
          "version del formato 130 para determinar el manejador de " +
          "expedientes de tareas");
      }

      DHelperExpedienteGeneracion130 manejador =
        getManejadorExpedientes(versionFormato130.intValue());
      if (manejador == null) {
        throw new DExcepcion("Error", "No fue posible encontrar el " +
          "manejador de Expediente para el formato 130 version " +
          versionFormato130);
      }
      manejador.procesarCancelacionVencimientoTarea(getTareaPersonaTo());
      isOk = true;
    } catch (DExcepcion ex) {
      mensajeError = ex.getMessage();
      mensajeErrorDetallado = ex.getMensajeDetallado();
      isOk = false;
    }
  }

  protected DHelperExpedienteGeneracion130 getManejadorExpedientes(int version) {
    if (manejadorExpedientes == null) {
      if (version == 6) {
        manejadorExpedientes =
          new DHelperExpedienteGeneracion130v6(this);
      }
      else if (version == 7) {
        manejadorExpedientes =
          new DHelperExpedienteGeneracion130v7(this);
      }
    }
    return manejadorExpedientes;
  }

    /**
     * Anula el documento de Declaracion
     *
     * @param tareaPersonaNegTo DTareaPersonaNegTO
     * @throws DExcepcion
     */
    private void anularDocumentoDeclaracion(DTareaPersonaNegTO tareaPersonaNegTo) throws
    DExcepcion {
    int NUM_FORMATO = 130;

    DDocGenExogenaAttTO docGenExogenaAtt = new DDocGenExogenaAttTO();
    docGenExogenaAtt.setIdeDocumentoTarea(
      tareaPersonaNegTo.getPk().getIdeDocumentoTarea());
    docGenExogenaAtt.setNumRepeticionDocTarea(
      tareaPersonaNegTo.getPk().getNumRepeticionDocTarea());

    DCmdSrvConsLstDocGenExogena consLstGenExogena =
      (DCmdSrvConsLstDocGenExogena) getServicio(
      "entradasalida.exogena.DCmdSrvConsLstDocGenExogena");
    consLstGenExogena.inicializarPorTarea(docGenExogenaAtt);
    consLstGenExogena.ejecutar();

    Collection docsGenerados = consLstGenExogena.getColeccionDocGenExogena();

    if (docsGenerados != null) {
      Iterator iter = docsGenerados.iterator();
      while (iter.hasNext()) {
        DDocGenExogenaTO docGenExogenaTo = (DDocGenExogenaTO) iter.next();

        //Determinar el numero de la version del formato
        versionFormato130 = docGenExogenaTo.getAtt().getVersion();
        //Actualizar el estado del documento
        docGenExogenaTo.getAtt().setCodEstado(
          new Byte((byte) IDDocumento.IDE_ESTADO_FUERA_TERMINO));

        DCmdSrvActDocGenExogena actGenExogena =
          (DCmdSrvActDocGenExogena) getServicio(
          "entradasalida.exogena.DCmdSrvActDocGenExogena");
        actGenExogena.inicializar(docGenExogenaTo);
        actGenExogena.ejecutar();

        //Actualizar Modo de Negocio
        DDocumentoPKTO documentoPK = new DDocumentoPKTO(
          docGenExogenaTo.getPk().getIdeDocumentoGen(),
          docGenExogenaTo.getPk().getNumRepeticion());

        DCmdSrvCambiarModoNegocioDoc cmdCambiarModoNegocioDoc =
          (DCmdSrvCambiarModoNegocioDoc) getServicio(
          "entradasalida.documento.DCmdSrvCambiarModoNegocioDoc");
        //Documentos Temporal solamente
        cmdCambiarModoNegocioDoc.inicializarActModoNegocioDocEs(documentoPK,
          new Integer(IDModosDiligenciamiento.MODO_ANULADO), NUM_FORMATO);
        cmdCambiarModoNegocioDoc.ejecutar();
      }
    }
  }
}
