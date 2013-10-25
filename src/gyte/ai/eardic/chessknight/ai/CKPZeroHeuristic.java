/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gyte.ai.eardic.chessknight.ai;

import aima.core.search.framework.HeuristicFunction;

/**
 *
 * @author Emre
 */
public class CKPZeroHeuristic  implements HeuristicFunction
{

    @Override
    public double h(Object o)
    {
        return 0;
    }
    
}
