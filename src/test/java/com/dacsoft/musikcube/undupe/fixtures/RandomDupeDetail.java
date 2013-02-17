package com.dacsoft.musikcube.undupe.fixtures;

import com.dacsoft.musikcube.undupe.DupeDetail;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomDupeDetail extends DupeDetail implements Cloneable {

//    public RandomDupeDetail(ResultSet rs) throws SQLException {
//        super(rs);
//    }

    private static RandomDataGenerator gen = new RandomDataGenerator();

    public RandomDupeDetail() {
//        super();
        super(gen.nextInt(1,999999), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomAlphanumeric(10), gen.nextInt(1,999999), RandomStringUtils.randomAlphanumeric(30));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
