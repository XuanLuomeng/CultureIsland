package com.cultureIsland.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class HtmlTransmission {
    public HtmlTransmission(HttpServletRequest req, HttpServletResponse resp,String htmlPath) throws IOException {
        ServletContext servletContext = req.getServletContext();
        String realPath = servletContext.getRealPath(htmlPath);
        PrintWriter writer = resp.getWriter();
        InputStream in = new FileInputStream(realPath);
        byte[] bys = new byte[1024];
        int len;
        while ((len = in.read(bys)) != -1) {
            writer.write(new String(bys, 0, len, "utf-8"));
        }
        in.close();
        writer.close();
    }
}
