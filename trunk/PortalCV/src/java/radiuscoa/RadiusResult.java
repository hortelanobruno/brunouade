/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiuscoa;

import net.jradius.packet.RadiusPacket;

/**
 *
 * @author bruno
 */
public class RadiusResult {

    private RadiusCoaResult resultType;
    private RadiusPacket reply;
    private String exception;

    public RadiusResult() {
    }

    
    public RadiusPacket getReply() {
        return reply;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setReply(RadiusPacket reply) {
        this.reply = reply;
    }

    public RadiusCoaResult getResultType() {
        return resultType;
    }

    public void setResultType(RadiusCoaResult resultType) {
        this.resultType = resultType;
    }
}
