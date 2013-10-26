/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.ai;

import aima.core.agent.Action;
import aima.core.search.framework.StepCostFunction;

/**
 *
 * @author Emre
 */
public class CKPStepCostFunction implements StepCostFunction
{

    /**
     * Knight moves 3 square for every action
     *
     * @param o
     * @param action
     * @param o1
     * @return
     */
    public double c(Object o, Action action, Object o1)
    {
        return 1;
    }

}
