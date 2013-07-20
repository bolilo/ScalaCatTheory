package main.com.cattheory.typeclasses

import main.com.cattheory.typeclasses.types.Types.Functor
import main.com.cattheory._

trait FunctorLikeMaybe extends Functor[Maybe] {

    def functorMap[A, B](f: A => B): Maybe[A] => Maybe[B] = {

      def fun(x: Maybe[A]): Maybe[B] = x match {
        case Just(k) => Just(f(k))
        case None => None
      }

      fun
    }
  }
