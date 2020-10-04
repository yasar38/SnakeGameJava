
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.*;//timer sınıfı

public class Yılan extends JLabel{
public Kutu mHead = new Kutu();
public Timer mTimer = null;
public Yem mYem = new Yem();
public Random mRandom =null;
public int gecen_sure =0;
public int yenen_yemek =0;
public ArrayList<Kutu>Liste = new ArrayList<Kutu>();//kutu için yapıyoruz listeyi birbirlerine bağlıcaz


    public Yılan() {
        mRandom = new Random(System.currentTimeMillis());//sayılar farklı olsun ki yemler farklı yerde olsun
        addKeyListener(new KlavyeKontrol());//fonksiyondan haberdar ediyoruz
        setFocusable(true);
        mTimer = new Timer(100, new TimerKontrol());
        mTimer.start();
        //Kutu A = mHead.KutuOlustur();
        /*Kutu B = A.KutuOlustur();
        Kutu C = B.KutuOlustur();*/
        //add(A);
        //add(B);
        //add(C);
        Liste.add(mHead);
        for(int i=1;i<10;i++){
            KuyrukEkle();
        }
        add(mYem);
        add(mHead);
        
    }
    
     class TimerKontrol implements ActionListener{
            public void actionPerformed(ActionEvent e){
                HepsiniYurut();
                 if(CarpismaVarmi()){//durdurmak için buraya da ekledik fonk
                    mTimer.stop();
                }
            }
        }
     public void KuyrukEkle(){
         Kutu K = Liste.get(Liste.size()-1).KutuOlustur();
            Liste.add(K);
            add(K);
     }
     public void YemEkle(){//kuyruga eleman ekleyip yem kaybolacak
         int Width = getWidth()-30-mYem.mGenlislik;//içeride olması lazım yemin o yüzden çıkardık
         int Height = getHeight()-30-mYem.mGenlislik;
         int PosX = 10+Math.abs(mRandom.nextInt())%Width;//sayıyı küçültmek için modunu aldık 
         int PosY = 10+Math.abs(mRandom.nextInt())%Height;
         PosX=PosX-PosX%20;//bunları yemin kordinatı sürekli 20 ve katalrında olsun diye yaptık ki yılan yiyebilsin çünkü herşey 20 katına göre yapıldı
         PosY=PosY-PosY%20;
         yenen_yemek++;
         for(int i=0;i<Liste.size();i++){
             if((PosX==Liste.get(i).getX())&&(PosY==Liste.get(i).getY())){//yılan üstünde yem çıkmasın diye
                 YemEkle();
                 return;
             }
         }
         mYem.setPosition(PosX, PosY);
     }
     public void HepsiniYurut(){//birbirlerine bağladık her kutu kendinden 1 önceki kutunun hareketini alıyor
         for(int i=Liste.size()-1;i>0;i--){//tersten fonksiyon yazdık ki ileri gitmek yerine kendinden sonraki kutucuğu takip etsin diye
             Kutu Onceki = Liste.get(i-1);
             Kutu Sonra =Liste.get(i);
             Liste.get(i).Hareket();
             Sonra.mYon=Onceki.mYon;
         }
         mHead.Hareket();
     }
     public boolean CarpismaVarmi(){//yılan çarpınca dursun diye
         int Kalem=10;//kalemle çizdik duracağı alanı
         int genislik=getWidth();//makx en
         int yukseklik=getHeight();//maks boy kalemle çizilen
         if(mHead.getX()<=10){//kenara çarpınca durması için
             return true;
         }
         if(mHead.getX()+mHead.mGenislik>=genislik-Kalem){
             return true;
         }
         if(mHead.getY()<=10){//kenara çarpınca durması için
             return true;
         }
         if(mHead.getY()+mHead.mGenislik>=yukseklik-Kalem){
             return true;
         }
         for(int i=1;i<Liste.size();i++){//yılanın kendi içinden geçmemesi için for döngüsü dönderdik
             int X = Liste.get(i).getX();
             int Y = Liste.get(i).getY();
             if(X==mHead.getX()&&(Y==mHead.getY())){
                 return true;
             }
         }
         if((mYem.getX()==mHead.getX())&&(mYem.getY()==mHead.getY())){
             //Burada yeme çarpınca neler olacağını kontrol edicez
             KuyrukEkle();
             YemEkle();
         }
         return false;
     }
    class KlavyeKontrol implements KeyListener{
       

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) { //tuşa basıldığında çağrılıyor burası
            //sağa sol yukarı aşa gidilmesi için yapıyoruz
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if(mHead.mYon!=Yon.SAG){
                    mHead.mYon=Yon.SOL;//sağa giderken aniden sola gidemesin diye kontrol yaptık
                }
                
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if(mHead.mYon!=Yon.SOL){
                    mHead.mYon=Yon.SAG;
                }
                
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                if(mHead.mYon!=Yon.ASAGI){
                    mHead.mYon=Yon.Yukarı;
                }
                
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(mHead.mYon!=Yon.Yukarı){
                    mHead.mYon=Yon.ASAGI;
                }
                
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    

    @Override
    public void paint(Graphics g) {
        super.paint(g); //super manası normalde default olan çizim işlemlerini yapmasını sağlıyoruz
        gecen_sure += 5;//geçen süreye ekliyoruz
        Graphics2D g2 = (Graphics2D)g;//dönşüm yapıyoruz içerisinde var zaten
        Rectangle2D rect = new Rectangle2D.Double(0,0,getWidth(),getHeight());
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(10));
        g2.draw(rect);//Şu ana kadar ana pencerenin içinde bir pencere açtık hepsi onun içindi...
        if(CarpismaVarmi()){
            mTimer.stop();
            String message = "Oyun Bitti..\n"+
                             "\n Puan : " + yenen_yemek + "." +
                             "\n Geçen Süre :" + gecen_sure/1000.0 + "saniye";
                             
            JOptionPane.showMessageDialog(this, message);//ekrana mesaj kutucugu
            System.exit(0);
        }
    }
    
    
    
}
