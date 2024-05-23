package com.snake;

import javax.swing.ImageIcon;

public class ImageLoader {
    public static final String background = "/images/background/snow.jpg";
    public static final String singleButtonImage = "/images/buttons/one.png";
    public static final String twoButtonImage = "/images/buttons/twoPlayer.png";
    public static final String appleImage = "/images/fruits/apple.png";
    public static final String orangeImage = "/images/fruits/orange.png";
    public static final String chickenLegImage = "/images/fruits/chickenLeg.png";
    public static final String bombImage = "/images/bombs/bomb.png";
    public static final String stoneImage = "";

    public static ImageIcon loadImageIconFromResource(String imagePath) {
        // Get the resource URL
        java.net.URL resourceUrl = ImageLoader.class.getResource(imagePath);
        
        // Check if the resource exists
        if (resourceUrl != null) {
            // Resource found, create and return ImageIcon
            return new ImageIcon(resourceUrl);
        } else {
            // Resource not found, handle accordingly (e.g., log a message or throw an exception)
            System.err.println("Resource not found: " + imagePath);
            return null;
        }
    }

}
