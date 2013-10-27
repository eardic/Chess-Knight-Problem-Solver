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
 * @author Emre
 */
public class CKPKingHeuristic  implements HeuristicFunction
{
    @Override
    public double h(Object o)
    {
        ChessBoard b = (ChessBoard) o;
        Point pos = b.getKnight().getPosition();
        Point goalPos = b.getKnight().getGoalPosition();        
        return Math.max(Math.abs(pos.x-goalPos.x), Math.abs(pos.y-goalPos.y));        
    }
    
}
