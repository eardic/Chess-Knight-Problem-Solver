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
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Emre
 */
public class ChessBoard extends JPanel
{

    private static final long serialVersionUID = 1L;
    private static final Color BARRIER_COLOR = Color.orange;

    private int row, column; // row x column square board
    private Square[][] squares;
    private Knight knight;

    /**
     * Initializes board using given row and column size
     *
     * @param row
     * @param column
     * @param dim
     */
    public ChessBoard(int row, int column, Dimension dim)
    {
        super();
        initBoard(row, column, dim);
        // Init Knight
        knight = new Knight(new Point(row - 1, column - 1));
        squares[0][0].add(knight);
    }

    public ChessBoard(ChessBoard board)
    {
        copyBoard(board);
    }
    
    private void copyBoard(ChessBoard board)
    {
        this.row = board.getRow();
        this.column = board.getColumn();

        setLayout(new GridLayout(row, column));
        setPreferredSize(board.getSize());
        setSize(board.getSize());
        setBounds(0, 0, board.getSize().width, board.getSize().height);

        squares = new Square[row][column];
        for (int i = 0; i < row; ++i)
        {
            for (int j = 0; j < column; ++j)
            {
                squares[i][j] = new Square(new Point(i, j), board.getSquare(i, j).getColor());
                add(squares[i][j]);
            }
        }
        knight = new Knight(board.getKnight().getGoalPosition());
        knight.setPosition(board.getKnight().getPosition());        
        squares[knight.getPosition().x][knight.getPosition().y].add(knight);
        
    }

    private void initBoard(int row, int column, Dimension size)
    {
        this.row = row;
        this.column = column;
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
        squares[p.x][p.y].setBackground(BARRIER_COLOR);
    }

    public boolean isBarrier(Point p)
    {
        return squares[p.x][p.y].getBackground().equals(BARRIER_COLOR);
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
        //Remove knight from old square
        int x = knight.getPosition().x;
        int y = knight.getPosition().y;
        squares[x][y].remove(knight);

        // Go to new square
        Point newPos = knight.goNewPosition(a);
        squares[newPos.x][newPos.y].add(knight);

        // Update squares
        this.repaint();
    }

}
