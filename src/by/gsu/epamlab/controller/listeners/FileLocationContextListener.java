package by.gsu.epamlab.controller.listeners;

import by.gsu.epamlab.controller.constants.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class FileLocationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String rootPath = System.getProperty("catalina.home");
        ServletContext ctx = servletContextEvent.getServletContext();
        String relativePath = ctx.getInitParameter(Constants.INIT_FILE_DIR);
        File file = new File(rootPath + File.separator + relativePath);
        if(!file.exists()){
            file.mkdirs();
        }
        ctx.setAttribute(Constants.FILES_DIR_FILE, file);
        ctx.setAttribute(Constants.FILES_DIR, rootPath + File.separator + relativePath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}



