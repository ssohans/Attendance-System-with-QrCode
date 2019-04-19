/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectverificationx;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import static com.itextpdf.text.pdf.PdfFormXObject.ONE;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jdk.nashorn.internal.scripts.JD;

/**
 *
 * @author SSohan
 */
public class GenerateQrCode {

    GenerateQrCode(String qrCodeText){
        String filePath = "qr.png";
        int size = 500;
        String fileType = "png";
        File qrFile = new File(filePath);
        try {
            createQRImage(qrFile, qrCodeText, size, fileType);
        } catch (WriterException | IOException ex) {
            Logger.getLogger(GenerateQrCode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createQRImage(File imageFile, String dataToBeEncrypted, int sizeOfImage, String typeOfImage) throws WriterException, IOException {

        // Create the ByteMatrix for the QR-Code that encodes the given String
        Map encodeHintType = new Hashtable();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        //Putting possible errors
        encodeHintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        //Encoding the Text("dataToBeEncrypted")
        //(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints)
        BitMatrix byteMatrix = qrCodeWriter.encode(dataToBeEncrypted, BarcodeFormat.QR_CODE, sizeOfImage, sizeOfImage, encodeHintType);

        // Make the BufferedImage that are to hold the QRCode
        Integer matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);

        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, typeOfImage, imageFile);
    }
}
