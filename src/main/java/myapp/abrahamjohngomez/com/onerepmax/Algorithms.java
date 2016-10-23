package myapp.abrahamjohngomez.com.onerepmax;
import java.lang.Math;
/**
 * Created by ryuhyoko on 6/19/2016.
 */
public class Algorithms {
    static double EULERCONSTANT = Math.exp(1.0);

    private double Epley(int reps, double w){

        double num = w * (1+.0333*reps);

        return num;
    }
    private double Brzycki(int reps, double w){
        double num;
        num = 37-reps;
        num = (36/num);
        num = num * w;

        return num;
    }
    private double Lander(int reps, double w){
        double num;
        num = (100*w)/(101.3 -(2.67123*reps));

        return num;
    }
    private double Lombardi(int reps, double w){
        double num;
        num = w * (Math.pow(reps,.10));

        return num;
    }
    private double Mayhew(int reps, double w){
        double num;
        num = (100*w) / (52.2 +(41.9* Math.pow(EULERCONSTANT,(-.055*reps))));

        return num;
    }
    private double OConnor(int reps, double w){
        double num;
        num = w*(1+(0.025*reps));

        return num;
    }
    private double Wathen(int reps, double w){
        double num;
        num = (w*100)/ (48.8 + (53.8*Math.pow(EULERCONSTANT,-0.075*reps)));

        return num;
    }
    private double Wendler(int reps, double w){
        double num;
        num = (w * reps * .0333) + w;

        return num;
    }
    public double OneRepMax(String alg, int reps, double w) {

        switch(alg) {
            case "Epley":
                return Epley(reps, w);
            case "Brzycki":
                return Brzycki(reps, w);
            case "Lander":
                return Lander(reps, w);
            case "Lombardi":
                return Lombardi(reps, w);
            case "Mayhew":
                return Mayhew(reps, w);
            case "OConnor":
                return OConnor(reps, w);
            case "Wathen":
                return Wathen(reps, w);
            case "Wendler":
                return Wendler(reps, w);
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + alg);
        }

    }
}
