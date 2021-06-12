package com.cloudfactory.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconHandler {
	public static ImageIcon resizeIcon(String path) {
		ImageIcon newIconSrc = new ImageIcon(path);
		ImageIcon newIcon = new ImageIcon(newIconSrc.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		return newIcon;
	}
}
