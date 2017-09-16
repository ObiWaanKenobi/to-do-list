package by.gsu.epamlab.controller.controllers.file;

import by.gsu.epamlab.controller.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DownloadFileController", urlPatterns = "/downloadFile")
public class DownloadFileController extends AbstractFileController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter(Constants.FILE_NAME);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        FileInputStream fileInputStream = new FileInputStream(filePath + fileName);

        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
    }
}
