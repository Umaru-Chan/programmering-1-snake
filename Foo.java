/**
 * Created by Alexander on 3/24/2016.
 */
import  java.io.*;
import java.util.HashMap;
import java.util.Map;

import  sun.audio.*;

public class Foo {

    static Map<String, AudioStream> streams = new HashMap<>();

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
                playSound("LMAO");
                Thread.sleep(500);
                playSound("AYY");
                Thread.sleep(500);
                playSound("WALLA");
                Thread.sleep(500);
                playSound("NEGER");

                Thread.sleep(15000);

            }catch(Exception e){e.printStackTrace();}
        }
    }

    static void playSound(String path) throws Exception
    {
        InputStream in = new FileInputStream(path);
        streams.put(path, new AudioStream(in));
        AudioPlayer.player.start(streams.get(path));
    }

    static void stopSound(String path) throws Exception
    {
        AudioPlayer.player.stop(streams.get(path));
    }
}
