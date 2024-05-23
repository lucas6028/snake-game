package com.snake;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sounds {
    public Sounds() {

    }

    public void GetPoints() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/GetPoints1.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/mySound.wav");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        
        // Create a Clip to play the audio
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        
        // Open the clip and start playing the audio
        clip.open(audioInputStream);
        clip.start(); // Plays the audio
    }

    public void Boom() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/Boom1.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/mySound.wav");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        
        // Create a Clip to play the audio
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        
        // Open the clip and start playing the audio
        clip.open(audioInputStream);
        clip.start(); // Plays the audio
    }

    public void GameOver() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/GameOver.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/mySound.wav");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        
        // Create a Clip to play the audio
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        
        // Open the clip and start playing the audio
        clip.open(audioInputStream);
        clip.start(); // Plays the audio
    }
    public void Start() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/Start.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/mySound.wav");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        
        // Create a Clip to play the audio
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        
        // Open the clip and start playing the audio
        clip.open(audioInputStream);
        clip.start(); // Plays the audio
    }

    public void BeforeGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/11638.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/11638.wav");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        
        // Create a Clip to play the audio
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        final Clip clip = (Clip) AudioSystem.getLine(info); // 加上 final 修飾符

        // 註冊 LineListener 監聽器
        clip.addLineListener(new LineListener() {
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) { // 當音檔播放完畢時
                    // 使用 SwingUtilities.invokeLater() 將重新播放音檔的動作排入事件分發線程中執行
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            clip.stop(); // 停止播放
                            clip.setFramePosition(0); // 將播放位置設定回開頭
                            clip.start(); // 重新播放音檔
                        }
                    });
                }
            }
        });

        // Open the clip and start playing the audio
        clip.open(audioInputStream);
        clip.start(); // 播放音檔
    }

    public void Click() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/Click.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/mySound.mp3");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        
        // Create a Clip to play the audio
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        
                // Open the clip and start playing the audio
        clip.open(audioInputStream);
        clip.start(); // Plays the audio
    }

    public void Gun() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/gun.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/gun.wav");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);

        // Create a Clip to play the audio
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);

        // Open the clip and start playing the audio
        clip.open(audioInputStream);
        clip.start(); // Plays the audio
    }

    public void Hurt() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/Hurt1.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/Hurt1.wav");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);

        // Create a Clip to play the audio
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);

        // Open the clip and start playing the audio
        clip.open(audioInputStream);
        clip.start(); // Plays the audio
    }
}
