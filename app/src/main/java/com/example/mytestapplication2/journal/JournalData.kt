package com.example.mytestapplication2.journal

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
/*object JournalContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<JournalData> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, JournalData> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createJournalData(i))
        }
    }

    private fun addItem(item: JournalData) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createJournalData(position: Int): JournalData {
        return PlaceholderItem(position.toString(), "Item " + position, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }

    data class JournalData(val id: String, val journalImage: Int, val journalTitle: String) {
        override fun toString(): String = journalTitle
    }
}
*/
data class JournalData(val journalImage: Int, val journalTitle: String) {
    override fun toString(): String = journalTitle
}