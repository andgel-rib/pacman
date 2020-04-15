package modele;

import java.awt.Point;

public class Map {
	protected int SIZE_X;
	protected int SIZE_Y;
	protected Point[] walls = {};
	protected Point[] spawnFantomes = {};
	protected Point[] exeptionsVides = {};
	
	public Map(int SIZE_X,int SIZE_Y) {
		this.SIZE_X = SIZE_X;
		this.SIZE_Y = SIZE_Y;
	}

	public int getSIZE_X() {
		return SIZE_X;
	}

	public int getSIZE_Y() {
		return SIZE_Y;
	}
	
	public Point[] getWalls() {
		return this.walls;
	}

	public Point[] getSpawnFantomes() {
		return spawnFantomes;
	}

	public Point[] pacgums() {
		return spawnFantomes;
	}

	public Point[] getExeptionsVides() {
		return exeptionsVides;
	}
}
