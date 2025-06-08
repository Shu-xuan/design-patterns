package com.hhy.design.composite.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: 省[城]
 * </p>
 *
 * @Author huhongyuan
 */
public class Province implements PopulationNode{
    private final String name;
    private List<PopulationNode> cityList = new ArrayList<>();

    public Province(String name) {
        this.name = name;
    }

    public void addCity(City city) {
        cityList.add(city);
    }

    @Override
    public int computePopulation() {
        return cityList.stream().mapToInt(PopulationNode::computePopulation).sum();
    }
}
