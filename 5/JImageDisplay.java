import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;

public class JImageDisplay extends JComponent
{
    private BufferedImage _image;

    JImageDisplay(int width, int height)
    {
        _image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension Size = new Dimension(width, height);
        super.setPreferredSize(Size);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(_image, 0, 0, _image.getWidth(), _image.getHeight(), null);
    }

    public void clearImage()
    {
        for (int i = 0; i < _image.getHeight(); i++)
        {
            for (int j = 0; j < _image.getWidth(); j++)
            {
                drawPixel(i, j, 0);
            }
        }
    }

    public BufferedImage getImage()
    {
        return _image;
    }

    public void drawPixel(int x, int y, int rgbColor)
    {
        _image.setRGB(x, y, rgbColor);
    }
}