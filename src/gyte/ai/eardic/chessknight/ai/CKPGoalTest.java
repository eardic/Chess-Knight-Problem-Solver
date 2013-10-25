/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.ai;

import aima.core.search.framework.GoalTest;
import gyte.ai.eardic.chessknight.board.ChessBoard;

/**
 *
 * @author Emre
 */
public class CKPGoalTest implements GoalTest
{

    @Override
    public boolean isGoalState(Object o)
    {
        if (o instanceof ChessBoard)
        {
            ChessBoard b = (ChessBoard) o;
            return b.getKnight().isReachedGoal();
        }        
        return false;
    }

}
