package com.hhy.design.composite.demo;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) {
        // 创建黑龙江省
        Province hlj = new Province("黑龙江");

        // 创建哈尔滨市及其区
        City herbin = new City("哈尔滨");
        District nangang = new District("南岗区", 1390679);
        District daowai = new District("道外区", 811178);
        District pingfang = new District("平房区", 238945);
        herbin.addDistrict(nangang);
        herbin.addDistrict(daowai);
        herbin.addDistrict(pingfang);

        // 创建佳木斯市及其区
        City jms = new City("佳木斯");
        District xiangyang = new District("向阳区", 291234);
        District jiaoqu = new District("郊区", 263514);
        jms.addDistrict(xiangyang);
        jms.addDistrict(jiaoqu);

        // 将城市添加到黑龙江省
        hlj.addCity(herbin);
        hlj.addCity(jms);

        // 输出人口信息
        System.out.println("黑龙江人口: " + hlj.computePopulation());
        System.out.println("哈尔滨人口: " + herbin.computePopulation());
    }
}
