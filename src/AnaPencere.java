import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class AnaPencere extends JFrame{

    private int mGenislik = 800;
    private int mYukseklik = 600;
    private static AnaPencere mPencere = null;//aşağıdaki static anapencere için yazdık ve private yaptımız için yaptık bunları
  

    private AnaPencere() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);//pencere kapanınca program kapanacak
        SetDimension(mGenislik,mYukseklik);//pencereyi genişlik yükseklik veriyoruz
        setResizable(false);//pencereyi elle büyütemezler
        
        Yılan Y = new Yılan();
        add(Y);//ana pencere ile bağlantı burası
        
    }
    public static AnaPencere PencereGetir(){//pencere oluşmamısa burda oluşacak kontrol ediyoruz
        if(mPencere == null){
            mPencere=new AnaPencere();
            return mPencere;
        }
        return new AnaPencere();
    }
    
    public void SetDimension(int Genislik,int Yukseklik){
        Dimension Dim= Toolkit.getDefaultToolkit().getScreenSize();//Ekranın ortasında çıksın diye oyun
        int PosX=0;
        int PosY=0;
        if(Genislik+100> Dim.width){
            mGenislik = Dim.width-100;
        }
        if(mYukseklik+100>Dim.height){
            mYukseklik = Dim.height-100;
        }
        PosX = (Dim.width-mGenislik)/2;
        PosY = (Dim.height-mYukseklik)/2;
        setBounds(PosX,PosY,mGenislik,mYukseklik);
    }

    
}
