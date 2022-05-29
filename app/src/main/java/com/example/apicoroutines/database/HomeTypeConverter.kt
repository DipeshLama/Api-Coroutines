package com.example.apicoroutines.database

import androidx.room.TypeConverter
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.feature.shared.model.response.Details
import com.example.apicoroutines.feature.shared.model.response.SectionDetails
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeTypeConverter {
    @TypeConverter
    fun detailListToString(listValues: List<Details>?): String? {
        if (listValues == null) {
            return null
        }

        val gson = Gson()
        val type = object : TypeToken<List<Details>>() {

        }.type
        return gson.toJson(listValues, type)
    }

    @TypeConverter // note this annotation
    fun detailsFromStringToList(optionValuesString: String?): List<Details>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Details>>() {

        }.type
        return gson.fromJson<List<Details>>(optionValuesString, type)
    }

    @TypeConverter
    fun categoryListToString(listValues: List<Category>?): String? {
        if (listValues == null) {
            return null
        }

        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {

        }.type
        return gson.toJson(listValues, type)
    }

    @TypeConverter // note this annotation
    fun categoryFromStringToList(optionValuesString: String?): List<Category>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {

        }.type
        return gson.fromJson<List<Category>>(optionValuesString, type)
    }

    @TypeConverter
    fun sectionDetailObjectToString(listValues: SectionDetails?): String? {
        if (listValues == null) {
            return null
        }

        val gson = Gson()
        val type = object : TypeToken<SectionDetails>() {

        }.type
        return gson.toJson(listValues, type)
    }

    @TypeConverter // note this annotation
    fun sectionDetailsStringToObject(optionValuesString: String?): SectionDetails? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<SectionDetails>() {

        }.type
        return gson.fromJson<SectionDetails>(optionValuesString, type)
    }
}