package com.example.davit_zakaryan.mvvmapp.data.db;

import android.content.Context;
import android.util.Log;

import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoMaster;
import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;
import com.example.davit_zakaryan.mvvmapp.di.DatabaseInfo;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

	@Inject
	public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
		super(context, name);
	}

	@Override
	public void onUpgrade(Database db, int oldVersion, int newVersion) {
		super.onUpgrade(db, oldVersion, newVersion);
		Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion); //TODO should use timber
		switch (oldVersion) {
			case 1:
			case 2:
				//db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
				// + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
		}
	}
}
