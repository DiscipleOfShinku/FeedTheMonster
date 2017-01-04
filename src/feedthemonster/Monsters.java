package feedthemonster;

import java.util.ArrayList;
import java.util.Iterator;

public class Monsters {
	
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	
	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	
	public ArrayList<String> monsterNames() {
		ArrayList<String> monsterNames = new ArrayList<String>();
		String name = new String();
		Iterator<Monster> itr = monsters.iterator();
		while (itr.hasNext()) {
			name = itr.next().getName();
			monsterNames.add(name);
			name = new String();
		}
		return monsterNames;
	}

}
