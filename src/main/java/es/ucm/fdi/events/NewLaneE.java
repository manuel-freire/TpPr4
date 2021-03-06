package es.ucm.fdi.events;

import java.util.ArrayList;

import es.ucm.fdi.exceptions.MissingObjectExc;
import es.ucm.sim.Simulator;
import es.ucm.sim.obj.Junction;
import es.ucm.sim.obj.Lane;
import es.ucm.sim.obj.Road;
import es.ucm.sim.obj.Vehicle;

/*
 * 		"Y voy silbando por la calle. Nada
 * me importas tú, ciudad donde naciera.
 * Ciudad donde muy lejos, muy lejano,
 * se escucha el mar, la mar de Dios, inmensa."
 * 
 * 		Blas de Otero
 */
public class NewLaneE extends NewRoadE{
	private int lanes;

	public NewLaneE(int lanes, int time, String src, String dest, int vMax, int length, String id) {
		super(time, src, dest, vMax, length, id);
		this.lanes = lanes;
	}
	
	public void ejecuta(Simulator s, ArrayList<Junction> js, ArrayList<Road> rs, ArrayList<Vehicle> vs) throws MissingObjectExc {
		if(done) return;
		if(s.getRoadMap().getJunction(iniJ) == null || s.getRoadMap().getJunction(finalJ) == null)
			throw new MissingObjectExc(this);
		Junction iniJJ = s.getRoadMap().getJunction(iniJ), finalJJ = s.getRoadMap().getJunction(finalJ);
		Lane myLane = new Lane(lanes, vMax, length, iniJJ, finalJJ, id);
		finalJJ.addRoad(myLane);
		rs.add(myLane);
		done = true;
	}
}
