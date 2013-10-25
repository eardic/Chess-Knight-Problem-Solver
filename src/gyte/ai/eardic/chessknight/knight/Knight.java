/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.knight;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Emre
 */
public class Knight extends JLabel
{
    // Actions of Knight
    public static Action LeftUp = new DynamicAction("LeftUp");
    public static Action LeftDown = new DynamicAction("LeftDown");
    
    public static Action RightUp = new DynamicAction("RightUp");
    public static Action RightDown = new DynamicAction("RightDown");
    
    public static Action UpLeft = new DynamicAction("UpLeft");
    public static Action UpRight = new DynamicAction("UpRight");
    
    public static Action DownLeft = new DynamicAction("DownLeft");
    public static Action DownRight = new DynamicAction("DownRight");
    
    private static final long serialVersionUID = 1L;
    private Point position;
    private final Point goalPosition;

    public Knight(Point goal)
    {
        ImageIcon img = new ImageIcon(getClass().getResource("../knight/resource/knight.png"));        
        this.setIcon(img);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.position = new Point(0, 0);
        this.goalPosition = goal;
    }

    public Point getGoalPosition()
    {
        return goalPosition;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }
    
    public boolean isReachedGoal()
    {
        return position.equals(goalPosition);
    }
    
    public Point getNewPosition(Action a)
    {
        Point[] path = getPath(a);
        return path[2];
    }
    
    public Point goNewPosition(Action a)
    {
        position = getNewPosition(a);
        return position;
    }
    
    public Point[] getPath(Action a)
    {
        Point[] path = new Point[3];
        
        // LEFT MOVES ###############
        if(a.equals(Knight.LeftUp))
        {
            path[0]=new Point(position.x,position.y-1);
            path[1]=new Point(position.x,position.y-2);
            path[2]=new Point(position.x-1,position.y-2);
        }
        else if(a.equals(Knight.LeftDown))
        {
            path[0]=new Point(position.x,position.y-1);
            path[1]=new Point(position.x,position.y-2);
            path[2]=new Point(position.x+1,position.y-2);
        }
        // RIGHT MOVES ###############
        else if(a.equals(Knight.RightUp))
        {
            path[0]=new Point(position.x,position.y+1);
            path[1]=new Point(position.x,position.y+2);
            path[2]=new Point(position.x-1,position.y+2);
        }
        else if(a.equals(Knight.RightDown))
        {
            path[0]=new Point(position.x,position.y+1);
            path[1]=new Point(position.x,position.y+2);
            path[2]=new Point(position.x+1,position.y+2);
        }
        // UP MOVES ###############
        else if(a.equals(Knight.UpRight))
        {
            path[0]=new Point(position.x-1,position.y);
            path[1]=new Point(position.x-2,position.y);
            path[2]=new Point(position.x-2,position.y+1);
        }
        else if(a.equals(Knight.UpLeft))
        {
            path[0]=new Point(position.x-1,position.y);
            path[1]=new Point(position.x-2,position.y);
            path[2]=new Point(position.x-2,position.y-1);
        }
        // DOWN MOVES ###############
        else if(a.equals(Knight.DownRight))
        {
            path[0]=new Point(position.x+1,position.y);
            path[1]=new Point(position.x+2,position.y);
            path[2]=new Point(position.x+2,position.y+1);
        }
        else if(a.equals(Knight.DownLeft))
        {
            path[0]=new Point(position.x+1,position.y);
            path[1]=new Point(position.x+2,position.y);
            path[2]=new Point(position.x+2,position.y-1);
        }
        
        return path;
    }
    
    @Override
    public String toString()
    {
        return "Knight-> Curr Pos :"+position+ "\n Goal : "+goalPosition;
    }
    
}
