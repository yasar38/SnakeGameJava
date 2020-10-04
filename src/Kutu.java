import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;

public class Kutu extends JLabel{
    public int mGenislik =20;
    public int mYon = Yon.SAG;
    public Kutu() {
       
        setBounds(100, 100, mGenislik, mGenislik);//yılan oluşturmak için kutu tanımladık
    }
    @Override
    public void paint(Graphics g){
           Graphics2D g2 = (Graphics2D)g;//dönşüm yapıyoruz içerisinde var zaten
        Rectangle2D rect = new Rectangle2D.Double(0,0,getWidth(),getHeight());
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(10));
        g2.draw(rect);//Şu ana kadar ana pencerenin içinde bir pencere açtık hepsi onun içindi...
        g2.fill(rect);//kutunun içi dolduruyor
    }
    public void SolaGit(){
                int PosX = getX();
                int PosY = getY();
                PosX-=mGenislik;
                setBounds(PosX, PosY, mGenislik, mGenislik);
    }
    public void SagaGit(){
        int PosX = getX();
                int PosY = getY();
                PosX+=mGenislik;
                setBounds(PosX, PosY, mGenislik, mGenislik);
    }
    public void YukarıGit(){
        int PosX = getX();
                int PosY = getY();
                PosY-=mGenislik;
                setBounds(PosX, PosY, mGenislik, mGenislik);
    }
    public void AsagiGit(){
        int PosX = getX();
                int PosY = getY();
                PosY+=mGenislik;
                setBounds(PosX, PosY, mGenislik, mGenislik);
    }
    public  Kutu KutuOlustur(){
        Kutu k =new Kutu();
        int X = getX();
        int Y = getY();
        k.setBounds(X,Y,mGenislik,mGenislik);//kutular burda üst üste
        k.mYon=-mYon;//oluşan kutu ters yöne gidicek diğer kutunun yani arkasına gelecek
        k.Hareket();//hareket komutu veriyoruz
        k.mYon=mYon;//tekrardan kutuyla aynı yöne gönderiyoruz böylece peşinde bir kutu daha olcak
        return k;
    }
    public void Hareket(){//yılanın otomaik gitmesi için
        if(mYon==Yon.SOL){
            SolaGit();
        }
        else if(mYon==Yon.SAG){
            SagaGit();
        }
        else if(mYon==Yon.ASAGI){
            AsagiGit();
        }
        else{
            YukarıGit();
        }
    }
}
