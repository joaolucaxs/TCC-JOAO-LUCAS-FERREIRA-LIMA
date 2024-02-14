package com.joaolucas.mapp.resources.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.joaolucas.mapp.model.Apresentacao;

public class QRCodeUtil {

	private static String URL = "http://192.168.18.211:8080/";

	public static byte[] generateByteQRCode(String endpointUrl, int width, int height) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		try {
			BitMatrix bitMatrix = qrCodeWriter.encode(URL + endpointUrl, BarcodeFormat.QR_CODE, width, height);
			MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, config);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}

}
