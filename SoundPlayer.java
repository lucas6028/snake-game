import javax.sound.sampled.*;

public class SoundPlayer {

    public static void main(String[] args) {
        playSound("sounds/your_wav_file.wav");
    }

    public static void playSound(String soundFilePath) {
        try {
            // Load the sound file
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    SoundPlayer.class.getResourceAsStream(soundFilePath));
            clip.open(inputStream);

            // Start playing the sound
            clip.start();

            // Wait for the sound to finish
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);

            // Clean up resources
            clip.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
