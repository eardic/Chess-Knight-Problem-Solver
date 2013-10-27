/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.ai;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;
import gyte.ai.eardic.chessknight.board.ChessBoard;
import gyte.ai.eardic.chessknight.knight.Knight;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emre
 */
public class CKPFunctionFactory
{
    private static ActionsFunction actionsFunction = null;
    private static ResultFunction resultFunction = null;

    public static ActionsFunction getActionsFunction()
    {
        if (null == actionsFunction)
        {
            actionsFunction = new CKPActionsFunction();
        }
        return actionsFunction;
    }

    public static ResultFunction getResultFunction()
    {
        if (null == resultFunction)
        {
            resultFunction = new CKPResultFunction();
        }
        return resultFunction;
    }

    private static class CKPActionsFunction implements ActionsFunction
    {

        @Override
        public Set<Action> actions(Object o)
        {
            ChessBoard b = (ChessBoard) o;
            Set<Action> actions = new LinkedHashSet<Action>();

            if (b.canMoveKnight(Knight.LeftUp))
            {
                actions.add(Knight.LeftUp);
            }
            if (b.canMoveKnight(Knight.LeftDown))
            {
                actions.add(Knight.LeftDown);
            }
            
            if (b.canMoveKnight(Knight.RightUp))
            {
                actions.add(Knight.RightUp);
            }
            if (b.canMoveKnight(Knight.RightDown))
            {
                actions.add(Knight.RightDown);
            }
            
            if (b.canMoveKnight(Knight.UpRight))
            {
                actions.add(Knight.UpRight);
            }
            if (b.canMoveKnight(Knight.UpLeft))
            {
                actions.add(Knight.UpLeft);
            }
            
            if (b.canMoveKnight(Knight.DownRight))
            {
                actions.add(Knight.DownRight);
            }
            if (b.canMoveKnight(Knight.DownLeft))
            {
                actions.add(Knight.DownLeft);
            }

            return actions;
        }
    }

    private static class CKPResultFunction implements ResultFunction
    {
        @Override
        public Object result(Object o, Action a)
        {
            ChessBoard b = (ChessBoard)o;            
            if(b.canMoveKnight(a))
            {
                ChessBoard newBoard = new ChessBoard(b);
                newBoard.moveKnight(a);                
                return newBoard;
            }            
            return o;
        }
    }
}
