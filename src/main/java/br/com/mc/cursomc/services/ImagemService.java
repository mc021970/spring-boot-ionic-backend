package br.com.mc.cursomc.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import br.com.mc.cursomc.domain.Arquivo;
import br.com.mc.cursomc.services.exception.FileException;

public interface ImagemService {

	String getCaminhoImagem(Arquivo arq);
	
	Arquivo guardarImagem(Arquivo arq);
	
	Arquivo obterImagem(Integer id);
	
	default byte[] createProfilePicture(byte[] b, String ext) throws IOException {
		BufferedImage bimg = getImage(b);
		bimg = cropSquare(bimg);
		bimg = resize(bimg, 200);
		return getBytes(bimg, ext);
	}

	default BufferedImage getImage(byte[] b) throws IOException {
		return ImageIO.read(new ByteArrayInputStream(b));
	}
	

	default BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(
			sourceImg, 
			(sourceImg.getWidth()/2) - (min/2), 
			(sourceImg.getHeight()/2) - (min/2), 
			min, 
			min);		
	}

	default BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}
	
	default byte[] getBytes(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return os.toByteArray();
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}
}
