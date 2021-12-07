package de.robi.solarsystem.frame;

import de.robi.solarsystem.math.Vector;
import de.robi.solarsystem.system.Body;
import de.robi.solarsystem.system.SolarSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Frame extends JFrame {

	@Serial
	private static final long serialVersionUID = 1L;

	private final BufferStrategy bufferStrategy;
	private final Canvas canvas;

	private final SolarSystem system;

	private final Random random;
	private final Map<Body, Color> colors;

	private Vector center;

	public Frame(SolarSystem system) {
		super();

		Dimension size = new Dimension(1200, 1000);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Point position = new Point((screen.width - size.width) / 2, (screen.height - size.height) / 2);

		setMinimumSize(size);
		setLocation(position);
		setSize(size);

		JPanel panel = (JPanel) getContentPane();

		canvas = new Canvas();
		panel.add(canvas);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		this.system = system;

		colors = new HashMap<>();
		random = new Random();
		run();
	}


	private void run() {
		new Thread(() -> {
			while(true) {
				Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

				graphics.setColor(Color.BLACK);
				graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

				double ratio = (double) canvas.getWidth() / canvas.getHeight();

				this.center = system.centerOfWeight(this.center, 200E9, 400E9);

				double minX = (this.center.x() - 160E9) * ratio;
				double minY = this.center.y() - 160E9;
				double maxX = (this.center.x() + 160E9) * ratio;
				double maxY = this.center.y() + 160E9;

				for(Body body : system.bodies) {
					int px = (int) ((body.x.x() - minX) * canvas.getWidth() / (maxX - minX));
					int py = (int) ((body.x.y() - minY) * canvas.getHeight() / (maxY - minY));

					if(!colors.containsKey(body)) {
						Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
						colors.put(body, color);
					}

					graphics.setColor(colors.get(body));

					graphics.fillOval(px - 5, py - 5, 11, 11);
				}

				graphics.dispose();
				bufferStrategy.show();
			}
		}).start();
	}
}
