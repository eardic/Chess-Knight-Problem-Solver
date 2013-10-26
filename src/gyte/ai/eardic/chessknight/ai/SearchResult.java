/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyte.ai.eardic.chessknight.ai;

/**
 *
 * @author Emre
 */
public class SearchResult
{

    private int expandedNode = 0, pathCost = 0;
    private double brachingFactor = 0;

    public SearchResult(int en, int pc, double bf)
    {
        this.expandedNode=en;
        this.pathCost=pc;
        this.brachingFactor=bf;
    }

    @Override
    public String toString()
    {
        return "Node Ex : " + expandedNode + "\n" + "Path C : " + pathCost + "\n"
                + "Braching F : " + brachingFactor;
    }

    public int getExpandedNode()
    {
        return expandedNode;
    }

    public void setExpandedNode(int expandedNode)
    {
        this.expandedNode = expandedNode;
    }

    public int getPathCost()
    {
        return pathCost;
    }

    public void setPathCost(int pathCost)
    {
        this.pathCost = pathCost;
    }

    public double getBrachingFactor()
    {
        return brachingFactor;
    }

    public void setBrachingFactor(double brachingFactor)
    {
        this.brachingFactor = brachingFactor;
    }

}
