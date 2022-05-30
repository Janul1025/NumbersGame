package com.example.mygame

class Solve {

    fun solve(str: String): Double {
        return object : Any() {
            var position = -1
            var character = 0
            fun nextChar() {
                character = if (++position < str.length) str[position].code else -1
            }

            fun parseNext(nxtCharacter: Int): Boolean {
                while (character == ' '.code) nextChar()
                if (character == nxtCharacter) {
                    nextChar()
                    return true
                }
                return false
            }

            //If invalid characters available
            fun parse(): Double {
                nextChar( )
                val x = parseExpression()
                if (position < str.length) throw RuntimeException()
                return x
            }

            //Add, Sub
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (parseNext('+'.code)) x += parseTerm() // addition
                    else if (parseNext('-'.code)) x -= parseTerm() // subtraction
                    else return x
                }
            }

            //Multiplication, Division
            fun parseTerm(): Double {
                var x = factor()
                while (true) {
                    if (parseNext('*'.code)) x *= factor() // multiplication
                    else if (parseNext('/'.code)) x /= factor() // division
                    else return x
                }
            }

            fun factor(): Double {
                if (parseNext('+'.code)) return +factor() // unary plus
                if (parseNext('-'.code)) return -factor() // unary minus
                var x: Double
                val startPos = position
                if (parseNext('('.code)) { // parentheses
                    x = parseExpression()
                    if (!parseNext(')'.code)) throw RuntimeException()
                } else if (character >= '0'.code && character <= '9'.code || character == '.'.code) { // numbers
                    while (character >= '0'.code && character <= '9'.code || character == '.'.code) nextChar()
                    x = str.substring(startPos, position).toDouble()
                } else if (character >= 'a'.code && character <= 'z'.code) { // functions
                    while (character >= 'a'.code && character <= 'z'.code) nextChar()
                    val func = str.substring(startPos, position)
                    if (parseNext('('.code)) {
                        x = parseExpression()
                        if (!parseNext(')'.code)) throw RuntimeException()
                    } else {
                        x = factor()
                    }
                    x = parseExpression()
                } else {
                    throw RuntimeException()
                }

                return x
            }
        }.parse()
    }
}