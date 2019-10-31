/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccProcesarMigTareasDocs130v8Impl.java, 4, 1/31/08 3:14:53 PM COT, ARMANDO PEREA MORA$
 */

//Paquetes requeridos

package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.impl;

import co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.*;
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.gestionexpediente.general.to.migracion.
  DDocumentoMigTO;
import java.util.Collection;
import co.gov.dian.muisca.gestionexpediente.general.constantes.
  IDConstantesMigracion;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Implementa el procesamiento de la migracion al nuevo
 * esquema de Eventos y Tareas de documentos 130 version 8</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 4$
 * <pre>
 * $Log[10]:
 *  4    EntradaSalida 1.3         1/31/08 3:14:53 PM COT ARMANDO PEREA MORA
 *       Correciones estabilizacion para ejecución
 *  3    EntradaSalida 1.2         1/28/08 1:45:29 PM COT ARMANDO PEREA MORA
 *       Migracion solo 130
 *  2    EntradaSalida 1.1         1/28/08 1:45:00 PM COT ARMANDO PEREA MORA 
 *  1    EntradaSalida 1.0         1/25/08 8:14:49 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */

public class DCmdAccProcesarMigTareasDocs130v8Impl extends
  DCmdAccProcesarMigTareasDocs130v8 {
  private static final Logger logger = Logger.getLogger(
    DCmdAccProcesarMigTareasDocs130v8Impl.class);

  public DCmdAccProcesarMigTareasDocs130v8Impl() {
  }

  private DDocumentoMigTO obtenerParametrosConsultaDocumentos() {
    DDocumentoMigTO result = new DDocumentoMigTO();
    //ideProceso
    String strIdeproceso = getValorParametro("ideProceso");
    if (strIdeproceso != null && !strIdeproceso.trim().equals("")) {
      result.getPK().setIdeProceso(Integer.valueOf(strIdeproceso.trim()));
    }
    //ideDocumento
    String strIdeDocumento = getValorParametro("ideDocumento");
    if (strIdeDocumento != null && !strIdeDocumento.trim().equals("")) {
      result.getPK().setIdeDocumento(Long.valueOf(strIdeDocumento.trim()));
    }
    //numRepeticion
    String strNumRepeticion = getValorParametro("numRepeticion");
    if (strNumRepeticion != null && !strNumRepeticion.trim().equals("")) {
      result.getPK().setNumRepeticion(Integer.valueOf(strNumRepeticion.trim()));
    }

    //ideLote
    String strIdeLote = getValorParametro("ideLote");
    if (strIdeLote != null && !strIdeLote.trim().equals("")) {
      result.getAtt().setIdeLote(Integer.valueOf(strIdeLote.trim()));
    }
    //codEstado
    String strCodEstado = getValorParametro("codEstado");
    if (strCodEstado != null && !strCodEstado.trim().equals("")) {
      result.getAtt().setCodEstado(strCodEstado.trim());
    }
    //codError
    String strCodError = getValorParametro("codError");
    if (strCodError != null && !strCodError.trim().equals("")) {
      result.getAtt().setCodError(strCodError.trim());
    }
    //desExcepcion
    String strDesExcepcion = getValorParametro("desExcepcion");
    if (strDesExcepcion != null && !strDesExcepcion.trim().equals("")) {
      result.getAtt().setDesExcepcion(strDesExcepcion.trim());
    }
    //fecDocumento
    String strFecDocumento = getValorParametro("fecDocumento");
    if (strFecDocumento != null && !strFecDocumento.trim().equals("")) {
      result.getAtt().setFecDocumento(Integer.valueOf(strFecDocumento.trim()));
    }
    //CodEstadoTareaTarea
    String strCodEstadoTarea = getValorParametro("CodEstadoTarea");
    if (strCodEstadoTarea != null && !strCodEstadoTarea.trim().equals("")) {
      result.getAtt().setCodEstadoTarea(strCodEstadoTarea.trim());
    }
    //ideDocumentoAsociado
    String strIdeDocumentoAsociado = getValorParametro("IdeDocumentoAsociado");
    if (strIdeDocumentoAsociado != null && !strIdeDocumentoAsociado.trim().equals("")) {
      result.getAtt().setIdeDocumentoAsociado(
        Long.valueOf(strIdeDocumentoAsociado.trim()));
    }
    //numRepeticionAsociado
    String strNumRepeticionAsociado = getValorParametro("numRepeticionAsociado");
    if (strNumRepeticionAsociado != null &&
      !strNumRepeticionAsociado.trim().equals("")) {
      result.getAtt().setNumRepeticionAsociado(
        Integer.valueOf(strNumRepeticionAsociado.trim()));
    }
    //fecInicial
    String strFecInicial = getValorParametro("fecInicial");
    if (strFecInicial != null && !strFecInicial.trim().equals("")) {
      result.getAtt().setFecInicial(Integer.valueOf(strFecInicial.trim()));
    }
    //fecFinal
    String strFecFinal = getValorParametro("fecFinal");
    if (strFecFinal != null && !strFecFinal.trim().equals("")) {
      result.getAtt().setFecFinal(Integer.valueOf(strFecFinal.trim()));
    }

    return result;
  }

  /**
   * Procesa un documento para ser migrado
   *
   * @param ideDocumento Long
   * @param numRepeticion Integer
   */
  private void procesarDocumento(Long ideDocumento,
    Integer numRepeticion) throws DExcepcion {
    try {
      actualizarEstadoDocumento(ideDocumento, numRepeticion,
        IDConstantesMigracion.EST_PROCESANDO, null, null);
      DCmdAccMigTareasDoc130v8 cmdAccMig = (DCmdAccMigTareasDoc130v8)
        getAccionOtroContexto(
        "diligenciamientomasivo.migracion.DCmdAccMigTareasDoc130v8", true);
      cmdAccMig.inicializar(ideDocumento, numRepeticion);
      cmdAccMig.ejecutar();
      actualizarEstadoDocumento(ideDocumento, numRepeticion,
        IDConstantesMigracion.EST_PROCESADO_OK, null, null);
    } catch (DExcepcion ex) {
      actualizarEstadoDocumento(ideDocumento, numRepeticion,
        IDConstantesMigracion.EST_ERROR_PROCESANDO, "1", ex);
      incrementarDocsError(1);
      logger.error(ex);
    } catch (Exception e) {
      actualizarEstadoDocumento(ideDocumento, numRepeticion,
        IDConstantesMigracion.EST_ERROR_PROCESANDO, "1", e);
      incrementarDocsError(1);
      logger.error(e);
    }
  }

  /**
   * Permite la ejecuci�n de codigo de procesamiento o consulta fuerte dentro
   * de un contexto no transaccional.
   * <br> Su implementaci�n por defecto es vacia.
   *
   * @throws DExcepcion
   */
  protected void ejecutarComandoSinTransaccion() throws DExcepcion {
    try {
      actualizarTotales(0);
      DDocumentoMigTO toDocumentoMig = obtenerParametrosConsultaDocumentos();
      while (true) {
        Collection documentos = consultaDocumentos(toDocumentoMig);
        if (documentos.isEmpty()) {
          break;
        }
        actualizarTotales(documentos.size());
        Iterator it = documentos.iterator();
        while (it.hasNext()) {
          DDocumentoMigTO documento = (DDocumentoMigTO) it.next();
          procesarDocumento(documento.getPK().getIdeDocumento(),
            documento.getPK().getNumRepeticion());
          incrementarDocsProcesados(1);
        }
      }
      finalizarProceso();
      isOk = true;
    } catch (DExcepcion ex) {
      isOk = false;
      setExcepcion(ex);
    }

  }

}
