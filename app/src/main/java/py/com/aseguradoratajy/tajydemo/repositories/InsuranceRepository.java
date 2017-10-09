package py.com.aseguradoratajy.tajydemo.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import py.com.aseguradoratajy.tajydemo.entities.Insurance;
import py.com.aseguradoratajy.tajydemo.entities.InsuranceDao;
import py.com.aseguradoratajy.tajydemo.models.Branches;
import py.com.aseguradoratajy.tajydemo.utils.MainSession;

/**
 * Created by Manu0 on 10/8/2017.
 */

public class InsuranceRepository {

    public static InsuranceDao getDao() {
        return MainSession.getDaoSession().getInsuranceDao();
    }

    public static void clearAll() {
        getDao().deleteAll();
    }

    public static List<Insurance> getAllPolcyData() {
        return getDao().queryBuilder().orderDesc(InsuranceDao.Properties.Policy).list();
    }

    public static List<Insurance> groupData() {
        List<Insurance> insuranceList = getAllPolcyData();
        Set<Insurance> removeObject = new TreeSet<>(new Comparator<Insurance>() {

            @Override
            public int compare(Insurance o1, Insurance o2) {
                if (o1.getPolicy().equals(o2.getPolicy())) {
                    return 0;
                }
                return 1;
            }

        });
        removeObject.addAll(insuranceList);
        return new ArrayList<>(removeObject);

    }

    public static List<Insurance> getInsuranceByPolicyNumber(String mPoliciNumber) {
        return getDao().queryBuilder().where(InsuranceDao.Properties.Policy.eq(mPoliciNumber)).list();
    }
}
