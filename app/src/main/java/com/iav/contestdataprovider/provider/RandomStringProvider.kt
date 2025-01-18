package com.iav.contestdataprovider.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log

class RandomStringProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.iav.contestdataprovider"
        const val PATH_TEXT = "text"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH_TEXT")
        private const val TEXT_CODE = 1

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, PATH_TEXT, TEXT_CODE)
        }
    }

    override fun onCreate(): Boolean {
        return true // Content provider is initialized.
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

//        val maxLength = uri.getQueryParameter("limit")?.toIntOrNull() ?: return null
//
//        // Log the maxLength to verify
//        Log.d("ContentProvider", "maxLength: $maxLength")
//
//        // Generate a random string (or retrieve from your data source)
//        val randomString = gene(maxLength)
//
//        // Populate a cursor
//        val cursor = MatrixCursor(arrayOf("data"))
//        cursor.addRow(arrayOf(randomString))
//        return cursor

        return null // Replace with your logic to fetch random strings.
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            TEXT_CODE -> "vnd.android.cursor.dir/text"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}
