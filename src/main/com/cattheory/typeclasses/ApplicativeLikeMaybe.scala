package main.com.cattheory.typeclasses

import main.com.cattheory.typeclasses.types.Types.ApplicativeLike
import main.com.cattheory.Maybe
import main.com.cattheory._

trait ApplicativeLikeMaybe extends ApplicativeLike[Maybe] {

  override def pure[A](a: A): Maybe[A] = Just(a)

  override def <*>[A, B](fun: Maybe[A => B])(context: Maybe[A]): Maybe[B] = fun match {
    case Just(f) => functorMap(f)(context)
    case None => None
  }
}