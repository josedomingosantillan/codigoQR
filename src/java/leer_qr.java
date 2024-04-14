import java.io.IOException;
import java.io.PrintWriter;
import java.util.EnumMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

@WebServlet(urlPatterns = {"/leer_qr"})
public class leer_qr extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>QR Code Scanner</title>");
            out.println("<script src=\"https://code.jquery.com/jquery-3.6.4.min.js\"></script>");
            out.println("<script src=\"https://rawgit.com/schmich/instascan-builds/master/instascan.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>QR Code Scanner</h1>");
            out.println("<video id=\"preview\"></video> <br>");
            out.println("<h2 id='texto'></h2>");
            out.println("<br>");
            out.println("<form action='http://localhost:8080/codigoQR/' method='get'>\n"
                    + "        <button type='submit'>Página principal</button>\n"
                    + "    </form>");
            out.println("<script>");
            out.println("let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });");
            out.println("scanner.addListener('scan', function(content) {");
            out.println("alert(content);");
            out.println("document.getElementById('texto').innerText= `El texto escanedaro en el qr es: ${content}`");
            out.println("});");
            out.println("Instascan.Camera.getCameras().then(function (cameras) {");
            out.println("    if (cameras.length > 0) {");
            out.println("        scanner.start(cameras[0]);");
            out.println("    } else {");
            out.println("        console.error('No cameras found.');");
            out.println("    }");
            out.println("}).catch(function (e) {");
            out.println("    console.error(e);");
            out.println("});");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        }
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
