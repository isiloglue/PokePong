package PokePong.Model;

import PokePong.Controller.GameState;
import org.junit.jupiter.api.Test;
import PokePong.Model.Ball;
import PokePong.Model.Player;
import PokePong.Model.XYTupel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class test {
    private GameState pongGame;
    private Ball acceleration;
    private Object size;
    private PongModel pongModel;


    @BeforeEach
    public void setUp() {
        // Her test öncesinde yeni bir PongModel oluştur.
        pongModel = new PongModel(800, 600);
    }

            @Test
            public void testUpdateBallPosition() {
                //size radyan 1 saniye sonra topun hala (0,0) konumda oldugunu kontrol eder.
                Ball ball = new Ball(0, 0, 10);
                ball.updateBallPosition(1.0);
                assertEquals(new XYTupel(0, 0), ball.getPosition());
                //topun evmesi bir saniye sonra degismediyse test basarili olmaz
                ball.randomizeAcceleration();
                ball.updateBallPosition(1.0);
                assertNotEquals(new XYTupel(0, 0), ball.getPosition());
            }

            @Test
            public void testRandomizeAcceleration() {
                //Bu test metodu randomizeAcceleration metodunun çalışmasını ve topun ivmesinin beklenen şekilde rastgele değiştirilip değiştirilmediğini doğrular
                Ball ball = new Ball(0, 0, 10);
                ball.randomizeAcceleration();
                assertNotEquals(new XYTupel(0, 0), ball.getAcceleration());
            }

            @Test
            public void testCollidesY() {
                //topun radyani 10 olarak ayarlanir
                Ball ball = new Ball(0, 0, 10);
                //y kordinati bes olarak ayarlanir
                ball.getPosition().setY(5);
                //Bu test metodu collidesY metodunun topun y koordinatı ile doğru bir şekilde çarpışıp çarpışmadığını kontrol eder
                assertTrue(ball.collidesY(5));
                assertFalse(ball.collidesY(0));
            }

            @Test
            public void testCollidesX() {
                //topun radyan 10
                Ball ball = new Ball(0, 0, 10);
                //x koordinati bes
                ball.getPosition().setX(5);
                //Bu test metodu collidesX metodunun topun x-koordinatı ile doğru bir şekilde çarpışıp çarpışmadığını kontrol eder
                assertTrue(ball.collidesX(5));
                assertFalse(ball.collidesX(0));
            }

    @Test
    public void testGetSize() {
        //topun radyan 15
        Ball ball = new Ball(0, 0, 15);
        // Check the getSize method
        assertEquals(15, ball.getSize());
    }

    @Test
    public void testGetPosition() {
        // erstellt  ball mit position (10, 20)
        Ball ball = new Ball(10, 20, 0);

        // prüf  getPosition method
        XYTupel expectedPosition = new XYTupel(10, 20);
        assertEquals(expectedPosition, ball.getPosition());
    }

    @Test
    public void testGetRotationAngle() {
        // erstell  ball mit angle 45
        Ball ball = new Ball(0, 0, 0);
        ball.angle = 45;

        // prüf  getRotationAngle method
        assertNotEquals(0.7853981633974483, ball.getRotationAngle());
    }

    @Test
    public void testGetAcceleration() {
        // erstellt  ball mit acceleration (2, 3)
        Ball ball = new Ball(0, 0, 0);
        ball.acceleration = new XYTupel(2, 3);

        // prüf  getAcceleration method
        XYTupel expectedAcceleration = new XYTupel(2, 3);
        assertEquals(expectedAcceleration, ball.getAcceleration());
    }


        @Test
        public void testHits() {
            // Oyuncu oluştur
            Player player = new Player(0, 0, 10);

            // Top oluştur ve pozisyonunu ayarla
            Ball ball = new Ball(0, 0, 5);

            // Oyuncu topa çarpıyor mu diye kontrol et capiyorsa basarili
            assertTrue(player.hits(ball));

            // Topun pozisyonunu değiştir
            ball.getPosition().setY(20);

            // Oyuncu topa çarpmıyor mu diye kontrol et
            assertFalse(player.hits(ball));
        }

        @Test
        public void testGetInstance() {
            // İlk çağrıda yeni bir nesnenin oluşturulup oluşturulmadığını kontrol et
            PongData firstInstance = PongData.getInstance();
            if (firstInstance == null) {
                // Eğer ilk çağrıda instance null ise, hata fırlat
                fail("Erste instance ist null");
            }

            // Aynı nesnenin tekrar döndürülüp döndürülmediğini kontrol et
            PongData secondInstance = PongData.getInstance();
            if (secondInstance == null) {
                // Eğer ikinci çağrıda instance null ise, hata fırlat
                fail("Zweite instance ist null");
            }

            // İlk ve ikinci çağrıların aynı nesneyi döndürüp döndürmediğini kontrol et
            //ayni nesneyi temsil edioyrsa test basarili olur
            assertEquals(firstInstance, secondInstance);
        }
        //Pong datanin testeri daha yapilmadi

    @Test
    public void testStartNewGame() {
        // startNewGame metodunu test et
        pongModel.startNewGame();

        // Oyunun durumu PLAYING olmalı
        assertEquals(GameState.PLAYING, pongModel.getData().state);

        // Oyuncuların ve topun oluşturulduğunu kontrol et herhangi biri null ise basarisiz olur
        assertNotNull(pongModel.getData().getPlayer1());
        assertNotNull(pongModel.getData().getPlayer2());
        assertNotNull(pongModel.getData().getBall());

        // Topun hızının rastgele atandığını kontrol et
        assertNotEquals(0, pongModel.getData().getBall().acceleration.x);
        assertNotEquals(0, pongModel.getData().getBall().acceleration.y);
    }
    @Test
    public void testGetSize2() {
        // Örnek bir Player nesnesi oluşturun
        Player player = new Player(0,0,0);

        // Beklenen boyutu belirleyin
        float expectedSize =0;

        // Metodu çağırın ve geri dönen değeri alın
        float actualSize = player.getSize();

        // Beklenen değerle gerçek değeri karşılaştırın
        assertEquals(expectedSize, actualSize, "Player size ist nicht richtig.");
    }
    @Test
    public void testGetPosition2() {
        // Örnek bir Player nesnesi oluşturun
        Player player = new Player(0,0,0);

        // Beklenen pozisyonu belirleyin
        XYTupel expectedPosition = new XYTupel(0,0);

        // Metodu çağırın ve geri dönen değeri alın
        XYTupel actualPosition = player.getPosition();

        // Beklenen değerle gerçek değeri karşılaştırın
        assertEquals(expectedPosition, actualPosition, "Player position ist nicht richtig .");
    }

    @Test
    public void testGetWidth() {
        // PongModel örneği oluşturun
        PongModel pongModel = new PongModel(10,20);

        // Beklenen genişliği belirleyin
        int expectedWidth =10 /* beklenen genişlik değerini belirleyin */;

        // Metodu çağırın ve geri dönen değeri alın
        int actualWidth = pongModel.getWidth();

        // Beklenen değerle gerçek değeri karşılaştırın
        assertEquals(expectedWidth, actualWidth, "PongModel genişliği doğru değil.");
    }

    @Test
    public void testGetHeight() {
        // PongModel örneği oluşturun
        PongModel pongModel = new PongModel(10,20);

        // Beklenen yüksekliği belirleyin
        int expectedHeight =20;

        // Metodu çağırın ve geri dönen değeri alın
        int actualHeight = pongModel.getHeight();

        // Beklenen değerle gerçek değeri karşılaştırın
        assertEquals(expectedHeight, actualHeight, "PongModel high ist nicht richtig.");
    }
    @Test
    public void testGetX() {
        // Örnek bir Player nesnesi oluşturun
        XYTupel xyTupel = new XYTupel(0,0);

        // Beklenen pozisyonu belirleyin
        double expectedPosition =0;

        // Metodu çağırın ve geri dönen değeri alın
        float actualPosition = xyTupel.getX();

        // Beklenen değerle gerçek değeri karşılaştırın
        assertEquals(expectedPosition, actualPosition, "Player pozisyonu doğru değil.");
    }
    @Test
    public void testGetY() {
        // Örnek bir Player nesnesi oluşturun
        XYTupel xyTupel = new XYTupel(0,0);

        // Beklenen pozisyonu belirleyin
        double expectedPosition =0;

        // Metodu çağırın ve geri dönen değeri alın
        float actualPosition = xyTupel.getY();

        // Beklenen değerle gerçek değeri karşılaştırın
        assertEquals(expectedPosition, actualPosition, "Player pozisyonu doğru değil.");
    }

   @Test
    public void testHandleCollisions(){
       // Örnek veri oluştur
       PongModel pongModel = new PongModel(800, 600);
       pongModel.startNewGame();

       // Topun başlangıç hızını kaydet
       float initialBallAccelerationX = pongModel.getData().getBall().acceleration.x;
       float initialBallAccelerationY = pongModel.getData().getBall().acceleration.y;

       // Topun x eksenine çarpmayacak bir konumda oluştur
      pongModel.getData().getBall().setPosition(new XYTupel(100, 100));
       // handleCollisions metodunu çağır
       pongModel.handleCollisions();

       // Beklenen davranışları kontrol et
       // Topun y eksenine çarpması durumunda y hızının tersine çevrildiğini kontrol et
       //delta=expected valuee-actualvalue
       assertEquals(-initialBallAccelerationY, pongModel.getData().getBall().acceleration.y, 0.001);

       // Topun oyuncu ile çarpışması durumunda x hızının tersine çevrildiğini kontrol et
       assertEquals(-initialBallAccelerationX, pongModel.getData().getBall().acceleration.x, 0.001);

       // Topun x eksenine çarpması durumunda oyun durumunun GAME_OVER olduğunu kontrol et
       assertEquals(GameState.GAME_OVER, pongModel.getData().state);

       }
    @Test
    public void testToString() {
        // Örnek veri oluştur
        PongModel pongModel = new PongModel(800, 600);
        pongModel.startNewGame();

        // Beklenen çıktıyı tanımla
        String expectedOutput = "Ball x: " + pongModel.getData().getBall().position.x + " " +
                "Ball y:" + pongModel.getData().getBall().position.y + " " +
                "Player 1 x: " + pongModel.getData().getPlayer1().position.x + " " +
                "Player 1 y:" + pongModel.getData().getPlayer1().position.y + " " +
                "Player 2 x: " + pongModel.getData().getPlayer2().position.x + " " +
                "Player 2 y:" + pongModel.getData().getPlayer2().position.y;

        // toString() metodunu çağır ve çıktıyı kontrol et
        assertEquals(expectedOutput, pongModel.toString());
    }




}

/**
    @Test
    public void testGetP1X() {
        // PongModel örneği oluşturun
        PongModel pongModel = new PongModel(10,20);

        // Beklenen X pozisyonunu belirleyin
        float expectedP1X =30;

        // Metodu çağırın ve geri dönen değeri alın
        float actualP1X = pongModel.getP1X();

        // Beklenen değerle gerçek değeri karşılaştırın
        assertEquals(expectedP1X, actualP1X, "P1 X pozisyonu doğru değil.");
    }

    // Diğer metotların testleri de benzer şekilde yazılabilir
} */



















