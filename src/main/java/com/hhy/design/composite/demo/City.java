package com.hhy.design.composite.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: 城
 * </p>
 *
 * @Author huhongyuan
 */
public class City implements PopulationNode{
    private final String name;
    private List<PopulationNode> districtList = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public void addDistrict(District district) {
        districtList.add(district);
    }

    @Override
    public int computePopulation() {
        return districtList.stream().mapToInt(PopulationNode::computePopulation).sum();
    }
}
