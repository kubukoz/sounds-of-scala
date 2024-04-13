package org.soundsofscala.models

enum Accidental:
  case Flat, Natural, Sharp

  def toPitchStep: Int = this match {
    case Flat => -1
    case Natural => 0
    case Sharp => 1
  }
