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
import org.ejml.data.Complex64F;
import org.ejml.data.DenseMatrix64F;
import org.ejml.factory.DecompositionFactory;
import org.ejml.factory.EigenDecomposition;

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

    public static SearchAgent solveByPawnMethod(ChessBoard chessBoard)
    {
        return solve(chessBoard, new CKPPawnHeuristic());
    }

    public static SearchAgent solveByKingMethod(ChessBoard chessBoard)
    {
        return solve(chessBoard, new CKPKingHeuristic());
    }

    private static SearchAgent solve(ChessBoard chessBoard, HeuristicFunction heuristic)
    {
        try
        {
            Problem problem = new Problem(chessBoard, CKPFunctionFactory
                    .getActionsFunction(), CKPFunctionFactory
                    .getResultFunction(), new CKPGoalTest(), new CKPStepCostFunction());
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

    public static SearchResult getResults(SearchAgent agent)
    {
        Properties properties = agent.getInstrumentation();
        int ne = 0, pc = 0;
        Iterator<Object> keys = properties.keySet().iterator();
        while (keys.hasNext())
        {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            if (key.equals("pathCost"))
            {
                pc = (int) (Double.parseDouble(property));
            }
            else if (key.equals("nodesExpanded"))
            {
                ne = Integer.parseInt(property);
            }
           
        }
        return new SearchResult(ne, pc, calculateEBF(ne,agent.getActions().size()));
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

    private static double calculateEBF(int N, int d)
    {
        Complex64F[] roots;
        double coeff[] = new double[d + 1];
        coeff[0] = -N;
        for (int i = 1; i < coeff.length; ++i)
        {
            coeff[i] = 1;
        }
        roots = findRoots(coeff);

        for (Complex64F num : roots)
        {
            if (num.real >= 0 && num.isReal())
            {
                return num.real;
            }
        }
        return 0;
    }

    private static Complex64F[] findRoots(double... coefficients)
    {
        int N = coefficients.length - 1;

        // Construct the companion matrix
        DenseMatrix64F c = new DenseMatrix64F(N, N);

        double a = coefficients[N];
        for (int i = 0; i < N; i++)
        {
            c.set(i, N - 1, -coefficients[i] / a);
        }
        for (int i = 1; i < N; i++)
        {
            c.set(i, i - 1, 1);
        }

        // use generalized eigenvalue decomposition to find the roots
        EigenDecomposition<DenseMatrix64F> evd = DecompositionFactory.eig(N, false);

        evd.decompose(c);

        Complex64F[] roots = new Complex64F[N];

        for (int i = 0; i < N; i++)
        {
            roots[i] = evd.getEigenvalue(i);
        }

        return roots;
    }
}
