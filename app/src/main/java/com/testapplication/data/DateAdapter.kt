package com.testapplication.data

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date

class DateTypeAdapter : TypeAdapter<Date>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) {
            out.nullValue()
        } else {
            out.value(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value.toInstant()))
        }
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Date? {
        if (`in`.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return null
        }

        val dateString = `in`.nextString()

        return Date.from(Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateString)))
    }
}