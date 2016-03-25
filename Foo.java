/**
 * Created by Alexander on 3/24/2016.
 */
import  java.io.*;
import  sun.audio.*;

public class Foo {

    static AudioStream as;

    public static void main(String[]args){
        loop();
    }

    static void loop()
    {
        while(true)
        {
            try{
                playSound("res/wellcome.wav");
                Thread.sleep(500);
                playSound("res/wellcome.wav");
                Thread.sleep(500);
                playSound("res/wellcome.wav");
                Thread.sleep(500);

                stopSound();
                break;

            }catch(Exception e){e.printStackTrace();}
        }
    }

    static void playSound(String path) throws Exception
    {
        InputStream in = new FileInputStream(path);
        as = new AudioStream(in);
        AudioPlayer.player.start(as);
    }

    static void stopSound()
    {
        AudioPlayer.player.stop(as);
    }
}
