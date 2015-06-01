package cat.copernic.projectefinal;

/**
 * Created by Pol on 22/05/2015.
 */
public class Img {
    private String s;
    private int i;


     public Img(String s, int i){
         this.s=s;
         this.i=i;
     }

    public int getI() {
        return i;
    }

    public String getS() {
        return s;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setS(String s) {
        this.s = s;
    }
}
