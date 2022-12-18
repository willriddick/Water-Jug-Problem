public class JugState {
    private JugState pred = null; 
    private int jug1, jug2, index = -1;

    public JugState() {
        jug1 = -1;
        jug2 = -1;
        index = -1;
        pred = null;
    }

    public JugState(int jug1, int jug2, int index, JugState pred) {
        this.jug1 = jug1;
        this.jug2 = jug2;
        this.index = index;
        this.pred = pred;
    }
   
    public JugState getPred() {
        return pred;
    } 
    
    public int getJug1() {
        return jug1;
    }
    
    public int getJug2() {
        return jug2;
    }

    public int getIndex() {
        return index;
    }

    public void setPred(JugState pred) {
        this.pred = pred;
    }
    
    public void setJug1(int jug1) {
        this.jug1 = jug2;
    }
    
    public void setJug2(int jug2) {
        this.jug2 = jug2;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
}
