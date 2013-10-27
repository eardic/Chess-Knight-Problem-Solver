/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.board;

import aima.core.agent.Action;
import gyte.ai.eardic.chessknight.knight.Knight;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Emre
 */
public class ChessBoard extends JPanel
{

    private static final long serialVersionUID = 1L;
    private int row, column; // row x column square board
    private Square[][] squares;
    private Knight knight;

    /**
     * Initializes board using given row and column size
     *
     * @param row
     * @param column
     * @param dim
     * @param barPer
     */
    public ChessBoard(int row, int column, Dimension dim, int barPer)
    {
        super();
        initBoard(row, column, dim, barPer);
        // Init Knight
        knight = new Knight(new Point(row - 1, column - 1), new Point(0, 0));
        squares[0][0].add(knight);        
    }

    public ChessBoard(ChessBoard board)
    {
        copyBoard(board);        
    }

    private void copyBoard(ChessBoard board)
    {
        // Shallow copy of board
        this.row = board.getRow();
        this.column = board.getColumn();

        setLayout(new GridLayout(row, column));
        setPreferredSize(board.getSize());
        setSize(board.getSize());
        setBounds(0, 0, board.getSize().width, board.getSize().height);
        this.squares = board.getSquares();

        // Deep copy - New Knight
        knight = new Knight(board.getKnight());
    }

    private void initBoard(int row, int column, Dimension size, int per)
    {
        this.row = row;
        this.column = column;
        
        size = new Dimension(column*50,row*50);
        
        setLayout(new GridLayout(row, column));
        setPreferredSize(size);
        setSize(size);
        setBounds(0, 0, size.width, size.height);

        squares = new Square[row][column];
        for (int i = 0; i < row; ++i)
        {
            for (int j = 0; j < column; ++j)
            {
                squares[i][j] = new Square(new Point(i, j),
                        ((i + j) % 2 == 0) ? Color.GRAY : Color.WHITE);
                add(squares[i][j]);
            }
        }

        addBarrierRandomly(per);
    }

    public void resetBoard()
    {
        for (int i = 0; i < row; ++i)
        {
            for (int j = 0; j < column; ++j)
            {
                if (!squares[i][j].isBlock())
                {
                    squares[i][j].setBackground(squares[i][j].getColor());
                    if (!knight.getInitPosition().equals(new Point(i, j)))
                    {
                        squares[i][j].removeAll();
                    }
                }
            }
        }
        this.repaint();
    }

    public void zoomOut()
    {
        Dimension size = getSize();
        size.width = (int) (size.width * 1.1);
        size.height = (int) (size.height * 1.1);
        setSize(size);
    }

    public void zoomIn()
    {
        Dimension size = getSize();
        size.width = (int) (size.width * 0.9);
        size.height = (int) (size.height * 0.9);
        setSize(size);
    }

    public Square[][] getSquares()
    {
        return squares;
    }

    public Knight getKnight()
    {
        return knight;
    }

    public void setKnight(Knight knight)
    {
        this.knight = knight;
    }

    public Square getSquare(Point p)
    {
        return squares[p.x][p.y];
    }

    public Square getSquare(int x, int y)
    {
        return squares[x][y];
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public void addBarrier(Point p)
    {
        squares[p.x][p.y].setBlock(true);
    }

    public boolean isBarrier(Point p)
    {
        return squares[p.x][p.y].isBlock();
    }

    public boolean inBoard(Point p)
    {
        return (p.x < row && p.x >= 0) && (p.y < column && p.y >= 0);
    }

    /**
     * Add barrier to board randomly
     *
     * @param percent is between 0-100
     */
    public void addBarrierRandomly(int percent)
    {
        Random rand = new Random();
        int barrierCount = row * column * percent / 100;
        Point p = new Point();
        for (int i = 0; i < barrierCount; ++i)
        {
            p.x = rand.nextInt(row);
            p.y = rand.nextInt(column);
            if (!isBarrier(p))
            {
                addBarrier(p);
            }
        }
    }

    public boolean canMoveKnight(Action a)
    {
        Point[] path = knight.getPath(a);
        for (Point p : path)
        {
            if (!inBoard(p) || isBarrier(p))
            {
                return false;
            }
        }
        return true;
    }

    public void moveKnight(Action a)
    {
        /*//Remove knight from old square
         int x = knight.getPosition().x;
         int y = knight.getPosition().y;
         squares[x][y].removeAll();*/

        // Go to new square
        Point newPos = knight.goNewPosition(a);
        // Paint expanded squares
        squares[newPos.x][newPos.y].setBackground(Color.red);
    }

    public void showSolution(List<Action> actions)
    {
        Knight k = new Knight(knight);
        k.goInitialPosition();

        for (Action a : actions)
        {
            Point[] path = k.getPath(a);
            for (Point p : path)
            {
                if (p.equals(path[path.length - 1]))
                {
                    squares[p.x][p.y].setBackground(Color.BLUE);
                }
                else
                {
                    squares[p.x][p.y].setBackground(Color.GREEN);
                }
            }
            k.goNewPosition(a);
        }
        squares[row-1][column-1].add(knight);
    }

}
