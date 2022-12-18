import java.util.Scanner;

public class JugSolver {
	public static int jug1_cap, jug2_cap, goal = 0;
	public static boolean[][] visited;
	
	// OUR MAIN METHOD TO INIATE OUR SOLUTION  WITH PROVIDED INPUTS
	public static void main(String[] args) {
		// Prompt user
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Jug1's capacity: ");
		jug1_cap = scan.nextInt();
		System.out.print("Enter Jug2's capacity: ");
		jug2_cap = scan.nextInt();
		System.out.print("Enter goal: ");
		goal = scan.nextInt();
	
		// Initialize arrays
		visited = new boolean[jug1_cap+1][jug2_cap+1];
		
		// Call depth first search	
		JugState ret = dfs(new JugState(0, 0, -1, null));	
		if (ret.getJug1() != -1 && ret.getJug2() != -1) {
			System.out.println("Solution:");
			print_solution(ret);
		}
		else System.out.println("No solution.");
	}

	// RECURSIVELY SOLVES OUR JUG PROBLEM
	public static JugState dfs(JugState state) {
		// Mark state as visited
		visited[state.getJug1()][state.getJug2()] = true;
	
		// Check if we have a goal state
		JugState ret = state;
		if (state.getJug1() + state.getJug2() == goal) {
			return ret; 
		} else ret = new JugState();

		JugState _new = null;
		for (int i = 0; i < 6; i++) {
			if (i == 0) _new = fill(1, state);
			if (i == 1) _new = empty(1, state);
			if (i == 2) _new = pour(1, state);
			if (i == 3) _new = fill(2, state);
			if (i == 4) _new = empty(2, state);
			if (i == 5) _new = pour(2, state);
			
			// Make sure we have not viisted this state previously
			if (visited[_new.getJug1()][_new.getJug2()] == false) {
				JugState recurse = dfs(_new);
				if (recurse.getJug1() != -1 && recurse.getJug2() != -1) {
					ret = recurse;
				}
				_new.setPred(state);
				_new.setIndex(i);
			}
		}		

		return ret;
	}
	
	// PRINT RECURSIVELY THE STEPS OF OUR SOLUTION
	public static void print_solution(JugState state) {
		if (state.getJug1() == -1 && state.getJug2() == -1) return;
		if (state.getPred() == null) return;	

		print_solution(state.getPred());

		if (state.getIndex() == 0) System.out.print("Fill Jug 1 ");
		if (state.getIndex() == 1) System.out.print("Empty Jug 1 ");
		if (state.getIndex() == 2) System.out.print("Pour Jug 1 -> Jug 2 ");
		if (state.getIndex() == 3) System.out.print("Fill Jug 2 ");
		if (state.getIndex() == 4) System.out.print("Empty Jug 2 ");
		if (state.getIndex() == 5) System.out.print("Pour Jug 2 -> Jug 1 ");

		if (!(state.getJug1() == 0 && state.getJug2() == 0)) {
			System.out.println("[a = " + state.getJug1() + ", b = " + state.getJug2() + "]");
		}
	}
	

	// FILL UP CHOICE JUG TO CAPACITY 
	public static JugState fill(int choice, JugState cur) {
		if (choice == 1) return new JugState(jug1_cap, cur.getJug2(), 0, null);
		return new JugState(cur.getJug1(), jug2_cap, 0, null);
	}

	// EMPTY JUG CHOICE
	public static JugState empty(int choice, JugState cur) {
		if (choice == 1) return new JugState(0, cur.getJug2(), 0, null);
		return new JugState(cur.getJug1(), 0, 0, null);
	}

	// FILL CHOICE JUG WITH OTHER JUG 
	public static JugState pour(int choice, JugState cur) {
		int jug1 = cur.getJug1();
		int jug2 = cur.getJug2();
		if (choice == 1) {
			jug1 += jug2;
			if (jug1 > jug1_cap) {
				jug2 = jug1 - jug1_cap;
				jug1 = jug1_cap;
			} else jug2 = 0;
		}
		else if (choice == 2) {
			jug2 += jug1;
			if (jug2 > jug2_cap) {
				jug1 = jug2 - jug2_cap;
				jug2 = jug2_cap;
			} else jug1 = 0;
		}
		return new JugState(jug1, jug2, -1, null);
	}
}