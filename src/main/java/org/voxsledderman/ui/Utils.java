package org.voxsledderman.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {

        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, newW, newH, null);
        g2d.dispose();

        return dimg;
    }
}
