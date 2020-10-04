
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JLabel;

public class Yem extends JLabel{
    public int mGenlislik=20;
    
    public Yem() {
        setPosition(20, 20);//yemin baslangıc pozisyonu
    }
    @Override
    public void paint(Graphics g){//yemin şeklini belirledik
           Graphics2D g2 = (Graphics2D)g;//dönşüm yapıyoruz içerisinde var zaten
        Ellipse2D elipse = new Ellipse2D.Double(0,0,mGenlislik,mGenlislik);//şekil kısmı
        g2.setColor(Color.RED);
        g2.draw(elipse);//Şu ana kadar ana pencerenin içinde bir pencere açtık hepsi onun içindi...
        g2.fill(elipse);//kutunun içi dolduruyor
        g2.setStroke(new BasicStroke(10));
    }
    public void setPosition(int PosX,int PosY){
        setBounds(PosX,PosY,mGenlislik,mGenlislik);
    }
}


