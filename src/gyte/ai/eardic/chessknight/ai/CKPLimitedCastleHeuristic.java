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
 * The Castle-2 can move max 2 square to 4 direction
 * @author Emre
 */
public class CKPLimitedCastleHeuristic implements HeuristicFunction
{

    @Override
    public double h(Object o)
    {
        ChessBoard b = (ChessBoard) o;
        Point pos = b.getKnight().getPosition();
        Point goalPos = b.getKnight().getGoalPosition();

        int move = 0;
        move += Math.ceil(Math.abs(pos.x - goalPos.x)/3.0);       
        move += Math.ceil(Math.abs(pos.y - goalPos.y)/3.0);
        
        return move;
    }

}
