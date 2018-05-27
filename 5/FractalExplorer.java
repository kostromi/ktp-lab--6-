import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.filechooser.*;
import java.io.*;

public class FractalExplorer
{
    private JImageDisplay display;
    private int displaySize;
    private FractalGenerator FracGen;
    private Rectangle2D.Double range = new Rectangle2D.Double(0,0,0,0);

    JComboBox ComboBox = new JComboBox();
    JFrame Frame = new JFrame("Fractals");

    public static void main(String[] args)
    {
        FractalExplorer Fractal = new FractalExplorer(800);
        Fractal.createAndShowGUI();
        Fractal.drawFractal();
    }

    public FractalExplorer(int size)
    {
        displaySize = size;
        display = new JImageDisplay(displaySize, displaySize);
        FracGen = new Mandelbrot();
        FracGen.getInitialRange(range);
    }

    public void createAndShowGUI()
    {

        display.addMouseListener(new MouseListener());
        Frame.add(display, BorderLayout.CENTER);

        JPanel Panel = new JPanel();

        JButton buttonReset = new JButton("Reset Display");
        buttonReset.addActionListener(new ActListener());
        Panel.add(buttonReset, BorderLayout.CENTER);

        JButton buttonSave = new JButton("Save Image");
        buttonSave.addActionListener(new ActListener());
        Panel.add(buttonSave, BorderLayout.CENTER);

        Frame.add(Panel, BorderLayout.SOUTH);

        JLabel comboboxLabel = new JLabel("Fractals:");
        JPanel comboboxPanel = new JPanel();

        ComboBox.addItem(new Mandelbrot());
        ComboBox.addItem(new Tricorn());
        ComboBox.addItem(new BurningShip());

        ComboBox.addActionListener(new ActListener());

        comboboxPanel.add(comboboxLabel, BorderLayout.CENTER);
        comboboxPanel.add(ComboBox,BorderLayout.CENTER);

        Frame.add(comboboxPanel,BorderLayout.NORTH);

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Frame.pack();
        Frame.setVisible(true);
        Frame.setResizable(false);
    }

    public void drawFractal()
    {
        for(int i=0;i<displaySize;i++)
            for (int j=0;j<displaySize;j++)
            {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, i);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, j);
                int numIters = FracGen.numIterations(xCoord, yCoord);
                if(numIters!=-1)
                {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(i, j, rgbColor);
                }
                else display.drawPixel(i, j, 0);
            }
        display.repaint();
    }

    private class ActListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand()== "Reset Display")
            {
                FracGen.getInitialRange(range);
                display.clearImage();
                drawFractal();
            }
            if(e.getActionCommand()== "Save Image")
            {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if(chooser.showSaveDialog(Frame) == JFileChooser.APPROVE_OPTION)
                {
                    try
                    {
                        File Picture = chooser.getSelectedFile();
                        String PicturePath = Picture.getPath();
                        if (!PicturePath.toLowerCase().endsWith(".png"))
                        {
                            Picture = new File(PicturePath + ".png");
                        }
                        ImageIO.write(display.getImage(), "png", Picture);
                    }
                    catch(IOException ex)
                    {
                        JOptionPane.showMessageDialog (Frame, ex.getMessage(), "Unable to save image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            if (e.getSource()== ComboBox)
            {
                FracGen = (FractalGenerator)ComboBox.getSelectedItem();
                FracGen.getInitialRange(range);
                display.clearImage();
                drawFractal();
            }
        }

    }

    private class MouseListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            super.mouseClicked(e);
            int mouseX=e.getX();
            int mouseY=e.getY();

            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, mouseX);
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, mouseY);

            FracGen.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            display.clearImage();
            drawFractal();
        }
    }

}