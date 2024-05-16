package com.snake;

import javax.swing.ImageIcon;

public class ImageLoader {
    public static final String background = "/images/background/navy_blue.jpg";
    public static final String singleButtonImage = "/images/buttons/onePlayer.jpg";
    public static final String twoButtonImage = "/images/buttons/twoPlayer.png";
    public static final String fruitImage = "/images/fruits/fruit.png";
    public static final String bombImage = "";

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
