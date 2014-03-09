package ax.hx.hx.pathtracer.pathtracer;

import ax.hx.hx.pathtracer.image.RGBPixel;

import java.util.Random;

/**
 * Created by hx on 3/7/14.
 */
public class Influence
{
    double r;
    double g;
    double b;

    int influencedBy = 1;

    Influence(double r, double g, double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    Influence(){
	Random rnd = new Random();

	r = rnd.nextDouble();
	g = rnd.nextDouble();
	b = rnd.nextDouble();
    }

    RGBPixel getPixel(){
	int ri = (int) (r * 255.0);
	int gi = (int) (g * 255.0);
	int bi = (int) (b * 255.0);
	return new RGBPixel(ri, gi, bi);
    }

    public void addInfluence(Influence i){
	// Calculates the avarage of all hits.
	r = (r*influencedBy + i.getR())/(influencedBy + 1);
	g = (g*influencedBy + i.getG())/(influencedBy + 1);
	b = (b*influencedBy + i.getB())/(influencedBy + 1);
	influencedBy++;
    }

    public double getR() {
	return r;
    }

    public double getG() {
	return g;
    }

    public double getB() {
	return b;
    }
}
