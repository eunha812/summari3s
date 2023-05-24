package com.notgenius.summari3s.model

enum class SummaryStrength(val power: Int) {
    LOW(20), MEDIUM(40), HIGH(60), MAX(100);

    companion object {
        fun Int.toSummaryStrength(): SummaryStrength {
            return when (this) {
                LOW.power -> LOW
                MEDIUM.power -> MEDIUM
                HIGH.power -> HIGH
                else -> MAX
            }
        }
    }
}