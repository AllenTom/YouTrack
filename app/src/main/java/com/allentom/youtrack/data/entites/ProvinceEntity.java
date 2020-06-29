package com.allentom.youtrack.data.entites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProvinceEntity {
    private String name;
    private int confirm;
    private int deaths;
    private int active;
    private int recovered;

    public static List<ProvinceEntity> fromCityList(List<CityEntity> cityEntities) {
        HashMap<String, ProvinceEntity> provinceMapping = new HashMap<>(0);
        for (CityEntity cityEntity : cityEntities) {
            if ("".equals(cityEntity.getProvince())){
                continue;
            }
            String cityProvince = cityEntity.getProvince();
            if (provinceMapping.containsKey(cityProvince)) {
                ProvinceEntity provinceEntity = provinceMapping.get(cityProvince);
                provinceEntity.addCityNumber(cityEntity);
            } else {
                provinceMapping.put(cityEntity.getProvince(), new ProvinceEntity(cityEntity));
            }
        }
        return new ArrayList<>(provinceMapping.values());
    }

    public ProvinceEntity(CityEntity entity) {
        this.active = entity.getActive();
        this.confirm = entity.getConfirmed();
        this.deaths = entity.getDeaths();
        this.recovered = entity.getRecovered();
        this.name = entity.getProvince();
    }

    public void addCityNumber(CityEntity entity) {
        this.active += entity.getActive();
        this.confirm += entity.getConfirmed();
        this.deaths += entity.getDeaths();
        this.recovered += entity.getRecovered();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}
