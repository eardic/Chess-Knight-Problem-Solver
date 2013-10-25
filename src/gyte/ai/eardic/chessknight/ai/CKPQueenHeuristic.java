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
 *
 * @author Emre
 */
public class CKPQueenHeuristic implements HeuristicFunction
{

    @Override
    public double h(Object o)
    {
        ChessBoard b = (ChessBoard) o;
        Point pos = b.getKnight().getPosition();
        Point goalPos = b.getKnight().getGoalPosition();
        
        int cross=0;
        cross = Math.min(Math.abs(pos.x - goalPos.x), Math.abs(pos.y - goalPos.y));
        Point crossPos = new Point(pos.x+cross,pos.y+cross);
        
        int straight=0;
        straight = Math.abs(crossPos.x - goalPos.x) + Math.abs(crossPos.y - goalPos.y);
        
        return straight + cross;
    }

}
