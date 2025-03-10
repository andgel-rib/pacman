package modele;

import java.awt.Point;

public class ClassicMap extends Map {
	
	public ClassicMap() {
		super(28,31);
		this.walls = new Point[]{
				new Point(2,2),new Point(3,2), //haut gauche zone A
				new Point(4,2),new Point(5,2),
				new Point(2,3),new Point(3,3),
				new Point(4,3),new Point(5,3),
				new Point(2,4),new Point(3,4),
				new Point(4,4),new Point(5,4),
				new Point(2,6),new Point(3,6),  // zone E
				new Point(4,6),new Point(5,6),
				new Point(2,7),new Point(3,7),
				new Point(4,7),new Point(5,7),
				new Point(7,2),new Point(8,2),  // zone B
				new Point(9,2),new Point(10,2),
				new Point(11,2),new Point(7,3),
				new Point(8,3),new Point(9,3),
				new Point(10,3),new Point(11,3),
				new Point(7,4),new Point(8,4),
				new Point(9,4),new Point(10,4),
				new Point(11,4),
				new Point(7,6), 				// zone F
				new Point(8,6),new Point(7,7),
				new Point(8,7),new Point(7,8),
				new Point(8,8),new Point(7,9),
				new Point(8,9),new Point(7,10),
				new Point(8,10),new Point(7,11),
				new Point(8,11),new Point(7,12),
				new Point(8,12),new Point(7,13),
				new Point(8,13),new Point(9,9),
				new Point(10,9),new Point(11,9),
				new Point(9,10),new Point(10,10),
				new Point(11,10),
				new Point(22,2),                //haut droit zone D
				new Point(23,2),new Point(24,2),
				new Point(25,2),new Point(22,3),
				new Point(23,3),new Point(24,3),
				new Point(25,3),new Point(22,4),
				new Point(23,4),new Point(24,4),
				new Point(25,4),
				new Point(22,6),				// zone I
				new Point(23,6),new Point(24,6),
				new Point(25,6),new Point(22,7),
				new Point(23,7),new Point(24,7),
				new Point(25,7),
				new Point(16,2),				// zone C
				new Point(17,2),new Point(18,2), 
				new Point(19,2),new Point(20,2),
				new Point(16,3),new Point(17,3),
				new Point(18,3),new Point(19,3),
				new Point(20,3),new Point(16,4),
				new Point(17,4),new Point(18,4),
				new Point(19,4),new Point(20,4),
				new Point(19,6),new Point(20,6), // zone H
				new Point(19,7),new Point(20,7),
				new Point(19,8),new Point(20,8),
				new Point(19,9),new Point(20,9),
				new Point(19,10),new Point(20,10),
				new Point(19,11),new Point(20,11),
				new Point(19,12),new Point(20,12),
				new Point(19,13),new Point(20,13),
				new Point(16,9),new Point(17,9),
				new Point(18,9),new Point(16,10),
				new Point(17,10),new Point(18,10),
				new Point(1,24),new Point(2,24), // bas gauche mur ext
				new Point(1,25),new Point(2,25),
				new Point(2,21),new Point(3,21), // zone N
				new Point(4,21),new Point(5,21),
				new Point(2,22),new Point(3,22),
				new Point(4,22),new Point(5,22),
				new Point(4,23),new Point(5,23),
				new Point(4,24),new Point(5,24),
				new Point(4,25),new Point(5,25),
				new Point(2,27),new Point(3,27), // zone R
				new Point(4,27),new Point(5,27),
				new Point(6,27),new Point(7,27),
				new Point(8,27),new Point(9,27),
				new Point(10,27),new Point(11,27),
				new Point(2,28),new Point(3,28),
				new Point(4,28),new Point(5,28),
				new Point(6,28),new Point(7,28),
				new Point(8,28),new Point(9,28),
				new Point(10,28),new Point(11,28),
				new Point(7,26),new Point(8,26),
				new Point(7,25),new Point(8,25),
				new Point(7,24),new Point(8,24),
				new Point(7,21),new Point(8,21),  // zone O
				new Point(9,21),new Point(10,21),
				new Point(11,21),new Point(7,22),
				new Point(8,22),new Point(9,22),
				new Point(10,22),new Point(11,22),
				new Point(7,15),new Point(8,15),  // zone J
				new Point(7,16),new Point(8,16),
				new Point(7,17),new Point(8,17),
				new Point(7,18),new Point(8,18),
				new Point(7,19),new Point(8,19),
				new Point(25,24),new Point(26,24), // bas droit mur ext
				new Point(25,25),new Point(26,25),
				new Point(22,21),new Point(23,21), // zone Q
				new Point(24,21),new Point(25,21),
				new Point(22,22),new Point(23,22),
				new Point(24,22),new Point(25,22),
				new Point(22,23),new Point(23,23),
				new Point(22,24),new Point(23,24),
				new Point(22,25),new Point(23,25),
				new Point(16,27),new Point(17,27), // zone T
				new Point(18,27),new Point(19,27),
				new Point(20,27),new Point(21,27),
				new Point(22,27),new Point(23,27),
				new Point(24,27),new Point(25,27),
				new Point(16,28),new Point(17,28),
				new Point(18,28),new Point(19,28),
				new Point(20,28),new Point(21,28),
				new Point(22,28),new Point(23,28),
				new Point(24,28),new Point(25,28),
				new Point(19,26),new Point(20,26),
				new Point(19,25),new Point(20,25),
				new Point(19,24),new Point(20,24),
				new Point(16,21),new Point(17,21), // zone P
				new Point(18,21),new Point(19,21),
				new Point(20,21),new Point(16,22),
				new Point(17,22),new Point(18,22),
				new Point(19,22),new Point(20,22),
				new Point(19,15),new Point(20,15), // zone L
				new Point(19,16),new Point(20,16),
				new Point(19,17),new Point(20,17),
				new Point(19,18),new Point(20,18),
				new Point(19,19),new Point(20,19),
				new Point(10,6),new Point(11,6), //Milieu zone G
				new Point(12,6),new Point(13,6),
				new Point(14,6),new Point(15,6),
				new Point(16,6),new Point(17,6),
				new Point(10,7),new Point(11,7),
				new Point(12,7),new Point(13,7),
				new Point(14,7),new Point(15,7),
				new Point(16,7),new Point(17,7),
				new Point(13,8),new Point(14,8),
				new Point(13,9),new Point(14,9),
				new Point(13,10),new Point(14,10),
				new Point(10,18),new Point(11,18), // zone M
				new Point(12,18),new Point(13,18),
				new Point(14,18),new Point(15,18),
				new Point(16,18),new Point(17,18),
				new Point(10,19),new Point(11,19),
				new Point(12,19),new Point(13,19),
				new Point(14,19),new Point(15,19),
				new Point(16,19),new Point(17,19),
				new Point(13,20),new Point(14,20),
				new Point(13,21),new Point(14,21),
				new Point(13,22),new Point(14,22),
				new Point(10,24),new Point(11,24), // zone S
				new Point(12,24),new Point(13,24),
				new Point(14,24),new Point(15,24),
				new Point(16,24),new Point(17,24),
				new Point(10,25),new Point(11,25),
				new Point(12,25),new Point(13,25),
				new Point(14,25),new Point(15,25),
				new Point(16,25),new Point(17,25),
				new Point(13,26),new Point(14,26),
				new Point(13,27),new Point(14,27),
				new Point(13,28),new Point(14,28),
				new Point(10,12),new Point(11,12),  // zone K
				new Point(12,12),new Point(15,12),
				new Point(16,12),new Point(17,12),
				new Point(10,16),new Point(11,16),
				new Point(12,16),new Point(13,16),
				new Point(14,16),new Point(15,16),
				new Point(16,16),new Point(17,16),
				new Point(10,13),new Point(10,14),
				new Point(10,15),new Point(10,16),
				new Point(17,13),new Point(17,14),
				new Point(17,15),new Point(17,16),
				new Point(1,9),new Point(2,9), // mur ext
				new Point(3,9),new Point(4,9),
				new Point(5,9),new Point(1,10),
				new Point(2,10),new Point(3,10),
				new Point(4,10),new Point(5,10),
				new Point(1,11),new Point(2,11),
				new Point(3,11),new Point(4,11),
				new Point(5,11),new Point(1,12),
				new Point(2,12),new Point(3,12),
				new Point(4,12),new Point(5,12),
				new Point(1,13),new Point(2,13),
				new Point(3,13),new Point(4,13),
				new Point(5,13),
				new Point(1,15), // mur ext 
				new Point(2,15),new Point(3,15),
				new Point(4,15),new Point(5,15),
				new Point(1,16),new Point(2,16),
				new Point(3,16),new Point(4,16),
				new Point(5,16),new Point(1,17),
				new Point(2,17),new Point(3,17),
				new Point(4,17),new Point(5,17),
				new Point(1,18),new Point(2,18),
				new Point(3,18),new Point(4,18),
				new Point(5,18),new Point(1,19),
				new Point(2,19),new Point(3,19),
				new Point(4,19),new Point(5,19),
				new Point(22,9),new Point(23,9), // mur ext 
				new Point(24,9),new Point(25,9),
				new Point(26,9),new Point(22,10),
				new Point(23,10),new Point(24,10),
				new Point(25,10),new Point(26,10),
				new Point(22,11),new Point(23,11),
				new Point(24,11),new Point(25,11),
				new Point(26,11),new Point(22,12),
				new Point(23,12),new Point(24,12),
				new Point(25,12),new Point(26,12),
				new Point(22,13),new Point(23,13),
				new Point(24,13),new Point(25,13),
				new Point(26,13),
				new Point(22,15), // mur ext 
				new Point(23,15),new Point(24,15),
				new Point(25,15),new Point(26,15),
				new Point(22,16),new Point(23,16),
				new Point(24,16),new Point(25,16),
				new Point(26,16),new Point(22,17),
				new Point(23,17),new Point(24,17),
				new Point(25,17),new Point(26,17),
				new Point(22,18),new Point(23,18),
				new Point(24,18),new Point(25,18),
				new Point(26,18),new Point(22,19),
				new Point(23,19),new Point(24,19),
				new Point(25,19),new Point(26,19),
				new Point(13,1),new Point(14,1), // mur ext 
				new Point(13,2),new Point(14,2),
				new Point(13,3),new Point(14,3),
				new Point(13,4),new Point(14,4),
				new Point(0,0),new Point(1,0),  // contour
				new Point(2,0),new Point(3,0),
				new Point(4,0),new Point(5,0),
				new Point(6,0),new Point(7,0),
				new Point(8,0),new Point(9,0),
				new Point(10,0),new Point(11,0),
				new Point(12,0),new Point(13,0),
				new Point(14,0),new Point(15,0),
				new Point(16,0),new Point(17,0),
				new Point(18,0),new Point(19,0),
				new Point(20,0),new Point(21,0),
				new Point(22,0),new Point(23,0),
				new Point(24,0),new Point(25,0),
				new Point(26,0),new Point(27,0),
				new Point(0,1),new Point(0,2),
				new Point(0,3),new Point(0,4),
				new Point(0,5),new Point(0,6),
				new Point(0,7),new Point(0,8),
				new Point(0,9),new Point(0,10),
				new Point(0,11),new Point(0,12),
				new Point(0,13),
				//new Point(0,14), //mur tp
				new Point(0,15),new Point(0,16),
				new Point(0,17),new Point(0,18),
				new Point(0,19),new Point(0,20),
				new Point(0,21),new Point(0,22),
				new Point(0,23),new Point(0,24),
				new Point(0,25),new Point(0,26),
				new Point(0,27),new Point(0,28),
				new Point(0,29),new Point(0,30),
				new Point(27,1),new Point(27,2),
				new Point(27,3),new Point(27,4),
				new Point(27,5),new Point(27,6),
				new Point(27,7),new Point(27,8),
				new Point(27,9),new Point(27,10),
				new Point(27,11),new Point(27,12),
				new Point(27,13),
				//new Point(27,14), mur tp
				new Point(27,15),new Point(27,16),
				new Point(27,17),new Point(27,18),
				new Point(27,19),new Point(27,20),
				new Point(27,21),new Point(27,22),
				new Point(27,23),new Point(27,24),
				new Point(27,25),new Point(27,26),
				new Point(27,27),new Point(27,28),
				new Point(27,29),new Point(27,30),
				new Point(1,30),new Point(2,30),
				new Point(3,30),new Point(4,30),
				new Point(5,30),new Point(6,30),
				new Point(7,30),new Point(8,30),
				new Point(9,30),new Point(10,30),
				new Point(11,30),new Point(12,30),
				new Point(13,30),new Point(14,30),
				new Point(15,30),new Point(16,30),
				new Point(17,30),new Point(18,30),
				new Point(19,30),new Point(20,30),
				new Point(21,30),new Point(22,30),
				new Point(23,30),new Point(24,30),
				new Point(25,30),new Point(26,30),
		};
		this.spawnFantomes = new Point[]{
				new Point(11, 13),
				new Point(14, 13)
		};
		this.tpTrigger.put(new Point(0,14),new Point(27,14));
		this.tpTrigger.put(new Point(27,14),new Point(0,14));

		this.exeptionsVides = new Point[]{
				new Point(0,14), //triggers
				new Point(27,14),

				new Point(11, 13),
				new Point(12, 13),
				new Point(13, 13),
				new Point(14, 13),
				new Point(15, 13),
				new Point(16, 13),
				new Point(11, 14),
				new Point(12, 14),
				new Point(13, 14),
				new Point(14, 14),
				new Point(15, 14),
				new Point(16, 14),
				new Point(11, 15),
				new Point(12, 15),
				new Point(13, 15),
				new Point(14, 15),
				new Point(15, 15),
				new Point(16, 15),
				new Point(13, 12),
				new Point(14, 12)
		};
	}
}
