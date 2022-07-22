import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivoSaida) throws IOException{
        // leitura da imagem
        
        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_9.jpg")
            //            .openStream();
        //BufferedImage imagemOriginal = ImageIO.read(new File("../entrada/TopMovies_9.jpg"));
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para novo imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escrever um texto na imagem nova
        graphics.drawString("Corra para Mordor", 0, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivoSaida));
    }

    /*public static void main(String[] args) throws IOException {
        GeradorDeFigurinhas geradorDeFigurinhas = new GeradorDeFigurinhas();
        geradorDeFigurinhas.cria();
    }*/
    
}
