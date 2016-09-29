package launch;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

	private static void killProcess()
	{
		try {
    	    String line;
    	    String pid = null;
    	    Process p = Runtime.getRuntime().exec
    	    	    (System.getenv("windir") +"\\system32\\"+"netstat -nao");
    	    BufferedReader input =
    	            new BufferedReader(new InputStreamReader(p.getInputStream()));
    	    while ((line = input.readLine()) != null) {
    	    	if(line.contains("8080"))
    	    	{
    	    		System.out.println(line);
    	    		String[] splited = line.split(" ");
    	    		for (int i = 0; i < splited.length; i++) {
						if(!splited[i].isEmpty())
						{
//							System.out.println("<------  " + splited[i] + " ------>");
							if(splited[i].matches("[0-9]{4}"))
							{
								System.out.println("<------ pidFOund :" + splited[i] + " ------>");
								pid=String.valueOf(Integer.parseInt(splited[i]));
							}
						}
					}
    	    	}
    	        
    	    }
    	    input.close();
    	    if(pid!=null)
    	    {
	    	    String cmd = "taskkill /F /PID " + pid;
	    	    Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+cmd);
    	    }
    	    
    	} catch (Exception err) {
    	    err.printStackTrace();
    	}
	}
	
    public static void main(String[] args) throws Exception {

    	killProcess();
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}