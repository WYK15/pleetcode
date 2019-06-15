package Scala

import scala.collection.mutable.{ListBuffer, Stack}
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext



object Hello {

  def minAddToMakeValid(S: String): Int = {
    if (S.length == 0 ) return 0
    var stack = Stack[Char]()
    for (s <- S) {
      if (s == '(') stack.push(s)
      else {
        if (!stack.isEmpty && stack.top == '(') stack.pop()
        else stack.push(s)
      }
    }
    return stack.size
  }

  def main(args: Array[String]): Unit = {
    print(buddyStrings("ab","ba"))
  }

}
