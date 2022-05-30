package com.example.mygame

import android.util.Log

class CreateExpression {

    val mathOps = arrayOf("+", "-", "*", "/")
    val evaluate = Solve()


    //Generate new expression
    public fun generate(): String {
        var exp = ""

        //Random number from 0 - 3 for exp type
        var expType = (0..3).random()

        //Expression type
        if (expType == 0) {
            exp = (0..20).random().toString()
        }
        if (expType == 1) {

            var n1 = (1..20).random()
            var n2 = (1..20).random()
            var operation = mathOps[(0..3).random()]

            var ans = evaluate.solve("$n1$operation$n2")

            exp = "$n1$operation$n2"

        } else if (expType == 2) {

            //Numbers
            var n1 = (1..20).random()
            var n2 = (1..20).random()
            var n3 = (1..20).random()

            //Operations
            var operation1 = mathOps[(0..3).random()]
            var operation2 = mathOps[(0..3).random()]

            //Equations
            var subExp1 = evaluate.solve("$n1$operation1$n2")
            var subExp2 = evaluate.solve("($n1$operation1$n2)$operation2$n3")


            //Evaluate sub expressions
            while (subExp1 % 1 != 0.0 || subExp1 > 100 || subExp2 % 1 != 0.0 || subExp2 > 100) {

                //Numbers
                n1 = (1..20).random()
                n2 = (1..20).random()
                n3 = (1..20).random()

                //Operation
                operation1 = mathOps[(0..3).random()]
                operation2 = mathOps[(0..3).random()]

                //Equation
                subExp1 = evaluate.solve("$n1$operation1$n2")
                subExp2 = evaluate.solve("($n1$operation1$n2)$operation2$n3")
            }

            exp = "($n1$operation1$n2)$operation2$n3"


        } else if (expType == 3) {

            //Numbers
            var n1 = (1..20).random()
            var n2 = (1..20).random()
            var n3 = (1..20).random()
            var n4 = (1..20).random()

            //Operations
            var operation1 = mathOps[(0..3).random()]
            var operation2 = mathOps[(0..3).random()]
            var operation3 = mathOps[(0..3).random()]

            //Equation
            var subExp1 = evaluate.solve("($n1$operation1$n2)")
            var subExp2 = evaluate.solve("(($n1$operation1$n2)$operation2$n3)")
            var subExp3 = evaluate.solve("(($n1$operation1$n2)$operation2$n3)$operation3$n4")

            //Evaluate sub expression
            while (subExp1 % 1 != 0.0 || subExp1 > 100 || subExp2 % 1 != 0.0 || subExp2 > 100 || subExp3 % 1 != 0.0 || subExp3 > 100) {

                //Numbers
                n1 = (1..20).random()
                n2 = (1..20).random()
                n3 = (1..20).random()
                n4 = (1..20).random()

                //Operations
                operation1 = mathOps[(0..3).random()]
                operation2 = mathOps[(0..3).random()]
                operation3 = mathOps[(0..3).random()]

                 //Equations
                subExp1 = evaluate.solve("($n1$operation1$n2)")
                subExp2 = evaluate.solve("(($n1$operation1$n2)$operation2$n3)")
                subExp3 = evaluate.solve("(($n1$operation1$n2)$operation2$n3)$operation3$n4")
            }

            exp = "(($n1$operation1$n2)$operation2$n3)$operation3$n4"
        }

        //Return generated expression
        return exp
    }
}