package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.Enquiry
import kotlinx.coroutines.flow.Flow

@Dao
interface EnquiryDao {
    @Query("SELECT * FROM enquiries ORDER BY timestamp DESC")
    fun getAllEnquiries(): Flow<List<Enquiry>>

    @Query("SELECT * FROM enquiries WHERE id = :id")
    suspend fun getEnquiryById(id: Long): Enquiry?

    @Query("SELECT * FROM enquiries WHERE requiredService = :serviceCategory ORDER BY timestamp DESC")
    fun getEnquiriesByService(serviceCategory: String): Flow<List<Enquiry>>

    @Query("SELECT * FROM enquiries WHERE status = :status ORDER BY timestamp DESC")
    fun getEnquiriesByStatus(status: String): Flow<List<Enquiry>>

    @Query("SELECT COUNT(*) FROM enquiries")
    fun getEnquiryCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnquiry(enquiry: Enquiry): Long

    @Update
    suspend fun updateEnquiry(enquiry: Enquiry)

    @Query("UPDATE enquiries SET status = :newStatus WHERE id = :id")
    suspend fun updateStatus(id: Long, newStatus: String)

    @Query("UPDATE enquiries SET followUpNotes = :notes WHERE id = :id")
    suspend fun updateNotes(id: Long, notes: String)

    @Delete
    suspend fun deleteEnquiry(enquiry: Enquiry)

    @Query("DELETE FROM enquiries WHERE id = :id")
    suspend fun deleteById(id: Long)
}
