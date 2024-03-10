package app.useCase

abstract class UseCase<I, O> {
  abstract fun handle(input: I): O?
}
