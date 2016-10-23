package myapp.abrahamjohngomez.com.onerepmax;

import android.widget.Spinner;

/**
 * Created by ryuhyoko on 6/27/2016.
 */
public class MaxObject {


    private int id;
    private double max;
    private String exerciseName;
    private Algorithms algo;

    public MaxObject(){

    }

    public MaxObject(String algorithmName, String exerciseName, int reps, double weight) {
        algo = new Algorithms();
        this.exerciseName = exerciseName;
        this.max = algo.OneRepMax(algorithmName, reps, weight);
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}
