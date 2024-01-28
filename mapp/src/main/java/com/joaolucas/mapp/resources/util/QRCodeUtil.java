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

	public static byte[] generateByteQRCode(Apresentacao apresentacao, int width, int height) {
		ByteArrayOutputStream outputStream = null;
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		String endpointUrl = "http://192.168.18.211:8080/apresentacoes/" + apresentacao.getId();
		try {
			outputStream = new ByteArrayOutputStream();
			BitMatrix bitMatrix = qrCodeWriter.encode(endpointUrl, BarcodeFormat.QR_CODE, width, height);
			MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, config);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}

}
