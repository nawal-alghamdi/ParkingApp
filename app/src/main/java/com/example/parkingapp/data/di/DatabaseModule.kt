package com.example.parkingapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.parkingapp.data.source.database.ParkingDao
import com.example.parkingapp.data.source.database.ParkingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ParkingDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ParkingDatabase::class.java,
            "parking_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideParkingDao(db: ParkingDatabase): ParkingDao {
        return db.parkingDao()
    }

}