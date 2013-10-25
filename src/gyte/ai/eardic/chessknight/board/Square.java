/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.board;

import gyte.ai.eardic.chessknight.knight.Knight;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Describes A Square in Chess Board
 *
 * @author Emre
 */
@SuppressWarnings("FieldMayBeFinal")
public class Square extends JPanel
{

    private static final long serialVersionUID = 1L;
    private Point loc; // for row,column info
    private Color color; // the bg image of square

    public Square(Point location, Color col)
    {
        super(new BorderLayout());
        this.loc = location;
        this.color = col;
        setBackground(col);
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
