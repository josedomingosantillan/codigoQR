
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/viewQR"})
public class viewQR extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet viewQR</title>");
            out.println("</head>");
            out.println("<body>");

            // Obtener el texto para el código QR desde el parámetro de la solicitud
            String textoParaQR = request.getParameter("valor_qr");
            String name= request.getParameter("name_qr");

            // Ruta donde se guardará la imagen del código QR
            String rutaImagenQR = "C:\\Users\\MSI PRENDAMEX\\Documents\\NetBeansProjects\\codigoQR\\web\\ArchivosQR\\"+ name+ ".png";

            // Dimensiones del código QR
            int anchoQR = 300;
            int altoQR = 300;

            // Generar el código QR
            codigoQR.generarCodigoQR(textoParaQR, rutaImagenQR, anchoQR, altoQR);

            out.println("El código QR se ha generado exitosamente en la ruta: <br>");
            
            out.println(rutaImagenQR+"<br>");

            // Mostrar la imagen del código QR en el navegador
            out.println("<img src='data:image/png;base64," + obtenerImagenBase64(rutaImagenQR) + "' alt='Código QR'> <br>");

            out.println("<form action='http://localhost:8080/codigoQR/' method='get'>\n"
                    + "        <button type='submit'>Página principal</button>\n"
                    + "    </form>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // Método para obtener la representación en base64 de la imagen
    private String obtenerImagenBase64(String rutaImagen) throws IOException {
        byte[] bytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(rutaImagen));
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
