public class TestPlanet {
	public static void main(String[] args) {
		Planet mass = new Planet(0, 0, 0, 0, 10, null);
		Planet earth = new Planet(1, 1, 0, 0, 5, null);
		double Fm_e = earth.calcForceExertedBy(mass);
		double Fe_m = mass.calcForceExertedBy(earth);
		if (Fm_e == Fe_m) {
			System.out.println("PASS: " + Fm_e + " equals to the force exerted by earth " + Fe_m);
		}
		else {
			System.out.println("FATAL: " + Fm_e + " doesn't equals to the force exerted by earth " + Fe_m);
		}
	}
}
