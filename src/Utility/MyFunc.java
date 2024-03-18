package Utility;

public class MyFunc {

    public static void Bekle(int sn){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
