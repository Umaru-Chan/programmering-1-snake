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
                playSound();
                Thread.sleep(1500);
            }catch(Exception e){e.printStackTrace();}
        }
    }

    static void playSound() throws Exception
    {
        InputStream in = new FileInputStream("DIN_FIl.LMAO");
        AudioStream as = new AudioStream(in);
        AudioPlayer.player.start(as);
    }
}
