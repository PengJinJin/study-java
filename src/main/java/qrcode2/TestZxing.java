package qrcode2;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class TestZxing {
	public static String createQrcode(String _text) {
		String qrcodeFilePath = "";
		try {
			int qrcodeWidth = 300;
			int qrcodeHeight = 300;
			String qrcodeFormat = "png";
			HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(_text, BarcodeFormat.QR_CODE, qrcodeWidth,
					qrcodeHeight, hints);
			BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
			Random random = new Random();
			File QrcodeFile = new File("D:\\qrcode\\" + random.nextInt() + "." + qrcodeFormat);
			ImageIO.write(image, qrcodeFormat, QrcodeFile);
			MatrixToImageWriter.writeToFile(bitMatrix, qrcodeFormat, QrcodeFile);
			qrcodeFilePath = QrcodeFile.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qrcodeFilePath;
	}

	public static String decodeQr(String filePath) {
		String retStr = "";
		if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
			return "图片路径为空!";
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap bitmap = new BinaryBitmap(binarizer);
			HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
			hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			hintTypeObjectHashMap.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
			retStr = result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStr;
	}

	public static String decode(InputStream input) {
		String result = "";
		BufferedImage image;
		try {
			if (null == input) {
				return "得到的文件不存在！";
			}
			image = ImageIO.read(input);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Map<DecodeHintType, Object> hints = new LinkedHashMap<DecodeHintType, Object>();
			// 解码设置编码方式为：utf-8，
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			// 优化精度
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			// 复杂模式，开启PURE_BARCODE模式
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			Result s = new MultiFormatReader().decode(bitmap, hints);
			result = s.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// System.out.print(decode(new FileInputStream("D://1.jpg")));
		System.out.println(decode(new FileInputStream("D://4.png")));
		// createQrcode("兄弟,我觉得你宛如一个智障");
	}
}
