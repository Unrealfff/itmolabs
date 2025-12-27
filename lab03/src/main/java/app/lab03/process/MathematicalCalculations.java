package app.lab03.process;

public class MathematicalCalculations {
    public boolean ifHits(float x, float y, Float r){
        if (x >= 0 && y <= 0){
            return x*x + y*y <= (r*r) / 4;
        }
        else if (x <= 0 && y <= 0) {
            return y >= -x/2 - r / 2;
        }
        else if (x <= 0 && y >= 0){
            return x >= -r && y <= r / 2;
        }
        return false;
    }
}
