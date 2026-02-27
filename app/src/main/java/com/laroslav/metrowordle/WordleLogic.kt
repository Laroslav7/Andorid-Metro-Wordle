package com.laroslav.metrowordle

enum class TileStatus { EMPTY, ABSENT, PRESENT, CORRECT }

data class LetterResult(val char: Char, val status: TileStatus)

class WordleEngine(val targetWord: String) {
    private val maxAttempts = 6

    fun checkGuess(guess: String): List<TileStatus> {
        val result = MutableList(targetWord.length) { TileStatus.ABSENT }
        val targetChars = targetWord.toCharArray().toMutableList()

        // Первый проход: ищем совпадения (CORRECT)
        for (i in guess.indices) {
            if (guess[i] == targetWord[i]) {
                result[i] = TileStatus.CORRECT
                targetChars[i] = ' ' // Помечаем как использованную
            }
        }

        // Второй проход: ищем наличие (PRESENT)
        for (i in guess.indices) {
            if (result[i] != TileStatus.CORRECT && targetChars.contains(guess[i])) {
                result[i] = TileStatus.PRESENT
                targetChars[targetChars.indexOf(guess[i])] = ' '
            }
        }
        return result
    }
}