package py.com.aseguradoratajy.tajydemo.repositories;

import java.util.List;

import py.com.aseguradoratajy.tajydemo.entities.Marks;
import py.com.aseguradoratajy.tajydemo.entities.MarksDao;
import py.com.aseguradoratajy.tajydemo.utils.MainSession;

/**
 * Created by Manu0 on 10/16/2017.
 */

public class MarksRepositories {


    public static List<Marks> getAllMarks() {
        return getDao().queryBuilder().list();
    }

    public static MarksDao getDao() {
        return MainSession.getDaoSession().getMarksDao();
    }
}
