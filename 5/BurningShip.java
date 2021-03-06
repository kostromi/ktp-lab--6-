import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange (Rectangle2D.Double range)
    {
        range.x=-2;
        range.y=-2.5;
        range.width=range.height=4;
    }

    public int numIterations(double x, double y)
    {
        double re=0;
        double im=0;
        for (int i=0; i<MAX_ITERATIONS; i++)
        {
            double _re=re*re-im*im+x;
            double _im=Math.abs(2*re*im)+y;

            if ((re*re+im*im) > 4)
                return i;

            re=_re;
            im=_im;
        }
        return -1;
    }
    public String toString ()
    {
        return "Burning Ship";
    }
}