//package com.iav.contestdataprovider.database
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.iav.contestdataprovider.data.model.RandomString
//
//@Dao
//interface RandomStringDao {
//    @Query("SELECT * FROM RandomString")
//    fun getAll(): List<RandomString>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(randomString: RandomString)
//
//    @Delete
//    suspend fun delete(randomString: RandomString)
//
//    @Query("DELETE FROM RandomString")
//    suspend fun deleteAll()
//}
