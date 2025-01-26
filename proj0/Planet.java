public class Planet {
	public static double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;	
	}
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p) {
		double xDif = xxPos - p.xxPos;
		double yDif = yyPos - p.yyPos;
		double result = Math.sqrt(xDif * xDif + yDif * yDif);
		return result;
	}
	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		if (r == 0) {
			return 0;
		}
		double F = G * mass * p.mass / (r * r);
		return F;
	}
	public double calcForceExertedByX(Planet p) {
		double r = this.calcDistance(p);
		if (r == 0) {
			return 0;
		}
		double xDif = p.xxPos - xxPos;
		double F = this.calcForceExertedBy(p);
		double Fx = F * (xDif / r);
		return Fx;
	}
	public double calcForceExertedByY(Planet p) {
		double r = this.calcDistance(p);
		if (r == 0) {
			return 0;
		}
		double yDif = p.yyPos - yyPos;
		double F = this.calcForceExertedBy(p);
		double Fy = F * (yDif / r);
		return Fy;
	}
	public double calcNetForceExertedByX(Planet[] allPlanet) {
		int length = allPlanet.length;
		double xSumNetForce = 0;
		for (int i = 0; i < length; i++) {
			double xNetForce = this.calcForceExertedByX(allPlanet[i]);
			xSumNetForce += xNetForce;
		}
		return xSumNetForce;
	}
	public double calcNetForceExertedByY(Planet[] allPlanet) {
		int length = allPlanet.length;
		double ySumNetForce = 0;
		for (int i = 0; i < length; i++) {
			double yNetForce = this.calcForceExertedByY(allPlanet[i]);
			ySumNetForce += yNetForce;
		}
		return ySumNetForce;
	}
	public void update(double dt, double Fx, double Fy) {
		double ax = Fx / mass;
		double ay = Fy / mass;
		double vx = xxVel + ax * dt;
		double vy = yyVel + ay * dt;
		xxVel = vx;
		yyVel = vy;
		double x = xxPos + xxVel * dt;
		double y = yyPos + yyVel * dt;
		xxPos = x;
		yyPos = y;
	}
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
		StdDraw.show();	
	} 
}
