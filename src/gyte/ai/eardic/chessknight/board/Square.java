/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Describes A Square in Chess Board
 *
 * @author Emre
 */
@SuppressWarnings("FieldMayBeFinal")
public class Square extends JPanel
{

    private static final Color BARRIER_COLOR = Color.orange;
    private static final long serialVersionUID = 1L;

    private Point loc; // for row,column info
    private Color color; // the bg color of square
    private boolean block;

    public Square(Point location, Color col)
    {
        super(new BorderLayout());
        this.loc = location;
        this.color = col;
        setBlock(false);
        setBackground(col);
        setBorder(BorderFactory.createEtchedBorder());
    }

    public boolean isBlock()
    {
        return block;
    }

    public void setBlock(boolean block)
    {
        if (block)
        {
            this.setBackground(BARRIER_COLOR);
        }
        else
        {
            this.setBackground(color);
        }
        this.block = block;
    }

    public Point getPosition()
    {
        return loc;
    }

    public Color getColor()
    {
        return color;
    }

}
