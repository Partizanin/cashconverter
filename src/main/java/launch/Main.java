package launch;
<<<<<<< HEAD

=======
>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
<<<<<<< HEAD
        if (webPort == null || webPort.isEmpty()) {
=======
        if(webPort == null || webPort.isEmpty()) {
>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
<<<<<<< HEAD
        tomcat.getServer().await();
=======
        tomcat.getServer().await();  
>>>>>>> f22dc58137c88b20b01febf6162ff54b82423d57
    }
}
