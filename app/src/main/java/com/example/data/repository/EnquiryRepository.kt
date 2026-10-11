package com.example.data.repository

import com.example.data.local.EnquiryDao
import com.example.data.model.Enquiry
import kotlinx.coroutines.flow.Flow

class EnquiryRepository(private val enquiryDao: EnquiryDao) {
    val allEnquiries: Flow<List<Enquiry>> = enquiryDao.getAllEnquiries()
    val totalCount: Flow<Int> = enquiryDao.getEnquiryCount()

    suspend fun submitEnquiry(enquiry: Enquiry): Long {
        return enquiryDao.insertEnquiry(enquiry)
    }

    suspend fun updateStatus(id: Long, newStatus: String) {
        enquiryDao.updateStatus(id, newStatus)
    }

    suspend fun updateNotes(id: Long, notes: String) {
        enquiryDao.updateNotes(id, notes)
    }

    suspend fun deleteEnquiry(id: Long) {
        enquiryDao.deleteById(id)
    }
}
