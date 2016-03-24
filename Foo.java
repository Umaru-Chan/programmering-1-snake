/**
 * Created by Alexander on 3/24/2016.
 */
import  java.io.*;
import  sun.audio.*;

public class Foo {
    public static void main(String[]args){
        loop();
    }

    static void loop()
    {
        while(true)
        {
            try{
                playSound("DIN_FIL.LMAO");
                Thread.sleep(1500);
                playSound("EN_ANNAN.LMAO");
                Thread.sleep(1500);
                playSound("ENNU_EN_ANNAN.LMAO");
                Thread.sleep(1500);
            }catch(Exception e){e.printStackTrace();}
        }
    }

    static void playSound(String path) throws Exception
    {
        InputStream in = new FileInputStream(path);
        AudioStream as = new AudioStream(in);
        AudioPlayer.player.start(as);
    }
}
