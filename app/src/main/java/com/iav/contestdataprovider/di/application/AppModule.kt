package com.iav.contestdataprovider.di.application

import android.content.ContentResolver
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
        return context.contentResolver
    }

//    @Provides
//    fun provideDatabase(@ApplicationContext context: Context): RandomStringDatabase {
//        return Room.databaseBuilder(
//            context, RandomStringDatabase::class.java, "random_string_db"
//        ).build()
//    }
//
//    @Provides
//    fun provideDao(database: RandomStringDatabase): RandomStringDao {
//        return database.randomStringDao()
//    }
}
