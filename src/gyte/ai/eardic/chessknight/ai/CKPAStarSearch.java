/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.ai;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import gyte.ai.eardic.chessknight.board.ChessBoard;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emre
 */
public class CKPAStarSearch
{
   
    public static SearchAgent solveByCastleMethod(ChessBoard chessBoard)
    {
        return solve(chessBoard, new CKPCastleHeuristic());
    }

    public static SearchAgent solveByQueenMethod(ChessBoard chessBoard)
    {
        return solve(chessBoard, new CKPQueenHeuristic());
    }

    public static SearchAgent solveByZeroMethod(ChessBoard chessBoard)
    {
        return solve(chessBoard, new CKPZeroHeuristic());
    }

    private static SearchAgent solve(ChessBoard chessBoard, HeuristicFunction heuristic)
    {
        try
        {
            Problem problem = new Problem(chessBoard, CKPFunctionFactory
                    .getActionsFunction(), CKPFunctionFactory
                    .getResultFunction(), new CKPGoalTest());
            Search search = new AStarSearch(new GraphSearch(), heuristic);
            SearchAgent agent = new SearchAgent(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            return agent;
        }
        catch (Exception ex)
        {
            Logger.getLogger(CKPAStarSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void printInstrumentation(Properties properties)
    {
        Iterator<Object> keys = properties.keySet().iterator();
        while (keys.hasNext())
        {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
    }

    private static void printActions(List<Action> actions)
    {
        for (int i = 0; i < actions.size(); i++)
        {
            String action = actions.get(i).toString();
            System.out.println(action);
        }
    }
}
