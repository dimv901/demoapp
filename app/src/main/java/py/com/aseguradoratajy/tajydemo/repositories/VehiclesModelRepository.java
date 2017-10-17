package py.com.aseguradoratajy.tajydemo.repositories;

import java.util.List;

import py.com.aseguradoratajy.tajydemo.entities.VehiclesModel;
import py.com.aseguradoratajy.tajydemo.entities.VehiclesModelDao;
import py.com.aseguradoratajy.tajydemo.utils.MainSession;

/**
 * Created by Manu0 on 10/16/2017.
 */

public class VehiclesModelRepository {



    public static List<VehiclesModel> getAllVehiclesModel(){
        return getDao().queryBuilder().list();
    }

    public static List<VehiclesModel> getModelsByMarks(int markId){
        return getDao().queryBuilder().where(VehiclesModelDao.Properties.BrandId.eq(markId)).list();
    }
    public static VehiclesModelDao getDao(){
        return MainSession.getDaoSession().getVehiclesModelDao();
    }
}
