/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.ai;

import aima.core.search.framework.HeuristicFunction;
import gyte.ai.eardic.chessknight.board.ChessBoard;
import java.awt.Point;

/**
 * The king can move every direction using one square
 *
 * @author Emre
 */
public class CKPLimitedQueenHeuristic implements HeuristicFunction
{

    @Override
    public double h(Object o)
    {
        ChessBoard b = (ChessBoard) o;
        Point pos = b.getKnight().getPosition();
        Point goalPos = b.getKnight().getGoalPosition();

        int move = 0;
        // find the square count for cross move
        int cross = Math.min(Math.abs(pos.x - goalPos.x), Math.abs(pos.y - goalPos.y));
        // Move count for crossing
        move += (int) Math.ceil(cross / 3.0);
        // go to final square after cross move
        Point cp = new Point(pos.x + cross, pos.y + cross);
        // Find move count for straight movement from cp
        move += Math.ceil(Math.abs(cp.x - goalPos.x) / 3.0);
        move += Math.ceil(Math.abs(cp.y - goalPos.y) / 3.0);
        return move;
    }

}
