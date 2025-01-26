public class NBody {
	public static double readRadius(String path) {
		In in = new In(path);
		double firstItem = in.readDouble();
		double secondItem = in.readDouble();
		return secondItem;
	}
	public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int number = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[number];
		for (int i = 0; i < number; i++) {
			double[] message = new double[5];
			for (int j = 0; j < 5; j++) {
				message[j] = in.readDouble();
			}
			String name = in.readString();
			planets[i] = new Planet(message[0], message[1], message[2], message[3], message[4], name);
		}
		return planets;
	}
	public static void main(String[] argus) {
		double T = Double.parseDouble(argus[0]);
		double dt = Double.parseDouble(argus[1]);
		String filename = argus[2];
		double radius = readRadius(filename);	
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(0-radius, radius);	
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		StdDraw.show();
		for (int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}	
		StdDraw.enableDoubleBuffering();
		double[] xF = new double[planets.length];
		double[] yF = new double[planets.length];
		int t = 0;
		while (t <= T) {
			for (int i = 0; i < planets.length; i++) {
				xF[i] = planets[i].calcNetForceExertedByX(planets);
				yF[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xF[i], yF[i]);
			}	
			StdDraw.clear();
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for (int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}	
		StdOut.printf("%d\n", planets.length);
  		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
		return ;
	}	
}
