package es.ucm.sim.obj;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Car extends Vehicle{
	
	private int resistKm;
	private int lastFaultKm;
	private int maxFault;
	private double faultProb;
	private Random n;
	private int kmSinceFault() {//distancia desde última avería
		return kilometrage() - lastFaultKm;
	}
	
	public Car(int resistance, double faultP, int maxFault, long seed, int vMax, ArrayList<Road> it, String id) {
		super(vMax, it, id);
		resistKm = resistance;
		this.maxFault = maxFault;
		faultProb = faultP;
		n = new Random(seed);
	}
	
	public void avanza() {
		if(tAveria > 0) {
			lastFaultKm = 0;
			--tAveria;
			return;
		}
		if(kmSinceFault() >= resistKm) {
			if(n.nextDouble() < faultProb)
				tAveria = n.nextInt(maxFault) + 1;
		}
		super.avanza();
	}
	protected void fillReportDetails(Map<String, String> out) {
		super.fillReportDetails(out);
		out.put("type", "car");
		out.put("resistance", String.valueOf(resistKm));
		out.put("fault_probability", String.valueOf(faultProb));
		out.put("max_fault_duration", String.valueOf(maxFault));
		out.put("seed", String.valueOf(n));
	}
}