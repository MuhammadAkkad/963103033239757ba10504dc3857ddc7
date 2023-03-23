package com.example.a963103033239757ba10504dc3857ddc7

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a963103033239757ba10504dc3857ddc7.data.db.AppDatabase
import com.example.a963103033239757ba10504dc3857ddc7.data.db.SatelliteDao
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {

    private lateinit var satelliteDao: SatelliteDao

    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        satelliteDao = db.satelliteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeSatelliteAndRead() {
        val satellite: SatelliteDetailModelDto? = null
        satellite?.apply {
            this.id = 1111
        }

        CoroutineScope(Dispatchers.Default).launch {
            satelliteDao.insertSatellite(satellite!!)
            val byId = satelliteDao.getSatelliteById(1111.toString())
            if (byId != null) {
                assertEquals(byId.id, satellite.id)
            }
        }
    }

}