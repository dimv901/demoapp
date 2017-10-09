package py.com.aseguradoratajy.tajydemo.repositories;

import java.util.List;

import py.com.aseguradoratajy.tajydemo.activities.MainActivity;
import py.com.aseguradoratajy.tajydemo.entities.Products;
import py.com.aseguradoratajy.tajydemo.entities.ProductsDao;
import py.com.aseguradoratajy.tajydemo.utils.MainSession;

/**
 * Created by Manu0 on 10/8/2017.
 */

public class ProductsRepository {

    public static ProductsDao getDao() {
        return MainSession.getDaoSession().getProductsDao();
    }

    public static List<Products> getAll() {
        return getDao().queryBuilder().list();
    }

}
