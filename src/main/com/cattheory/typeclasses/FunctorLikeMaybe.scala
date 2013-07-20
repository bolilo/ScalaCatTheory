package main.com.cattheory.typeclasses

import main.com.cattheory.typeclasses.types.Types.Functor
import main.com.cattheory._

trait FunctorLikeMaybe extends Functor[Maybe] {

  override def functorMap[A, B](f: A => B)(context: Maybe[A]): Maybe[B] = context match {
    case Just(k) => Just(f(k))
    case None => None
  }
}
