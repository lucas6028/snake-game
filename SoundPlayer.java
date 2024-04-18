public class MySoundPlayer {

    public void playSound() throws IOException, UnsupportedAudioFileException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL soundURL = classLoader.getResource("sounds/mySound.wav"); // Adjust path if needed

        if (soundURL == null) {
            throw new IOException("Sound file not found: sounds/mySound.wav");
        }

        // Use the URL to create an AudioInputStream for playback
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        // ... (Continue with playback using an audio player library)
        // ... (code from previous step)
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioInputStream);
        clip.start(); // Plays the audio
    }
}
