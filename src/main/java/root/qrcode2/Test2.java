package root.qrcode2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Test2 {
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	private static final int WIDTH = 220;
	private static final int HEIGHT = 220;

	/**
	 * �����ɵĶ�ά����뵽ͼƬ��
	 * 
	 * @param matrix
	 *            zxing���µĶ�ά����
	 * @return
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * ���ɶ�ά�벢д���ļ�
	 * 
	 * @param content
	 *            ɨ���ά�������
	 * @param format
	 *            ͼƬ��ʽ jpg
	 * @param file
	 *            �ļ�
	 * @throws Exception
	 */
	public static void writeToFile(String content, String format, File file) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		@SuppressWarnings("rawtypes")
		Map hints = new HashMap();
		// ����UTF-8�� ��ֹ��������
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		// ���ö�ά�����ܰ�ɫ����Ĵ�С
		hints.put(EncodeHintType.MARGIN, 1);
		// ���ö�ά����ݴ���
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// ����ά��
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
		BufferedImage image = toBufferedImage(bitMatrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	/**
	 * ����ά��ͼƬ��������
	 * 
	 * @param pressText
	 *            ����
	 * @param qrFile
	 *            ��ά���ļ�
	 * @param fontStyle
	 * @param color
	 * @param fontSize
	 */
	public static void pressText(String pressText, File qrFile, int fontStyle, Color color, int fontSize)
			throws Exception {
		//pressText = new String(pressText.getBytes(), "utf-8");
		Image src = ImageIO.read(qrFile);
		int imageW = src.getWidth(null);
		int imageH = src.getHeight(null);
		BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		g.drawImage(src, 0, 0, imageW, imageH, null);
		// ���û��ʵ���ɫ
		g.setColor(color);
		// ��������
		Font font = new Font("����", fontStyle, fontSize);
		FontMetrics metrics = g.getFontMetrics(font);
		// ������ͼƬ�е����� �����������м�
		int startX = (WIDTH - metrics.stringWidth(pressText)) / 2;
		int startY = HEIGHT / 2;
		g.setFont(font);
		g.drawString(pressText, startX, startY);
		g.dispose();
		FileOutputStream out = new FileOutputStream(qrFile);
		ImageIO.write(image, "JPEG", out);
		out.close();
		System.out.println("image press success");
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File qrcFile = new File("D:/root.qrcode/", "google.jpg");
//		writeToFile("www.google.com.hk", "jpg", qrcFile);
		pressText("�ȸ�", qrcFile, 5, Color.RED, 32);
	}

}
