package by.gsu.epamlab.controller.impls;

import by.gsu.epamlab.controller.ifaces.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadFileController extends AbstractController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String gurufile = fileName;
        String gurupath = "C:\\Program Files\\apache-tomcat-8.5.16-windows-x64\\apache-tomcat-8.5.16\\webapps\\data\\";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + gurufile + "\"");

        FileInputStream fileInputStream = new FileInputStream(gurupath
                + gurufile);

        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
    }
}
