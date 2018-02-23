package com.common.UI;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.beans.ConstructorProperties;
import java.beans.Transient;
import java.net.URL;

import javax.swing.ImageIcon;

public class MyImageIcon extends ImageIcon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7352021165935345846L;

	/*
	 * Keep references to the filename and location so that alternate
	 * persistence schemes have the option to archive images symbolically rather
	 * than including the image data in the archive.
	 */
	@SuppressWarnings("unused")
	transient private String filename;
	@SuppressWarnings("unused")
	transient private URL location;

	transient Image image;
	transient int loadStatus = 0;
	ImageObserver imageObserver;
	String description = null;

	/**
	 * Creates an ImageIcon from the specified file. The image will be preloaded
	 * by using MediaTracker to monitor the loading state of the image.
	 * 
	 * @param filename
	 *            the name of the file containing the image
	 * @param description
	 *            a brief textual description of the image
	 * @see #ImageIcon(String)
	 */
	public MyImageIcon(String filename, String description) {
		image = Toolkit.getDefaultToolkit().getImage(filename);
		if (image.getHeight(null) > 0) {
			return;
		}
		if (filename != null) {
			StackTraceElement[] stackList = Thread.currentThread().getStackTrace();
			StackTraceElement lastStack = null;
			for (int i = stackList.length - 1; i >= 0; i--) {
				if (!stackList[i].getClass().equals(getClass())) {
					lastStack = stackList[i];
					break;
				}
			}
			if (filename.contains("\\")) {
				while (filename.contains("\\")) {
					filename = filename.replaceAll("\\\\", "/");
				}
			}
			if (filename.contains("/")) {
				if (filename.indexOf("/") != 0 && filename.indexOf("./") != 0) {
					filename = "/" + filename;
				}
			} else
				filename = "/" + filename;

			if (lastStack != null) {
				URL url = lastStack.getClass().getResource(filename);
				if (url != null)
					image = new ImageIcon(url).getImage();
				else
					System.out.println("Cannot find the image file : " + filename);
			}

			else
				System.out.println("lastStack is null");
			if (image == null) {
				System.out.println("123");
				return;
			}
			this.filename = filename;
			this.description = description;
			loadImage(image);

		}
	}

	/**
	 * Creates an ImageIcon from the specified file. The image will be preloaded
	 * by using MediaTracker to monitor the loading state of the image. The
	 * specified String can be a file name or a file path. When specifying a
	 * path, use the Internet-standard forward-slash ("/") as a separator. (The
	 * string is converted to an URL, so the forward-slash works on all
	 * systems.) For example, specify:
	 * 
	 * <pre>
	 * new ImageIcon("images/myImage.gif")
	 * </pre>
	 * 
	 * The description is initialized to the <code>filename</code> string.
	 *
	 * @param filename
	 *            a String specifying a filename or path
	 * @see #getDescription
	 */
	@ConstructorProperties({ "description" })
	public MyImageIcon(String filename) {
		this(filename, filename);
	}

	/**
	 * Creates an ImageIcon from the specified URL. The image will be preloaded
	 * by using MediaTracker to monitor the loaded state of the image.
	 * 
	 * @param location
	 *            the URL for the image
	 * @param description
	 *            a brief textual description of the image
	 * @see #ImageIcon(String)
	 */
	public MyImageIcon(URL location, String description) {
		image = Toolkit.getDefaultToolkit().getImage(location);
		if (image == null) {
			return;
		}
		this.location = location;
		this.description = description;
		loadImage(image);
	}

	/**
	 * Creates an ImageIcon from the specified URL. The image will be preloaded
	 * by using MediaTracker to monitor the loaded state of the image. The
	 * icon's description is initialized to be a string representation of the
	 * URL.
	 * 
	 * @param location
	 *            the URL for the image
	 * @see #getDescription
	 */
	public MyImageIcon(URL location) {
		this(location, location.toExternalForm());
	}

	/**
	 * Creates an ImageIcon from the image.
	 * 
	 * @param image
	 *            the image
	 * @param description
	 *            a brief textual description of the image
	 */
	public MyImageIcon(Image image, String description) {
		super(image, description);
	}

	/**
	 * Creates an ImageIcon from an image object. If the image has a "comment"
	 * property that is a string, then the string is used as the description of
	 * this icon.
	 * 
	 * @param image
	 *            the image
	 * @see #getDescription
	 * @see java.awt.Image#getProperty
	 */
	public MyImageIcon(Image image) {
		this.image = image;
		Object o = image.getProperty("comment", imageObserver);
		if (o instanceof String) {
			description = (String) o;
		}
		loadImage(image);
	}

	/**
	 * Creates an ImageIcon from an array of bytes which were read from an image
	 * file containing a supported image format, such as GIF, JPEG, or (as of
	 * 1.3) PNG. Normally this array is created by reading an image using
	 * Class.getResourceAsStream(), but the byte array may also be statically
	 * stored in a class.
	 *
	 * @param imageData
	 *            an array of pixels in an image format supported by the AWT
	 *            Toolkit, such as GIF, JPEG, or (as of 1.3) PNG
	 * @param description
	 *            a brief textual description of the image
	 * @see java.awt.Toolkit#createImage
	 */
	public MyImageIcon(byte[] imageData, String description) {
		this.image = Toolkit.getDefaultToolkit().createImage(imageData);
		if (image == null) {
			return;
		}
		this.description = description;
		loadImage(image);
	}

	/**
	 * Creates an ImageIcon from an array of bytes which were read from an image
	 * file containing a supported image format, such as GIF, JPEG, or (as of
	 * 1.3) PNG. Normally this array is created by reading an image using
	 * Class.getResourceAsStream(), but the byte array may also be statically
	 * stored in a class. If the resulting image has a "comment" property that
	 * is a string, then the string is used as the description of this icon.
	 *
	 * @param imageData
	 *            an array of pixels in an image format supported by the AWT
	 *            Toolkit, such as GIF, JPEG, or (as of 1.3) PNG
	 * @see java.awt.Toolkit#createImage
	 * @see #getDescription
	 * @see java.awt.Image#getProperty
	 */
	public MyImageIcon(byte[] imageData) {
		this.image = Toolkit.getDefaultToolkit().createImage(imageData);
		if (image == null) {
			return;
		}
		Object o = image.getProperty("comment", imageObserver);
		if (o instanceof String) {
			description = (String) o;
		}
		loadImage(image);
	}

	/**
	 * Creates an uninitialized image icon.
	 */
	public MyImageIcon() {
	}

	/**
	 * Returns this icon's <code>Image</code>.
	 * 
	 * @return the <code>Image</code> object for this <code>ImageIcon</code>
	 */
	@Transient
	public Image getImage() {
		return image;

	}
}
