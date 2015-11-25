import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Date;

public class ScreenShot {
	/**
	* method to capture screen shot
	* @param String uploadPath to save screen shot as image
	* @returns boolean true if capture successful else false
	*/
	public static BufferedImage captureScreenShot() throws AWTException
	{
		boolean isSuccesful = false;
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage capture;
		capture = new Robot().createScreenCapture(screenRect);
		return capture;
	}

	public static void main(String[] args) throws InterruptedException,AWTException,IOException
	{

		int time = 15000;
		if (args.length > 0) {
			try {
		        time = Integer.parseInt(args[0]) * 1000;
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		    }
		}

		int fps = 30;
		if (args.length > 1) {
			try {
		        fps = Integer.parseInt(args[1]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[1] + " must be an integer.");
		    }
		}

		int timeOfFrame = (int)(1000/fps);
		int frame = 0;

		long currentTime = new Date().getTime();
		long finalTime = currentTime + time;

		BufferedImage[] images = new BufferedImage[(int)(time/timeOfFrame)];

		int imgIdx = 0;
		while (true) {
			System.out.println(String.format("Capturing frame %d", frame));
			images[imgIdx++] = captureScreenShot();
			if ((new Date().getTime()) >= finalTime)
				break;
			frame++;
		}
		
		System.out.println(String.format("Captured %d frames! Recording to disc...", frame));

		for (int i = 0; i < frame; i++) {
			// screen shot image will be save at given path with name "screen.jpeg"
			ImageIO.write(images[i], "jpg", new File( "/tmp/screenshot/files", String.format("screen-%d.jpeg", i))); 
		}

		System.out.println("Done!");
	}
}