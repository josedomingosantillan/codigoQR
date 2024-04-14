import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class codigoQR {
    public static void generarCodigoQR(String texto, String rutaImagen, int ancho, int alto) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, ancho, alto);

            BufferedImage image = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < ancho; i++) {
                for (int j = 0; j < alto; j++) {
                    image.setRGB(i, j, bitMatrix.get(i, j) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            File qrFile = new File(rutaImagen);
            ImageIO.write(image, "png", qrFile);

            System.out.println("¡Código QR generado exitosamente!");

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
