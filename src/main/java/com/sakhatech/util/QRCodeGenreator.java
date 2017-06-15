package com.sakhatech.util;

import java.awt.Image;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sakhatech.dto.UserProfileDto;

/**
 * The utility class to generate the QR (Quick Response) code.
 *
 * @author Naveen
 * @createdDate 14-Jun-2017
 * @modifiedDate 15-Jun-2017
 */
public class QRCodeGenreator {
	
	public static Image generateQRForUser(UserProfileDto user) throws WriterException{
		
		StringBuilder sb = new StringBuilder();
//		sb.append("BEGIN:VCARD\n");
//		sb.append("VERSION:3.0\n");
		sb.append("Name : "+user.getName()+"\n");
		sb.append("Email : "+user.getEmail()+"\n");
		sb.append("Mobile : "+user.getMobile()+"\n");
		sb.append("Dob : "+user.getDob()+"\n");
//		sb.append("END:VCARD");
		
		String qrData = sb.toString();
		
//		System.out.println("Data inQr Code : "+qrData);
		
		QRCodeWriter qr = new QRCodeWriter();
		BitMatrix matrix = qr.encode(qrData, BarcodeFormat.QR_CODE, 100, 100);

		Image img= MatrixToImageWriter.toBufferedImage(matrix);
		
		return img;
	
	}

}
