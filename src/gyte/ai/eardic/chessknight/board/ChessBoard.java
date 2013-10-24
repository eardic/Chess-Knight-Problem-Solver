/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Emre
 */
public class ChessBoard extends JPanel
{

    private static final long serialVersionUID = 1L;
    private static final int MIN_BOARD_SIZE = 3;
    private static final Color BARRIER_COLOR = Color.RED;

    private int row, column; // row x column square board
    private Square[][] squares;

    /**
     * Initializes board using given row and column size Board size has to be
     * larger than 3x3 Otherwise size is set to 3x3
     *
     * @param row
     * @param column
     * @param dim
     */
    public ChessBoard(int row, int column, Dimension dim)
    {
        super();
        if (row >= MIN_BOARD_SIZE && column >= MIN_BOARD_SIZE)
        {
            initBoard(row, column, dim);
        }
        else
        {
            initBoard(MIN_BOARD_SIZE, MIN_BOARD_SIZE, dim);
        }
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
                        ((i + j) % 2 == 0) ? Color.DARK_GRAY : Color.WHITE);
                add(squares[i][j]);
            }
        }
    }

    public Square getSquare(Point p)
    {
        return squares[p.x][p.y];
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
        return squares[p.x][p.y].getBackground() == BARRIER_COLOR;
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

}
