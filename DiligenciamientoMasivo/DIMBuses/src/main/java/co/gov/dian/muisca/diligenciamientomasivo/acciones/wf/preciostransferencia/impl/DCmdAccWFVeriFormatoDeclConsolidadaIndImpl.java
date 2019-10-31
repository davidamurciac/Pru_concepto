package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.impl;

import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.
        DCmdAccWFVeriFormatoDeclConsolidadaInd;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;

public class DCmdAccWFVeriFormatoDeclConsolidadaIndImpl extends
        DCmdAccWFVeriFormatoDeclConsolidadaInd {

    public DCmdAccWFVeriFormatoDeclConsolidadaIndImpl() {
    }
    protected void ejecutarComandoWF() {
        try {
            DDocumentoPKTO documentoPK = (DDocumentoPKTO) getDocumentos().iterator().next();
            Long idDocumento = new Long(documentoPK.getIdeDocumento().longValue());
            Integer numRepeticion = new Integer( documentoPK.getNumRepeticion().intValue());

            IDDocumento doc = getDocumentoDeBaseDatos(idDocumento.longValue(),numRepeticion.intValue());
            IDOcurrencia ocurrencia = doc.getGrupos().getGrupo(1).getOcurrencia(1);
            Integer idFormato = new Integer(ocurrencia.getValorCasilla(40).getValorEntero().intValue());
            if(idFormato.intValue() == NUM_FORMATO_TRAFICO_DEC_CONSOLIDADA){
                salida = SALIDAS[0];
            }
            else{
                salida = SALIDAS[1];
            }
            isFinalizado = true;
            isOk = true;
        }
        catch (DExcepcion ex) {
            LOGGER.error("Error en ejecutarComandoWF()",ex);
            isOk = false;
        }
    }

    /**
     * Trae un documento de la base de datos
     *
     * @param idDocumento long
     * @param numRepeticion int
     * @return IDDocumento
     * @throws DExcepcion
     */
    protected final IDDocumento getDocumentoDeBaseDatos(long idDocumento,
            int numRepeticion) throws DExcepcion {
        DCmdSrvConsDocumentoMUISCA srvConsDocumento = (DCmdSrvConsDocumentoMUISCA)
                                                  this.getServicio("entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
        srvConsDocumento.inicializar(idDocumento, numRepeticion);
        srvConsDocumento.ejecutar();
        IDDocumento documento = srvConsDocumento.getDocumento();
        return documento;
    }

}
