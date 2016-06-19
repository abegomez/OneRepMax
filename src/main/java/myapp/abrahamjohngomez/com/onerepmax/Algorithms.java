package myapp.abrahamjohngomez.com.onerepmax;
import java.lang.Math;
/**
 * Created by ryuhyoko on 6/19/2016.
 */
public class Algorithms {
    static double EULERCONSTANT = Math.exp(1.0);

    public double Epley(int reps, double w){

        double num = w * (1+.0333*reps);

        return num;
    }
    public double Brzycki(int reps, double w){
        double num;
        num = 37-reps;
        num = (36/num);
        num = num * w;

        return num;
    }
    public double Lander(int reps, double w){
        double num;
        num = (100*w)/(101.3 -(2.67123*reps));

        return num;
    }
    public double Lombardi(int reps, double w){
        double num;
        num = w * (Math.pow(reps,.10));

        return num;
    }
    public double Mayhew(int reps, double w){
        double num;
        num = (100*w) / (52.2 +(41.9* Math.pow(EULERCONSTANT,(-.055*reps))));

        return num;
    }
    public double OConnor(int reps, double w){
        double num;
        num = w*(1+(0.025*reps));

        return num;
    }
    public double Wathen(int reps, double w){
        double num;
        num = (w*100)/ (48.8 + (53.8*Math.pow(EULERCONSTANT,-0.075*reps)));

        return num;
    }
    public double Wendler(int reps, double w){
        double num;
        num = (w * reps * .0333) + w;

        return num;
    }

}
