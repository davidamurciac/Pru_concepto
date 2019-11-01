package co.gov.dian.muisca.diligenciamientomasivo.general.eventos;

import javax.ejb.CreateException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.eventos.DBrokerSubsistema;
import co.gov.dian.muisca.arquitectura.general.mensajes.implgenerica.DTransportadorMensaje;
import co.gov.dian.muisca.arquitectura.mensajes.IDConstantesAplicacion;

/**
 *
 * <p>Title: Proyecto muisca</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004 DIAN</p>
 * <p>Company: DIAN</p>
 * @author Rb2
 * @version 1.0
 */
public class DSrvMensajesDiligenciamientoMasivoBean implements MessageDrivenBean,
    MessageListener {

  private MessageDrivenContext messageDrivenContext;
  private Logger logger = Logger.getLogger(DSrvMensajesDiligenciamientoMasivoBean.class);

  /**
   *
   * @throws CreateException
   */
  public void ejbCreate(){
  }

  /**
   *
   */
  public void ejbRemove() {
  }

  /**
   *
   * @param message Message
   */
  public void onMessage(Message mensaje) {
    logger.debug("Arrivo de mensaje");
    DTransportadorMensaje miTransportador = null;
    DBrokerSubsistema miRuteadorEventos = new DBrokerSubsistema();

    try {
      // VerificaciÃ³n del tipo de mensaje recibido.
      if (mensaje instanceof ObjectMessage) {
        // RecuperaciÃ³n del mensaje.
        miTransportador=(DTransportadorMensaje)((ObjectMessage)mensaje).getObject();
        miRuteadorEventos.setFuncion(IDConstantesAplicacion.ENTRADA_SALIDA);
        miRuteadorEventos.enrutar(miTransportador);
      }
    }
    catch (Exception ex) {
      logger.error("Error en la cola de mensajes de Diligenciamiento Masivo de Informacion.", ex);
      messageDrivenContext.setRollbackOnly();
    }
  }

  /**
   *
   * @param messageDrivenContext MessageDrivenContext
   */
  public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext) {
    this.messageDrivenContext = messageDrivenContext;
  }

}
